package com.sasa.sell.repository;

import com.sasa.sell.dataobject.OrderDetail;
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
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("100002");
        orderDetail.setOrderId("000001");
        orderDetail.setProductId("123458");
        orderDetail.setProductName("冷冰冰");
        orderDetail.setProductPrice(new BigDecimal(3.8));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://xxxxx.jpg");
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = orderDetailRepository.findByOrderId("000001");
        Assert.assertNotEquals(0, result.size());
    }
}