package com.company.fastsbapi.service;

import com.company.fastsbapi.dataobject.model.Product;
import com.company.fastsbapi.dataobject.ro.ProductAddRO;
import com.company.fastsbapi.dataobject.ro.ProductUpdateRO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest; /**
 * @Author: WireChen
 * @Date: Created in 下午5:50 2018/3/16
 * @Description:
 */
public interface ProductService {

    void addProduct(ProductAddRO productAddRO);

    void deleteProductById(Integer id);

    void updateProduct(ProductUpdateRO productUpdateRO);

    Product findProductById(Integer id);

    Page<Product> findProductPage(PageRequest pageRequest);
}
