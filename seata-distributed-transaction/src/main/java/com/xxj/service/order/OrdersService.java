package com.xxj.service.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxj.entity.order.Orders;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:06 上午
 */
public interface OrdersService  extends IService<Orders> {

    String createOrder(Integer productId, Integer userId) throws Exception;
}
