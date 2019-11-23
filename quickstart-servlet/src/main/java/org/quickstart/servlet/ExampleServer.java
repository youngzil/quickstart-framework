/**
 * 项目名称：quickstart-container-jetty 文件名：ExampleServer.java 版本信息： 日期：2018年4月25日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.servlet;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

/**
 * ExampleServer
 * 
 * @author：youngzil@163.com
 * @2018年4月25日 上午10:58:44
 * @since 1.0
 */
public class ExampleServer {

  public static void main(String[] args) throws Exception {

    // http://localhost:8080/s1

    // Server server = new Server(port);
    Server server = new Server();
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8080);
    server.setConnectors(new Connector[] {connector});

    /* ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    // context.addServlet(HelloServlet.class, "/hello");
    context.addServlet(new ServletHolder(new HelloServlet()), "/hello");
    // context.addServlet(ServletContainer.class, "/hello");
    // context.addServlet(AsyncEchoServlet.class, "/echo/*");
    HandlerCollection handlers = new HandlerCollection();
    handlers.setHandlers(new Handler[] {context, new DefaultHandler()});*/

    final WebAppContext handlers = new WebAppContext();
    handlers.setContextPath("/");
    handlers.setParentLoaderPriority(true);
    final String webappDirLocation = "src/main/webapp/";
    handlers.setResourceBase(webappDirLocation);
    handlers.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");

    handlers.setConfigurations(new Configuration[] {new AnnotationConfiguration(), new WebXmlConfiguration(), new WebInfConfiguration(),
        new PlusConfiguration(), new MetaInfConfiguration(), new FragmentConfiguration(), new EnvConfiguration()});

    handlers.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
    handlers.setParentLoaderPriority(true);

    // Config and launch server
    server.setHandler(handlers);
    server.start();
    server.dump(System.err);
    server.join();

  }

}
