package com.sasa.sell.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sasa.sell.enums.ProductStatusEnum;
import com.sasa.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {

    //商品Id
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
    //类目编号
    private Integer categoryType;

}
