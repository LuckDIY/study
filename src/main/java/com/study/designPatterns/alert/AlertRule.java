package com.study.designPatterns.alert;

/**
 * @program: study
 * @description: 报警规则
 * @author: WangChaoLei
 * @create: 2022-01-26 20:37
 **/
public class AlertRule {

    public Rule getMatchedRule(String api) {
        Rule rule = new Rule();
        rule.setMaxErrorCount(0);
        rule.setMaxTps(0);

        return rule;
    }
}
