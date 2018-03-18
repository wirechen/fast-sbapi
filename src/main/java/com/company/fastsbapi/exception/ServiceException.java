package com.company.fastsbapi.exception;

import com.company.fastsbapi.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: WireChen
 * @Date: Created in 下午10:26 2018/2/23
 * @Description: 业务异常类
 */
@Getter
public class ServiceException extends RuntimeException{

    private Integer code;

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
