package com.xxj.product.controller;

import com.xxj.entity.product.Product;
import com.xxj.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 5:16 下午
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/reduceStock/{productId}/{productCnt}")
    Product reduceStock(@PathVariable("productId") Integer productId, @PathVariable("productCnt")Integer productCnt) throws Exception {
       return productService.reduceStock(productId,productCnt);
    }

}
