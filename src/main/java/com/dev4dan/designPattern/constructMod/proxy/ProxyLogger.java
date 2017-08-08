package com.dev4dan.designPattern.constructMod.proxy;

class ProxyLogger {
    //模拟实现日志记录  
    public void log(String userId) {
        System.out.println(userId+"更新数据库，用户查询次数加1");
    }
} 