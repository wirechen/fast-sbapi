package ${basePackage}.repository;

import ${basePackage}.dataobject.model.${modelNameUpperCamel};
import org.springframework.data.jpa.repository.JpaRepository;

/**
* Created by ${author} on ${date}.
*/
public interface ${modelNameUpperCamel}Repository extends JpaRepository<${modelNameUpperCamel}, Integer>{

}