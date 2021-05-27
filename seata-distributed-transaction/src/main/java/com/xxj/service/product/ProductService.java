package com.xxj.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxj.entity.product.Product;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:11 上午
 */
public interface ProductService  extends IService<Product> {


    Product reduceStock(Integer productId,Integer productCnt) throws Exception;

}
