package com.zcr.volitale;

/**
 * @author zcr
 * @date 2019/7/19-11:48
 * 验证volatile禁止指令重排
 */
public class TestVolatile2 {

    public static void main(String args[]) {
        TestVolatile2 testVolatile2 = new TestVolatile2();
        testVolatile2.method01();
        testVolatile2.method02();

    }

    volatile int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1;
        flag = true;
    }

    public void method02() {
        if (flag) {
            a = a + 5;
            System.out.println("result:" + a);
        }
    }


}

