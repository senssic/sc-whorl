package sc.whorl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sc.whorl.logic.service.user.UserService;
import sc.whorl.system.commons.MsgResponseBody;
import sc.whorl.system.commons.limitrate.Limit;
import sc.whorl.system.commons.preventresubmit.PreventParam;
import sc.whorl.system.commons.preventresubmit.PreventResubmitLock;
import sc.whorl.system.commons.webhandler.RequestJsonParam;
import sc.whorl.system.config.jwt.JWTUserDetail;
import sc.whorl.web.vo.user.UserVo;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping(value = "/sc/user/auth")
@Api(value = "UserLoginWeb", description = "用户登陆相关接口,此url路径不会有权限拦截")
public class UserLoginWeb {
    @Autowired
    private UserService userService;


    /**
     * 登陆接口
     * 1.判断用户名密码是否正确
     * 2.生成令牌和用户信息返回到前端
     */
    @ApiOperation(value = "用户登陆", httpMethod = "POST",code = 200,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/login",method = RequestMethod.POST, produces = { "application/json"})
    //限流 10秒内最多允许1000次访问
    @Limit(key = "USER:LOGIN", period = 10, count = 1000)
    public MsgResponseBody<JWTUserDetail> login(@RequestJsonParam("userName") String userName, @RequestJsonParam("passWord") String passWord) {
        UserVo userVo = new UserVo();
        userVo.setAccountname(userName);
        userVo.setPassword(passWord);
        return userService.login(userVo);
    }

    /**
     * 注册接口
     */
    @ApiOperation(value = "用户注册", httpMethod = "POST",code = 200,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/register",method = RequestMethod.POST, produces = { "application/json"})
    //默认5秒内同一手机号不可重复提交
    @PreventResubmitLock(prefix = "USER:REGISTER")
    //cache会在方法执行后缓存,相当于更新了缓存,少了一步查询缓存
    public MsgResponseBody<String> register(@RequestBody @PreventParam(name = "userPhone") UserVo userVo) {
        userService.register(userVo);
        return MsgResponseBody.success().setResult("注册成功");
    }


}
