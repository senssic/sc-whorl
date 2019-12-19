package sc.whorl.system.config.springsecurity.conf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import sc.whorl.system.config.jwt.JwtAuthenPreFilter;
import sc.whorl.system.config.springsecurity.handler.UnauthorizedHandler;


/***
 *
 * @FileName: WebSecurityConfig
 * @remark: web 安全性配置
 * @explain 当用户登录时会进入此类的loadUserByUsername方法对用户进行验证，验证成功后会被保存在当前回话的principal对象中
 *             系统获取当前登录对象信息方法 WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        //@Secured("ROLE_ADMIN")
        securedEnabled = true,
        //@RolesAllowed("ROLE_ADMIN")
        jsr250Enabled = true,
        //@PreAuthorize("hasRole('ROLE_USER')")
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // 不需要认证的接口
    @Value("${jwt.security.antMatchers}")
    private String antMatchers;

    /**
     * 置user-detail服务
     * 方法描述
     * accountExpired(boolean)                定义账号是否已经过期
     * accountLocked(boolean)                 定义账号是否已经锁定
     * and()                                  用来连接配置
     * authorities(GrantedAuthority...)       授予某个用户一项或多项权限
     * authorities(List)                      授予某个用户一项或多项权限
     * authorities(String...)                 授予某个用户一项或多项权限
     * disabled(boolean)                      定义账号是否已被禁用
     * withUser(String)                       定义用户的用户名
     * password(String)                       定义用户的密码
     * roles(String...)                       授予某个用户一项或多项角色
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置UserDetailsService
        auth.userDetailsService(customUserDetailsService)
                //使用BCrypt进行密码的hash
                .passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 声明AuthenticationManager
     *
     * @return
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    /**
     * 密码加密方式
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置如何通过拦截器保护请求
     * 指定哪些请求需要认证，哪些请求不需要认证，以及所需要的权限
     * 通过调用authorizeRequests()和anyRequest().authenticated()就会要求所有进入应用的HTTP请求都要进行认证
     *
     * 方法描述
     * anonymous()                                        允许匿名用户访问
     * authenticated()                                    允许经过认证的用户访问
     * denyAll()                                          无条件拒绝所有访问
     * fullyAuthenticated()                如果用户是完整的话（不是通过Remember-me功能认证的），就允许访问
     * hasAnyAuthority(String...)                 如果用户具备给定权限中的某一个的话，就允许访问
     * hasAnyRole(String...)                    如果用户具备给定角色中的某一个的话，就允许访问
     * hasAuthority(String)                     如果用户具备给定权限的话，就允许访问
     * hasIpAddress(String)                    如果请求来自给定IP地址的话，就允许访问
     * hasRole(String)                        如果用户具备给定角色的话，就允许访问
     * not()                               对其他访问方法的结果求反
     * permitAll()                           无条件允许访问
     * rememberMe()                          如果用户是通过Remember-me功能认证的，就允许访问
     *
     * @param http
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf验证
        http.cors().and().csrf().disable()
                // 基于token，所以不需要session,此处策略为不需要创建
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //对请求进行认证  url认证配置顺序为：1.先配置放行不需要认证的 permitAll() 2.然后配置 需要特定权限的 hasRole() 3.最后配置 anyRequest().authenticated()
                .authorizeRequests().antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js","/swagger-resources/**","/webjars/springfox-swagger-ui/**","/v2/api-docs/**")
                .permitAll()
                // 所有 antMatchers配置的 请求的都放行 不做认证即不需要登录即可访问,可以配置登陆下载等不需要token的请求路径
                .antMatchers(antMatchers.split(",")).permitAll()
                .antMatchers(HttpMethod.GET, "/api/download/**")
                .permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()//跨域请求会先进行一次options请求
                // 其他请求都需要进行认证,认证通过够才能访问   待考证：如果使用重定向 httpServletRequest.getRequestDispatcher(url).forward(httpServletRequest,httpServletResponse); 重定向跳转的url不会被拦截（即在这里配置了重定向的url需要特定权限认证不起效），但是如果在Controller 方法上配置了方法级的权限则会进行拦截
                .anyRequest().authenticated()
                .and().exceptionHandling()
                // 认证配置当用户请求了一个受保护的资源，但是用户没有通过登录认证，则抛出登录认证异常，MyAuthenticationEntryPointHandler类中commence()就会调用
                .authenticationEntryPoint(myAuthenticationEntryPoint());


        // 添加JWT filter 验证其他请求的Token是否合法
        http.addFilterBefore(jwtAuthenPreFilterBean(), FilterSecurityInterceptor.class);
        // 禁用缓存
        http.headers().cacheControl();


    }


    /**
     * 登录认证异常
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint myAuthenticationEntryPoint() {
        return new UnauthorizedHandler();
    }

    /**
     * 注册jwt 过滤器
     */
    @Bean
    public JwtAuthenPreFilter jwtAuthenPreFilterBean() throws Exception {

        return new JwtAuthenPreFilter();
    }

}
