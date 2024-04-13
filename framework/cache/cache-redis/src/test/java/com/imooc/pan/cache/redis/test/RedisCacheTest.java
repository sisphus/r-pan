package com.imooc.pan.cache.redis.test;

import cn.hutool.core.lang.Assert;
import com.imooc.pan.cache.core.constants.CacheConstants;
import com.imooc.pan.cache.redis.test.instance.CacheAnnotationTester;
import com.imooc.pan.core.constants.RPanConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * redis缓存单元测试
 */
@SpringBootTest(classes = RedisCacheTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH + ".cache.redis.test")
public class RedisCacheTest {

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