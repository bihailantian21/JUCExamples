package com.zcr.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zcr
 * @date 2019/7/20-22:27
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用mylock方法自己持有锁5秒钟，
 * B随后进来发现当前有线程持有锁，不是null，所以只能通过自旋等待，直到A释放锁后B随即抢到。
 */
public class TestSpinLock {

    public static void main(String[] args) {



        TestSpinLock spinLock = new TestSpinLock();

        new Thread(()->{
            spinLock.myLock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"A").start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLock.myLock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnLock();
        },"B").start();




    }

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t come in");

        while (!atomicReference.compareAndSet(null,thread)){

        }
        //如果是第一次进来线程，就不进循环
        //A线程进来，发现期望的是空的，那么while的条件就是false，于是不进入循环，直接拿到了锁。
        //B线程进来，发现期望的值不是空，那么while的条件就是true，于是它进入锁中，一直会循环的判断，直到期望的值是空了，才能推出循环，获得锁。
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"\t invoked muUnlock");
    }
}
