package com.dev4dan.designPattern.constructMod.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class FacadeFileWriter {
    public void write(String encryptStr, String fileNameDes) {
        System.out.println("保存密文，写入文件。");
        FileOutputStream fos = null;
        try {
            File file = new File(fileNameDes);
            if(!file.exists()){
                file.createNewFile();
            }

            fos = new FileOutputStream(fileNameDes);
            byte[] str = encryptStr.getBytes();
            fos.write(str, 0, str.length);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件操作错误！");
        }
    }
}