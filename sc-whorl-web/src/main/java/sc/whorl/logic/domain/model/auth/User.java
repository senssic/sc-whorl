package sc.whorl.logic.domain.model.auth;

import java.util.Date;
import javax.persistence.*;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Table(name = "user")
public class User {
    /**
     * 记录标识,自增
     */
    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    /**
     * 登陆账号,不为空
     */
    @Column(name = "loginName")
    private String loginName;

    /**
     * 用户密码
     */
    @Column(name = "passWord")
    private String passWord;

    /**
     * 用户姓名
     */
    @Column(name = "userName")
    private String userName;

    /**
     * 用户手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 用户时间
     */
    @Column(name = "email")
    private String email;

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
     * 获取记录标识,自增
     *
     * @return tid - 记录标识,自增
     */
    public Long getTid() {
        return tid;
    }

    /**
     * 设置记录标识,自增
     *
     * @param tid 记录标识,自增
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * 获取登陆账号,不为空
     *
     * @return loginName - 登陆账号,不为空
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登陆账号,不为空
     *
     * @param loginName 登陆账号,不为空
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取用户密码
     *
     * @return passWord - 用户密码
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置用户密码
     *
     * @param passWord 用户密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取用户姓名
     *
     * @return userName - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户手机号
     *
     * @return mobile - 用户手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置用户手机号
     *
     * @param mobile 用户手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取用户时间
     *
     * @return email - 用户时间
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户时间
     *
     * @param email 用户时间
     */
    public void setEmail(String email) {
        this.email = email;
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