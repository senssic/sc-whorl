package sc.whorl.system.config.springsecurity.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import sc.whorl.system.config.springsecurity.utils.ErrorCodeEnum;
import sc.whorl.system.utils.rest.ResultUtil;

/***
 *
 * @FileName: JwtAuthenticationEntryPoint
 * @remark: jwt 认证处理类
 *
 */
@Slf4j
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        // 用户登录时身份认证未通过
        if (e instanceof BadCredentialsException) {
            log.info("用户登录时身份认证失败!");
            ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.TOKEN_INVALID.getCode(), e.getMessage());
        } else if (e instanceof InsufficientAuthenticationException) {
            log.info("缺少请求头参数,Authorization传递是token值所以参数是必须的.");
            ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.NO_TOKEN.getCode(), ErrorCodeEnum.NO_TOKEN.getMessage());
        } else {
            log.info("用户token无效.");
            ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.TOKEN_INVALID.getCode(), ErrorCodeEnum.TOKEN_INVALID.getMessage());
        }

    }
}
