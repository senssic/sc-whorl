package sc.whorl.logic.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sc.whorl.logic.domain.dao.auth.UserMapper;
import sc.whorl.logic.domain.model.auth.User;
import sc.whorl.logic.domain.model.auth.UserRole;
import sc.whorl.system.commons.MsgResponseBody;
import sc.whorl.system.commons.PageResponse;
import sc.whorl.system.commons.SenUnitDic;
import sc.whorl.system.commons.SenUnitException;
import sc.whorl.system.commons.base.BaseService;
import sc.whorl.system.commons.lock.RedisLock;
import sc.whorl.system.config.jwt.JWTUserDetail;
import sc.whorl.system.config.jwt.JwtTokenUtil;
import sc.whorl.system.config.springsecurity.utils.ErrorCodeEnum;
import sc.whorl.system.config.springsecurity.utils.UserAuthInfoUtils;
import sc.whorl.system.utils.ScUtils;
import sc.whorl.system.utils.redis.RedisUtil;
import sc.whorl.system.utils.spring.SpringUtil;
import sc.whorl.web.vo.system.UserListQueryRequest;
import sc.whorl.web.vo.user.UserVo;
import tk.mybatis.mapper.entity.Example;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
@Slf4j
public class UserService extends BaseService<UserMapper, User> {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.access_token:#{30*24*60*60}}")
    private Long access_token_expiration;

    @Autowired
    AuthenticationManager authenticationManager;

    public MsgResponseBody<String> upUser(Long userId, User user) {
        user.setTid(userId);
        updateByPrimaryKeySelective(user);
        return MsgResponseBody.success().setResult("用户更新成功!");
    }

    @Transactional(rollbackFor = Exception.class)
    public MsgResponseBody<String> dblUser(Long userId) {
        User user = new User();
        user.setStatus(SenUnitDic.USER_STATUS_DBL);
        user.setTid(userId);
        this.updateByPrimaryKeySelective(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setStatus(SenUnitDic.USER_STATUS_DBL);
        return MsgResponseBody.success().setResult("用户禁用成功!");
    }


    public PageResponse<User> searchListUser(UserListQueryRequest userRequest) {
        PageHelper.startPage(userRequest.getPageIndex(), userRequest.getPageSize());
        List<User> userList = selectListAll();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return new PageResponse(pageInfo);

    }

    public void sample() {
        //获取spring上下文
        WebApplicationContext wac = (WebApplicationContext) SpringUtil.getApplicationContext();
        //获取请求上下文
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        ServletContext sc = httpRequest.getServletContext();
        //获取响应上下文
        HttpServletResponse httpResponse = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        //获取用户登陆后的信息
        UserAuthInfoUtils.getUser();
    }

    public void register(UserVo userVo) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("loginName", userVo.getAccountname()).orEqualTo("mobile", userVo.getUserPhone());
        RedisLock.tryLock(userVo.getAccountname());
        List<User> userList = this.selectByExample(example);
        RedisLock.unLock(userVo.getAccountname());
        if (!ScUtils.isEmpty(userList)) {
            throw new SenUnitException(SenUnitDic.RESULT_CODE_SERVICE_UNAVAILABLE, "账户已存在!");
        }
        User users = new User();
        users.setLoginName(userVo.getAccountname());
        users.setPassWord(bCryptPasswordEncoder.encode(userVo.getPassword()));
        users.setMobile(userVo.getUserPhone());
        users.setStatus("EBL");
        this.insert(users);
    }


    /**
     * 用户登陆相关,主要验证用户用户密码以及设置jwt和用户缓存相关,并返回jwt的凭证
     *
     * @param userVo
     * @return
     */
    public MsgResponseBody<JWTUserDetail> login(UserVo userVo) {
        User user = new User();
        user.setLoginName(userVo.getAccountname());
        User userOne = selectOne(user);
        if (ObjectUtils.isEmpty(userOne)) {
            log.info("用户不存在!");
            return MsgResponseBody.error(ErrorCodeEnum.LOGIN_INCORRECT.getCode()).setResult(ErrorCodeEnum.LOGIN_INCORRECT.getMessage());
        }
        if (!bCryptPasswordEncoder.matches(userVo.getPassword(), userOne.getPassWord())) {
            log.info("用户登陆密码错误!");
            return MsgResponseBody.error(ErrorCodeEnum.LOGIN_INCORRECT.getCode()).setResult(ErrorCodeEnum.LOGIN_INCORRECT.getMessage());
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userVo.getAccountname(),
                        userVo.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //使用jwt生成token 用于权限效验
        JWTUserDetail jwtUserDetail = new JWTUserDetail();
        jwtUserDetail.setLoginName(userOne.getLoginName());
        jwtUserDetail.setLoginTime(new Date());
        jwtUserDetail.setUserId(userOne.getTid());
        jwtUserDetail.setUserType(JWTUserDetail.UserType.User);
        String token = jwtTokenUtil.generateToken(jwtUserDetail);
        jwtUserDetail.setJwtToken(token);
        return MsgResponseBody.success().setResult(jwtUserDetail);
    }

    public void logout() {
        redisUtil.setEx(String.format(JwtTokenUtil.JWT_TOKEN_PREFIX, UserAuthInfoUtils.getUserType(), UserAuthInfoUtils.getUserId()), UserAuthInfoUtils.getUser().getJwtToken(), access_token_expiration, TimeUnit.SECONDS);
    }
}
