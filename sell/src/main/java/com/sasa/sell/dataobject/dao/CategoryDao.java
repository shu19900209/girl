package com.sasa.sell.dataobject.dao;

import com.sasa.sell.dataobject.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

public class CategoryDao {
    @Autowired
    CategoryMapper categoryMapper;

    int insertByMap(Map<String, Object> map){
        return categoryMapper.insertByMap(map);
    }
}
