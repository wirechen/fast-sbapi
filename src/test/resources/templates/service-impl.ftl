package ${basePackage}.service.impl;

import ${basePackage}.dataobject.model.${modelNameUpperCamel};
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}AddRO;
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}UpdateRO;
import ${basePackage}.repository.${modelNameUpperCamel}Repository;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.enums.ResultEnum;
import ${basePackage}.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* Created by ${author} on ${date}.
*/
@Service
@Transactional
@Slf4j
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {

    @Autowired
    private ${modelNameUpperCamel}Repository ${modelNameLowerCamel}Repository;

    @Override
    public void add${modelNameUpperCamel}(${modelNameUpperCamel}AddRO ${modelNameLowerCamel}AddRO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = new ${modelNameUpperCamel}();
        BeanUtils.copyProperties(${modelNameLowerCamel}AddRO, ${modelNameLowerCamel});
        ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel});
    }

    @Override
    public void delete${modelNameUpperCamel}(Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Repository.getOne(id);
        if (${modelNameLowerCamel} == null) {
            log.error("【delete${modelNameUpperCamel}出错】id不存在,id:{}", id);
            throw new ServiceException(ResultEnum.ERROR_ID);
        }
        ${modelNameLowerCamel}Repository.delete(id);

    }

    @Override
    public void update${modelNameUpperCamel}(${modelNameUpperCamel}UpdateRO ${modelNameLowerCamel}UpdateRO) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Repository.findOne(${modelNameLowerCamel}UpdateRO.getId());
        if (${modelNameLowerCamel} == null) {
            log.error("【update${modelNameUpperCamel}出错】id不存在,id:{}", ${modelNameLowerCamel}UpdateRO.getId());
            throw new ServiceException(ResultEnum.ERROR_ID);
        }
        BeanUtils.copyProperties(${modelNameLowerCamel}UpdateRO, ${modelNameLowerCamel});
        ${modelNameLowerCamel}Repository.save(${modelNameLowerCamel});
    }

    @Override
    public ${modelNameUpperCamel} find${modelNameUpperCamel}(Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Repository.findOne(id);
        if (${modelNameLowerCamel} == null) {
            log.error("【find${modelNameUpperCamel}出错】id不存在,id:{}", id);
            throw new ServiceException(ResultEnum.ERROR_ID);
        }
        return ${modelNameLowerCamel};
    }

    @Override
    public Page<${modelNameUpperCamel}> find${modelNameUpperCamel}Page(PageRequest pageRequest) {
        return ${modelNameLowerCamel}Repository.findAll(pageRequest);
    }

}