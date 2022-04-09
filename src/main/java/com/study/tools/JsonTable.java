package com.study.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: study
 * @description: json转table使用，用于生成接口文档返回字段
 * @author: WangChaoLei
 * @create: 2021-05-17 11:34
 **/
public class JsonTable {

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        String str = "{\n" +
                "\t\t\t\"activityInventory\": -1,\n" +
                "\t\t\t\"defaultStatus\": 1,\n" +
                "\t\t\t\"discountPrice\": 525,\n" +
                "\t\t\t\"discountType\": 1,\n" +
                "\t\t\t\"goodsDiscount\": 0.75000000,\n" +
                "\t\t\t\"goodsOrder\": 4,\n" +
                "\t\t\t\"id\": 258217,\n" +
                "\t\t\t\"soldCount\": -1,\n" +
                "\t\t\t\"status\": true\n" +
                "\t\t}";
        StringBuilder builder = new StringBuilder();
        builder.append("| 名称                  | 类型    | 描述                      |").append("\r\n");
        builder.append("| :-------------------- | :------ | :------------------------ |").append("\r\n");

        //if (scanner.hasNext()) {
            //String json = scanner.next();
            JSONObject jsonObject = JSON.parseObject(str);
            jsonObject.forEach((k,v)->{
                String[] split = v.getClass().getCanonicalName().split("\\.");
                builder.append("| ").append(k).append("    |").append(split[split.length-1]).append("|   |").append("\r\n");
            });
            System.out.println(builder);
        //}

    }
}
