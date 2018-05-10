package com.sasa.sell.controller;

import com.sasa.sell.VO.ResultVO;
import com.sasa.sell.converter.OrderForm2OrderDTOConverter;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.form.OrderForm;
import com.sasa.sell.service.BuyerService;
import com.sasa.sell.service.OrderService;
import com.sasa.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @RequestMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确 orderForm ={}", orderForm);
            throw new SellException(ResultEnum.PRAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车为空 orderDTO ={}", orderDTO);
            throw  new SellException(ResultEnum.CARD_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
    //订单列表
    @RequestMapping("/list")
    public ResultVO list(@RequestParam("openid") String openid,
                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());


    }
    //订单详情
    @RequestMapping("/detail")
    public ResultVO detail(@RequestParam("orderId") String orderId,
                           @RequestParam("openid") String openid){
        if (StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】 orderId不能为空");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单详情】 openid不能为空");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.findOrderOne(orderId,openid);
        return  ResultVOUtil.success(orderDTO);

    }
    //取消订单
    @RequestMapping("/cancel")
    public ResultVO cancel(@RequestParam("orderId") String orderId,
                           @RequestParam("openid") String openid){
        if (StringUtils.isEmpty(orderId)){
            log.error("【取消订单】 orderId不能为空");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        if (StringUtils.isEmpty(openid)){
            log.error("【取消订单】 openid不能为空");
            throw new SellException(ResultEnum.PRAM_ERROR);
        }
        OrderDTO orderDTO = buyerService.cancel(orderId,openid);
        return  ResultVOUtil.success();

    }


}