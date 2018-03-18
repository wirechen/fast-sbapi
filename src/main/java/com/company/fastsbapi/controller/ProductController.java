package com.company.fastsbapi.controller;

import com.company.fastsbapi.dataobject.model.Product;
import com.company.fastsbapi.dataobject.ro.ProductAddRO;
import com.company.fastsbapi.dataobject.ro.ProductUpdateRO;
import com.company.fastsbapi.dataobject.vo.ProductVO;
import com.company.fastsbapi.dataobject.vo.ResultVO;
import com.company.fastsbapi.service.ProductService;
import com.company.fastsbapi.utils.ListBeanConvertUtil;
import com.company.fastsbapi.utils.ROValidUtil;
import com.company.fastsbapi.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:45 2018/3/16
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResultVO add(@Valid @RequestBody ProductAddRO productAddRO, BindingResult result) {
        ROValidUtil.valid(result);
        productService.addProduct(productAddRO);
        return ResultVOUtil.returnSuccess();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return ResultVOUtil.returnSuccess();
    }

    @PutMapping
    public ResultVO update(@RequestBody ProductUpdateRO productUpdateRO) {
        productService.updateProduct(productUpdateRO);
        return ResultVOUtil.returnSuccess();
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable Integer id) {
        Product product = productService.findProductById(id);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);
        return ResultVOUtil.returnSuccess("product", productVO);
    }

    @GetMapping
    public ResultVO list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Product> productPage = productService.findProductPage(pageRequest);
        List<Product> productList = productPage.getContent();
        List<ProductVO> productVOList = ListBeanConvertUtil.convert(productList, ProductVO.class);
        return ResultVOUtil.returnSuccess("productList", productVOList);
    }
}
