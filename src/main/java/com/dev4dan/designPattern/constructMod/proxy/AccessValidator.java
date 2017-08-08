package com.dev4dan.designPattern.constructMod.proxy;

class AccessValidator {
    //模拟实现登录验证
    public boolean Validate(String userId) {
        System.out.println("在数据库中验证用户'" + userId + "'是否是合法用户？");
        if (userId.equals("杨过")) {
            System.out.println("登录成功:" + userId);
            return true;
        } else {
            System.out.println("登录失败:" + userId);
            return false;
        }
    }
}