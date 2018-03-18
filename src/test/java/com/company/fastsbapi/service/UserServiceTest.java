package com.company.fastsbapi.service;

import com.company.fastsbapi.FastSbapiApplicationTests;
import com.company.fastsbapi.dataobject.ro.UserRegisterRO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest extends FastSbapiApplicationTests{

    @Autowired
    UserService userService;

    @Test
    public void normalUserRegister() throws Exception {
        UserRegisterRO userRegisterRO = new UserRegisterRO();
        userRegisterRO.setUsername("张三");
        userRegisterRO.setPassword("1234568dasflasd8ya2asda");
        userService.normalUserRegister(userRegisterRO);
    }

}