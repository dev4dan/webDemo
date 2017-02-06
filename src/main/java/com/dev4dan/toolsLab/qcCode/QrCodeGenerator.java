package com.dev4dan.toolsLab.qcCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.util.Hashtable;

/**
 * Created by danielwood on 2017/1/18.
 */
public class QrCodeGenerator {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String text = "http://www.baidu.com";
        int width = 300;
        int height = 300;
        //二维码的图片格式
        String format = "gif";
        Hashtable hints = new Hashtable();
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        //生成二维码
        String pathName = "."+File.separator+"new.gif";
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        System.out.println("pathName : "+pathName);

    }


}
