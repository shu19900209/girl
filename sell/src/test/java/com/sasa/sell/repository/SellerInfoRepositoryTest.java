package com.sasa.sell.repository;

import com.sasa.sell.dataobject.SellerInfo;
import com.sasa.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {

    @Autowired
    SellerInfoRepository sellerInfoRepository;

    private static final String openid ="abc";

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("洒洒兮");
        sellerInfo.setPassword("123456");
        sellerInfo.setOpenid("abc");

        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {

        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        Assert.assertEquals(sellerInfo.getOpenid(), openid);

    }
}