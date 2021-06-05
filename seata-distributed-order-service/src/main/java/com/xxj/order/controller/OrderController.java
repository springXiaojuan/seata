package com.xxj.order.controller;

import com.xxj.order.service.order.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:16 上午
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/createOrder/{productId}/{userId}")
    public String createOrder(@PathVariable("productId") Integer productId,@PathVariable("userId") Integer userId) throws Exception{
        log.info("[createOrder]下单商品{},操作人员{}",productId,userId);
        return ordersService.createOrder(productId,userId);

    }


}
