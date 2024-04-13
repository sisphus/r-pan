package com.imooc.pan.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.ScheduledFuture;

/*
 *定时任务和定时结果的缓存对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleTaskHolder implements Serializable {

    private static final long serialVersionUID = 1444488140009722221L;
    /*
     * 执行任务实体
     */
    private ScheduleTask scheduleTask;
    /*
    * 执行任务结果实体
     */
    private ScheduledFuture scheduledFuture;

}
