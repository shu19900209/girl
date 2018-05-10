package com.sasa.sell.controller;

import com.sasa.sell.config.ProjectUrlConfig;
import com.sasa.sell.constant.CookieConstant;
import com.sasa.sell.constant.RedisConstant;
import com.sasa.sell.dataobject.SellerInfo;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.service.SellerService;
import com.sasa.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map){

        //openid先去数据库匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg",ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("/common/error",map);
        }
        //设置token到redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token), openid, expire, TimeUnit.SECONDS);
        //设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell() +"/sell/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Map<String, Object> map){
        //从cookie里查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie != null){
            //清除cookies
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清除redis
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("/common/success",map);


    }
}
