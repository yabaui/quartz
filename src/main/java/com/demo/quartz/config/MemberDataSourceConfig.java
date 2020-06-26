package com.demo.quartz.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConfigurationProperties(prefix = "datasource.member")
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactoryMember",
    transactionManagerRef = "transactionManagerMember",
    basePackages = {
        "com.demo.quartz.repository.member"
    }
)
public class MemberDataSourceConfig extends HikariConfig {

    @Bean
    public DataSource dataSourceMember() {
        return new LazyConnectionDataSourceProxy(
            new HikariDataSource(this)
        );
    }

    @Bean
    public EntityManagerFactory entityManagerFactoryMember() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(this.dataSourceMember());
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaPropertyMap(ImmutableMap.of(
            "hibernate.hdm2ddl.auto", "none",
            "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect",
            "hibernate.show_sql", "true"
        ));
        factory.setPackagesToScan("com.demo.quartz.model.member");
        factory.setPersistenceUnitName("member");
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManagerMember() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(this.entityManagerFactoryMember());

        return manager;
    }
}
