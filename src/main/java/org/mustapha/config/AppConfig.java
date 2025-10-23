package org.mustapha.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"org.mustapha", "org.mustapha.repository"})
@EnableTransactionManagement

public class AppConfig {

    public AppConfig() {
        System.out.println("=== AppConfig Constructor Called ===");
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("=== Creating DataSource Bean ===");

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/cinehub_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        ds.setUsername("phpmyadmin");  //local mysql
        ds.setPassword("Php@12345");  //
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");



        // Connection pool settings
        ds.setInitialSize(2);
        ds.setMaxTotal(5);
        ds.setMaxIdle(3);
        ds.setMinIdle(1);
        ds.setMaxWaitMillis(10000);

        // Test connection
        testDatabaseConnection();

        return ds;
    }

    private void testDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("=== Testing Database Connection ===");

        try {
            String baseUrl = "jdbc:mysql://localhost:3306/cinehub_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            var conn = DriverManager.getConnection(baseUrl, "phpmyadmin", "Php@12345");
            System.out.println("✓ Connection to MySQL server SUCCESSFUL");

            // Check if database exists
            var stmt = conn.createStatement();
            var result = stmt.executeQuery("SHOW DATABASES LIKE 'cinehub_db'");
            if (result.next()) {
                System.out.println("✓ Database 'cinehub' EXISTS");
            } else {
                System.out.println("✗ Database 'cinehub' DOES NOT EXIST");
                System.out.println("Please run: CREATE DATABASE cinehub;");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("✗ Connection to MySQL server FAILED: " + e.getMessage());
            System.out.println("Please check:");
            System.out.println("1. MySQL is running on port 3308");
            System.out.println("2. User 'user' with password 'usr' exists");
            System.out.println("3. Database 'cinehub_db' exists");
            e.printStackTrace();
        }
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
//
//package org.mustapha.config;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import jakarta.persistence.EntityManagerFactory;
//import java.util.Properties;
//
//@Configuration
//@ComponentScan(basePackages = {
//        "org.mustapha.service",
//        "org.mustapha.service.Impl",
//        "org.mustapha.controller",
//        "org.mustapha.mapper"
//})
//@EnableJpaRepositories(basePackages = "org.mustapha.repository")
//@EnableTransactionManagement
//public class AppConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/cinehub?useSSL=false&serverTimezone=UTC");
//        ds.setUsername("user");
//        ds.setPassword("usr");
//        ds.setInitialSize(2);
//        ds.setMaxTotal(5);
//        return ds;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setPackagesToScan("org.mustapha.model");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        jpaProperties.put("hibernate.show_sql", true);
//        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
//        jpaProperties.put("hibernate.format_sql", true);
//
//        emf.setJpaProperties(jpaProperties);
//        return emf;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }
//}