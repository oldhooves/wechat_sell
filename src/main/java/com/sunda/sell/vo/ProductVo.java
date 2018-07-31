package com.sunda.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * Created by 老蹄子 on 2018/7/31 下午8:34
 */
@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
