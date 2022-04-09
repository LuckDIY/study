package com.study.designPatterns.alert;

import lombok.Data;

/**
 * @program: study
 * @description:
 * @author: WangChaoLei
 * @create: 2022-01-26 20:44
 **/
@Data
public class Rule {


    private Integer maxErrorCount;


    private Integer maxTps;
}
