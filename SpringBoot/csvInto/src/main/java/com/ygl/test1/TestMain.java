package com.ygl.test1;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ygl
 * @description
 * @date 2020/11/30 19:41
 */
public class TestMain {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor schedulePool = new ScheduledThreadPoolExecutor(1);
        //作为一个周期任务提交,period 为1000ms,任务执行时间为2000ms 5000代表首次执行任务时的延迟  1000代表每次执行任务的间隔
        schedulePool.scheduleAtFixedRate(new MyRunnable(), 5000, 1000, TimeUnit.MILLISECONDS);
    }


}
