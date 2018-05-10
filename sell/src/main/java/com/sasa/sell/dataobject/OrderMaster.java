package com.sasa.sell.dataobject;

import com.sasa.sell.enums.OrderStatusEnum;
import com.sasa.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    //订单id
    @Id
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
    private Date createTime;
    //修改时间
    private Date updateTime;
    //订单列表
//    @Transient
//    private List<OrderDetail> orderDetailList;

}
