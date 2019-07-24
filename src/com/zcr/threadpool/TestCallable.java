package com.zcr.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zcr
 * @date 2019/7/21-21:40
 * 区别：
 * runnable接口没有返回值，不会抛异常，实现run
 * callable接口有返回值，会抛异常，实现call
 */
public class TestCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        Thread thread = new Thread(futureTask,"A");
        thread.start();

        System.out.println(Thread.currentThread().getName());


        int result01=100;

        while (!futureTask.isDone()){

        }

        int result02 = futureTask.get();

        System.out.println("result:"+(result01+result02));



    }
}

class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("callable come in");

        Thread.sleep(2000);
        return 1024;
    }
}
