package com.zcr.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zcr
 * @date 2019/7/21-18:21
 *
 * 多个线程之间按顺序调用，实现A-B_C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次....进行10轮
 */
public class ManyConditionLock {

    public static void main(String[] args) {

        Sharesource sharesource = new Sharesource();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                sharesource.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                sharesource.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                sharesource.print15();
            }
        },"C").start();

    }
}


class Sharesource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();


    public void print5() {
        lock.lock();


            try {
                while (number != 1) {
                    condition.await();
                }

                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName()+"\t"+i);
                }

                number = 2;
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


    }





    public void print10() {
        lock.lock();


        try {
            while (number != 2) {
                condition1.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 3;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void print15() {
        lock.lock();


        try {
            while (number != 3) {
                condition2.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 1;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
