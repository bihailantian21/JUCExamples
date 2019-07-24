package com.zcr.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zcr
 * @date 2019/7/20-22:38
 */
public class TestReentrantReadWriteLock {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }

    }
}


class MyCache {
    private volatile Map<String,Object> map = new HashMap<>();
    //private Lock lock = new ReentrantLock();//锁不能进行细粒度的划分，只能把全部进行封杀

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key,Object value) {

        readWriteLock.writeLock().lock();


        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成：");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {

        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取："+key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成："+result);
        } finally {
            readWriteLock.readLock().unlock();
        }


    }
}