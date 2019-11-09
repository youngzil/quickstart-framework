package org.quickstart.apache.commons.pool2;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.commons.pool2.proxy.CglibProxySource;
import org.apache.commons.pool2.proxy.ProxiedKeyedObjectPool;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:35
 */
public class Pool2Test {

  private static final Object LOCK = new Object();
  public static void main(String[] args) throws InterruptedException {
    final KeyedObjectPool<String, User> pool = getGenericKeyedObjectPool();
    //final GenericKeyedObjectPool<String, User> pool = new GenericKeyedObjectPool<String, User>(factory, config);
    //addObject 相当于一种预加载  把预先创建好的对象放在pool里面
    try {
      pool.addObject("aa");
      pool.addObject("aa");
      pool.addObject("aa");
      pool.addObject("aa");
      pool.addObject("aa");
      pool.addObject("aa");
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    //TimeUnit.SECONDS.sleep(2);
    //多线程测试
		/*for(int i = 0;i < 20;i++){
			new Thread(new Runnable() {
				public void run() {
					for(int j = 0;j < 20;j++){
						try {
							String key = "aa" + j;
							synchronized(LOCK){
								////////////////////////////////////////////////////////////
								//如果要调用returnObject方法 那么 borrowObject returnObject必需在一个同步块里(如果是多线程环境的话) 原因看下面returnObject注释
								User user = pool.borrowObject(key);
								//不能连续调用returnObject否则会抛异常 returnObject方法会非 PooledObjectState.ALLOCATED的对象变为PooledObjectState.RETURNING
								pool.returnObject(key, user);
								/////////////////////////////////////////////////////////////
								System.out.println(user);
							}
						} catch (NoSuchElementException e) {
							e.printStackTrace();
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}*/
    //单线程测试
    for(int i = 0;i < 20;i++){
      try {
        String key = "aa";
        User user = pool.borrowObject(key);
        //如果第一次从缓存里没取到那么再次获取 如果在 borrowMaxWaitMillis没有获取到则抛异常 前提 blockWhenExhausted设置为true
        //User user = pool.borrowObject(key,2);
        //if(i == 9){
        //	System.out.println();
        //}
        //不能连续调用returnObject否则会抛异常 returnObject方法会非 PooledObjectState.ALLOCATED的对象变为PooledObjectState.RETURNING
        pool.returnObject(key, user);
        //如果用了代理(ProxiedKeyedObjectPool)的话 那么 调用returnObject后 该值不可用 用的话 会报错的 因为returnObject 会把原始值赋空值
        System.out.println(user);
      } catch (NoSuchElementException e) {
        e.printStackTrace();
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    TimeUnit.MINUTES.sleep(10);
  }

  private static KeyedObjectPool<String, User> getProxiedKeyedObjectPool(){
    KeyedObjectPool<String, User> p = getGenericKeyedObjectPool();
    CglibProxySource<User> proxy = new CglibProxySource<User>(User.class);
    KeyedObjectPool<String, User> pool = new ProxiedKeyedObjectPool<String, User>(p,proxy);
    return pool;
  }

  private static KeyedObjectPool<String, User> getGenericKeyedObjectPool(){
    KeyedPooledObjectFactory<String, User> factory = new MyKeyedPooledObjectFactory();

    GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
    /**
     * 如果pool里没有存储(如果要存储得调用returnObject才行因为borrowObject调用完后会调用deregister方法移除缓存)
     * 那么没辞都会从工厂里获取对象(对于pool来说就是创建) 这里指每个key最多能创建多少个对象 默认为8(当到达限制后线程会等待) 负数代表不限制
     */
    config.setMaxTotalPerKey(-1);
    config.setMaxTotal(-1);//最多能存储多少个数据 负数代表不限制 默认-1
    config.setMaxWaitMillis(-1);//和borrowObject(key,2);第二个参数一个意义
    config.setFairness(false);//设置锁的模式  默认为非公平所
    //缓存里最多能存多少个数据和MaxTotal不同的是 MaxTotal是针对所有key 这个是针对单个key允许存多少个数据 如果设置为0的话addObject是没作用的哦
    config.setMaxTotalPerKey(8);
    //config.setLifo(true);//后进先出法 用于pool缓存
    //还是弄默认吧。。。。。。。。。在取的时候因为缓存中有数据所以没有创建 所以导致调用returnObject方法获取不到缓存而报错 //最好是CPU核数*2吧 好像都是这么玩的 比如netty
    config.setMaxIdlePerKey(8);//(该配置属于returnObject方法)如果maxIdlePerKey 大于预加载里的数量(同一个key) 那么预加载的作用失效(前提是你调用returnObject方法)
    config.setBlockWhenExhausted(true);//默认为TRUE 请看单线程测试用例
    //config.setTestOnReturn(true);//针对returnObject方法 默认为false 当设置为true时会调用工厂的validateObject方法 若验证失败则调用工厂方法的destroyObject方法
    //该参数大于0(默认-1) 启动清楚数据作业 该功能需要setEvictionPolicyClassName支持
    config.setTimeBetweenEvictionRunsMillis(1000 * 10);
    config.setTestWhileIdle(true);//(默认为false)代表如果evictionPolicyClassName验证失败 是否需要调用工厂的validateObject方法 如果返回false则清楚数据
    config.setEvictionPolicyClassName(MyEvictionPolicy.class.getName());//默认DefaultEvictionPolicy
    /**
     * SynchronizedKeyedObjectPool所有方法都加了锁
     */
    KeyedObjectPool<String, User> pool = new GenericKeyedObjectPool<String, User>(factory, config);
    return pool;
  }

}
