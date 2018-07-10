/**
 * 项目名称：quickstart-javase 
 * 文件名：MonitorSample.java
 * 版本信息：
 * 日期：2017年6月27日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.guava;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.Monitor;

/**
 * MonitorSample 
 *  
 * @author：yangzl@asiainfo.com
 * @2017年6月27日 下午10:16:21 
 * @version 1.0
 */
/**
 * 通过Monitor的Guard进行条件阻塞
 */
public class MonitorSample {
    private List<String> list = new ArrayList<String>();
    private static final int MAX_SIZE = 10;
    private Monitor monitor = new Monitor();

    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity); // Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }

    public void addToList2(String item) throws InterruptedException {
        monitor.enterIf(listBelowCapacity); // Guard(形如Condition)，不满足则阻塞，而且我们并没有在Guard进行任何通知操作
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }

    // 其他的Monitor访问方法：
    // Monitor.enter //进入Monitor块，将阻塞其他线程直到Monitor.leave
    // Monitor.tryEnter //尝试进入Monitor块，true表示可以进入, false表示不能，并且不会一直阻塞
    // Monitor.tryEnterIf //根据条件尝试进入Monitor块

}
