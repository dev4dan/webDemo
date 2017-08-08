package com.dev4dan.designPattern.constructMod.facade;

import java.io.*;

class FacadeFileReader {
    public String read(String fileNameSrc) {
        System.out.println("读取文件，获取明文：");
        FileInputStream fs = null;
        StringBuilder sb = new StringBuilder();
        try {
            fs = new FileInputStream(new File(fileNameSrc));
            byte[] data = new byte[16];
            while (fs.read(data) != -1) {
                String str = new String(data, "utf-8");
                sb = sb.append(str);
            }
            fs.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件操作错误！");
            e.printStackTrace();
        }
        return sb.toString();
    }
}