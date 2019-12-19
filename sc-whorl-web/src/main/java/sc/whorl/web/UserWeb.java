package sc.whorl.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sc.whorl.logic.domain.model.auth.User;
import sc.whorl.logic.service.user.UserService;
import sc.whorl.system.commons.MsgResponseBody;
import sc.whorl.system.commons.PageResponse;
import sc.whorl.system.utils.mapper.BeanMapper;
import sc.whorl.web.vo.system.UpUserRequest;
import sc.whorl.web.vo.system.UserListQueryRequest;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/sc/user/user")
@Api(value = "UserLoginWeb", description = "用户相关接口")
public class UserWeb {
    @Autowired
    private UserService userService;

    /**
     * 登陆接口
     */
    @ApiOperation(value = "用户登出", httpMethod = "POST", code = 200, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = {"application/json"})
    public MsgResponseBody<String> logout() {
        userService.logout();
        return MsgResponseBody.success().setResult("退出成功！");
    }

    @ApiOperation(value = "查询人员列表", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/searchListUser", method = RequestMethod.POST, produces = {"application/json"})
    public MsgResponseBody<PageResponse<User>> searchListUser(@RequestBody UserListQueryRequest userRequest) {
        return MsgResponseBody.success().setResult(userService.searchListUser(userRequest));
    }

    @ApiOperation(value = "禁用用户", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/dblUser/{userId}", method = RequestMethod.PUT)
    public MsgResponseBody<String> dblUser(@PathVariable Long userId) {
        return MsgResponseBody.success().setResult(userService.dblUser(userId));
    }

    @ApiOperation(value = "修改用户", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/upUser/{userId}", method = RequestMethod.PUT)
    public MsgResponseBody<String> upUser(@PathVariable Long userId, @RequestBody UpUserRequest userReq) {
        return MsgResponseBody.success().setResult(userService.upUser(userId, BeanMapper.map(userReq, User.class)));
    }


}
