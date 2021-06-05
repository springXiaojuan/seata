package com.xxj.account.controller;

import com.xxj.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 5:14 下午
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/reduceAccount/{userId}/{price}")
    public void reduceAccount(@PathVariable("userId") Integer userId, @PathVariable("price") BigDecimal price) throws Exception{
        accountService.reduceAccount(userId,price);
    }


}
