package com.landingis.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {
//
//    // ✅ Define a DataSource Bean for both JPA and JdbcTemplate
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return org.springframework.boot.jdbc.DataSourceBuilder.create().build();
//    }
//
//    // ✅ Define EntityManagerFactory for Hibernate
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.landingis.api.model"); // Set your entity package
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        // Manually define Hibernate properties (since HibernateProperties doesn't exist in Spring Boot 2.3)
//        Properties properties = new Properties();
//        properties.put("hibernate.hbm2ddl.auto", "update"); // Change as needed
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//
//        em.setJpaProperties(properties);
//        return em;
//    }
//
//    // ✅ Define Transaction Manager for JPA
//    @Bean
//    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    // ✅ Define Transaction Manager for JdbcTemplate
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
