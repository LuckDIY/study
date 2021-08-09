package com.study.generatorMybatis.mapper;

import com.study.generatorMybatis.entity.SubContentInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubContentInfoMapper {
    int insert(SubContentInfo record);

    List<SubContentInfo> selectAll();
}