package com.study.aop;

import java.lang.annotation.*;

/**
 * 写入request log
 * @author chaoleiwang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WriteLog {
}
