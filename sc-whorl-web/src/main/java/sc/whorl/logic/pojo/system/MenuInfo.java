package sc.whorl.logic.pojo.system;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <一句话功能简述>
 * 页面相关属性
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class MenuInfo implements Serializable {
    /**
     * 父级菜单编号
     */
    private Long parentId;
    /**
     * 父级菜单名称
     */
    private String parentMenuName;
    List<ChildMenuInfo> childMenuList;
}
