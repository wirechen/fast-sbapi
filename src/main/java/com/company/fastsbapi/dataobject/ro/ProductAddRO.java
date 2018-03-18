package com.company.fastsbapi.dataobject.ro;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:52 2018/3/16
 * @Description:
 */
@Data
public class ProductAddRO {

    @NotNull(message = "产品名称不能为空")
    private String productName;

    private String description;

}
