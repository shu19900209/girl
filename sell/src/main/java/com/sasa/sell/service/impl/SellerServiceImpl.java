package com.sasa.sell.service.impl;

import com.sasa.sell.dataobject.SellerInfo;
import com.sasa.sell.enums.ResultEnum;
import com.sasa.sell.exception.SellException;
import com.sasa.sell.repository.SellerInfoRepository;
import com.sasa.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {

        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        return  sellerInfo;
    }
}
