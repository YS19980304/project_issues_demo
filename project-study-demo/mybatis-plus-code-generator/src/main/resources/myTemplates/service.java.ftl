package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

  /**
   * 通过id删除数据
   *
   * @param list id集合
   */
  void deleteByIds(List<Long> list);


}
</#if>
