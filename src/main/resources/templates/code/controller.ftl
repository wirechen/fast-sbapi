package ${basePackage}.controller;

import ${basePackage}.dataobject.model.${modelNameUpperCamel};
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}AddRO;
import ${basePackage}.dataobject.ro.${modelNameUpperCamel}UpdateRO;
import ${basePackage}.dataobject.vo.${modelNameUpperCamel}VO;
import ${basePackage}.dataobject.vo.ResultVO;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import ${basePackage}.utils.ListBeanConvertUtil;
import ${basePackage}.utils.ROValidUtil;
import ${basePackage}.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping
    public ResultVO add(@Valid @RequestBody ${modelNameUpperCamel}AddRO ${modelNameLowerCamel}AddRO, BindingResult result) {
        ROValidUtil.valid(result);
        ${modelNameLowerCamel}Service.add${modelNameUpperCamel}(${modelNameLowerCamel}AddRO);
        return ResultVOUtil.returnSuccess();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
    ${modelNameLowerCamel}Service.delete${modelNameUpperCamel}(id);
    return ResultVOUtil.returnSuccess();
    }

    @PutMapping
    public ResultVO update(@RequestBody ${modelNameUpperCamel}UpdateRO ${modelNameLowerCamel}UpdateRO) {
        ${modelNameLowerCamel}Service.update${modelNameUpperCamel}(${modelNameLowerCamel}UpdateRO);
        return ResultVOUtil.returnSuccess();
    }

    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.find${modelNameUpperCamel}(id);
        ${modelNameUpperCamel}VO ${modelNameLowerCamel}VO = new ${modelNameUpperCamel}VO();
        BeanUtils.copyProperties(${modelNameLowerCamel}, ${modelNameLowerCamel}VO);
        return ResultVOUtil.returnSuccess("${modelNameLowerCamel}", ${modelNameLowerCamel}VO);
    }

    @GetMapping
    public ResultVO list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<${modelNameUpperCamel}> ${modelNameLowerCamel}Page = ${modelNameLowerCamel}Service.find${modelNameUpperCamel}Page(pageRequest);
        List<${modelNameUpperCamel}> ${modelNameLowerCamel}List = ${modelNameLowerCamel}Page.getContent();
        List<${modelNameUpperCamel}VO> ${modelNameLowerCamel}VOList = ListBeanConvertUtil.convert(${modelNameLowerCamel}List, ${modelNameUpperCamel}VO.class);
        return ResultVOUtil.returnSuccess("${modelNameLowerCamel}_list", ${modelNameLowerCamel}VOList);
    }

}