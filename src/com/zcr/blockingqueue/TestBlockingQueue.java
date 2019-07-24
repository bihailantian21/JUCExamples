package com.zcr.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zcr
 * @date 2019/7/21-17:43
 */
public class TestBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        //add element remove
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());java.util.NoSuchElementException

        //System.out.println(blockingQueue.element());java.util.NoSuchElementException


        //offer peek poll
        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue1.offer("a"));
        System.out.println(blockingQueue1.offer("b"));
        System.out.println(blockingQueue1.offer("c"));
        System.out.println(blockingQueue1.offer("d"));

        System.out.println(blockingQueue1.peek());

        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());//null

        System.out.println(blockingQueue1.peek());//null



        //put take
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        blockingQueue2.put("a");
        blockingQueue2.put("b");
        blockingQueue2.put("c");
        //blockingQueue2.put("d");//阻塞的意思是：我现在满了，就等着，直到有元素出去。因为我不能丢消息呀，就等着
        //取不出来就堵着
        System.out.println(blockingQueue2);
        blockingQueue2.take();
        blockingQueue2.take();
        blockingQueue2.take();




        //offer
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue3.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue3.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue3.offer("a",2L, TimeUnit.SECONDS));//这时候没有等2秒钟
        System.out.println(blockingQueue3.offer("a",2L, TimeUnit.SECONDS));//只阻塞2秒钟。2秒钟之后就打印出false






    }
}
