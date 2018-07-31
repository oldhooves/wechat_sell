package com.sunda.sell.repository;

import com.sunda.sell.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 老蹄子 on 2018/7/31 下午6:24
 */
public interface ProductInfoRepository  extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);
}
