package com.sasa.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详细信息
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;
    //商品名称
    @JsonProperty("name")
    private String productName;
    //商品单价
    @JsonProperty("price")
    private BigDecimal productPrice;
    //商品描述
    @JsonProperty("description")
    private String productDescription;
    //小图
    @JsonProperty("icon")
    private String productIcon;

}
