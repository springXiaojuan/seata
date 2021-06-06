# 说明
该项目主要为了测试seata 在不同数据库之间操作数据，产生异常时进行回滚


#配置操作（单机）
 1. 配置文件(application.properties)

- 开启与seata的集成
`spring.datasource.dynamic.seata=true`
- 配置seata的应用编号，事务组编号，虚拟组与分组的映射，分组和seata服务的映射
   
   - seata的应用编号
   
   `seata.application-id=${spring.application.name}`
   - seata的事务组编号，用于TC集群名
   `seata.tx-service-group=${seata.application-id}-group`
   - 虚拟组和分组的映射
   `seata.service.vgroup-mapping.seata-distributed-transaction-group=default`
   - 分组和seata服务的映射
    `seata.service.grouplist.default=x:8091`
  
 2. 类或方法
- 在需要回滚的方法和类上加上`@GlobalTransactional`

# 配置操作（集群（nacos））
1. 由于不是spring-cloud项目，所以先引入nacos的客户端
```
        <dependency>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
        </dependency>
```
2. 配置文件

- 开启对seata的集成
 ` spring.datasource.dynamic.seata=true`
 
- 配置seata的应用编号，事务组编号，虚拟组与分组的映射和nacos注册服务

    - seata的应用编号
    
    `seata.application-id=${spring.application.name}`
    - seata的事务组编号，用于TC集群名
    
    `seata.tx-service-group=${seata.application-id}-group`
    - 虚拟组和分组的映射
    
    `seata.service.vgroup-mapping.seata-distributed-transaction-group=default
`
    - 注册到nacos上需要的配置
    `
    seata.enabled=true
    seata.registry.type=nacos
    seata.registry.nacos.application=seata-server
    seata.registry.nacos.group=SEATA_GROUP
    seata.registry.nacos.cluster=default
    seata.registry.nacos.server-addr=x:80
    `
    
3. 类或方法
 - 在需要回滚的方法和类上加上`@GlobalTransactional`
