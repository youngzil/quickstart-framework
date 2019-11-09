package org.quickstart.apache.commons.pool2;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.EvictionConfig;
import org.apache.commons.pool2.impl.EvictionPolicy;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:35
 */
public class MyEvictionPolicy implements EvictionPolicy<User> {
  public boolean evict(EvictionConfig config, PooledObject<User> underTest,
      int idleCount) {
    /**
     * config : idleEvictTime 的值由setMinEvictableIdleTimeMillis设置  默认：1800000
     * 			idleSoftEvictTime setSoftMinEvictableIdleTimeMillis 默认：1800000
     * 			minIdle setMinIdlePerKey 如果MinIdlePerKey > MaxIdlePerKey 则取 MaxIdlePerKey 否则取 MinIdlePerKey
     * 不设置也可以有默认值的
     */
    System.out.println("MyEvictionPolicy");
    //返回true代表要 清除
    return true;
  }

}
