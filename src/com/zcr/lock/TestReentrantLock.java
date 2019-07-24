package com.zcr.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zcr
 * @date 2019/7/20-22:12
 *
 * 验证可重入锁
 */
public class TestReentrantLock {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(()->{
            phone.sendSMS();
        },"T1").start();//t1线程在外层方法获取锁的时候，t1在进入内层方法会自动获取锁

        new Thread(()->{
            phone.sendSMS();
        },"T2").start();


        System.out.println("========");

        Thread t3 = new Thread(new Reen(),"t3");
        Thread t4 = new Thread(new Reen(),"t4");
        t3.start();
        t4.start();


    }
}

class Phone {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName()+"\t ####invoked sendEmail");
    }

}

class Reen implements Runnable{

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();//只要锁匹配，几把锁都可以。
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke get");
            set();
        } finally {
            lock.unlock();
        }

    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t ####invoke set");
        } finally {
            lock.unlock();
        }
    }
}
