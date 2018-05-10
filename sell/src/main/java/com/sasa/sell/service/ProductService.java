package com.sasa.sell.service;

import com.sasa.sell.dataobject.ProductInfo;
import com.sasa.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 */
public interface ProductService {

    ProductInfo findOne(String productId);
    /**查询上架商品  */
    List<ProductInfo> findUpAll();
    /**分页 */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加减库存
    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);

    //上下架
    ProductInfo offSale(String productId);
    ProductInfo onSale(String productId);
}
