package com.sasa.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sasa.sell.dataobject.OrderDetail;
import com.sasa.sell.enums.OrderStatusEnum;
import com.sasa.sell.enums.PayStatusEnum;
import com.sasa.sell.utils.EnumUtil;
import com.sasa.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrderDTO {

    //订单id
    private String orderId;
    //买家名称
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openId
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态默认0 新建订单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态默认0 未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //修改时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    //订单列表
    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }

}
