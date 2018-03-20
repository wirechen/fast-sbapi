package ${basePackage}.service;

import ${basePackage}.dataobject.model.${modelNameUpperCamel};
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}AddRO;
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}UpdateRO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
* Created by ${author} on ${date}.
*/
public interface ${modelNameUpperCamel}Service {

    void add${modelNameUpperCamel}(${modelNameUpperCamel}AddRO ${modelNameLowerCamel}AddRO);

    void delete${modelNameUpperCamel}(Integer id);

    void update${modelNameUpperCamel}(${modelNameUpperCamel}UpdateRO ${modelNameLowerCamel}UpdateRO);

    ${modelNameUpperCamel} find${modelNameUpperCamel}(Integer id);

    Page<${modelNameUpperCamel}> find${modelNameUpperCamel}Page(PageRequest pageRequest);

}