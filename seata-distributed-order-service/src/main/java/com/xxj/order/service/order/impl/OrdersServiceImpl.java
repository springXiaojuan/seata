package com.xxj.order.service.order.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxj.entity.order.Orders;
import com.xxj.entity.product.Product;

import com.xxj.fegin.AccountFeginService;
import com.xxj.fegin.ProductFeginService;
import com.xxj.order.mapper.OrdersMapper;
import com.xxj.order.service.order.OrdersService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:07 上午
 */
@Service

public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    @Autowired
    private AccountFeginService accountFeginService;

    @Autowired
    private ProductFeginService productFeginService;

    @Override
    @GlobalTransactional
    public String createOrder(Integer productId, Integer userId) throws Exception {
        Integer productCnt = 1;

//        // 减库存
        Product product = productFeginService.reduceStock(productId, productCnt);
        // 减余额
        accountFeginService.reduceAccount(userId,product.getPrice());

//         创建订单
        Orders orders = new Orders().setProductId(productId)
                .setUserId(userId)
                .setPayAmount(product.getPrice().multiply(new BigDecimal(productCnt)));

        this.save(orders);

//        int i = 10 / 0;

        return orders.toString();
    }




}
