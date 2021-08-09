package com.study.generatorMybatis.mapper;

import com.study.generatorMybatis.entity.Tribe;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TribeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tribe record);

    Tribe selectByPrimaryKey(Long id);

    List<Tribe> selectAll();

    int updateByPrimaryKey(Tribe record);
}