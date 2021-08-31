package cn.hrsweb.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class jdbcProperties {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.url}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String userName;
    @Value("${jdbc.url}")
    private String password;

    @Bean
    public DataSource dateSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

}
