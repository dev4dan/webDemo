package com.dev4dan.designPattern.actionMod.template.sample;

abstract class AbstractClass {
    //模板方法
    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        primitiveOperation3();
    }

    //基本方法—具体方法
    public void primitiveOperation1() {
        //实现代码
    }

    //基本方法—抽象方法
    public abstract void primitiveOperation2();

    //基本方法—钩子方法
    public void primitiveOperation3() {
    }
}