package com.sasa.sell.dataobject.mapper;

import com.sasa.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryMapperTest {


    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void insertByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name","师兄最爱");
        map.put("category_type", 102);
        int result = categoryMapper.insertByMap(map);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("爆款");
        productCategory.setCategoryType(103);
        int result = categoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory productCategory = categoryMapper.findByCategoryType(103);
        Assert.assertEquals(103, productCategory.getCategoryType().intValue());

    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> productCategory = categoryMapper.findByCategoryName("师兄最爱");
        Assert.assertNotEquals(1, productCategory.size());

    }

    @Test
    public void updateByCategoryType(){
        int result = categoryMapper.updateByCategoryType(102, "师妹最爱");
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteByCategoryType(){
        int result = categoryMapper.deleteByCategoryType(103);
        Assert.assertEquals(1, result);
    }
}