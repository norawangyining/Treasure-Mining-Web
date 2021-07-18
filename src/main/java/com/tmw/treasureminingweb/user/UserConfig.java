package com.tmw.treasureminingweb.user;

import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationToken;
import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenRepository;
import com.tmw.treasureminingweb.ConfirmationToken.ConfirmationTokenService;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
//        basePackages = "com.tmw.treasureminingweb.user",
//        entityManagerFactoryRef = "userEntityManager",
//        transactionManagerRef = "userTransactionManager"
//)
@Configuration(proxyBeanMethods = false)
public class UserConfig {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean userEntityManager() {
//        LocalContainerEntityManagerFactoryBean em
//                = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(userDataSource());
//        em.setPackagesToScan(
//                "com.tmw.treasureminingweb.user");
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
//
//    @Primary
//    @Bean
//    @ConfigurationProperties(prefix="app.datasource.users")
//    public DataSource userDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager userTransactionManager() {
//
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                userEntityManager().getObject());
//        return transactionManager;
//    }
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users.configuration")
    public HikariDataSource userDataSource(DataSourceProperties userDataSourceProperties) {
        return userDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ConfirmationTokenService confirmationTokenService){
        return args -> {
            User user = new User();
            user.setEmail("mao");
            user.setPassword("gou");
            user.setUserRole(UserRole.ADMIN);
            user.setEnabled(true);
            user.setLocked(false);
            userRepository.save(user);
            confirmationTokenService.saveConfirmationTokenByUser(user);
        };
    };
}
