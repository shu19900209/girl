package com.sasa.sell.dataobject.mapper;


import com.sasa.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR},#{category_type, jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType, jdbcType=INTEGER}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName, jdbcType=VARCHAR}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Update("update product_category set category_name = #{categoryName, jdbcType=VARCHAR} where category_type= #{categoryType, jdbcType=INTEGER} ")
    int updateByCategoryType(@Param("categoryType") Integer categoryType, @Param("categoryName") String categoryName);

    @Delete("delete from product_category where category_type= #{categoryType, jdbcType= INTEGER} ")
    int deleteByCategoryType(Integer categoryType);

}
