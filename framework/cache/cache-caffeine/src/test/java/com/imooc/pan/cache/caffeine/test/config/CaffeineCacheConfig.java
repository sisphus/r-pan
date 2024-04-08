package com.imooc.pan.cache.caffeine.test.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.imooc.pan.cache.core.constants.CacheConstants;
import com.imooc.pan.core.constants.RPanConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Caffeine缓存管理器配置
 * 该缓存管理器不支持事务
 * 该缓存策略推荐使用，效率最高
 */
@SpringBootConfiguration
@EnableCaching
@ComponentScan(value = RPanConstants.BASE_COMPONENT_SCAN_PATH + ".cache.caffeine.test")
public class CaffeineCacheConfig {

    @Autowired
    private CaffeineCacheProperties caffeineCacheProperties;

    @Bean
    public CacheManager caffeineManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheConstants.R_PAN_CACHE);
        cacheManager.setAllowNullValues(caffeineCacheProperties.getAllowNullValues());
        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
                .initialCapacity(caffeineCacheProperties.getInitCacheCapacity())
                .maximumSize(caffeineCacheProperties.getMaxCacheCapacity());
        cacheManager.setCaffeine(caffeineBuilder);
        return cacheManager;
    }

}