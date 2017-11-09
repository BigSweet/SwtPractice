package cn.swt.m.firstcode;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/9/1
 */
public class Test {
    public static void main(String args[]) {
        B b = new B();
    }
}


class A {
    protected String name = "名字0";

    public A() {
        this.init();
    }

    void init() {
        System.out.print("init: " + this.name);
    }
}

class B extends A {
    public B() {

    }

    protected String name = "名字1";

    @Override
    void init() {
        super.init();
        System.out.print("init: " + this.name);
    }
}