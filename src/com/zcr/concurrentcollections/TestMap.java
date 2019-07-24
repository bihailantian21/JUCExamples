package com.zcr.concurrentcollections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zcr
 * @date 2019/7/20-21:58
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        /*for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }*/

        Map<String,String> map1 = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                map1.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map1);
            },String.valueOf(i)).start();
        }
    }
}
