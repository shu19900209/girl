package com.sasa.sell.repository;

import com.sasa.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
       ProductCategory productCategory =  productCategoryRepository.findOne(1);
       System.out.println(productCategory.toString());
       Assert.assertNotEquals(null,productCategory);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory =  productCategoryRepository.findOne(2);
        productCategory.setCategoryName("男生最爱榜");
        productCategory.setCategoryType(2);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void save(){
        ProductCategory productCategory =  new ProductCategory();
        productCategory.setCategoryName("最爱榜");
        productCategory.setCategoryType(200);
        productCategoryRepository.save(productCategory);
    }


    @Test
    public void findbyCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory>  result =  productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0 ,result.size());

    }

}