package com.imooc.pan.cache.caffeine.test;

import cn.hutool.core.lang.Assert;
import com.imooc.pan.cache.caffeine.test.config.CaffeineCacheConfig;
import com.imooc.pan.cache.caffeine.test.instance.CacheAnnotationTester;
import com.imooc.pan.cache.core.constants.CacheConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * caffeine缓存单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CaffeineCacheConfig.class)
public class CaffeineCacheSpringTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheAnnotationTester cacheAnnotationTester;

    /**
     * 测试缓存是否正常
     */
    @Test
    public void simpleCacheManagerTest() {
        Cache cache = cacheManager.getCache(CacheConstants.R_PAN_CACHE);
        Assert.notNull(cache);
        cache.put("key", "value");
        String value = cache.get("key", String.class);
        Assert.isTrue("value".equals(value));
    }

    /**
     * 测试注解是否正常
     */
    @Test
    public void simpleCacheAnnotationTest() {
        for (int i = 0; i < 2; i++) {
            cacheAnnotationTester.testCacheable("imooc");
        }
    }

}
