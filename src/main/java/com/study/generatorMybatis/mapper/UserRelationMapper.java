package com.study.generatorMybatis.mapper;

import com.study.generatorMybatis.entity.UserRelation;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRelation record);

    UserRelation selectByPrimaryKey(Long id);

    List<UserRelation> selectAll();

    int updateByPrimaryKey(UserRelation record);
}