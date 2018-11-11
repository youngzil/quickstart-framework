/**
 * 项目名称：quickstart-logging 
 * 文件名：FileThreadServlet.java
 * 版本信息：
 * 日期：2017年12月5日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.logging.all;

import javax.servlet.http.HttpServlet;

/**
 * FileThreadServlet
 * 
 * @author：youngzil@163.com
 * @2017年12月5日 下午4:31:02
 * @since 1.0
 */
public class FileThreadServlet extends HttpServlet {

    public void init() {
        String prefix = getServletContext().getRealPath("/");
        String log4jFile = getInitParameter("log4jConfigLocation");// web.xml中配置
        if (log4jFile != null) {
            // PropertyEditor.configure(prefix + log4jFile);
        }
    }
}
