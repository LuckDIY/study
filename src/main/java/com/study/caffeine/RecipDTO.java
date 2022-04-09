package com.study.caffeine;

import lombok.Data;

import java.util.List;

/**
 * @program: study
 * @description: xmemcached学习
 * @author: WangChaoLei
 * @create: 2021-11-22 18:29
 **/
@Data
public class RecipDTO {


    private Long yardDutyId;


    private List<JianCe> data1;



    @Data
    public static class JianCe{
        private String recipientsItem;

        private String remake;
    }
}
