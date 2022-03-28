package cn.hrsweb.elasticsearch;

import cn.hrsweb.elasticsearch.pojo.Item;
import cn.hrsweb.elasticsearch.repository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SourceFilter;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate restTemplate;  // ElasticsearchTemplate 自4.0被弃用改为ElasticsearchResTemplate

    @Autowired
    private ItemRepository itemRepository;


    /**
     * 创建索引（4.0后自动创建索引）
     */
    @Test
    public void createIndex(){
        // 创建索引，会根据Item类的@Document注解信息来创建
        restTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        restTemplate.putMapping(Item.class);
    }

    /**
     * 新增文档
     */
    @Test
    public void index(){
        Item item = new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg");
        this.itemRepository.save(item);
    }

    /**
     * 批量新增
     */
    @Test
    public void indexList(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(4L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(5L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(6L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        this.itemRepository.saveAll(list);
    }

    // 基本查询
    @Test
    public void testQuery(){
        /* 根据id查询
        Optional<Item> optional = this.itemRepository.findById(1L);
        System.out.println(optional.get());*/

        // 查询所有，并根据价格降序排序
        Iterable<Item> items = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        items.forEach(item -> System.out.println(item));
    }

    // 自定义查询
    @Test
    public void testFind(){
        // 查询标题含手机的数据
        //List<Item> items = this.itemRepository.findByTitle("手机");
        // 根据价格范围查询
        List<Item> items = this.itemRepository.findByPriceBetween(2499d, 3699d);
        items.forEach(System.out::println);
    }

    @Test
    public void testMatch(){
        // 词条查询
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("title", "手机");
        // 执行查询
        Iterable<Item> items = this.itemRepository.search(matchQuery);
        items.forEach(System.out::println);
    }
    // 分页查询
    @Test
    public void testNative(){
        // 构造一个自定义查询构造器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 给构造器添加查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("category","手机"));
        // 添加分页条件，页码从0开始
        int page = 1;
        int size = 3;
        queryBuilder.withPageable(PageRequest.of(page,size));
        // 添加排序条件
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 执行查询，返回分页结果集
        Page<Item> itemPage = this.itemRepository.search(queryBuilder.build());
        // 总页数
        System.out.println(itemPage.getTotalPages());
        // 总条数
        System.out.println(itemPage.getTotalElements());
        // 当前页的数据
        itemPage.forEach(System.out::println);
    }

    /**
     * 聚合查询
     */
    @Test
    public void testAgg(){
        // 构建自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加聚合查询条件，AggregationBuilders工具类构建聚合
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"));
        // 添加查询结果集过滤，不包含任何字段，也就是不需要普通结果集
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        // 执行查询，获取结果集，需要强转
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 根据聚合名称获取想要的聚合对象。强转成子类：LongTerms StringTerms DoubleTerms
        StringTerms brandAgg = (StringTerms) itemPage.getAggregation("brands");
        // 获取集合中的桶，并遍历打印
        brandAgg.getBuckets().forEach(bucket -> {
            // 获取聚合中的key
            System.out.println(bucket.getKeyAsString());
            // 获取聚合中的记录数
            System.out.println(bucket.getDocCount());
        });
    }

    /**
     * 嵌套聚合，查询平均值
     */
    @Test
    public void testSubAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加聚合查询，AggregationBuilders工具类构建聚合，
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand")
                    .subAggregation(AggregationBuilders.avg("price_avg").field("price")));
        // 添加结果过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        AggregatedPage<Item> itemPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 获取聚合对象
        StringTerms brandAgg = (StringTerms) itemPage.getAggregation("brands");
        brandAgg.getBuckets().forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            // 解析子聚合，子聚合结果集转成map结构，key-聚合名称，value-聚合对象 Map<price_avg, aggregation>
            Map<String, Aggregation> asMap = bucket.getAggregations().asMap();
            InternalAvg price_avg = (InternalAvg) asMap.get("price_avg");
            System.out.println(price_avg.getValue());
    });

    }

}
