package sc.whorl.logic.domain.model.system;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Alias("menu")
@Table(name = "menu")
public class Menu {
    /**
     * 菜单表主键,标记唯一列自增
     */
    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    /**
     * 父级菜单编号.如果没有父级则父级为0
     */
    @Column(name = "parentId")
    private Long parentId;

    /**
     * 菜单Url
     */
    @Column(name = "menuUrl")
    private String menuUrl;

    /**
     * 菜单名称
     */
    @Column(name = "menuName")
    private String menuName;

    /**
     * 关联权限表
     */
    @Column(name = "permissionId")
    private Long permissionId;

    /**
     * 状态EBL-可用,DBL-不可用
     */
    @Column(name = "status")
    private String status;

    /**
     * 不同应用菜单的区分
     */
    @Column(name = "applicationCode")
    private String applicationCode;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "modifyAt")
    private Date modifyAt;

    @Column(name = "modifyBy")
    private String modifyBy;

    @Column(name = "orderIndex")
    private Integer orderIndex;

    /**
     * 获取菜单表主键,标记唯一列自增
     *
     * @return tid - 菜单表主键,标记唯一列自增
     */
    public Long getTid() {
        return tid;
    }

    /**
     * 设置菜单表主键,标记唯一列自增
     *
     * @param tid 菜单表主键,标记唯一列自增
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * 获取父级菜单编号.如果没有父级则父级为0
     *
     * @return parentId - 父级菜单编号.如果没有父级则父级为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级菜单编号.如果没有父级则父级为0
     *
     * @param parentId 父级菜单编号.如果没有父级则父级为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单Url
     *
     * @return menuUrl - 菜单Url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单Url
     *
     * @param menuUrl 菜单Url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取菜单名称
     *
     * @return menuName - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取关联权限表
     *
     * @return permissionId - 关联权限表
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * 设置关联权限表
     *
     * @param permissionId 关联权限表
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * 获取状态EBL-可用,DBL-不可用
     *
     * @return status - 状态EBL-可用,DBL-不可用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态EBL-可用,DBL-不可用
     *
     * @param status 状态EBL-可用,DBL-不可用
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取不同应用菜单的区分
     *
     * @return applicationCode - 不同应用菜单的区分
     */
    public String getApplicationCode() {
        return applicationCode;
    }

    /**
     * 设置不同应用菜单的区分
     *
     * @param applicationCode 不同应用菜单的区分
     */
    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

    /**
     * @return createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return createBy
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return modifyAt
     */
    public Date getModifyAt() {
        return modifyAt;
    }

    /**
     * @param modifyAt
     */
    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    /**
     * @return modifyBy
     */
    public String getModifyBy() {
        return modifyBy;
    }

    /**
     * @param modifyBy
     */
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    /**
     * @return orderIndex
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }

    /**
     * @param orderIndex
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}