package com.sasa.sell.service;

import com.sasa.sell.dataobject.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
