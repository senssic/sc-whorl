package sc.whorl.system.commons.webhandler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 不可以与ReqeustBody混用
 *  新增Json日期格式属性提取.默认日期格式为为:yyyy-MM-dd HH:mm:ss ,同时可以通过dateFormat设置日期格式.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonParam
{

    public static final String YMDHMS="yyyy-MM-dd HH:mm:ss";
    /**
     * 用于绑定的请求参数名字
     */
    String value() default "" ;
    /**
     * 是否必须，默认是
     */
    boolean required() default true;

    /**
     * 是否是当前json对象
     * 默认false,表示当前josn对象中的某一个值
     * 若是true,则忽略value
     * @return
     */
    boolean current() default false;

    /**
     * 如果是日期类型的,设置其时间.
     * 默认为:yyyy-MM-dd HH:mm:ss
     * @return
     */
    String dateFormat() default YMDHMS;
}
