package com.imooc.pan.schedule.test;

import com.imooc.pan.schedule.ScheduleManager;
import com.imooc.pan.schedule.test.config.ScheduleTestConfig;
import com.imooc.pan.schedule.test.task.SimpleScheduleTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScheduleTestConfig.class)
public class ScheduleTaskTest {
    @Autowired
    private ScheduleManager scheduleManager;

    @Autowired
    private SimpleScheduleTask testTask;

    @Test
    public void testRunScheduleTask() throws Exception {

        String cron = "0/5 * * * * ? ";

        String taskKey = scheduleManager.startTask(testTask, cron);

        Thread.sleep(10000);

        cron = "0/1 * * * * ? ";

        taskKey = scheduleManager.changeTask(taskKey, cron);

        Thread.sleep(10000);

        scheduleManager.stopTask(taskKey);
    }
}
