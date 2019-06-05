/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HelloServlet.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HelloServlet
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 上午11:29:31
 * @since 1.0
 */
public class HelloServlet extends HttpServlet {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("test over");
        out.flush();
        out.close();
    }
}
