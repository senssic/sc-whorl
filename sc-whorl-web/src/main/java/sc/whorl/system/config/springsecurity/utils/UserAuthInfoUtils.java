package sc.whorl.system.config.springsecurity.utils;


import sc.whorl.system.config.jwt.JWTUserDetail;
import sc.whorl.system.config.jwt.JwtTokenUtil;

/***
 *
 * @FileName: UserUtils
 * @remark: 用户信息操作工具类
 *
 */
public class UserAuthInfoUtils {
    /**
     * 根据token 获取用户信息
     */
    public static JWTUserDetail getUser() {
        return JwtTokenUtil.LOCAL_USER.get();
    }


    /**
     * 用户ID
     *
     * @return
     */
    public static Long getUserId() {
        return JwtTokenUtil.LOCAL_USER.get().getUserId();
    }

    /**
     * 获取登陆人id
     */
    public static String getLoginName() {
        return JwtTokenUtil.LOCAL_USER.get().getLoginName();
    }


    public static JWTUserDetail.UserType getUserType() {
        return JwtTokenUtil.LOCAL_USER.get().getUserType();
    }

}
