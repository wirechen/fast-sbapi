package ${basePackage}.dataobject.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
<#list modelFieldList as field>
    <#if field.fieldType == "BigDecimal">
import java.math.BigDecimal;
        <#break>
    </#if>
</#list>

/**
* Created by ${author} on ${date}.
*/
@Data
public class ${modelName}VO implements Serializable{

    private static final long serialVersionUID = ${serialVersionUID}L;

    private Integer id;
<#list modelFieldList as field>

    @JsonProperty("${field.sqlName}")
    private ${field.fieldType} ${field.fieldName};
</#list>

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("create_time")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("update_time")
    private Date updateTime;

}