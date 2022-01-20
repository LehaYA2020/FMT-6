package ru.fmt.university.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Lazy
@Configuration
@ComponentScan("ru.fmt.university.dao")
@PropertySource("classpath:database.properties")
public class TestAppConfig {
    @Value("${url}")
    private String url;
    @Value("${user}")
    String user;
    @Value("${password}")
    String password;

    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
