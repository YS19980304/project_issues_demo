package ${modulePath};

<#list importEntityJavaPackages as pkg>
import ${pkg};
</#list>
    <#if swagger>
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
    </#if>


<#assign prefix = "${table_prefix}">
<#assign tableName = "${table.name}">


<#-- 去掉表前缀 -->
<#assign noPrefixString = tableName?replace(prefix, "")>
<#-- 将下划线分割的字符串转换为驼峰命名 -->
<#assign parts = noPrefixString?split("_")>
<#assign result = "">
<#list parts as part>
    <#if part?has_content>
        <#assign result = result + part?capitalize>
    </#if>
</#list>


/**
* <p>
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
public class ${result}Dto  implements Serializable {

    private static final long serialVersionUID = 1L;

    <#if entityFieldUseJavaDoc>
    /**
    * id
    */
    </#if>
    <#if swagger>
    @ApiModelProperty("id")
    </#if>
    private Long id;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
        <#if entityFieldUseJavaDoc>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    @ApiModelProperty("${field.comment}")
    private ${field.propertyType} ${field.propertyName};
</#list>
<#-- ----------  END 字段循环遍历  ---------->

}
