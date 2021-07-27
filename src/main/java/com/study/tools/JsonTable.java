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
                "            \"createTime\": \"2021-05-13 16:28:32\",\n" +
                "            \"createUser\": \"\",\n" +
                "            \"discountGoodsSkuId\": 3,\n" +
                "            \"discountPrice\": 700,\n" +
                "            \"goodsDiscount\": 1.00000000,\n" +
                "            \"goodsSkuName\": \"颜色分类:B款猴配盒子链  \",\n" +
                "            \"id\": 1,\n" +
                "            \"sessionGoodsId\": 2,\n" +
                "            \"skuCode\": \"636725587871797248\",\n" +
                "            \"skuPrice\": 700,\n" +
                "            \"status\": false,\n" +
                "            \"updateTime\": \"2021-05-13 16:36:14\",\n" +
                "            \"updateUser\": \"\"\n" +
                "        }";
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
