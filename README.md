## 简介  
FastSbAPI主要是基于SpringBoot + SpringDataJPA打造的快速构建Restul API的项目，借鉴了另一个优秀的开源项目：[spring-boot-api-project-seed](https://github.com/lihengming/spring-boot-api-project-seed)，增加了可视化脚手架，持久层用的SpringDataJPA，让用Java快速开发API不失为一个不错的选择，欢迎大家提出[issue](https://github.com/wirechen/fast-sbapi/issues)或提交[pr](https://github.com/wirechen/fast-sbapi/pulls)。请先花4分钟观看我用心录制的视频哦↓
## 使用教程[[完整版](http://v.youku.com/v_show/id_XMzQ3ODkxMTYwMA==.html?spm=a2h3j.8428770.3416059.1)]
![FastSb使用教程](http://wirechen-image.test.upcdn.net/blog/8dc526346097d2582c5634a8c130ecb5.png)

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
* 集成swagger，生成API的同时自动生成API文档

## 项目结构  
<img src="http://wirechen-image.test.upcdn.net/blog/a50366019efaaae0f560f720dbec1364.png" width=400 height=520 />

- aspect: AOP切面类
- config: 项目配置
- controller: API路由控制层
- dataobject: javebean对象
    * dto: data transfer object数据传输对象(在controller-service-repository三层之间传输)  
    * model: 数据持久模型(对应数据库字段)
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
这里主要对dataobject的各个object间的转换与传输做了一个模型。既然选择了用java开发API，那么我们必然是看中了java的严谨与高可维护性，java向来也不是快速开发的主导，项目中各种object的之间的相互转换看似繁琐复杂，但不可否认的是这样做为后期的维护与扩展打下了良好的基础。  
![](http://wirechen-image.test.upcdn.net/blog/dfd13ec62e7ecfd3b335603dc9b4aa96.png) 
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
3. 执行初始化SQL脚本
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
4. 启动项目,浏览器打开`http://localhost:8080/api/v1/code/index`根据提示生成代码,具体操作请看视频教程

## End
希望大家 [Star](https://github.com/wirechen/fast-sbapi/stargazers) & [Fork](https://github.com/wirechen/fast-sbapi/network) 给予支持。  

---
============================更新============================

1. 我们公司的后台就是在该脚手架项目的基础上开发的，主要是给五个前端提供API服务（用户PC端、管理PC端、用户移动端、硬件设备端、小程序端），经过几个月的线上环境运行测试效果稳定。顺便展示一下我们公司后台的项目包结构和后台简易架构图吧。
<img src="http://wirechen-image.test.upcdn.net/blog/2118c254be305043e7d3ae0a3050dd3f.png" width="250" hegiht="100" align=center />
<img src="http://wirechen-image.test.upcdn.net/blog/a12a0cbcd6d2fa1fc4f926276b317178.png" align=center />  
2. 该项目可能我不会再更新了，因为它单纯作为一个自动生成API脚手架项目的话这里有一个更智能的替代品，那就是Spring推出的更好更快更“傻瓜”的API脚手架项目：Spring Data Rest。它可以让我们通过定义Model自动生成Restful风格的API，感兴趣的朋友可以去官方看一看，地址：https://projects.spring.io/spring-data-rest/

感谢大家的支持，我会继续坚持开源，坚持分享，猿类们我们一起共同进步吧！
