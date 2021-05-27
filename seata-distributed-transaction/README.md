# 说明
该项目主要为了测试seata 在不同数据库之间操作数据，产生异常时进行回滚


#配置操作
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
    `seata.service.grouplist.default=39.101.163.179:8091`
  
 2. 类或方法
- 在需要回滚的方法和类上加上`@GlobalTransactional`