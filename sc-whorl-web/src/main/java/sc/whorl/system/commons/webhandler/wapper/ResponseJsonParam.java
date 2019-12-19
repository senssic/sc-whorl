package sc.whorl.system.commons.webhandler.wapper;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseJsonParam {
    /**
     * 只包含的字段key
     *
     * @return
     */
    String[] include() default {};

    /**
     * 只过滤的字段key
     *
     * @return
     */
    String[] filter() default {};
}

