package com.zcr.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zcr
 * @date 2019/7/21-21:53
 */
public class TestThreadPool {

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());//看CPU的核数

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        } finally {
            threadPool.shutdown();
        }


    }
}
