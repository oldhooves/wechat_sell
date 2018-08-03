package com.sunda.sell.controller;

import com.sunda.sell.dataObject.ProductCategory;
import com.sunda.sell.dataObject.ProductInfo;
import com.sunda.sell.exception.SellException;
import com.sunda.sell.form.ProductForm;
import com.sunda.sell.service.CategoryService;
import com.sunda.sell.service.ProductService;
import com.sunda.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by 老蹄子 on 2018/8/3 下午5:46
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);

        return new ModelAndView("product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        map.put("msg","上架成功");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        map.put("msg","下架成功");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                              Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("categoryList",productCategoryList);
        return new ModelAndView("product/index",map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else {
                form.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url","/sell/seller/product/list");
        map.put("msg","保存成功");
        return new ModelAndView("common/success",map);
    }
}
