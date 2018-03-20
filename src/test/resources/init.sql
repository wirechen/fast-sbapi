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

