package com.sasa.sell.controller;

import com.sasa.sell.dataobject.ProductCategory;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.form.CategoryForm;
import com.sasa.sell.service.CategoryService;
import com.sasa.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 商品类目
     * @param map
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(Map<String, Object> map){

        List<ProductCategory> productCategoryList= categoryService.findAll();

        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("/category/list",map);

    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                             Map<String, Object> map){

        if (categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }

        return new ModelAndView("/category/index",map);


    }

    /**
     * 修改/新增
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){

        if (bindingResult.hasErrors()){
           map.put("msg",bindingResult.getFieldError().getDefaultMessage());
           map.put("url","sell/seller/category/index");
           return new ModelAndView("/common/error",map);
        }

        ProductCategory productCategory = new ProductCategory();
        try{
            if (categoryForm.getCategoryId() != null){
                productCategory = categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
        }catch(SellException e){
            map.put("msg",e.getMessage());
            map.put("url","sell/seller/category/index");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("/common/success",map);


    }
}
