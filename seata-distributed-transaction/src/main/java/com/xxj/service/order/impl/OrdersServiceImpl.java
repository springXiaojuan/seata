package com.xxj.service.order.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxj.entity.order.Orders;
import com.xxj.entity.product.Product;
import com.xxj.mapper.orders.OrdersMapper;
import com.xxj.service.account.AccountService;
import com.xxj.service.order.OrdersService;
import com.xxj.service.product.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:07 上午
 */
@Service
@DS("orderdb")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Override
    @GlobalTransactional
    public String createOrder(Integer productId, Integer userId) throws Exception {
        Integer productCnt = 1;

        // 减库存
        Product product = productService.reduceStock(productId, productCnt);
        // 减余额
        accountService.reduceAccount(userId,product.getPrice());

        // 创建订单
        Orders orders = new Orders().setProductId(productId)
                .setUserId(userId)
                .setPayAmount(product.getPrice().multiply(new BigDecimal(productCnt)));

        this.save(orders);

//        int i = 10 / 0;

        return orders.toString();
    }




}
