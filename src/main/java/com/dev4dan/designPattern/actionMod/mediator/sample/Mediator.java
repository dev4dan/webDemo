package com.dev4dan.designPattern.actionMod.mediator.sample;

import java.util.ArrayList;
import java.util.List;

abstract class Mediator {
    protected List<Colleague> colleagues; //用于存储同事对象

    //注册方法，用于增加同事对象
    public void register(Colleague colleague) {
        if(colleagues == null ){
            colleagues = new ArrayList<>();
        }
        colleagues.add(colleague);
    }

    //声明抽象的业务方法
    public abstract void operation();
}