package org.mustapha.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"org.mustapha.service", "org.mustapha.repository", "org.mustapha.mapper"})
@EnableJpaRepositories(basePackages = "org.mustapha.repository")
@EnableTransactionManagement
public class AppConfig {

    public AppConfig() {
        System.out.println("=== AppConfig Constructor Called ===");
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("=== Creating DataSource Bean ===");

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/cinehub_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC");
        ds.setUsername("phpmyadmin");
        ds.setPassword("Php@12345");


        // Connection pool settings
        ds.setInitialSize(2);
        ds.setMaxTotal(5);
        ds.setMaxIdle(3);
        ds.setMinIdle(1);
        ds.setMaxWaitMillis(10000);
        return ds;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        System.out.println("=== Creating EntityManagerFactory Bean ===");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.mustapha.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        // Use the recommended dialect instead of deprecated MySQL8Dialect
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.format_sql", "true");

        emf.setJpaProperties(jpaProperties);
        System.out.println("=== EntityManagerFactory Created Successfully ===");
        return emf;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println("=== Creating TransactionManager Bean ===");
        return new JpaTransactionManager(emf);
    }
}