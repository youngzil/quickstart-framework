/**
 * 项目名称：quickstart-xbean 
 * 文件名：SpringBeanReloadJob.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.hot.loading.xml;

/**
 * SpringBeanReloadJob 
 *  
 * @author：youngzil@163.com
 * @2017年3月19日 下午7:47:35 
 * @version 1.0
 */
/**
 * declaration： 设计线程，保持对资源文件的不断探测 author wenkangqiang date 2016年3月5日
 */
public class SpringBeanReloadJob implements Runnable {

    @Override
    public void run() {
        try {
            XmlRefreshWebApplicationContext applicationContext = (XmlRefreshWebApplicationContext) ContextLoader.getCurrentWebApplicationContext();
            applicationContext.reload();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Sping重载配置失败");
        }

    }

}
