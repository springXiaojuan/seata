package com.xxj.service.account.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxj.entity.account.Account;
import com.xxj.mapper.account.AccountMapper;
import com.xxj.service.account.AccountService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:05 上午
 */
@Service
@Slf4j
@DS("accountdb")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public synchronized void reduceAccount(Integer userId, BigDecimal price) throws Exception {

        Account account = this.getById(userId);

//        if (account.getBalance().compareTo(price)  <= -1) {
//           return;
//        }

        Integer integer = accountMapper.reduceBalance(userId, price);
        if(integer == 0) {
            throw new Exception("余额不足");
        }
        log.info("扣除用户{} 余额成功",userId);

//        int i = 10 / 0;
    }

    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("AccountServiceImpl reduceAccount 扣减余额 提交成功！");
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized boolean cancelTcc(BusinessActionContext context) {
        log.info("AccountServiceImpl reduceAccount 取消扣减余额 提交成功！");
        Integer userId = (Integer)context.getActionContext("userId");
        BigDecimal price = (BigDecimal)context.getActionContext("price");

        Integer integer = accountMapper.increaseAccount(userId, price);
        if(integer == 1){
            return true;
        }
        return false;
    }
}
