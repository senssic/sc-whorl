package sc.whorl.system.commons.webhandler.filter;


import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HessianHeaderServletFilter implements Filter {

    public static final String CHANNEL = "channel";
    public static final String SUBCHANNEL = "subChannel";
    public static final String SERVICE_VERSION = "serviceVersion";

    public static final String CHANNEL_DEF = "noneChannel";
    public static final String SUBCHANNEL_DEF = "noneSubChannel";
    public static final String SERVICE_VERSION_DEF = "0";

    private String channel;
    private String subChannel;
    private String serviceVersion;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //设置默认值
        channel = StringUtils.defaultIfBlank(filterConfig.getInitParameter(CHANNEL), CHANNEL_DEF);
        subChannel = StringUtils.defaultIfBlank(filterConfig.getInitParameter(SUBCHANNEL), SUBCHANNEL_DEF);
        serviceVersion = StringUtils.defaultIfBlank(filterConfig.getInitParameter(SERVICE_VERSION), SERVICE_VERSION_DEF);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * 拦截器相关配置
         */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //清理本次拦截器相关

    }
}
