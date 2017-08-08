package com.dev4dan.designPattern.actionMod.template.banking;

class SavingAccount extends Account {
    //覆盖父类的抽象基本方法
    @Override
    public void CalculateInterest() {
        System.out.println("按定期利率计算利息！");
    }
}