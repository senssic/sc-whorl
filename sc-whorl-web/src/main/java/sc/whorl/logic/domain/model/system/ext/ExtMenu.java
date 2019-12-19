package sc.whorl.logic.domain.model.system.ext;

import lombok.Data;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
public class ExtMenu {
    /**
     * 父级菜单编号
     */
    private Long parnetId;
    /**
     * 父级菜单名称
     */
    private String parentMenuName;
    /**
     * 菜单编号
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单对应访问路径
     */
    private String menuUrl;
    /**
     * 菜单所属项目
     */
    private String applicationCode;
    /**
     * 菜单排序 越小对应越靠前
     */
    private Integer orderIndex;
}
