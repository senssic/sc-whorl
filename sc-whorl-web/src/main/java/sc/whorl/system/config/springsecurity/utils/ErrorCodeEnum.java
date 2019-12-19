package sc.whorl.system.config.springsecurity.utils;

/***
 * 异常代码 值
 */

public enum ErrorCodeEnum {


    FAIL("系统异常,操作失败!", "1"),
    SUCCESS("操作成功!", "0"),


    LOGIN_INCORRECT("登录账户或者密码错误.","-3"),
    TOKEN_INVALID("无效的用户token.","401"),
    NO_TOKEN("要访问此资源,需要完全身份验证,缺少请求头参数,Authorization传递是token值所以参数是必须的.", "401");

    private String message ;
    private String code ;

    ErrorCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
