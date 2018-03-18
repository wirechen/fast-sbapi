package com.company.fastsbapi.dataobject.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: WireChen
 * @Date: Created in 上午11:13 2018/2/24
 * @Description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -3032060746893382446L;

    // 错误码
    private Integer code;

    // 提示信息
    private String msg;

    // 具体内容
    private T data;
}
