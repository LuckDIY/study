package com.study.generatorMybatis.entity;

import lombok.Data;

/**
 * 关注和粉丝表
 * user_relation
 * @author chaoleiwang
 * @date 2021-08-04 17:38:21
 */
@Data
public class UserRelation {
    /**
     * id
     */
    private Long id;

    /**
     * 当前用户，通过此字段查关注的人
     */
    private Long curUserId;

    /**
     * 关注的人， 通过此字段查你的粉丝
     */
    private Long toUserId;

    /**
     * 1为 失效  0 有效
     */
    private Boolean deleted;
}