package org.quickstart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/23 10:30
 */
@WebServlet(name = "servlet1", urlPatterns = {"/s1", "/s1/*"}, loadOnStartup = 1, initParams = {@WebInitParam(name = "msg", value = "hello world")})
public class HelloServlet extends HttpServlet {

  private String msg;

  public HelloServlet() {
    System.out.println("load on startup");
  }

  @Override
  public void init() throws ServletException {
    super.init();
    msg = this.getInitParameter("msg");
    System.out.println(msg);
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
    System.out.println(msg);
  }

}
