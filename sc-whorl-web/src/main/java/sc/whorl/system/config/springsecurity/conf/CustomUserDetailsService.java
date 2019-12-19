package sc.whorl.system.config.springsecurity.conf;


import com.google.common.base.Function;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import sc.whorl.logic.domain.dao.auth.UserMapper;
import sc.whorl.logic.domain.model.auth.Role;
import sc.whorl.logic.domain.model.auth.User;


/***
 *
 * @FileName: CustomUserDetailsService

 * @remark: 配置用户权限认证
 * @explain 当用户登录时会进入此类的loadUserByUsername方法对用户进行验证，验证成功后会被保存在当前回话的principal对象中
 *             系统获取当前登录对象信息方法 WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 *              异常信息：
 *              UsernameNotFoundException     用户找不到
 *              BadCredentialsException       坏的凭据
 *              AccountExpiredException       账户过期
 *              LockedException               账户锁定
 *              DisabledException             账户不可用
 *              CredentialsExpiredException   证书过期
 *
 *
 */
@Slf4j
@Service("myUserDetailService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户：" + username);
        //用户用户信息和用户角色
        User user = new User();
        user.setLoginName(username);
        User userOne = userMapper.selectOne(user);
        if (ObjectUtils.isEmpty(userOne)) {
            //后台抛出的异常是：org.springframework.security.authentication.BadCredentialsException: Bad credentials  坏的凭证 如果要抛出UsernameNotFoundException 用户找不到异常则需要自定义重新它的异常
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
        List<Role> roles = userMapper.selectRolsByUserId(userOne.getTid());
        if (!ObjectUtils.isEmpty(roles)) {
            grantedAuths.addAll(Lists.transform(roles, (Function<Role, GrantedAuthority>) role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        }
        org.springframework.security.core.userdetails.User baseUser = new org.springframework.security.core.userdetails.User(userOne.getLoginName(), userOne.getPassWord(),
                grantedAuths);
        return baseUser;
    }

}
