package com.zcr.CountDownLatchAndCyclicBarrier;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.CountDownLatch;

/**
 * @author zcr
 * @date 2019/7/20-22:53
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws Exception{

        //NoCountDownLatch();

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长最后关门");


    }

    private static void NoCountDownLatch() {
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习离开教室");
            },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName()+"\t班长最后关门");
    }
}
