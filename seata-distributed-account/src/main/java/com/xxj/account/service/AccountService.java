package com.xxj.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxj.entity.account.Account;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:01 上午
 */
public interface AccountService  extends IService<Account> {

    void reduceAccount(Integer userId, BigDecimal price) throws Exception;

}
