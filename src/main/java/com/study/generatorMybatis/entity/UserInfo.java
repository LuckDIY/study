package com.study.generatorMybatis.entity;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用户表
 * user_info
 * @author chaoleiwang
 * @date 2021-08-04 17:38:21
 */
@Data
public class UserInfo {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码，先使用md5吧
     */
    private String password;

    /**
     * 性别 0 女 1男 2未知
     */
    private Boolean sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String addr;

    /**
     */
    private LocalDateTime createdAt;

    /**
     */
    private LocalDateTime updatedAt;
}