package com.sasa.sell.controller;


import com.lly835.bestpay.model.PayResponse;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.service.OrderService;
import com.sasa.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;
    @RequestMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String,Object> map){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if(orderDTO == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    @RequestMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify();

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }


    @PostMapping(value = "/girls")
    public void girlAdd(@RequestParam("cupSize")String cupSize,
                        @RequestParam("age") Integer age){
    }
    @GetMapping(value = "/girls/{id}")
    public void girlFindByOne(@PathVariable("id") Integer id){
    }
    @PutMapping(value = "/girls/{id}")
    public void girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("age") Integer age,
                           @RequestParam("cupSize") String cupSzie){

    }
    @DeleteMapping(value = "/girls/{id}")
    public void girlDeleteById(@PathVariable("id") Integer id){

    }


}
