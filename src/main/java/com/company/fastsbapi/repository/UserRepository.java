package com.company.fastsbapi.repository;

import com.company.fastsbapi.dataobject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: WireChen
 * @Date: Created in 下午4:03 2018/3/16
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPasswordAndRole(String username, String password, Integer role);

    User findByUsernameAndRole(String username, Integer role);
}
