package com.zcr.volitale;

/**
 * @author zcr
 * @date 2019/7/20-12:42
 */
public class VolatileSingleton {

    private static volatile VolatileSingleton instance = null;//所以要在instance变量上面加上volatile

    private VolatileSingleton() {
        System.out.println(Thread.currentThread().getName()+"\t我是构造方法");
    }

    //懒汉模式，构造函数被调用了多次
    /*public static VolatileSingleton getInstance() {
        if (instance == null) {
            instance = new VolatileSingleton();
        }
        return instance;
    }*/

    //如果加上synchronized，构造函数只被调用了一次，在多线程的环境控制住了,但是太重了，并发性下降了
    /*public static synchronized VolatileSingleton getInstance() {
        if (instance == null) {
            instance = new VolatileSingleton();
        }
        return instance;
    }*/

    //双重检测所模式，也不一定线程安全，因为有指令重排的存在，所以要加入volatile
    public static VolatileSingleton getInstance() {
        if (instance == null) {
            synchronized (VolatileSingleton.class) {
                if (instance == null) {
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单线程情况下，main线程的操作，构造函数只被调用了一次
        //System.out.println(VolatileSingleton.getInstance() == VolatileSingleton.getInstance());

        //并发多线程后，情况发生了很大的变化。构造函数被调用了多次
        //如果加上synchronized，构造函数只被调用了一次，在多线程的环境控制住了,但是太重了，并发性下降了
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                VolatileSingleton.getInstance();
            },String.valueOf(i)).start();
        }



    }
}
