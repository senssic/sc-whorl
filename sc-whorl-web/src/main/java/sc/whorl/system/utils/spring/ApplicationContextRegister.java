package sc.whorl.system.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 使springUtil生效
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
    @Autowired
    private Environment env;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setApplicationContext(applicationContext);
        SpringUtil.setEnv(env);
    }
}