package com.xxj.mapper.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxj.entity.account.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;


/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:06 上午
 */
public interface AccountMapper extends BaseMapper<Account> {

    @Update("update account set balance = balance - ${money} where user_id = ${userId} and balance >= 0")
    Integer reduceBalance(@Param("userId") Integer userId,@Param("money")  BigDecimal money) ;
}
