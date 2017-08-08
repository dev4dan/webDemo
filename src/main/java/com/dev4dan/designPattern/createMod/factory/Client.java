package com.dev4dan.designPattern.createMod.factory;

class Client {
    public static void main(String args[]) {
        testLoggerFactory();
    }

    public static void testChartFactory(){
        Chart chart;
        chart = ChartFactory.getChart("histogram"); //通过静态工厂方法创建产品
        chart.display();
    }

    public static void testLoggerFactory(){
        LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory(); //可引入配置文件实现
        logger = factory.createLogger();
        logger.writeLog();
    }
} 