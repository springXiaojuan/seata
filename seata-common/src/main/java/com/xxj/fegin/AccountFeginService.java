package com.xxj.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 5:07 下午
 */
@FeignClient(name = "seata-distributed-account-service")
public interface AccountFeginService {

    @PostMapping("/reduceAccount/{userId}/{price}")
    void reduceAccount(@PathVariable("userId") Integer userId, @PathVariable("price") BigDecimal price);

}
