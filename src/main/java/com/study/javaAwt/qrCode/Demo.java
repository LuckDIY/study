package com.study.javaAwt.qrCode;

import com.sun.javafx.iio.ImageStorage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @program: study
 * @description: 测试图形绘制
 * @author: WangChaoLei
 * @create: 2022-01-05 16:37
 **/
public class Demo {

    public static void main(String[] args) throws IOException {
        // 创建画布
        BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) img.getGraphics();


        //画个六横六竖的棋盘 7*7 => 60*6 = 360
        for (int i = 0; i < 7; i++) {

            int x1 = 20;
            int y1 = 20 + i * 60;

            int x2 = 380;
            int y2 = y1;

            //在(0,50)与(50,50)之间画一条线段
            graphics2D.drawLine(x1, y1, x2, y2);
        }

        for (int i = 0; i < 7; i++) {

            int x1 = 20 + i * 60;
            int y1 = 20;

            int x2 = x1;
            int y2 = 380;

            //在(0,50)与(50,50)之间画一条线段
            graphics2D.drawLine(x1, y1, x2, y2);
        }

        //在(0,50)与(50,50)之间画一条线段
        //graphics2D.drawLine(0,50,200,50);

        ImageIO.write(img, "JPG", new File("/Users/chaoleiwang/Downloads/aaa.jpg"));

        //System.out.println(img);

    }
}
