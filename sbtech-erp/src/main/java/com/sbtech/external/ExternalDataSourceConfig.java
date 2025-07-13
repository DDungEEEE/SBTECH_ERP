package com.sbtech.external;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.sbtech.external.product.adapter.out.persistence.repository",
        entityManagerFactoryRef = "externalEntityManagerFactory",
        transactionManagerRef = "externalTransactionManager"
)
public class ExternalDataSourceConfig {
    @Value("${spring.datasource.external.url}")
    private String url;

    @Value("${spring.datasource.external.username}")
    private String username;

    @Value("${spring.datasource.external.password}")
    private String password;

    @Value("${spring.datasource.external.driver-class-name}")
    private String driver;


    @Bean(name = "externalDataSource")
    public DataSource externalDataSource() {
        System.out.println(url);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }


    @Bean(name = "externalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean externalEntityManagerFactory(
            @Qualifier("externalDataSource") DataSource externalDataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(externalDataSource);
        emf.setPackagesToScan("com.sbtech.external.product.adapter.out.persistence.entity");
        emf.setPersistenceUnitName("external");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("show-sql", "true");
        emf.setJpaPropertyMap(properties);

        return emf;
    }



    @Bean
    public PlatformTransactionManager externalTransactionManager(
            @Qualifier("externalEntityManagerFactory")
            LocalContainerEntityManagerFactoryBean factoryBean
    ) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
