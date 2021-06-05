package com.xxj.entity.order;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author xuxiaojuan
 * @date 2021/5/26 6:31 下午
 */
@Getter
@Setter
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Integer userId;

    private Integer productId;

    private BigDecimal payAmount;

    private Timestamp addTime;

    private Timestamp updateTime;
}
