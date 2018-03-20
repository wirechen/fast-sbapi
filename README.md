## 简介  
fast-sbapi是基于纯Spring Boot框架打造的快速构建Resultul API的项目。目前发布1.0Beta版本,该仓库长期维护,也欢迎大家提出issue或提交pr,旨在为java开发api的大众提供一个不错的选择。  

## 功能  
> 已实现
* 最具java风格的项目结构、配置文件、精简的POM
* 统一响应结果封装  
* 统一异常处理
* 简单的普通用户登录注册
* 基于JWT的接口权限认证
* 基于logback的生产环境日志按天滚动记录
* 基于SpringDataJPA的常用CRUD方法示例
* 常用基础方法抽象封装
> TODO
* 基于模板一键生成SQL脚本、dataobject、repository、service及controller（重要）
* 集成swaggerAPI文档
* 升级Spring Boot2.0

## 项目结构  
<img src="http://wirechen-github.oss-cn-beijing.aliyuncs.com/readme/fast-api-tree.png?Expires=1521360645&OSSAccessKeyId=TMP.AQEjvPPc0N1o1J50rCQq6MWamrSjARke6FgL0OdTuoqoj3L8w-hwDXBvx8dZADAtAhRKEzs0CZFGtkj_7d2Iwkj8V78n7AIVANOD5SzujwIzPqkT-m4Eh0-08fe0&Signature=%2FPNWSIVgUfnavQ5X4EhUjj7RBEw%3D" width=400 height=520 />

- aspect: AOP切面类
- config: 项目配置
- controller: API路由控制层
- dataobject: javebean对象
    * dto: data transfer object数据传输对象(在controller-service-repository三层之间传输)
    * model: 数据持久模型(对象数据库字段)
    * ro: request object数据请求对象(前端传过来的数据的封装)
    * vo: view obejct视图对象(返回给前端数据的封装)
- enum: 枚举类
- exception: 自定义异常类
    * handler: 异常处理类
- repository: 数据仓库(采用的SpringDataJPA作为的数据持久层)
- service: 业务接口
    * impl: 业务实现
- utils: 项目工具集合


## 项目模型  
这里主要对dataobject的各个object的转化与传输做了一个模型。既然选择了用java开发API,那么我们必然是看中了java的严谨与高可维护性,java向来也不是快速开发的主导,项目中各种object的之间的相互转换看似繁琐复杂,但不可否认的是这样做为后期的维护与扩展打下了良好的基础。  
![](http://wirechen-github.oss-cn-beijing.aliyuncs.com/readme/fast-api-dataobject.png?Expires=1521421872&OSSAccessKeyId=TMP.AQEjvPPc0N1o1J50rCQq6MWamrSjARke6FgL0OdTuoqoj3L8w-hwDXBvx8dZADAtAhRKEzs0CZFGtkj_7d2Iwkj8V78n7AIVANOD5SzujwIzPqkT-m4Eh0-08fe0&Signature=SrRef4eSm5dEvcJsaRb8sU2RnXQ%3D)
前端请求接口时传输RO(request object),在Controller层对RO进行转化（根据业务需求转为DTO或者直接转为Model）,Service层将DTO转为Model并传输给Repository层,Repository层作为数据的仓库必然只能与Model打交道。响应时Controller层应该对DTO或者Model做VO(view object)的转化，VO可以满足前端各种不同的格式的需求,Model层应该与数据库表严格一一对应。

## 快速开始
1. 确保自己编译器安装了[lombok](http://blog.csdn.net/v2sking/article/details/73431364)插件
2. fork项目
3. 克隆项目
4. 执行test包下的init.sql(测试环境:Mysql5.7)
5. 启动FastSbapiApplication
6. 如果运用到生产环境请将logback-spring.xml的注释全部去掉并保证有相应的日志文件生成目录和写文件的权限

## Lisence  
无，纯粹开源分享，感谢大家 [Star](https://github.com/wirechen/fast-sbapi/stargazers) & [Fork](https://github.com/wirechen/fast-sbapi/network) 的支持。

