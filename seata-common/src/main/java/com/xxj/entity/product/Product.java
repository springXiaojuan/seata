package com.xxj.entity.product;



import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xuxiaojuan
 * @date 2021/5/26 6:33 下午
 */
@Getter
@Setter
@Accessors(chain = true)
public class Product {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Date addTime;

    private Date updateTime;

}
