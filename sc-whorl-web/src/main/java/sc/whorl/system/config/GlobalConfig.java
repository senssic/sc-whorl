package sc.whorl.system.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;

import sc.whorl.system.commons.preventresubmit.CacheKeyGenerator;
import sc.whorl.system.commons.preventresubmit.LockKeyGenerator;
import sc.whorl.system.commons.webhandler.filter.HessianHeaderServletFilter;
import sc.whorl.system.commons.webhandler.handler.RequestAttrHandlerMethodArgumentResolver;
import sc.whorl.system.commons.webhandler.handler.RequestHeaderJsonHandlerMethodArgumentResolver;
import sc.whorl.system.commons.webhandler.handler.RequestJsonHandlerMethodArgumentResolver;
import sc.whorl.system.commons.webhandler.handler.SessionScopeMethodArgumentResolver;
import sc.whorl.system.commons.webhandler.wapper.RequestBodyThreadLocalInterceptor;

@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Value("${spring.http.multipart.location:/}")
    private String multipartLocation;


    /**
     * 全局日期格式化处理
     *
     * @return
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }


    /**
     * 基于redis的分布式锁
     * @return
     */
    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }

    /**
     * 为限流提供redis模板
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 静态资源处理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+multipartLocation+"/upload/");
        registry.addResourceHandler("/resoures/**").addResourceLocations("classpath:/resoures/");

    }

    /**
     * 编码过滤器
     */
    @Bean
    @Order(Integer.MIN_VALUE)
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registration.addUrlPatterns("/");
        registration.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST));
        registration.setFilter(characterEncodingFilter);
        return registration;
    }

    /**
     * 请求头处理器 order从小到大
     */
    @Bean
    @Order(Integer.MAX_VALUE)
    public FilterRegistrationBean headerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        HessianHeaderServletFilter hessianHeaderServletFilter = new HessianHeaderServletFilter();
        registration.addUrlPatterns("/*");
        Map<String, String> map = new HashMap<>();
        map.put("channel", "noneChannel");
        map.put("subChannel", "noneSubChannel");
        map.put("serviceVersion", "0");
        registration.setInitParameters(map);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(hessianHeaderServletFilter);
        return registration;
    }

    /**
     * 文件上传
     */
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        //5M
        commonsMultipartResolver.setMaxUploadSize(5242880L);
        commonsMultipartResolver.setMaxInMemorySize(10485760);
        commonsMultipartResolver.setResolveLazily(true);
        return commonsMultipartResolver;
    }


    /**
     * 配置静态资源的处理
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



    /**
     * 允许所有请求跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE").maxAge(3600);
    }

    /**
     * 自定义注解处理器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestJsonHandlerMethodArgumentResolver());
        argumentResolvers.add(new RequestAttrHandlerMethodArgumentResolver());
        argumentResolvers.add(new RequestHeaderJsonHandlerMethodArgumentResolver());
        argumentResolvers.add(new SessionScopeMethodArgumentResolver());

    }

    /**
     * 自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestBodyThreadLocalInterceptor()).addPathPatterns("/**");
    }


}
