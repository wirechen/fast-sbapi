package com.company.fastsbapi.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: WireChen
 * @Date: Created in 下午12:15 2018/2/24
 * @Description: beanList的转换器
 */
public class ListBeanConvertUtil {

    public static <T,V extends Object> T convert(V v, Class<T> classT){
        T t = null;
        try {
            t = classT.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(v, t);
        return t;
    }

    public static <T,V extends Object> List<T> convert(List<V> vList, Class<T> classT) {
        return vList.stream().map(e ->
                convert(e, classT)
        ).collect(Collectors.toList());
    }
}
