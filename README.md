## 简介  
FastSbAPI主要是基于SpringBoot + SpringDataJPA打造的快速构建Restul API的项目。目前发布1.0版本，该仓库长期维护，也欢迎大家提出[issue](https://github.com/wirechen/fast-sbapi/issues)或提交[pr](https://github.com/wirechen/fast-sbapi/pulls)，旨在为Java开发API的大众提供一个不错的选择。  

## 功能  
> 已实现
* ***通过可视化脚手架一键生成SQL脚本、dataobject、repository、service及controller***
* 最具Java风格的项目结构、配置文件、精简的POM
* 统一响应结果封装  
* 统一异常处理
* 简单的普通用户登录注册
* 基于[JWT](https://www.jianshu.com/p/576dbf44b2ae)的接口权限认证
* 基于logback的生产环境日志按天滚动记录
* 常用基础方法抽象封装
> TODO
* 集成swagger，自动生成API文档
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


## 数据传输模型
这里主要对dataobject的各个object的转化与传输做了一个模型。既然选择了用java开发API，那么我们必然是看中了java的严谨与高可维护性，java向来也不是快速开发的主导，项目中各种object的之间的相互转换看似繁琐复杂，但不可否认的是这样做为后期的维护与扩展打下了良好的基础。  
![](http://wirechen-github.oss-cn-beijing.aliyuncs.com/readme/fast-api-dataobject.png?Expires=1521421872&OSSAccessKeyId=TMP.AQEjvPPc0N1o1J50rCQq6MWamrSjARke6FgL0OdTuoqoj3L8w-hwDXBvx8dZADAtAhRKEzs0CZFGtkj_7d2Iwkj8V78n7AIVANOD5SzujwIzPqkT-m4Eh0-08fe0&Signature=SrRef4eSm5dEvcJsaRb8sU2RnXQ%3D) 
客户端请求接口时传输RO(request object)，返回结果时接受VO(view object)，二者在HTTP传输过程中为json格式
* Controller层对RO进行转化（根据业务需求转为DTO或者直接转为Model)  
* Service层将DTO转为Model并传输给Repository层  
* Repository层作为数据的仓库必然只能与Model打交道。响应时Controller层应该对DTO或者Model做VO的转化，VO可以满足前端各种不同的格式的需求，Model层应该与数据库表严格一一对应

## 开发环境
* JDK 8
* Maven 3
* Mysql 5.7

## 快速开始
1. 本项目使用了[lombok](http://blog.csdn.net/v2sking/article/details/73431364),所以请确保自己编译器安装了该插件
2. 克隆项目
3. 执行初始化脚本
```
create database fastsbapiDB;
use fastsbapiDB;
drop table if EXISTS user;
create table user (
	id int not null auto_increment,
	username varchar(100) not null comment '用户名',
	password varchar(100) not null comment '登录密码',
  email varchar(100) comment '联系邮箱',
	phone varchar(20) comment '联系手机',
	role tinyint(1) not null comment '用户角色, 0管理员/1普通用户',
	create_time timestamp not null default current_timestamp comment '创建时间',
	update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (id)
) comment '用户表';
insert into user values(1,'管理员','123456','','13344445555',0,'2018-03-16 16:10:35','2018-03-16 16:10:39');
```
4. 启动项目,浏览器打开`http://localhost:8080/api/v1/code/index`根据提示生成代码,具体操作请看首页视频
5. 注意：如果运用到生产环境请将logback-spring.xml的注释全部去掉并保证有相应的日志文件生成目录和写文件的权限

## End
希望大家 [Star](https://github.com/wirechen/fast-sbapi/stargazers) & [Fork](https://github.com/wirechen/fast-sbapi/network) 给予支持。

