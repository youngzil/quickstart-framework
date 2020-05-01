/**
 * 项目名称：quickstart-javase 
 * 文件名：HttpProxyTest.java
 * 版本信息：
 * 日期：2018年1月21日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.proxy.http;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * HttpProxyTest 
 *  
 * @author：youngzil@163.com
 * @2018年1月21日 下午3:59:01 
 * @since 1.0
 */
/**
 * 网络代理测试
 * 
 * <pre>
 * 设置代理主机及端口：系统变量(https 需同步设置)
 * 设置代理验证方式：全局代理对象
 * 
 * 
 * https链接错误：
 * Unable to tunnel through proxy. Proxy returns "HTTP/1.0 407 Proxy Authentication Required"
 * 使用全局代理验证解决
 * 
 * </pre>
 * 
 * @author tzz
 * @createDate 2015年7月23日
 * 
 */
public class HttpProxyTest {
    private static String proxyHost = "us01.jiasudu.biz";
    private static int proxyPort = 12488;
    private static String proxyUser = "user";
    private static String proxyPass = "pass";

    public static void main(String[] args) {
        String url = "https://www.google.com/";
        String content = doProxy(url);
        System.out.println("Result :===================\n " + content);
    }

    /**
     * 通过系统变量方式实现代理
     * 
     * @param url
     * @return
     */
    public static String doProxy(String url) {
        // 设置代理服务器地址端口的两种方式
        // 方式一：Java支持以System.setProperty的方式设置http代理及端口
        // 设置系统变量
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", "" + proxyPort);
        // 针对https也开启代理
        System.setProperty("https.proxyHost", proxyHost);
        System.setProperty("https.proxyPort", "" + proxyPort);

        // 方式二：使用Proxy对象，在建立连接时注入到URLConnection即可：
        // 初始化proxy对象
        /* Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        // 创建连接
        URL u = new URL(url);
        URLConnection conn = u.openConnection(proxy);*/

        // 实现用户密码校验的两种方式

        // 方式二：实现Authenticator接口，并注入为全局验证器：
        // 设置默认校验器
        setDefaultAuthentication();

        // 方式一：将校验信息写入http头，将用户名密码进行base64编码之后设置Proxy-Authorization头：
        /*String headerKey = "Proxy-Authorization";  
        String encoded = new String(Base64.encodeBase64((new String(proxyUser + ":" + proxyPass).getBytes())));  
        String headerValue = "Basic " + encoded;  
        conn.setRequestProperty(headerKey, headerValue);  */

        // 开始请求
        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            HttpsURLConnection httpsCon = (HttpsURLConnection) conn;
            httpsCon.setFollowRedirects(true);

            String encoding = conn.getContentEncoding();
            if (StringUtils.isEmpty(encoding)) {
                encoding = "UTF-8";
            }
            InputStream is = conn.getInputStream();
            String content = IOUtils.toString(is, encoding);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 设置全局校验器对象
     */
    public static void setDefaultAuthentication() {
        BasicAuthenticator auth = new BasicAuthenticator(proxyUser, proxyPass);
        Authenticator.setDefault(auth);
    }
}
