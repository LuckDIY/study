package com.study.javaAwt.qrCode;
import com.alibaba.fastjson.JSON;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2022-01-05 18:40
 **/
@Data
public class Demo2 {


    private String cmd;

    private String sandBox;


    private List<Group> processGroup;



    @Data
    public static class Group{

        private String group;

        private String program;
    }

    /**
     * 前端传processGroup转沙盒需要的processGroup
     * @return
     */
    public JSONArray toShaHeProcessGroup(){

        JSONArray jsonArray = new JSONArray();

        for (Group group : this.processGroup) {
            JSONObject jsonObject = new JSONObject();
            List<String> shaHeGroup = Arrays.stream(group.getGroup().split(",")).map(x -> "<" + x + ">").collect(Collectors.toList());
            jsonObject.put("group",shaHeGroup);
            List<String> shaHeProgram = Arrays.stream(group.getProgram().split(",")).map(x -> "<" + x + ">").collect(Collectors.toList());
            jsonObject.put("program",shaHeProgram);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.stream("1,2".split(",")).map(x -> "<" + x + ">").collect(Collectors.toList()));
    }



}
