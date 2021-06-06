package com.xxj.service.account;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxj.entity.account.Account;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:01 上午
 */
@LocalTCC
public interface AccountService  extends IService<Account> {


    /**
     * 扣除余额
     * 定义两阶段提交
     * name = reduceStock为一阶段try方法
     * commitMethod = commitTcc 为二阶段确认方法
     * rollbackMethod = cancel 为二阶段取消方法
     * BusinessActionContextParameter注解 可传递参数到二阶段方法
     *
     * @param userId 用户ID
     * @param price  扣减金额
     * @throws Exception 失败时抛出异常
     */
    @TwoPhaseBusinessAction(name = "reduceAccount",commitMethod = "commitTcc", rollbackMethod = "cancelTcc")
    void reduceAccount(@BusinessActionContextParameter(paramName = "userId") Integer userId,
                       @BusinessActionContextParameter(paramName = "price") BigDecimal price) throws Exception;


    /**
     * 确认方法、可以另命名，但要保证与commitMethod一致
     * context可以传递try方法的参数
     *
     * @param context 上下文
     * @return boolean
     */
    boolean commitTcc(BusinessActionContext context);

    /**
     * 二阶段取消方法
     *
     * @param context 上下文
     * @return boolean
     */
    boolean cancelTcc(BusinessActionContext context);
}
