package com.study.generatorMybatis.entity;

import lombok.Data;

/**
 * theme
 * @author chaoleiwang
 * @date 2021-08-04 17:38:21
 */
@Data
public class Theme {
    /**
     * 话题id
     */
    private Long id;

    /**
     * 所属部落id
     */
    private Long tribeId;

    /**
     * 发帖人id
     */
    private Long userId;

    /**
     * 主题标题
     */
    private String titile;

    /**
     * 赞数量
     */
    private Long praise;

    /**
     * 踩的数量
     */
    private Long poor;

    /**
     * 内容，html
     */
    private String content;
}