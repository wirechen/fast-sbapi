package com.company.fastsbapi.utils;

import com.company.fastsbapi.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @Author: WireChen
 * @Date: Created in 下午6:01 2018/3/16
 * @Description: 请求对象验证工具
 */
public class ROValidUtil {

    public static void valid(BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                throw new ServiceException(1, error.getDefaultMessage());
            }
        }
    }
}
