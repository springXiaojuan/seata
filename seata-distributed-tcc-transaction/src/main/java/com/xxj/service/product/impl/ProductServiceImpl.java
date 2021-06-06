package com.xxj.service.product.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxj.entity.product.Product;
import com.xxj.mapper.produc.ProductMapper;
import com.xxj.service.product.ProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:12 上午
 */
@Service
@DS("productdb")
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public synchronized Product reduceStock(Integer productId, Integer productCnt) throws Exception {

        Product product = this.getById(productId);
//        if(product.getStock() < productCnt) {
//            throw new Exception("库存不足");
//        }
        Integer integer = productMapper.reduceStock(productId, productCnt);
        if (integer == 1) {
            log.info("库存扣减成功");
            return product;
        }
        throw new Exception("库存不足，扣减失败");
    }

    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("ProductServiceImpl reduceStock减库存 提交成功");
        return true;
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean cancelTcc(BusinessActionContext context) {
        log.info("ProductServiceImpl reduceStock 回滚库存 ");
        Integer productId = (Integer) context.getActionContext("productId");
        Integer productCnt = (Integer) context.getActionContext("productCnt");

        Integer integer = productMapper.increaseStock(productId, productCnt);
        if(integer == 1) {
            return true;
        }
        return false;
    }
}
