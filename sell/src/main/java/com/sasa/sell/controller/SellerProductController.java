package com.sasa.sell.controller;

import com.sasa.sell.dataobject.ProductCategory;
import com.sasa.sell.dataobject.ProductInfo;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.form.ProductForm;
import com.sasa.sell.service.CategoryService;
import com.sasa.sell.service.ProductService;
import com.sasa.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Map<String, Object> map){
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoList = productService.findAll(pageRequest);

        map.put("productInfoList",productInfoList);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list",map);
    }


    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        try{
            productService.onSale(productId);
        }catch(SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/common/success",map);
    }

    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map){
        try{
            productService.offSale(productId);
        }catch(SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/common/success",map);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String, Object> map){

        if(!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
            //查询所有类目
            List<ProductCategory> productCategoryList = categoryService.findAll();
            map.put("productCategoryList",productCategoryList);
            return new ModelAndView("/product/index",map);

    }

    /**
     * 保存/更新
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            if (!StringUtils.isEmpty(productForm.getProductId())){
                productInfo = productService.findOne(productForm.getProductId());
            }else{
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        }catch(SellException e){
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/common/success",map);
    }
}
