package com.study.collection;

import com.alibaba.fastjson.JSON;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.Map;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2021-06-10 18:32
 **/
public class Demo {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(999);
        objects.add(111);
        DiyList diyList = new DiyList(objects);

        System.out.println(JSON.toJSONString(diyList));

        int price = 10000;
        float f = 0.04f;
        int i=0;
        while(20000>(price=(int)(price*(1+f)))){
            i++;
        }
        System.out.println(i);





        ArrayList<Map> jsonStr = new ArrayList();

        String yamlStr = null;

        for (int j = 0; j < jsonStr.size(); j++) {

            Map o = jsonStr.get(j);
            String str = new Yaml().dumpAsMap(o);
            if(j!=0){
                yamlStr+="---"+str;
            }else{
                yamlStr+=str;
            }
        }
    }

}
