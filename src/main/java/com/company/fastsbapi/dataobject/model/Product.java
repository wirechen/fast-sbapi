package com.company.fastsbapi.dataobject.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: WireChen
 * @Date: Created in 下午5:42 2018/3/16
 * @Description:
 */
@Entity
@DynamicUpdate
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String productName;

    private String description;

    private Date createTime;

    private Date updateTime;
}
