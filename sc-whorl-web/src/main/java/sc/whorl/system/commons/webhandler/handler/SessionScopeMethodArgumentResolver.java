package sc.whorl.system.commons.webhandler.handler;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import sc.whorl.system.commons.webhandler.SessionScope;


public class SessionScopeMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //让方法和参数，两种target通过
        if (parameter.hasParameterAnnotation(SessionScope.class)) {
            return true;
        } else if (parameter.getMethodAnnotation(SessionScope.class) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String annoVal = null;

        if(parameter.getParameterAnnotation(SessionScope.class)!=null){
            logger.debug("param anno val::::"+parameter.getParameterAnnotation(SessionScope.class).value());
            annoVal = parameter.getParameterAnnotation(SessionScope.class).value();
        }else if(parameter.getMethodAnnotation(SessionScope.class)!=null){
            logger.debug("method anno val::::"+parameter.getMethodAnnotation(SessionScope.class).value());
            annoVal = parameter.getMethodAnnotation(SessionScope.class)!=null? StringUtils.defaultString(parameter.getMethodAnnotation(SessionScope.class).value()) : StringUtils.EMPTY;
        }

        if (webRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION) != null){
            return webRequest.getAttribute(annoVal, RequestAttributes.SCOPE_SESSION);
        }
        else
            return null;
    }

    final Logger logger = LoggerFactory.getLogger(SessionScopeMethodArgumentResolver.class);
}
