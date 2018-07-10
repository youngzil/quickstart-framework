package com.quickstart.test.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.registry.Registry;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.sun.net.httpserver.HttpContext;

public class Test {

    /**
     * 多线程并发访问服务器
     * 
     * 每次http连接需要三次握手，开销很大，http/1.1默认支持连接复用；
     * 
     * PoolingHttpClientConnectionManager 允许管理器限制最大连接数 ，还允许每个路由器针对某个主机限制最大连接数。
     * 
     * 如下：setDefaultMaxPerRoute(3)之后，每次并发3个访问，所以打印输出是每次输出三个"test",验证了http连接管理器生效 ；
     * 
     */
    private static void test9() throws InterruptedException, ExecutionException, IOException {

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(200);// 设置最大连接数200
        connManager.setDefaultMaxPerRoute(3);// 设置每个路由默认连接数
        HttpHost host = new HttpHost("webservice.webxml.com.cn");// 针对的主机
        connManager.setMaxPerRoute(new HttpRoute(host), 5);// 每个路由器对每个服务器允许最大5个并发访问

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();

        String[] urisToGet = {"http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo", "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo",
                "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo", "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo",
                "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo", "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo",
                "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo", "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo",
                "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo", "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx/getDatabaseInfo"};

        GetThread[] threads = new GetThread[urisToGet.length];

        for (int i = 0; i < threads.length; i++) {
            HttpGet httpget = new HttpGet(urisToGet[i]);
            threads[i] = new GetThread(httpClient, httpget);
        }

        for (int j = 0; j < threads.length; j++) {
            threads[j].start();
        }

        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }

    }

    static class GetThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpClientContext context;
        private final HttpGet httpget;

        public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpget = httpget;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(httpget, context);
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        System.out.println("test");
                    }
                } finally {
                    response.close();
                }
            } catch (ClientProtocolException ex) {
            } catch (IOException ex) {
            }
        }
    }

    /**
     * 清空失效连接：
     * 
     * 连接的有效性检测是所有连接池都面临的一个通用问题，大部分HTTP服务器为了控制资源开销，并不会 永久的维护一个长连接，而是一段时间就会关闭该连接。放回连接池的连接，如果在服务器端已经关闭，客 户端是无法检测到这个状态变化而及时的关闭Socket的。这就造成了线程从连接池中获取的连接不一定是有效的。 这个问题的一个解决方法就是在每次请求之前检查该连接是否已经存在了过长时间，可能已过期。
     * 但是这个方法会使得每次请求都增加额外的开销。HTTP Client4.0的ThreadSafeClientConnManager 提供了 closeExpiredConnections()方法和closeIdleConnections()方法来解决该问题。 前一个方法是清除连接池中所有过期的连接，至于连接什么时候过期可以设置，设置方法将在下面提到，
     * 而后一个方法则是关闭一定时间空闲的连接，可以使用一个单独的线程完成这个工作。
     */
    private static void test10() {

        HttpClientConnectionManager manager = new BasicHttpClientConnectionManager();

        new IdleConnectionMonitorThread(manager).start();// 启动线程，5秒钟清空一次失效连接

        CloseableHttpClient client = HttpClients.custom().setConnectionManager(manager).build();

        URI uri = null;// 构建uri实体
        try {
            uri = new URIBuilder().setScheme("http").setHost("webservice.webxml.com.cn").setPath("/WebServices/MobileCodeWS.asmx/getDatabaseInfo").setParameter("", "").build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpPost post = new HttpPost(uri);

        try {
            client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 这个线程负责使用连接管理器清空失效连接和过长连接
    private static class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        System.out.println("清空失效连接...");
                        // 关闭失效连接
                        connMgr.closeExpiredConnections();
                        // 关闭空闲超过30秒的连接
                        connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    /**
     * 
     * http长连接策略： 可以根据须要定制所须要的长连接策略，可根据服务器指定的超时时间，也可根据主机名自己指定超时时间；
     */
    private static void test11() {

        // 参考第一章的DefaultConnectionKeepAliveStrategy类
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // 遍历response的header
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));

                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {// 如果头部包含timeout信息，则使用
                        try {
                            // 超时时间设置为服务器指定的值
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                // 获取主机
                HttpHost target = (HttpHost) ((org.apache.http.protocol.HttpContext) context).getAttribute(HttpClientContext.HTTP_TARGET_HOST);
                if ("webservice.webxml.com.cn".equalsIgnoreCase(target.getHostName())) {
                    // 如果访问webservice.webxml.com.cn主机则设置长连接时间为5秒
                    return 5 * 1000;
                } else {
                    // 其他为30秒
                    return 30 * 1000;
                }
            }

            @Override
            public long getKeepAliveDuration(HttpResponse arg0, org.apache.http.protocol.HttpContext arg1) {
                // TODO Auto-generated method stub
                return 0;
            }
        };
        CloseableHttpClient client = HttpClients.custom().setKeepAliveStrategy(myStrategy).build();

        URI uri = null;// 构建uri实体
        try {
            uri = new URIBuilder().setScheme("http").setHost("webservice.webxml.com.cn").setPath("/WebServices/MobileCodeWS.asmx/getDatabaseInfo").setParameter("", "").build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpPost post = new HttpPost(uri);

        try {

            CloseableHttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println("返回的结果=====" + result);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test12() throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        /**
         * Http连接使用java.net.Socket类来传输数据。这依赖于ConnectionSocketFactory接口来创建、 初始化和连接socket。
         */
        HttpClientContext clientContext = HttpClientContext.create();
        PlainConnectionSocketFactory sf = PlainConnectionSocketFactory.getSocketFactory();// Plain：简洁的
        Socket socket = sf.createSocket(clientContext);
        HttpHost target = new HttpHost("localhost");
        InetSocketAddress remoteAddress = new InetSocketAddress(InetAddress.getByAddress(new byte[] {127, 0, 0, 1}), 80);
        sf.connectSocket(1000, socket, target, remoteAddress, null, clientContext);

        // 创建通用socket工厂
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();

        // Creates default SSL context based on system
        // properties(HttpClient没有自定义任何加密算法。它完全依赖于Java加密标准)
        SSLContext sslcontext = SSLContexts.createSystemDefault();

        // 创建ssl socket工厂
        LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);

        // 自定义的socket工厂类可以和指定的协议（Http、Https）联系起来，用来创建自定义的连接管理器。
        Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

        // //////////////////////////////////////////////////////////
        // 自定义SSLContext
        // KeyStore myTrustStore = null;
        // SSLContext sslContext = SSLContexts.custom()
        // .useTLS() //安全传输层协议（TLS）用于在两个通信应用程序之间提供保密性和数据完整性。该协议由两层组成： TLS
        // 记录协议（TLS Record）和 TLS 握手协议（TLS Handshake）。
        // .useSSL()
        // .loadTrustMaterial(myTrustStore)
        // .build();
        // SSLConnectionSocketFactory sslsf = new
        // SSLConnectionSocketFactory(sslContext);
        // //////////////////////////////////////////////////////////////////

    }

    /**
     * HttpClient代理服务器配置
     * 
     * 使用代理服务器最简单的方式就是，指定一个默认的proxy参数。 我们也可以让HttpClient去使用jre的代理服务器。 又或者，我们也可以手动配置RoutePlanner，这样就可以完全控制Http路由的过程。
     */
    private static void test13() {

        // 1、使用默认的代理
        HttpHost proxy = new HttpHost("8.8.8.8", 8080);
        DefaultProxyRoutePlanner routePlanner1 = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient httpclient1 = HttpClients.custom().setRoutePlanner(routePlanner1).build();

        // 2、使用jre的代理
        SystemDefaultRoutePlanner routePlanner2 = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
        CloseableHttpClient httpclient2 = HttpClients.custom().setRoutePlanner(routePlanner2).build();

        // 3、自定义代理
        HttpRoutePlanner routePlanner3 = new HttpRoutePlanner() {
            public HttpRoute determineRoute(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {

                return new HttpRoute(target, null, new HttpHost("8.8.8.8", 8080), "https".equalsIgnoreCase(target.getSchemeName()));
            }

            @Override
            public HttpRoute determineRoute(HttpHost arg0, HttpRequest arg1, org.apache.http.protocol.HttpContext arg2) throws HttpException {
                // TODO Auto-generated method stub
                return null;
            }
        };
        CloseableHttpClient httpclient3 = HttpClients.custom().setRoutePlanner(routePlanner3).build();

    }
}
