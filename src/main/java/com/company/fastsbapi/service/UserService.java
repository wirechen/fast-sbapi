package com.company.fastsbapi.service;

import com.company.fastsbapi.dataobject.model.User;
import com.company.fastsbapi.dataobject.ro.UserLoginRO;
import com.company.fastsbapi.dataobject.ro.UserRegisterRO;

import java.util.Map;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:15 2018/3/16
 * @Description:
 */
public interface UserService {

    // 管理员登录
    Boolean adminUserLogin(UserLoginRO userLoginRO);

    // 普通用户登录
    Map<String,Object> normalUserLogin(UserLoginRO userLoginRO);

    // 用户注册
    User normalUserRegister(UserRegisterRO userRegisterRO);
}
