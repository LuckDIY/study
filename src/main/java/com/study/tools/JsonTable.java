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
                "            \"categoryName\": \"\",\n" +
                "            \"goodsCategoryName\": \"美容个护-洗护\",\n" +
                "            \"goodsCode\": \"1021\",\n" +
                "            \"goodsImages\": \"http://img.xiaoxiangyoupin.com/image/8527b226-e259-4be4-a3bc-bb74de55a36c.png\",\n" +
                "            \"goodsName\": \"【博朗】欧乐3D蓝牙智能电动牙刷\",\n" +
                "            \"goodsOrder\": \"\",\n" +
                "            \"goodsType\": 2,\n" +
                "            \"id\": 352,\n" +
                "            \"inventory\": \"\",\n" +
                "            \"originalPrice\": 139900,\n" +
                "            \"plusNewConfig\": \"\",\n" +
                "            \"supplyPrice\": \"\",\n" +
                "            \"updateTime\": \"2021-08-23 18:43:39\",\n" +
                "            \"updateUser\": \"admin\",\n" +
                "            \"vipShopConfig\": {\n" +
                "                \"discountPrice\": 1279200,\n" +
                "                \"goodsDiscount\": 8.00000000,\n" +
                "                \"status\": true\n" +
                "            },\n" +
                "            \"xxypOriginalPrice\": 249900\n" +
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
