package com.sasa.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
    private String mchId;
    private String mchKey;
    private String keyPath;

    //微信支付 异步通知地址
    private String notifyUrl;

    //开发平台id
    private String openAppId;
    //开发平台密钥
    private String openAppSecret;

}
