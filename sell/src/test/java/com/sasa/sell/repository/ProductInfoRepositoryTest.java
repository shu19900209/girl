package com.sasa.sell.repository;

import com.sasa.sell.dataobject.ProductInfo;
import com.sasa.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(2.3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("非常好吃的粥");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertNotEquals(0, productInfoList.size());
    }
}