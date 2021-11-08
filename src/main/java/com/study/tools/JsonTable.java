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
                "                \"userId\": 657366401,\n" +
                "                \"avatar\": \"https://cdn.xiaoxiangyoupin.com/image/750x750_1122736622484905984.png\",\n" +
                "                \"userName\": \"大卫-汤普森\",\n" +
                "                \"launchCount\": 0,\n" +
                "                \"awardCoins\": 10\n" +
                "            }";
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
