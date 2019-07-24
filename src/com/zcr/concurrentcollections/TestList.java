package com.zcr.concurrentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zcr
 * @date 2019/7/20-13:54
 */
public class TestList {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        /*for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }*/

        //启动30个会出现java.util.ConcurrentModificationException并发修改异常
        //发生了线程不安全

        //方法一：Vector类可以解决这个问题，加锁一致性可以保证，但是并发性急剧下降。

        //方法二：Collections.synchronizedList(new ArrayList<>())
        /*List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list1);
            },String.valueOf(i)).start();
        }*/

        //方法三：写时复制CopyOnWriteArrayList
        List<String> list2 = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {//启动三个线程，每个线程到列表中加一个元素
            new Thread(()->{
                list2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list2);
            },String.valueOf(i)).start();
        }
    }
}
