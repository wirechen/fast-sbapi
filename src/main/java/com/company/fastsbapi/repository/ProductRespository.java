package com.company.fastsbapi.repository;

import com.company.fastsbapi.dataobject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:48 2018/3/16
 * @Description:
 */
public interface ProductRespository extends JpaRepository<Product, Integer>{
}
