package com.sasa.sell.service.impl;

import com.sasa.sell.dataobject.ProductInfo;
import com.sasa.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        Assert.assertNotEquals(0, productInfoPage.getSize());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123458");
        productInfo.setProductName("冷冰冰");
        productInfo.setProductPrice(new BigDecimal(3.8));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很冷很冷的冰");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}