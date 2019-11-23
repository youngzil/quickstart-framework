package org.quickstart.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/23 12:24
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/time"})
public class TimeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");
    response.getWriter().write(new Date().toString());
  }
}
