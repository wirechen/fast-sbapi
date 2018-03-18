package com.company.fastsbapi.dataobject.ro;

import lombok.Data;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:58 2018/3/16
 * @Description:
 */
@Data
public class ProductUpdateRO {

    private Integer id;

    private String description; //只能修改描述不允许用户修改其他属性
}
