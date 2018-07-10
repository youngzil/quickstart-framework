/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Watched.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.observer;

/**
 * Watched
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月26日 下午11:51:20
 * @since 1.0
 */
public interface Watched {
    public void addWatcher(Watcher watcher);

    public void removeWatcher(Watcher watcher);

    public void notifyWatchers();
}
