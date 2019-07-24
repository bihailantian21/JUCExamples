package com.zcr.blockingqueue;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zcr
 * @date 2019/7/21-18:07
 */
public class ProdConsumer_Tradition {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();



    }
}


class ShareData {//资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();

        try {
            while (number != 0) {
                condition.await();
            }

            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void decrement() throws Exception {
        lock.lock();

        try {
            while (number == 0) {
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}