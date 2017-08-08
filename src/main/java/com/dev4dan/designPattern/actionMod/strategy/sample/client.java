package com.dev4dan.designPattern.actionMod.strategy.sample;

/**
 * Created by danielwood on 19/06/2017.
 */
public class client {
    public static void main(String[] args) {
        Context context = new Context();
        AbstractStrategy strategy = new ConcreteStrategyA(); //可在运行时指定类型
        context.setStrategy(strategy);
        context.algorithm();
    }
}
