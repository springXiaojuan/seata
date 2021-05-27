package com.xxj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuxiaojuan
 * @date 2021/5/27 11:04 上午
 */

@SpringBootApplication
@MapperScan("com.xxj.mapper")
public class TransactionApplication  {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class,args);
    }
}
