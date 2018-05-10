package com.sasa.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sasa.sell.enums.ProductStatusEnum;
import com.sasa.sell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    //商品Id
    @Id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //小图
    private String productIcon;
    //商品状态 0正常 1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号
    private Integer categoryType;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }
}
