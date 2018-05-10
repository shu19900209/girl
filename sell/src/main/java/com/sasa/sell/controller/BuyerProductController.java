package com.sasa.sell.controller;

import com.sasa.sell.VO.ProductInfoVO;
import com.sasa.sell.VO.ProductVO;
import com.sasa.sell.VO.ResultVO;
import com.sasa.sell.dataobject.ProductCategory;
import com.sasa.sell.dataobject.ProductInfo;
import com.sasa.sell.service.CategoryService;
import com.sasa.sell.service.ProductService;
import com.sasa.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询商品所属类目（一次性查询）

        //传统方式
//        List<Integer> categortyTypeList = new ArrayList<Integer>();
//        for (ProductInfo productInfo : productInfoList){
//            categotyTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法（java8 lambda）
        List<Integer> categortyTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categortyTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtil.success(productVOList);
    }
}
