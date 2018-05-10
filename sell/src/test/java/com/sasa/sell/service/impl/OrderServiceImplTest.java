package com.sasa.sell.service.impl;

import com.sasa.sell.dataobject.OrderDetail;
import com.sasa.sell.dataobject.OrderMaster;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.OrderStatusEnum;
import com.sasa.sell.enums.PayStatusEnum;
import com.sasa.sell.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private final String BUYER_OPENID ="110110";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("梧桐书");
        orderDTO.setBuyerPhone("12345678912");
        orderDTO.setBuyerAddress("南京鼓楼");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123456");
        orderDetail.setProductQuantity(2);


        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123458");
        orderDetail1.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}", result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne("152422972690650183");
        log.info("【查询订单】 result={}",result);
        Assert.assertNotNull(result);
    }


    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,pageRequest);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("152422972690650183");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("152422972690650183");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());

    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("152422972690650183");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

   @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        Assert.assertNotEquals(0 ,orderDTOPage.getTotalElements());

   }
}