package com.dev4dan.designPattern.constructMod.proxy;

import com.dev4dan.designPattern.XMLUtil;

class ProxyProgram {
    public static void main(String[] args) {
        //读取配置文件
        String proxy = "proxy";

        //反射生成对象，针对抽象编程，客户端无须分辨真实主题类和代理类
        Searcher searcher;
        searcher = (Searcher) XMLUtil.getBean(proxy);

        String result = searcher.DoSearch("杨过", "玉女心经");
        System.out.println("result : " + result);
    }
}