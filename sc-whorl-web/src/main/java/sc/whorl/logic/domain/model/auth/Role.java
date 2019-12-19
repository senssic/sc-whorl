package sc.whorl.logic.domain.model.auth;

import java.util.Date;
import javax.persistence.*;
import org.apache.ibatis.type.Alias;

@Alias("role")
@Table(name = "role")
public class Role {
    /**
     * 记录编号,自增
     */
    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    /**
     * 父级角色编号
     */
    @Column(name = "parentId")
    private Long parentId;

    /**
     * 角色名称
     */
    @Column(name = "roleName")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "modifyAt")
    private Date modifyAt;

    @Column(name = "modifyBy")
    private String modifyBy;

    /**
     * EBL-可用,DBL-不可用
     */
    @Column(name = "status")
    private String status;

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
     * 获取父级角色编号
     *
     * @return parentId - 父级角色编号
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级角色编号
     *
     * @param parentId 父级角色编号
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取角色名称
     *
     * @return roleName - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return description - 角色描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置角色描述
     *
     * @param description 角色描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * 获取EBL-可用,DBL-不可用
     *
     * @return status - EBL-可用,DBL-不可用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置EBL-可用,DBL-不可用
     *
     * @param status EBL-可用,DBL-不可用
     */
    public void setStatus(String status) {
        this.status = status;
    }
}