package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Properties;

@Slf4j
@Configuration
@PropertySource("classpath:db/db.properties")
@EnableTransactionManagement
public class SpringDBConfig {
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[] {"ru.net.arh.**.model"});

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(AvailableSettings.DIALECT,"org.hibernate.dialect.PostgreSQL9Dialect");
        jpaProperties.setProperty(AvailableSettings.MAX_FETCH_DEPTH, "3");
        jpaProperties.setProperty(AvailableSettings.STATEMENT_FETCH_SIZE, "50");
        jpaProperties.setProperty(AvailableSettings.STATEMENT_BATCH_SIZE, "10");
        jpaProperties.setProperty(AvailableSettings.SHOW_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.FORMAT_SQL, "true");
        jpaProperties.setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
        jpaProperties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }


}
