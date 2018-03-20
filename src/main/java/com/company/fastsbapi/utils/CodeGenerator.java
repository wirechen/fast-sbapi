package com.company.fastsbapi.utils;


import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author: WireChen
 * @Date: Created in 下午5:27 2018/3/9
 * @Description: 代码生成脚本
 */
public class CodeGenerator {

    public static final String BASE_PACKAGE = "com.company.fastsbapi";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".dataobject.model";//Model所在包
    public static final String VO_PACKAGE = BASE_PACKAGE + ".dataobject.vo";//VO所在包
    public static final String RO_PACKAGE = BASE_PACKAGE + ".dataobject.ro";//RO所在包
    public static final String REPOSITORY_PACKAGE = BASE_PACKAGE + ".repository";//Repository所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";//Controller所在包

    public static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    public static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates/code";//模板位置
    public static final String JAVA_PATH = "/src/main/java"; //java文件路径

    public static final String MODEL_PATH = packageConvertPath(MODEL_PACKAGE); //model的path路径
    public static final String VO_PATH = packageConvertPath(VO_PACKAGE); //vo的path路径
    public static final String RO_PATH = packageConvertPath(RO_PACKAGE); //ro的path路径
    public static final String REPOSITORY_PATH = packageConvertPath(REPOSITORY_PACKAGE); //repository的path路径
    public static final String SERVICE_PATH = packageConvertPath(SERVICE_PACKAGE); //service的path路径
    public static final String SERVICE_IMPL_PATH = packageConvertPath(SERVICE_IMPL_PACKAGE); //serviceImpl的path路径
    public static final String CONTROLLER_PATH = packageConvertPath(CONTROLLER_PACKAGE); //controller的path路径

    public static final String SQL_FILE_PATH = packageConvertPath("src.main.resources.sql");//SQL脚本路径


