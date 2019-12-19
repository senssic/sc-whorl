package sc.whorl;


import com.spring4all.swagger.EnableSwagger2Doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//启用aop
@EnableAspectJAutoProxy
//@ComponentScan指明对象扫描范围，默认指扫描当前启动类所在的包里的对象，如果当前启动类没有包，则在启动时会报错：Your ApplicationContext is unlikely to start due to a @ComponentScan of the default package错误。
//因为启动类不能直接放在main/java文件夹下，必须要建一个包把它放进去或者使用@ComponentScan指明要扫描的包,@ComponentScan配置多个@ComponentScan
//开启事务控制,默认使用DataSourceTransactionManager,如果有多个事务管理器需要自己配置
@EnableTransactionManagement
@MapperScan(basePackages = "sc.whorl.logic.domain.dao")
@EnableSwagger2Doc
//Boot根据下面的顺序去侦测缓存提供者：Generic,JCache (JSR-107),EhCache 2.x,Hazelcast,Infinispan,Redis,Guava,Simple
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}