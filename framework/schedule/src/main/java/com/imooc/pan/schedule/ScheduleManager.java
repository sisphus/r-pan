package com.imooc.pan.schedule;

import com.imooc.pan.core.exception.RPanFrameworkException;
import com.imooc.pan.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/*
 *定时任务调度器
 * 1. create and start a scheduled task
 * 2. stop a scheduled task
 * 3. update a scheduled task
 */
@Component
@Slf4j
public class ScheduleManager {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    /*
     * 内部准备执行的定时内部缓存
     */
    private Map<String, ScheduleTaskHolder> cache = new ConcurrentHashMap<>();

    /**
     * 启动定时任务
     *
     * @param scheduleTask
     * @param cron
     */
    public String startTask(ScheduleTask scheduleTask, String cron) {
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(scheduleTask, new CronTrigger(cron));
        String key = UUIDUtil.getUUID();
        ScheduleTaskHolder scheduleTaskHolder = new ScheduleTaskHolder(scheduleTask, scheduledFuture);
        cache.put(key, scheduleTaskHolder);
        log.info("{}启动成功！唯一标识为: {}", scheduleTask.getName(), key);
        return key;
    }

    /**
     * 停止定时任务
     *
     * @param key
     */
    public void stopTask(String key) {
        if (StringUtils.isBlank(key)) {
            return;
        }
        ScheduleTaskHolder cacheValue = cache.get(key);
        if (Objects.isNull(cacheValue)) {
            return;
        }
        ScheduledFuture scheduledFuture = cacheValue.getScheduledFuture();
        boolean cancel = scheduledFuture.cancel(true);

        if(cancel){
            log.info("{}停止成功！唯一标识为: {}", cacheValue.getScheduleTask().getName(), key);
        } else {
            log.info("{}停止失败！唯一标识为: {}", cacheValue.getScheduleTask().getName(), key);
        }

    }

    /**
     * 变更任务时间
     *
     * @param key
     * @param cron
     * @return
     */
    public String changeTask(String key, String cron) {
        if (StringUtils.isAnyBlank(key,cron)) {
            throw new RPanFrameworkException("定时任务的唯一标识及执行表达式不能为空");
        }
        ScheduleTaskHolder cacheValue = cache.get(key);
        if (Objects.isNull(cacheValue)) {
            throw new RPanFrameworkException(key + "标识不存在");
        }

        stopTask(key);
        return startTask(cacheValue.getScheduleTask(), cron);
    }



}
