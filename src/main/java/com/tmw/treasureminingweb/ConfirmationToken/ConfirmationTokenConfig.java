package com.tmw.treasureminingweb.ConfirmationToken;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 *
 */
//@Configuration
//@PropertySource({ "classpath:application.yml" })
//@EnableJpaRepositories(
//        basePackages = "com.tmw.treasureminingweb.ConfirmationToken",
//        entityManagerFactoryRef = "ConfirmationTokenEntityManager",
//        transactionManagerRef = "ConfirmationTokenTransactionManager"
//)
@Configuration(proxyBeanMethods = false)
public class ConfirmationTokenConfig {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean ConfirmationTokenEntityManager() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(ConfirmationTokenDataSource());
//        em.setPackagesToScan(
//                "com.tmw.treasureminingweb.ConfirmationToken");
//
//        HibernateJpaVendorAdapter vendorAdapter
//                = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto",
//                env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect",
//                env.getProperty("hibernate.dialect"));
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix="app.datasource.confirmation-token")
//    public DataSource ConfirmationTokenDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager ConfirmationTokenTransactionManager() {
//
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                ConfirmationTokenEntityManager().getObject());
//        return transactionManager;
//    }
    @Bean
    @ConfigurationProperties("app.datasource.confirmation-token")
    public DataSourceProperties confirmationTokenDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.confirmation-token.configuration")
    public HikariDataSource confirmationTokenDataSource(DataSourceProperties confirmationTokenDataSourceProperties) {
        return confirmationTokenDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
