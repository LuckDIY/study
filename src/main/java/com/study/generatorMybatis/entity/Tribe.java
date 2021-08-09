package com.study.generatorMybatis.entity;

import lombok.Data;

/**
 * tribe
 * @author chaoleiwang
 * @date 2021-08-04 17:38:21
 */
@Data
public class Tribe {
    /**
     * 贴吧主键
     */
    private Long id;

    /**
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 吧主id
     */
    private Long leader;

    /**
     * 吧规贴id
     */
    private Long law;
}