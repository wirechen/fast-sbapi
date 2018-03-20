package ${basePackage}.dataobject.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
<#list modelFieldList as field>
    <#if field.isNull != "">
import javax.validation.constraints.NotNull;
        <#break>
    </#if>
</#list>
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
public class ${modelName}UpdateRO {

    private Integer id;
<#list modelFieldList as field>

    <#if field.isNull != "">
    @NotNull(message = "${field.sqlName}不能为空")
    </#if>
    @JsonProperty("${field.sqlName}")
    private ${field.fieldType} ${field.fieldName};
</#list>

}