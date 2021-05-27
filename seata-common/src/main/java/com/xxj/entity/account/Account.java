package com.xxj.entity.account;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuxiaojuan
 * @date 2021/5/26 6:32 下午
 */
@Getter
@Setter
@Accessors(chain = true)
public class Account {
    private Integer id;

    private Integer userId;

    private BigDecimal balance;

    private Date updateTime;
}
