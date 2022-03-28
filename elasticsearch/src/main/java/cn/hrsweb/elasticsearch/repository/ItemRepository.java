package cn.hrsweb.elasticsearch.repository;

import cn.hrsweb.elasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    List<Item> findByTitle(String title);

    List<Item> findByPriceBetween(double v, double v1);
}
