package ru.net.arh.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.cache.CacheManager;
import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
@EnableCaching
@PropertySource("classpath:cache/cache.properties")
public class SpringCacheConfig {
    @Autowired
    Environment env;

    @Bean
    public JCacheCacheManager cacheManager(CacheManager manager) {
        return new JCacheCacheManager(manager);
    }

    @Bean
    public JCacheManagerFactoryBean jCacheCacheManagerFactoryBean() throws URISyntaxException, IOException {
        JCacheManagerFactoryBean factoryBean = new JCacheManagerFactoryBean();

        factoryBean.setBeanClassLoader(getClass().getClassLoader());
        factoryBean.setCacheManagerUri(new ClassPathResource(env.getProperty("ehcache.uri")).getURI());
        return factoryBean;
    }
}
