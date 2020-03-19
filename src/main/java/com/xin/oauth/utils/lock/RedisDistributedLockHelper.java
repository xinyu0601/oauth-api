package com.xin.oauth.utils.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author xinyu.huang02
 * @date 2020-03-19 15:18
 * @class 通过Redis实现的分布式锁
 */

@Component
@Slf4j
public class RedisDistributedLockHelper extends AbstractDistributedLockHelper {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();

    public static final String UNLOCK_LUA_SCRIPT;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA_SCRIPT = sb.toString();
    }

    /**
     * 通过redis setnx进行加锁操作
     *
     * @param key
     * @param expire
     * @param retryTimes
     * @param sleepMillis
     * @return
     */
    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setLock(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.debug("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setLock(key, expire);
        }
        return result;
    }

    /**
     * 通过setNX指令设置锁
     *
     * @param key
     * @param expire
     * @return
     */
    private boolean setLock(String key, long expire) {
        try {
            String result = redisTemplate.execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    return commands.set(key, uuid, "NX", "PX", expire);
                }
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("set redis occured an exception", e);
        }
        return false;
    }

    /**
     * 通过Lua脚本删除锁，释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，
     * 此时有可能已经被另外一个线程持有锁，所以不能直接删除
     *
     * @param key
     * @return
     */
    @Override
    public boolean releaseLock(String key) {
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(lockFlag.get());
            Long result = redisTemplate.execute(new RedisCallback<Long>() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    Object nativeConnection = connection.getNativeConnection();
                    // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                    // 集群模式
                    if (nativeConnection instanceof JedisCluster) {
                        return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, args);
                    }
                    // 单机模式
                    else if (nativeConnection instanceof Jedis) {
                        return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA_SCRIPT, keys, args);
                    }
                    return 0L;
                }
            });
            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        }
        return false;
    }

}