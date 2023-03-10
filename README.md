# template

## Start
```shell
make run
```
or
```shell
./gradlew bootRun
```
## Local DB
### start
```shell
make dbup
```
### down
```shell
make dbdown
```

# Structure
![img.png](source/structure.png)

Api
+ 实现Controller,定义Api接口
+ 依赖关系: application

Application
+ application调用多个domain服务
+ DTO:一个不含业务逻辑的简单对象,用于api层与application层间的数据传输
+ 依赖关系: domain

Domain:
+ 实现领域业务逻辑
+ Command:作为数据实体对象，用于application与domain间传输，Model实现聚合根
+ 定义Repository Interface
+ 不依赖其他层

Infrastructure：
+ 通过对第三方库的集成或抽象,来满足其它层的非核心业务逻辑的实现
+ 实现domain层里的接口，向domain提供服务
+ 依赖关系: domain

Common:
+ common层提供工具，集中处理异常和错误
+ 依赖关系: 所有层都可依赖common

## Swagger
http://localhost:8080/swagger-ui/