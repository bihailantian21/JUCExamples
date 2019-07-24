package com.zcr.volitale;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zcr
 * @date 2019/7/19-11:48
 * 验证volatile可见性
 * 验证volatile不保证原子性
 */
public class TestVolatile {

    public static void main(String args[]) {
        //seeokByVolatile();

        MyData myData = new MyData();

        for (int i = 1; i < 20; i++) {//启动20个线程
            new Thread(()->{
                for (int j = 1; j < 1000; j++) {//每个线程加1000
                    myData.addPlus();
                    myData.addSynPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {//如果活跃线程数数大于2，那么就让位还让上面的线程执行完毕
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int type finally number value"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t int type2 finally number value"+myData.number2);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type finally number value"+myData.atomicInteger);

    }

    private static void seeokByVolatile() {
        MyData myData = new MyData();//资源类

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in"); try {
            Thread.sleep(3000);
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:" + myData.number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        },"AAA").start();

        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}

class MyData {
    /*
    int number = 0;
     3秒之后，a线程已经把number改了，
     但是main线程不知道，对main线程不可见，还在傻傻的等着执行无限循环，没有人通知我
     现在修改程序，加了volatile后，3秒后，a线程改了，主线程立马获取到改动，于是退出循环
     */
    volatile int number = 0;//增加了内存之间的可见性
    int number2 = 0;

    public void addTo60() {
        this.number = 60;
    }

    /*
    volatile不保证原子性，20个线程，每个线程加1000，最后加不到20000。
    原因：拷贝回自己的内存空间，每个人都拿到0，写回到主内存时，
    线程1写回到的时候被挂起了，线程2歘的写回了。然后线程1恢复后又写回了一遍，把原来的1给覆盖了。
    三个线程都拿到1，都在各自的工作内存中加1，写回到的时候，没有拿到最新的值就又写了，写覆盖
    如何解决？1.加synchronized,不要杀鸡焉用牛刀，太重了
    2.使用AtomicInteger
     */
    public void addPlus() {
        number++;
    }

    public synchronized void addSynPlus() {
        number2++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}