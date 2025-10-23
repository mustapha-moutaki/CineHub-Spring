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
@ComponentScan(basePackages = {"org.mustapha.service", "org.mustapha.repository"})
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
        ds.setUrl("jdbc:mysql://localhost:3308/cinehub_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        ds.setUsername("user");
        ds.setPassword("usr");

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
        System.out.println("=== Testing Database Connection ===");

        try {
            String baseUrl = "jdbc:mysql://127.0.0.1:3308/?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            var conn = DriverManager.getConnection(baseUrl, "user", "usr");
            System.out.println("✓ Connection to MySQL server SUCCESSFUL");

            // Check if database exists
            var stmt = conn.createStatement();
            var result = stmt.executeQuery("SHOW DATABASES LIKE 'cinehub'");
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
            System.out.println("3. Database 'cinehub' exists");
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