package sc.whorl.system.utils.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

public class SpringUtil {

    private static ApplicationContext ac = null;
    private static Environment env = null;

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

    public static void setApplicationContext(ApplicationContext ac) {
        SpringUtil.ac = ac;
    }

    public static void setEnv(Environment env) {
        SpringUtil.env = env;
    }

    /**
     * 通过bean名字获取bean
     */
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }


    /**
     * 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        Assert.hasText(name, "name为空");
        return getApplicationContext().getBean(name, clazz);
    }


    public static String getProperty(String string) {
        return env.getProperty(string);
    }

}