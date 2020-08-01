/**
 * 项目名称：quickstart-javase 
 * 文件名：RedisDistributedLock.java
 * 版本信息：
 * 日期：2018年6月13日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.distributed.lock;

import java.util.Collections;
import java.util.Objects;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/*首先Redis的 public String set(final String key, final String value, final String nxxx, final String expx, final int time)方法参数说明：
其中前面两个是key,value值；
nxxx为模式，这里我们设置为NX，意思是说如果key不存在则插入该key对应的value并返回OK，否者什么都不做返回null；
参数expx这里我们设置为PX，意思是设置key的过期时间为time 毫秒
通过tryLock方法尝试获取锁，内部是具体调用Redis的set方法，多个线程同时调用tryLock时候会同时调用set方法，但是set方法本身是保证原子性的，对应同一个key来说，多个线程调用set方法时候只有一个线程返回OK，其它线程因为key已经存在会返回null，所以返回OK的线程就相当与获取到了锁，其它返回null的线程则相当于获取锁失败。
另外这里我们要保证value（requestId）值唯一是为了保证只有获取到锁的线程才能释放锁,这个下面释放锁时候会讲解。
通过lock 方法让使用tryLock获取锁失败的线程本地自旋转重试获取锁，这类似JUC里面的CAS。
Redis有一个叫做eval的函数，支持Lua脚本执行，并且能够保证脚本执行的原子性，也就是在执行脚本期间，其它执行redis命令的线程都会被阻塞。这里解锁时候使用下面脚本：
if redis.call('get', KEYS[1]) == ARGV[1] then 
return redis.call('del', KEYS[1 ]) 
else return 0
end
其中keys[1]为unLock方法传递的key，argv[1]为unLock方法传递的requestId;脚本redis.call('get', KEYS[1])的作用是获取key对应的value值，这里会返回通过Lock方法传递的requetId, 然后看当前传递的RequestId是否等于key对应的值，等于则说明当前要释放锁的线程就是获取锁的线程，则继续执行redis.call('del', KEYS[1])脚本，删除key对应的值，这相当于释放了锁。因为删除后，其他线程中的一个线程在调用set方法发现该key已经不存在了，则会插入对应的value，然后返回OK，这相当于这个线程获取到了锁。
另外可知该锁是不可重入锁。
总结
本文使用redis单实例结合redis的set方法和eval函数实现了一个简单的分布式锁，但是这个实现还是明显有问题的。虽然使用set方法设置了超时时间，以避免线程获取到锁后redis挂了后锁没有被释放的情况，但是超时时间设置为多少合适那？如果设置太小，可能会存在线程获取锁后执行业务逻辑时间大于锁超时时间，那么就会存在逻辑还没执行完，锁已经因为超时自动释放了，而其他线程可能获取到锁，那么之前获取锁的线程的业务逻辑的执行就没有保证原子性。
另外还有一个问题是Lock方法里面是自旋调用tryLock进行重试，这就会导致像JUC中的AtomicLong一样，在高并发下多个线程竞争同一个资源时候造成大量线程占用cpu进行重试操作。这时候其实可以随机生成一个等待时间，等时间到后在进行重试，以减少潜在的同时对一个资源进行竞争的并发量。
*/
/**
 * RedisDistributedLock
 * 
 * https://mp.weixin.qq.com/s/LYZ7JnDoSmx2-P3FGaqCUQ
 * 
 * @author：youngzil@163.com
 * @2018年6月13日 上午9:53:44
 * @since 1.0
 */
public class RedisDistributedLock {

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    private static void validParam(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {

        if (null == jedisPool) {
            throw new IllegalArgumentException("jedisPool obj is null");
        }

        if(Objects.isNull(lockKey) || lockKey.isEmpty()){
            throw new IllegalArgumentException("lock key  is blank");
        }

        if(Objects.isNull(requestId) || requestId.isEmpty()){
            throw new IllegalArgumentException("requestId is blank");
        }

        if (expireTime < 0) {
            throw new IllegalArgumentException("expireTime is not allowed less zero");
        }

    }

    /**
     * 
     * 
     * 
     * @param jedisPool
     * 
     * @param lockKey
     * 
     * @param requestId
     * 
     * @param expireTime
     * 
     * @return
     * 
     */

    public static boolean tryLock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {
        validParam(jedisPool, lockKey, requestId, expireTime);
        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            // String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            String result = "";

            if (LOCK_SUCCESS.equals(result)) {

                return true;

            }

        } catch (Exception e) {

            throw e;

        } finally {

            if (null != jedis) {

                jedis.close();

            }

        }

        return false;

    }

    /**
     * 
     * 
     * 
     * @param jedisPool
     * 
     * @param lockKey
     * 
     * @param requestId
     * 
     * @param expireTime
     * 
     */

    public static void lock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {

        validParam(jedisPool, lockKey, requestId, expireTime);

        while (true) {

            if (tryLock(jedisPool, lockKey, requestId, expireTime)) {

                return;

            }

        }

    }

    /**
     * 
     * 
     * 
     * @param jedisPool
     * 
     * @param lockKey
     * 
     * @param requestId
     * 
     * @return
     * 
     */

    public static boolean unLock(JedisPool jedisPool, String lockKey, String requestId) {

        validParam(jedisPool, lockKey, requestId, 1);

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Jedis jedis = null;

        try {

            jedis = jedisPool.getResource();

            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;

            }

        } catch (Exception e) {
            throw e;

        } finally {

            if (null != jedis) {

                jedis.close();

            }

        }
        return false;
    }

}
