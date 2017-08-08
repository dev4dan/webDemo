package com.dev4dan.designPattern.constructMod.proxy;

class RealSearcher implements Searcher {
    //模拟查询商务信息
    public String DoSearch(String userId, String keyword) {
        System.out.println(userId+"用户使用关键词"+keyword+"查询商务信息！");
        return "返回具体内容";
    }
}