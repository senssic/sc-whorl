package sc.whorl.system.commons.webhandler.handler;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import sc.whorl.system.commons.webhandler.RequestHeaderJsonParam;


public class RequestHeaderJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver
{
    private static Logger logger= LoggerFactory.getLogger(RequestHeaderJsonHandlerMethodArgumentResolver.class);


    @Override
    public boolean supportsParameter(MethodParameter parameter)
    {
        return parameter.hasParameterAnnotation(RequestHeaderJsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
    {
        RequestHeaderJsonParam requestJsonParam = parameter.getParameterAnnotation(RequestHeaderJsonParam.class);

        final HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        //获取注解别名
        String[] aliasNames=getAlias(requestJsonParam, parameter);

        String requestHeader= servletRequest.getHeader(aliasNames[0]);

        if (StringUtils.isBlank(requestHeader)) {
            logger.error("requestHeader:{}为空",aliasNames[0]);
            return null;
        }

        if (2==aliasNames.length){
            String aliasName=aliasNames[1];
            // 利用fastjson转换为对应的类型
            if ( Long.class.isAssignableFrom(parameter.getParameterType())){
                return  JSON.parseObject(requestHeader).getLong(aliasName);
            }else if (String.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getString(aliasName);
            }else if (Integer.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getInteger(aliasName);
            }else if (Boolean.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getBoolean(aliasName);
            }else if (Byte.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getByte(aliasName);
            }else if (Short.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getShort(aliasName);
            }else if (Double.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getDouble(aliasName);
            }else if (Float.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getFloat(aliasName);
            }  else if (BigDecimal.class.isAssignableFrom(parameter.getParameterType())){
                return JSON.parseObject(requestHeader).getBigDecimal(aliasName);
            }else {
                String innerParam= JSON.parseObject(requestHeader).getString(aliasName);
                if (Collection.class.isAssignableFrom(parameter.getParameterType()))
                {
                    return JSON.parseArray(innerParam,parameter.getParameterType());
                }
                return JSON.parseObject(innerParam, parameter.getParameterType());
            }
        }

        if (isJson(requestHeader)){
            return JSON.parseObject(requestHeader, parameter.getParameterType());
        }
        return requestHeader;
    }


    private boolean isJson(String headerParam){
        try{
            JSON.parseObject(headerParam);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    private String[] getAlias(RequestHeaderJsonParam requestJsonParam, MethodParameter parameter)
    {
        String alias = requestJsonParam.value();
        if (StringUtils.isBlank(alias))
        {
            alias = parameter.getParameterName();
            return new String[]{alias};
        }
        return StringUtils.split(alias,".");
    }
}
