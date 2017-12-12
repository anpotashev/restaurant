package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Slf4j
@Configuration
@PropertySource("classpath:db/db.properties")
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = {"ru.net.arh.repository.datajpa"})
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
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"ru.net.arh.**.model"});
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties());
        return localContainerEntityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty(DIALECT, env.getProperty("DIALECT"));
        jpaProperties.setProperty(MAX_FETCH_DEPTH, env.getProperty("MAX_FETCH_DEPTH"));
        jpaProperties.setProperty(STATEMENT_FETCH_SIZE, env.getProperty("STATEMENT_FETCH_SIZE"));
        jpaProperties.setProperty(STATEMENT_BATCH_SIZE, env.getProperty("STATEMENT_BATCH_SIZE"));
        jpaProperties.setProperty(SHOW_SQL, env.getProperty("SHOW_SQL"));
        jpaProperties.setProperty(FORMAT_SQL, env.getProperty("FORMAT_SQL"));
        jpaProperties.setProperty(USE_SQL_COMMENTS, env.getProperty("USE_SQL_COMMENTS"));
        return jpaProperties;
    }

}
