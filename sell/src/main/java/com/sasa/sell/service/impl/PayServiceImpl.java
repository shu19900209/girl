package com.sasa.sell.service.impl;

import com.google.gson.Gson;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.sasa.sell.config.WechatPayConfig;
import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.service.OrderService;
import com.sasa.sell.service.PayService;
import com.sasa.sell.utils.JsonUtil;
import com.sasa.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME ="微信点单订单";

    Gson gson = new Gson();
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;


    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_WAP);

        log.info("【微信支付】 request = {}",JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 response ={}", JsonUtil.toJson(payResponse));
        return payResponse;

    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知 ={}",JsonUtil.toJson(payResponse));


        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断金额是否一致(0.10 0.1)
        if (!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())){
            log.error("【微信支付】异步通知 金额不一致 orderDTO={},payResponse={}",orderDTO,payResponse);
            throw new SellException(ResultEnum.WECHAT_PAY_MONEY_VERTFY_ERROR);
        }
        //修改订单支付状态
        orderService.paid(orderDTO);

        return payResponse;

    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

        log.info("【微信退款】request ={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse =  bestPayService.refund(refundRequest);
        log.info("【微信退款】response ={}", JsonUtil.toJson(refundResponse));
        return  refundResponse;
    }
}
