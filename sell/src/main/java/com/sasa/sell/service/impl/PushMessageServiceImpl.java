package com.sasa.sell.service.impl;

import com.sasa.sell.dto.OrderDTO;
import com.sasa.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;


    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();

        List<WxMpTemplateData> data = Arrays.asList(
            new WxMpTemplateData("first", "亲，请记得收货。 ")
        );
        templateMessage.setTemplateId("");//模版ID
        templateMessage.setToUser("");//微信用户openid
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch(WxErrorException e){
            log.error("【微信模版消息】发送失败,{}",e);

        }
    }
}
