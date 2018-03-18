package com.company.fastsbapi.service.impl;

import com.company.fastsbapi.dataobject.model.Product;
import com.company.fastsbapi.dataobject.ro.ProductAddRO;
import com.company.fastsbapi.dataobject.ro.ProductUpdateRO;
import com.company.fastsbapi.repository.ProductRespository;
import com.company.fastsbapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:50 2018/3/16
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRespository productRespository;


    @Override
    public void addProduct(ProductAddRO productAddRO) {
        Product product = new Product();
        BeanUtils.copyProperties(productAddRO, product);
        productRespository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        Product product = productRespository.getOne(id);
        if (product != null) {
            productRespository.delete(id);
        }
    }

    @Override
    public void updateProduct(ProductUpdateRO productUpdateRO) {
        Product product = productRespository.getOne(productUpdateRO.getId());
        BeanUtils.copyProperties(productUpdateRO, product);
        productRespository.save(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return productRespository.getOne(id);
    }

    @Override
    public Page<Product> findProductPage(PageRequest pageRequest) {
        return productRespository.findAll(pageRequest);
    }
}
