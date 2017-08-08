package com.dev4dan.designPattern.actionMod.template.banking;

import com.dev4dan.designPattern.XMLUtil;

class Program {
    public static void main(String[] args) {
        Account account;
        //读取配置文件
        String subClassStr = "savingAccount";
        //反射生成对象
        account = (Account)XMLUtil.getBean(subClassStr);
        account.handle("张无忌", "123456");
    }
}