    public static final String AUTHOR = "CodeGenerator";//@author
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    //生成dataobject和sql
    public static String genModel(String modelName, String tableName, Map<String, Object> map, Boolean requireSql) {

        String voName = modelName + "VO";
        String addRoName = modelName + "AddRO";
        String updateRoName = modelName + "UpdateRO";

        //map : {name: ['String','1', '']} 第一个字段类型 第二个是否为null 第三个备注
        List<Map<String, Object>> dataList = new ArrayList<>();
        map.forEach((key, value) -> {
            Map<String, Object> fieldMap = new HashMap<>();
            fieldMap.put("fieldName", key);
            fieldMap.put("sqlName", getTableName(key));
            String type = ((List<String>) value).get(0);
            fieldMap.put("fieldType", type);
            fieldMap.put("sqlType", convertSqlType(type));
            String isNull = ((List<String>) value).get(1);
            fieldMap.put("isNull", isNull);
            String comment = ((List<String>) value).get(2);
            fieldMap.put("comment", comment);
            dataList.add(fieldMap);
        });

        Map<String, Object> data = new HashMap<>();
        data.put("date", DATE);
        data.put("author", AUTHOR);
        data.put("basePackage", BASE_PACKAGE);
        data.put("modelName", modelName);
        if ("".equals(tableName)) {
            data.put("tableName", getTableName(modelName));
        } else {
            data.put("tableName", tableName);
        }
        data.put("modelFieldList", dataList);
        data.put("serialVersionUID", getSerialVersionUID());

        String sqlRequireName = "";
        try {
            Configuration cfg = getConfiguration();
            //model
            File modelFile = new File(PROJECT_PATH + JAVA_PATH + MODEL_PATH + modelName + ".java");
            if (!modelFile.getParentFile().exists()) {
                modelFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("model.ftl").process(data,
                    new FileWriter(modelFile));
            System.out.println(modelName + ".java 生成成功");
            //vo
            File voFile = new File(PROJECT_PATH + JAVA_PATH + VO_PATH + voName + ".java");
            if (!voFile.getParentFile().exists()) {
                voFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("vo.ftl").process(data,
                    new FileWriter(voFile));
            System.out.println(voName + ".java 生成成功");
            //addRo
            File addRoFile = new File(PROJECT_PATH + JAVA_PATH + RO_PATH + addRoName + ".java");
            if (!addRoFile.getParentFile().exists()) {
                addRoFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("add-ro.ftl").process(data,
                    new FileWriter(addRoFile));
            System.out.println(addRoName + ".java 生成成功");
            //updateRo
            File updateRoFile = new File(PROJECT_PATH + JAVA_PATH + RO_PATH + updateRoName + ".java");
            if (!updateRoFile.getParentFile().exists()) {
                updateRoFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("update-ro.ftl").process(data,
                    new FileWriter(updateRoFile));
            System.out.println(updateRoName + ".java 生成成功");

            //sql
            if (requireSql) {
                String sqlInitName = data.get("tableName").toString() + "_init.sql";
                File sqlFile = new File(PROJECT_PATH +  SQL_FILE_PATH + sqlInitName);
                if (!sqlFile.getParentFile().exists()) {
                    sqlFile.getParentFile().mkdirs();
                }
                cfg.getTemplate("sql.ftl").process(data,
                        new FileWriter(sqlFile));
                sqlRequireName = sqlInitName;
                System.out.println(sqlInitName + " 生成成功");
            }

        } catch (Exception e) {
            throw new RuntimeException("生成dataobject失败", e);
        }

        return sqlRequireName;

    }

    //生成repository
    public static void genRepository(String modelName) {
        try {
            String repositoryName = modelName + "Repository";
            Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("modelNameUpperCamel", modelName);
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + REPOSITORY_PATH + repositoryName + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("repository.ftl").process(data,
                    new FileWriter(file));
            System.out.println(repositoryName + ".java 生成成功");

        } catch (Exception e) {
            throw new RuntimeException("生成Repository失败", e);
        }

    }

    //生成service
    public static void genService(String modelName) {
        try {
            String serviceName = modelName + "Service";
            String serviceImplName = modelName + "ServiceImpl";
            Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("modelNameUpperCamel", modelName);
            data.put("modelNameLowerCamel", getLowerCamel(modelName));
            data.put("basePackage", BASE_PACKAGE);

            File serviceFile = new File(PROJECT_PATH + JAVA_PATH + SERVICE_PATH + serviceName + ".java");
            if (!serviceFile.getParentFile().exists()) {
                serviceFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(serviceFile));
            System.out.println(serviceName + ".java 生成成功");

            File serviceImplFile = new File(PROJECT_PATH + JAVA_PATH + SERVICE_IMPL_PATH + serviceImplName + ".java");
            if (!serviceImplFile.getParentFile().exists()) {
                serviceImplFile.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(serviceImplFile));
            System.out.println(serviceImplName + ".java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    //生成controller
    public static void genController(String modelName) {
        try {
            String controllerName = modelName + "Controller";
            Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("modelNameUpperCamel", modelName);
            data.put("modelNameLowerCamel", getLowerCamel(modelName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + CONTROLLER_PATH + controllerName + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller.ftl").process(data,
                    new FileWriter(file));
            System.out.println(controllerName + ".java 生成成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String getSerialVersionUID() {
        Random random = new Random();
        return String.valueOf(random.nextLong());
    }

    private static Object convertSqlType(String type) {
        String sqlType = "";
        switch (type) {
            case "String":
                sqlType = "varchar(255)";
                break;
            case "Integer":
                sqlType = "int";
                break;
            case "Long":
                sqlType = "bigint";
                break;
            case "Boolean":
                sqlType = "tinyint(1)";
                break;
            case "Date":
                sqlType = "timestamp";
                break;
            case "BigDecimal":
                sqlType = "decimal(10,2)";
                break;
            default:
                sqlType = "varchar(255)";
                break;
        }
        return sqlType;
    }

    private static String getLowerCamel(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private static String getTableName(String name) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("orderName", Arrays.asList(new String[]{"String", "not null", "订单名称"}));
        map.put("orderPerson", Arrays.asList(new String[]{"String","not null","下单人"}));
        map.put("ifPay", Arrays.asList(new String[]{"Boolean","not null","是否完成支付"}));
        map.put("orderAmount", Arrays.asList(new String[]{"BigDecimal","","订单总金额"}));

        String modelName = "Order";
        String tableName = "order_table";
        genModel(modelName,tableName, map, true);
        genRepository(modelName);
        genService(modelName);
        genController(modelName);

    }
}
