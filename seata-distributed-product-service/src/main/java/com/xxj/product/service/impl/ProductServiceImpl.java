package com.xxj.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxj.entity.product.Product;
import com.xxj.product.mapper.ProductMapper;
import com.xxj.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:12 上午
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product reduceStock(Integer productId, Integer productCnt) throws Exception {

        Product product = this.getById(productId);
        if(product.getStock() < productCnt) {
            throw new Exception("库存不足");
        }
        Integer integer = productMapper.reduceStock(productId, productCnt);
        if (integer == 1) {
            log.info("库存扣减成功");
            return product;
        }
        throw new Exception("库存不足，扣减失败");
    }
}
