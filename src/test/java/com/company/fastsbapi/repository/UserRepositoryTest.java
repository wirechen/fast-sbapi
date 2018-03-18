package com.company.fastsbapi.repository;

import com.company.fastsbapi.FastSbapiApplicationTests;
import com.company.fastsbapi.dataobject.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryTest extends FastSbapiApplicationTests{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        Assert.assertNotNull(userList);

    }

}