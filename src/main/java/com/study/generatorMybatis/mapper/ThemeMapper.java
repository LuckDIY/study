package com.study.generatorMybatis.mapper;

import com.study.generatorMybatis.entity.Theme;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThemeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Theme record);

    Theme selectByPrimaryKey(Long id);

    List<Theme> selectAll();

    int updateByPrimaryKey(Theme record);
}