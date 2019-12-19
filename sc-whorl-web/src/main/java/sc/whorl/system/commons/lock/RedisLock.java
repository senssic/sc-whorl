package sc.whorl.system.commons.lock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import sc.whorl.system.commons.preventresubmit.RedisLockHelper;
import sc.whorl.system.utils.spring.SpringUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:qiss
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisLock {

    /**
     * 获取锁
     *
     * @param lockKey
     *         默认5秒超时过期
     * @return
     */
    public static boolean tryLock(final String lockKey) {
        return SpringUtil.getBean(RedisLockHelper.class).tryLock(lockKey, UUID.randomUUID().toString(), 5, TimeUnit.SECONDS);
    }

    /**
     * 获取锁
     *
     * @param lockKey
     * @param time
     *         过期时间(秒)
     * @return
     */
    public static boolean tryLock(final String lockKey, final long time) {
        return SpringUtil.getBean(RedisLockHelper.class).tryLock(lockKey, UUID.randomUUID().toString(), time, TimeUnit.SECONDS);
    }

    /**
     * 根据加锁key释放相应锁
     *
     * @param lockKey
     */
    public static void unLock(final String lockKey) {
        SpringUtil.getBean(RedisLockHelper.class).unlock(lockKey);
    }


}
