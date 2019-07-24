package com.zcr.CountDownLatchAndCyclicBarrier;

import java.util.concurrent.CountDownLatch;

/**
 * @author zcr
 * @date 2019/7/20-23:00
 */
public class TestCountDownLatchEnum {

    public static void main(String[] args) throws Exception{

        //NoCountDownLatch();

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国，被灭");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();

        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t秦国一统华夏");


        //测试枚举
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
        System.out.println(CountryEnum.values()[1].getRetCode());
        System.out.println(CountryEnum.values()[1].getRetMessage());


    }
}
