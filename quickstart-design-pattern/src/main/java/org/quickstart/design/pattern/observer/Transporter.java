/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Transporter.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Transporter
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:53:06
 * @since 1.0
 */
public class Transporter implements Watched {
    private List<Watcher> list = new ArrayList<Watcher>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notifyWatchers() {
        for (Watcher watcher : list) {
            watcher.update();
        }
    }

}
