package chapter03;

class VolatileFeaturesExample {
    volatile long vl = 0L; //使用volatile声明64位的long型变量

    public void set(long l) {
        vl = l; //单个volatile变量的写
    }

    public void getAndIncrement() {
        vl++; //复合（多个）volatile变量的读/写
    }

    public long get() {
        return vl; //单个volatile变量的读
    }

    //一个volatile变量的单个读/写操作，
    // 与一个普通变量的读/写操作都是使用同一个锁来同步，它们之间的执行效果相同。
}
