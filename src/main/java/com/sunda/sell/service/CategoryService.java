package com.sunda.sell.service;

import com.sunda.sell.dataObject.ProductCategory;

import java.util.List;

/**
 * Created by 老蹄子 on 2018/7/31 下午5:18
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
