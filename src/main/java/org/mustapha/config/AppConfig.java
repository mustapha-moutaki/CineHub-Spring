//package org.mustapha.config;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import jakarta.persistence.EntityManagerFactory;
//import java.sql.DriverManager;
//import java.util.Properties;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;//addded
//
//@Configuration
//@ComponentScan(basePackages = {"org.mustapha", "org.mustapha.repository","org.mustapha.mapper", "org.mustapha.service"})
//@EnableTransactionManagement
////@EnableJpaRepositories(basePackages = "org.mustapha.repository")//added
//
//public class AppConfig {
//
//    public AppConfig() {
//        System.out.println("===  AppConfig Constructor Called ===");
//        System.out.println("===  ComponentScan: org.mustapha, org.mustapha.repository ===");
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        System.out.println("===  Creating DataSource Bean ===");
//        System.out.println("===  Database URL: jdbc:mysql://localhost:3306/cinehub_db ===");
//
//        BasicDataSource ds = new BasicDataSource();
//
//        // Database connection settings
//        ds.setUrl("jdbc:mysql://localhost:3306/cinehub_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
//        ds.setUsername("phpmyadmin");
//        ds.setPassword("Php@12345");
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//        // Connection pool settings
//        ds.setInitialSize(2);
//        ds.setMaxTotal(5);
//        ds.setMaxIdle(3);
//        ds.setMinIdle(1);
//        ds.setMaxWaitMillis(10000);
//
//        System.out.println("===  DataSource Configuration Complete ===");
//
//        // Test connection
//        testDatabaseConnection();
//
//        return ds;
//    }
//
//    private void testDatabaseConnection() {
//        System.out.println("=== 🔍 Testing Database Connection ===");
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("===  MySQL Driver Loaded Successfully ===");
//        } catch (ClassNotFoundException e) {
//            System.out.println("===  MySQL Driver NOT FOUND: " + e.getMessage() + " ===");
//            return;
//        }
//
//        try {
//            String baseUrl = "jdbc:mysql://localhost:3306/cinehub_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//            var conn = DriverManager.getConnection(baseUrl, "phpmyadmin", "Php@12345");
//            System.out.println("===  Connection to MySQL server SUCCESSFUL ===");
//
//            // Check if database exists
//            var stmt = conn.createStatement();
//            var result = stmt.executeQuery("SHOW DATABASES LIKE 'cinehub_db'");
//            if (result.next()) {
//                System.out.println("===  Database 'cinehub_db' EXISTS ===");
//            } else {
//                System.out.println("===  Database 'cinehub_db' DOES NOT EXIST ===");
//                System.out.println("===  Please run: CREATE DATABASE cinehub_db; ===");
//            }
//
//            // Check tables in database
//            var tablesStmt = conn.createStatement();
//            var tablesResult = tablesStmt.executeQuery("SHOW TABLES FROM cinehub_db");
//            boolean hasTables = false;
//            while (tablesResult.next()) {
//                if (!hasTables) {
//                    System.out.println("===  Tables in cinehub_db: ===");
//                    hasTables = true;
//                }
//                System.out.println("===   - " + tablesResult.getString(1) + " ===");
//            }
//            if (!hasTables) {
//                System.out.println("=== ℹNo tables found in cinehub_db (Hibernate will create them) ===");
//            }
//
//            conn.close();
//            System.out.println("===  Database Test Completed Successfully ===");
//
//        } catch (Exception e) {
//            System.out.println("=== Connection to MySQL server FAILED: " + e.getMessage() + " ===");
//            System.out.println("=== ==Troubleshooting Steps: ===");
//            System.out.println("===   1. Check MySQL is running on port 3306 ===");
//            System.out.println("===   2. Verify user 'phpmyadmin' with password 'Php@12345' exists ===");
//            System.out.println("===   3. Ensure database 'cinehub_db' exists ===");
//            e.printStackTrace();
//        }
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//        System.out.println("===  Creating EntityManagerFactory Bean ===");
//        System.out.println("===  Package Scan: org.mustapha.model ===");
//
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource);
//        emf.setPackagesToScan("org.mustapha.model");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        jpaProperties.put("hibernate.show_sql", "true");
//        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
//        jpaProperties.put("hibernate.format_sql", "true");
//
//        System.out.println("=== ⚙ Hibernate Properties: ===");
//        System.out.println("===   - hibernate.dialect: MySQLDialect ===");
//        System.out.println("===   - hibernate.show_sql: true ===");
//        System.out.println("===   - hibernate.hbm2ddl.auto: update ===");
//        System.out.println("===   - hibernate.format_sql: true ===");
//
//        emf.setJpaProperties(jpaProperties);
//
//        System.out.println("=== EntityManagerFactory Created Successfully ===");
//        return emf;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        System.out.println("=== Creating TransactionManager Bean ===");
//        System.out.println("=== EntityManagerFactory: " + (emf != null ? "VALID" : "NULL") + " ===");
//
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//
//        System.out.println("===  TransactionManager Created Successfully ===");
//        return transactionManager;
//    }
//}

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