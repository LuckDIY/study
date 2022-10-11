package com.study.javaProxy;

import org.apache.ibatis.annotations.Select;

public interface HelloDao {
 
    @Select("select * from hello where name = #{name}")
    Object sayHello(String name);
}