drop table if exists ${tableName};
create table ${tableName} (
id int not null auto_increment,
<#list modelFieldList as field>
${field.sqlName} ${field.sqlType}<#if field.isNull != "1"> not null</#if><#if field.comment != ""> comment '${field.comment}'</#if>,
</#list>
create_time timestamp not null default current_timestamp comment '创建时间',
update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
primary key (id)
)