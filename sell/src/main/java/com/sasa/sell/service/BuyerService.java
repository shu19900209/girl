package com.sasa.sell.service;

import com.sasa.sell.dto.OrderDTO;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);
    //取消订单
    OrderDTO cancel(String openid, String orderId);
}
