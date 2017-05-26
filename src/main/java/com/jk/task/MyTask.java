package com.jk.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by dell on 2017/5/26.
 */
@Configuration
@EnableScheduling
public class MyTask {

    /**
     * 我们希望这个方法每十秒钟打印一次
     *
     * cron:定时任务表达式
     *
     * 指定：秒、分钟、小时、日期、月份、星期、年（可选）
     * *：任意
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void test1(){
        System.out.println("MyTask.test1()"+new Date());
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void test2(){
        System.out.println("MyTask.test2()"+new Date());
    }

}
