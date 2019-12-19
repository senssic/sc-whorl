package sc.whorl.system.commons;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SenUnitDic {
    /**
     * 成功
     */
    public static final int RESULT_CODE_SUCCESS = 0;

    /**
     * 数据校验失败
     */
    public static final int RESULT_CODE_VALIDATE_FAILED = -1;

    /**
     * 服务不可用
     */
    public static final int RESULT_CODE_SERVICE_UNAVAILABLE = -98;

    /**
     * 未知错误
     */
    public static final int RESULT_CODE_UNKNOWN = -99;

    /**
     *
     * 用户相关
     */
    //禁用用户
    public static final  String USER_STATUS_DBL="DBL";
    //删除用户,会禁用用户权限关系
    public static final  String USER_STATUS_DEL="DEL";

    /**
     * 用户可用
     */
    public static final  String USER_STATUS_EBL="EBL";


}
