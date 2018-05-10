package com.sasa.sell.controller;

import com.sasa.sell.dataobject.OrderDetail;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 卖家段订单
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {


    @Autowired
    OrderService orderService;

    /**
     * 订单列表
     * @param page 第几页,从1开始
     * @param size 一页有多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "2") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page -1 , size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);

        return new ModelAndView("/order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public  ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String, Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 出现异常 ={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }

        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success",map);
    }

    /**
     * 订单详情
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try{
            orderDTO= orderService.findOne(orderId);
            List<OrderDetail> orderDetailList= orderDTO.getOrderDetailList();
        }catch (SellException e){
            log.error("【卖家端查询订单详情】 出现异常 ={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("/order/detail",map);
    }

    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        OrderDTO orderDTO = new OrderDTO();

        try{
            orderDTO= orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】 出现异常 ={}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("/common/success",map);

    }
}
