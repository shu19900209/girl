package com.sasa.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sasa.sell.dataobject.OrderDetail;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {


    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.info("【对象转换】 错误, String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
            orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
