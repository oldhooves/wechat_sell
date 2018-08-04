package com.sunda.sell.service;

import com.sunda.sell.dataObject.SellerInfo;

/**
 * Created by 老蹄子 on 2018/8/4 下午9:48
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
