package cn.hrsweb.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration  //声明一个类为java配置类，相当xml配置
//@PropertySource("classpath:jdbc.properties")   //读取外部资源文件
@EnableConfigurationProperties(jdbcProperties.class)
public class JdbcConfiguration {
    //@Autowired
    //private jdbcProperties jdbcProperties;
    private jdbcProperties prop;
    public JdbcConfiguration(jdbcProperties prop){
        this.prop = prop;
    }


    @Bean
    @ConfigurationProperties(prefix = "jdbc") //声明要注入的属性前缀，springboot会自动把相关属性通过set方法注入到DataSource类中
    public DataSource dateSource(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

}
