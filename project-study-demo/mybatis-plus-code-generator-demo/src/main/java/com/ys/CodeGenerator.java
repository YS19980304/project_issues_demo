package com.ys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 代码生成器
 *
 * @author ys
 */
public class CodeGenerator {

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/demo";
    private static final String USER_NAME = "demo";
    private static final String PASSWORD = "demo";
    /**
     * 文件输出路径
     */
    private static final String OUTPUT_DIR = System.getProperty("user.dir") + "\\project_study_demo\\mybatis_plus_code_generator\\src\\main\\java";
    /**
     * 每个文件上面的包路径
     */
    private static final String PARENT_PATH = "com.ys.modules.test";
    /**
     * 表前缀,如果填写前缀,生成的时候会把前缀去掉
     */
    private static final String TABLE_PREFIX = "";
    /**
     * entity父类全路径,如果没有写成不填
     */
    private static final String SUPER_ENTITY_CLASS = "com.ys.common.entity.BaseEntity";
    /**
     * 忽略字段
     */
    private static final String[] IGNORE_COLUMNS = {"ID"};

    /**
     * 需要生成的表名 注意大小写
     */
    private static final String[] GENERATE_TABLES = {"dc_pipeline_pl"};

    public static void main(String[] args) {

        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(URL, USER_NAME, PASSWORD);

        // 全局配置
        Consumer<GlobalConfig.Builder> globalConfig = builder -> builder.disableOpenDir()
                .enableSwagger()
                .outputDir(OUTPUT_DIR)
                .author("ys")
                .disableOpenDir()
                .commentDate("yyyy-MM-dd HH:mm:ss")
                .dateType(DateType.ONLY_DATE);

        // 数据库字段映射配置
        Consumer<DataSourceConfig.Builder> DataSourceConfig = builder -> builder
                .typeConvertHandler((config, typeRegistry, metaInfo) -> {
                    String jdbcType = metaInfo.getJdbcType().name();
                    if ("NUMERIC".equals(jdbcType)) {
                        if (metaInfo.getScale() == 0) {
                            if (metaInfo.getLength() <= 9) {
                                return DbColumnType.INTEGER;
                            } else {
                                return DbColumnType.LONG;
                            }
                        } else {
                            return DbColumnType.DOUBLE;
                        }
                    }
                    return typeRegistry.getColumnType(metaInfo);
                });

        // 包配置
        Consumer<PackageConfig.Builder> packageConfig = builder -> builder
                .parent(PARENT_PATH)
                .entity("entity")
                .mapper("dao")
                .service("service")
                .serviceImpl("service.impl")
                .xml("mapper");

        // 表策略
        Consumer<StrategyConfig.Builder> tableConfigConsumer = builder -> builder
                .addInclude(GENERATE_TABLES)
                .addTablePrefix(TABLE_PREFIX);

        // controller 策略
        Consumer<StrategyConfig.Builder> controllerConsumer = builder -> builder
                .controllerBuilder()
                .enableRestStyle()
                .formatFileName("%sController")
                .enableFileOverride();

        // service 策略
        Consumer<StrategyConfig.Builder> serviceConfigConsumer = builder -> builder
                .serviceBuilder()
                .serviceTemplate("/myTemplates/service.java")
                .serviceImplTemplate("/myTemplates/serviceImpl.java")
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl")
                .enableFileOverride();

        // mapper 策略
        Consumer<StrategyConfig.Builder> mapperConsumer = builder -> builder
                .mapperBuilder()
                .enableFileOverride()
                .formatMapperFileName("%sDao")
                .formatXmlFileName("%sMapper");

        // entity 策略
        Consumer<StrategyConfig.Builder> entityConfigConsumer = builder -> builder
                .entityBuilder()
                .enableTableFieldAnnotation()
                .javaTemplate(ConstVal.TEMPLATE_ENTITY_JAVA)
                .formatFileName("%sEntity")
                .enableLombok(lombokAnn())
                .enableRemoveIsPrefix()
                .columnNaming(NamingStrategy.underline_to_camel)
                .superClass(SUPER_ENTITY_CLASS)
                .naming(NamingStrategy.underline_to_camel)
                .addIgnoreColumns(IGNORE_COLUMNS)
                .idType(IdType.ASSIGN_ID)
                .fieldUseJavaDoc(false)
                .enableFileOverride();

        // 自定义的dto模版
        Map<String, Object> customMap = new HashMap<>(8);
        customMap.put("modulePath", PARENT_PATH + ".dto");
        customMap.put("table_prefix", TABLE_PREFIX + "_");
        // 把文件的名称后缀的 Entity 换成 Dto
        Consumer<InjectionConfig.Builder> injectionConfig = builder -> builder
                .customMap(customMap)
                .customFile(new CustomFile.Builder()
                        .enableFileOverride()
                        .formatNameFunction(tableInfo -> tableInfo.getEntityName().replace("Entity", "Dto"))
                        .fileName(".java")
                        .templatePath("myTemplates/dto.java.ftl")
                        .packageName("dto")
                        .build());


        fastAutoGenerator.globalConfig(globalConfig);
        fastAutoGenerator.dataSourceConfig(DataSourceConfig);
        fastAutoGenerator.packageConfig(packageConfig);
        fastAutoGenerator.strategyConfig(tableConfigConsumer);
        fastAutoGenerator.strategyConfig(tableConfigConsumer);
        fastAutoGenerator.strategyConfig(controllerConsumer);
        fastAutoGenerator.strategyConfig(serviceConfigConsumer);
        fastAutoGenerator.strategyConfig(mapperConsumer);
        fastAutoGenerator.strategyConfig(entityConfigConsumer);
        fastAutoGenerator.injectionConfig(injectionConfig);
        fastAutoGenerator.templateEngine(new FreemarkerTemplateEngine());
        fastAutoGenerator.execute();
    }

    /**
     * entity上面添加注解
     * <p>
     * 如果存在父类,添加 @EqualsAndHashCode 注解,不存在不添加这个注解
     */
    private static ClassAnnotationAttributes[] lombokAnn() {
        List<ClassAnnotationAttributes> list = new ArrayList<>();
        list.add(new ClassAnnotationAttributes("@Data", "lombok.Data"));
        if (StringUtils.hasLength(CodeGenerator.SUPER_ENTITY_CLASS)) {
            list.add(new ClassAnnotationAttributes("@EqualsAndHashCode(callSuper = true)", "lombok.EqualsAndHashCode"));
        }
        return list.toArray(new ClassAnnotationAttributes[0]);
    }

}
