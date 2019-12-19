package sc.whorl.logic.domain.model.auth;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Alias("userRole")
@Table(name = "userrole")
public class UserRole {
    /**
     * 用户校色主编号,自增
     */
    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    /**
     * 用户编号
     */
    @Column(name = "userId")
    private Long userId;

    /**
     * 角色编号
     */
    @Column(name = "roleId")
    private Long roleId;

    /**
     * 状态EBL-可用,DBL-不可用
     */
    @Column(name = "status")
    private String status;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "createBy")
    private String createBy;

    @Column(name = "modifyAt")
    private Date modifyAt;

    @Column(name = "modifyBy")
    private String modifyBy;

    /**
     * 获取用户校色主编号,自增
     *
     * @return tid - 用户校色主编号,自增
     */
    public Long getTid() {
        return tid;
    }

    /**
     * 设置用户校色主编号,自增
     *
     * @param tid 用户校色主编号,自增
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * 获取用户编号
     *
     * @return userId - 用户编号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取角色编号
     *
     * @return roleId - 角色编号
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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