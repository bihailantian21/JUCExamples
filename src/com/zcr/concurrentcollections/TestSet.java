package com.zcr.concurrentcollections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zcr
 * @date 2019/7/20-21:55
 */
public class TestSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        /*for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }*/
        //ConcurrentModificationException


        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
        /*for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                set1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set1);
            },String.valueOf(i)).start();
        }*/

        Set<String> set2 = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                set2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set2);
            },String.valueOf(i)).start();
        }
    }
}
