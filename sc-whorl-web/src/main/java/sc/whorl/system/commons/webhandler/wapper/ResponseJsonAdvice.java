package sc.whorl.system.commons.webhandler.wapper;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;


/**
 * 对controller的返回值做一些处理,比如加密,返回格式,日志记录等
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Order(10)
@ControllerAdvice(basePackages = "sc.whorl.web")
public class ResponseJsonAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(ResponseJsonParam.class);
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseJsonParam responseJsonParam = returnType.getMethodAnnotation(ResponseJsonParam.class);
        if (responseJsonParam != null) {
            String[] filter = responseJsonParam.filter();
            String[] include = responseJsonParam.include();
            SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter(body.getClass(), StringUtils.trimArrayElements(include));
            simplePropertyPreFilter.getExcludes().addAll(Arrays.asList(StringUtils.trimArrayElements(filter)));
            return JSONObject.parse(JSONObject.toJSONString(body, simplePropertyPreFilter));
        }
        return body;
    }
}
