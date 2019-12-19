package sc.whorl.web.vo.system;

import java.io.Serializable;

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
public class MenuPremInfo implements Serializable {

    /**
     * 父级菜单编号.如果没有父级则父级为0
     */
    private Long parentid;

    /**
     * 菜单Url
     */
    private String menuurl;

    /**
     * 菜单名称
     */
    private String menuname;

    /**
     * 所属应用默认未whorl
     */
    private String applicationcode;

    /**
     * 菜单排序默认为0
     */
    private Integer orderIndex;

    /**
     * 权限代码,需要唯一
     */
    private String functionNumber;

}
