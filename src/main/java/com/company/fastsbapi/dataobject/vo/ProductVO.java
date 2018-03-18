package com.company.fastsbapi.dataobject.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: WireChen
 * @Date: Created in 下午6:08 2018/3/16
 * @Description:
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -3065928608195572953L;

    private Integer id;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("desc")
    private String description;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
