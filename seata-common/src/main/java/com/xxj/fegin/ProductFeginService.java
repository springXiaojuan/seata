package com.xxj.fegin;

import com.xxj.entity.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author xuxiaojuan
 * @date 2021/5/27 5:07 下午
 */
@FeignClient(value = "seata-distributed-product-service")
public interface ProductFeginService {

    @PostMapping("/reduceStock/{productId}/{productCnt}")
    Product reduceStock(@PathVariable("productId") Integer productId, @PathVariable("productCnt")Integer productCnt);

}
