package com.sunda.sell.service;

import com.sunda.sell.dataObject.ProductInfo;
import com.sunda.sell.dto.CartDto;
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

    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
