package com.study.generatorMybatis.entity;

import lombok.Data;

/**
 * sub_content_info
 * @author chaoleiwang
 * @date 2021-08-04 17:38:21
 */
@Data
public class SubContentInfo {
    /**
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 父编号
     */
    private Long fatherId;

    /**
     * 主题贴编号
     */
    private Long mainId;

    /**
     * 回帖信息
     */
    private String content;
}