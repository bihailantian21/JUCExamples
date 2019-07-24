package com.zcr.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zcr
 * @date 2019/7/20-12:57
 * AtomicInteger
 * AtomicReference
 *
 *
 */
public class TestCAS {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2019));
        System.out.println("current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,23));
        System.out.println("current data:" + atomicInteger.get());

        //多个线程去操作主内存中的数据。一个叫做期望值、一个叫做更新值主内存中数据是5
        //一个线程拷贝回去自己的工作内存，对它进行修改，然后写回到主内存的时候，会进行比较和交换，
        // 如果和拷贝的数据一样的话，就将改变后的数据写回去；否则的话，就不进行写回。




        User z1 = new User("z1",22);
        User l2 = new User("l2",34);
        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(z1);
        System.out.println(atomicReference.compareAndSet(z1,l2));
        System.out.println(atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z1,l2));
        System.out.println(atomicReference.get().toString());


    }
}


class User {
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
