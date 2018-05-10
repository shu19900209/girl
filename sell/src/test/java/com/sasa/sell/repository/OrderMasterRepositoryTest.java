package com.sasa.sell.repository;

import com.sasa.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    private final String OPENID = "123456";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("000001");
        orderMaster.setBuyerName("洒洒兮");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("浦口海德");
        orderMaster.setBuyerOpenid("123456");
        orderMaster.setOrderAmount(new BigDecimal(10));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID,pageRequest);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}