package sc.whorl.logic.pojo.system;

import java.io.Serializable;

import lombok.Data;

/**
 * 菜单详情
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class ChildMenuInfo implements Serializable {
    /**
     * 菜单编号
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单对应路径
     */
    private String menuUrl;
    /**
     * 菜单所属应用
     */
    private String applicationCode;
    /**
     * 菜单排序,越小越靠前
     */
    private Integer orderIndex;

}
