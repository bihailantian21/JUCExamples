package com.zcr.threadpool;

import java.util.concurrent.*;

/**
 * @author zcr
 * @date 2019/7/21-22:09
 */
public class MyThreadPool {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()//默认的拒绝策略：马上报异常
                //new ThreadPoolExecutor.CallerRunsPolicy()//调用者运行机制：不会抛弃任务也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
                //谁让你调用我的，那么你就去找他

                //new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃队列中等待时间最久的任务这样的话只能完成8个人的业务，剩下的就直接丢弃了

        );

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
