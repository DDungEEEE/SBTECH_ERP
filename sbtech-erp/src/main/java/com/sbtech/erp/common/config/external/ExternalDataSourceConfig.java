package com.sbtech.erp.common.config.external;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Qualifier;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.sbtech.erp.product.adapter.out.persistence.repository",
        entityManagerFactoryRef = "externalEntityManagerFactory",
        transactionManagerRef = "externalTransactionManager"
)
public class ExternalDataSourceConfig {

    @Bean(name = "externalDataSource")
    @ConfigurationProperties(prefix = "external-datasource")
    public DataSource externalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "externalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean externalEntityManagerFactory(
            @Qualifier("externalDataSource") DataSource externalDataSource
    ) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(externalDataSource);
        emf.setPackagesToScan("com.example.external.entity");
        emf.setPersistenceUnitName("external");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
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
