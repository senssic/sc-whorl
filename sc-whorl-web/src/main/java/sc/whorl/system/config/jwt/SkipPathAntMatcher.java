package sc.whorl.system.config.jwt;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.ObjectUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/***
 *  配置忽略url 和认证的url
 */
public class SkipPathAntMatcher implements RequestMatcher {
    private List<String> pathsToSkip;

    public SkipPathAntMatcher(List<String> pathsToSkip) {
        this.pathsToSkip = pathsToSkip;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        System.out.println("请求路径-->" + request.getRequestURL());
        if (!ObjectUtils.isEmpty(pathsToSkip)) {
            for (String s : pathsToSkip) {
                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(s);
                if (antPathRequestMatcher.matches(request)) {
                    return true;
                }
            }
        }
        return false;
    }
}
