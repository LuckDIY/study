package com.study.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCvsUtils {

    private static Logger logger = LoggerFactory.getLogger(ReadCvsUtils.class);

    public static class Loophole{

        private String a;

        private String b;
    }

    public static void main(String[] args) {
        List<Loophole> loopholes = readCSV(Arrays.asList("a", "b"), Loophole.class);

    }

    /**
     * @return List<List<String>>
     * @Description 读取CSV文件的内容（不含表头）
     * @Param filePath 文件存储路径，colNum 列数
     **/
    public static <T>List<T> readCSV(List<String> colNames,Class<T> clazz) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("D:\\FW\\CVEs.csv");
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
//            CSVParser parser = new CSVParser(bufferedReader, CSVFormat.DEFAULT);
//          表内容集合，外层List为行的集合，内层List为字段集合
            List<T> values = new ArrayList<>();

            List<CSVRecord> records = parser.getRecords();

            for (int j = 1; j < records.size(); j++) {
                CSVRecord record = records.get(j);

                T entity = clazz.newInstance();
                for (int i = 0; i < colNames.size(); i++) {

                    //获取字段名
                    String fileName = colNames.get(i);

                    //拿到字段对象
                    Field declaredField = clazz.getDeclaredField(fileName);

                    //打开权限
                    declaredField.setAccessible(true);

                    //字段默认都是string类型
                    declaredField.set(entity,record.get(i));
                }

                values.add(entity);
            }

            return values;
        } catch (Exception e) {
            logger.error("解析CSV内容失败" + e.getMessage(), e);
        }finally {
            //关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
