package com.ygl.test1;

/**
 * @author ygl
 * @description
 * @date 2020/11/30 19:42
 */
public class MyRunnable implements Runnable{
    int perio = 1;
    @Override
    public void run() {
        try {
            System.out.println("---------------第 " + perio + " 周期-------------");
            System.out.println("begin = " + System.currentTimeMillis() / 1000);//秒
            //任务执行时间
            Thread.sleep(2000);
            System.out.println("end =   " + System.currentTimeMillis() / 1000);
            perio++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
