package com.sunda.sell.service;

import com.sunda.sell.dataObject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 老蹄子 on 2018/7/31 下午7:14
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //TODO 加库存

    //TODO 减库存
}
