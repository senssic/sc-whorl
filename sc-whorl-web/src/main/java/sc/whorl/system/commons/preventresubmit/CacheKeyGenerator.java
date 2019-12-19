package sc.whorl.system.commons.preventresubmit;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @param pjp PJP
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}