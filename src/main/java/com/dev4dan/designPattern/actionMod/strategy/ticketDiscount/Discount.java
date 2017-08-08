package com.dev4dan.designPattern.actionMod.strategy.ticketDiscount;

//折扣类：抽象策略类
interface Discount {
    public double calculate(double price);
} 