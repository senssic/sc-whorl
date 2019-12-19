package sc.whorl.logic.domain.model.auth;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Alias("permission")
@Table(name = "permission")
public class Permission {
    /**
     * 记录编号,自增
     */
    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    /**
     * 权限名称
     */
    @Column(name = "permissionName")
    private String permissionName;

    /**
     * 权限描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 权限代码
     */
    @Column(name = "functionNumber")
    private String functionNumber;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "modifyAt")
    private Date modifyAt;

    @Column(name = "modifyBy")
    private String modifyBy;

    /**
     * 获取记录编号,自增
     *
     * @return tid - 记录编号,自增
     */
    public Long getTid() {
        return tid;
    }

    /**
     * 设置记录编号,自增
     *
     * @param tid 记录编号,自增
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * 获取权限名称
     *
     * @return permissionName - 权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名称
     *
     * @param permissionName 权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取权限描述
     *
     * @return description - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     *
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取权限代码
     *
     * @return functionNumber - 权限代码
     */
    public String getFunctionNumber() {
        return functionNumber;
    }

    /**
     * 设置权限代码
     *
     * @param functionNumber 权限代码
     */
    public void setFunctionNumber(String functionNumber) {
        this.functionNumber = functionNumber;
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
}