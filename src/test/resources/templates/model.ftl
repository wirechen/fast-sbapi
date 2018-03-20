package ${basePackage}.dataobject.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@DynamicUpdate
@Data
@Table(name = "${tableName}")
public class ${modelName} {

    @Id
    @GeneratedValue
    private Integer id;
<#list modelFieldList as field>

    private ${field.fieldType} ${field.fieldName};
</#list>

    private Date createTime;

    private Date updateTime;

}