/**
 * 项目名称：quickstart-concurrentlinkedhashmap 
 * 文件名：Tets.java
 * 版本信息：
 * 日期：2018年5月21日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.concurrentlinkedhashmap;

/**
 * Tets 
 * 
 *  主要用于测试concurrentlinkedhashmap在并发的情况下，一边读，一遍update
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月21日 下午11:39:05 
 * @since 1.0
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

/**
 * 
 * @author enxiang.wex
 * @version $Id: Tets.java, v 0.1 2015-7-22 下午11:42:09 enxiang.wex Exp $
 */
public class Tets {

    private static ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
    private static ConcurrentLinkedHashMap<Integer, DO> cache = new ConcurrentLinkedHashMap.Builder<Integer, DO>().maximumWeightedCapacity(10).weigher(Weighers.singleton()).build();

    public static void main(String[] args) throws Exception {
        cache.put(1, new DO(1));
        cache.put(2, new DO(2));
        cache.put(3, new DO(3));
        cache.put(4, new DO(4));
        cache.put(5, new DO(5));
        cache.put(6, new DO(6));
        cache.put(7, new DO(7));
        cache.put(8, new DO(8));
        cache.put(9, new DO(9));
        cache.put(10, new DO(10));
        //
        List<MyTread> list = new ArrayList<MyTread>();
        for (int i = 0; i < 20; i++) {
            MyTread d = new MyTread(cache);
            d.start();
            list.add(d);
        }
        Random rand = new Random(20);
        for (;;) {
            Iterator<DO> iterator = new CopiedIterator<DO>(cache.values().iterator());
            while (iterator.hasNext()) {
                DO item = iterator.next();
                item.setParam(rand.nextInt(1000));
            }
            Iterator<DO> iterator2 = cache.values().iterator();
            Thread.sleep(10);
            while (iterator2.hasNext()) {
                DO item = iterator2.next();
                System.out.print(item.getParam() + "  ");
            }
            System.out.print("\n");
        }

    }

    /**
     * Getter method for property <tt>map</tt>.
     * 
     * @return property value of map
     */
    public ConcurrentMap<String, String> getMap() {
        return map;
    }

    /**
     * Setter method for property <tt>map</tt>.
     * 
     * @param map value to be assigned to property map
     */
    public void setMap(ConcurrentMap<String, String> map) {
        this.map = map;
    }

}
