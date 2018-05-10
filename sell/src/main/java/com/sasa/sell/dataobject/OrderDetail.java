package com.sasa.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    //详情id
    @Id
    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuantity;
    //小图
    private String productIcon;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
