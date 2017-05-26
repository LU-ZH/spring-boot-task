package com.jk.task;

/**
 * Created by dell on 2017/5/26.
 */

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 思路：
 * 1.新建一个task.class
 * 2.在class上添加注解@EnableScheduling
 * 3.让我们的class实现接口SchedudingConfigurer
 * 4.实现SchedudingConfigurer中的方法
 */
@RestController
@EnableScheduling
public class TaskCromChange implements SchedulingConfigurer {

    private String expression = "0/5 * * * * *";//5秒执行一次

    //动态修改为每10秒执行一次
    @RequestMapping("changeExpression")
    public String changeExpression(){
        expression = "0/10 * * * * *";
        return "changeExpression";
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("configureTasks.run " + new Date());
            }
        };
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(expression);
                return cronTrigger.nextExecutionTime(triggerContext);
            }
        };
        scheduledTaskRegistrar.addTriggerTask(task,trigger);

    }
}
