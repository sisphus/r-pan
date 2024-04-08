package com.imooc.pan.cache.caffeine.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Caffeine本地缓存配置项实体
 * 该缓存策略推荐使用，效率最高
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.imooc.pan.cache.caffeine")
public class CaffeineCacheProperties {

    /**
     * 缓存初始容量
     */
    private Integer initCacheCapacity = 256;

    /**
     * 缓存最大容量，超过之后将会按照recently or very often（最近最少）策略做缓存剔除
     */
    private Long maxCacheCapacity = 10000L;

    /**
     * 是否允许空值Value
     */
    private Boolean allowNullValues = Boolean.TRUE;

}