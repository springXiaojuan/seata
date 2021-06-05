package com.xxj.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxj.entity.product.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 10:13 上午
 */
public interface ProductMapper extends BaseMapper<Product> {

    @Update("update product set stock = stock - ${productCnt} where id = ${productId} and stock >= 0")
    Integer reduceStock(@Param("productId") Integer productId,@Param("productCnt")  Integer productCnt);
}
