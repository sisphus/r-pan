package com.imooc.pan.schedule.test.task;

import com.imooc.pan.schedule.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleScheduleTask implements ScheduleTask {

    /**
     * 获取执行器名称
     *
     * @return
     */
    @Override
    public String getName() {
        return "测试定时任务";
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        log.info("开始执行测试定时任务...");
    }

}
