package com.zcr.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author zcr
 * @date 2019/7/21-18:01
 */
public class TestSynchronousQueue {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put(1);
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put(2);
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"A").start();


        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
                System.out.println(Thread.currentThread().getName()+"\t"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"B").start();



    }
}
