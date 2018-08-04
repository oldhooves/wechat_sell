package com.sunda.sell.repository;

import com.sunda.sell.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 老蹄子 on 2018/8/4 下午9:28
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
