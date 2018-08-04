package com.sunda.sell.service.impl;

import com.sunda.sell.dataObject.SellerInfo;
import com.sunda.sell.repository.SellerInfoRepository;
import com.sunda.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 老蹄子 on 2018/8/4 下午9:49
 */
@Service
public class SellerServiceImpl implements SellerService {


    @Autowired
    private SellerInfoRepository repository;


    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
