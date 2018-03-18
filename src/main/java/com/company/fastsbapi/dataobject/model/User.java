package com.company.fastsbapi.dataobject.model;

import com.company.fastsbapi.enums.UserRoleEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: WireChen
 * @Date: Created in 下午3:41 2018/3/16
 * @Description:
 */
@Entity
@DynamicUpdate
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    //0 超级管理员  1 普通用户
    private Integer role = UserRoleEnum.NORMAL.getCode();

    private Date createTime;

    private Date updateTime;

}
