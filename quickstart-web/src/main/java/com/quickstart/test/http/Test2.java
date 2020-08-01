package com.quickstart.test.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 类名： NewsInfoLoad 包名： com.newsTest.infoload 作者： zhouyh 时间： 2014-9-4 上午08:47:06 描述： 新闻下载类
 */
public class Test2 {
    // 创建httpclient实例
    private CloseableHttpClient httpClient = null;

    /**
     * 
     * 私有的构造函数 作者：zhouyh
     */
    private Test2() {
        initHttpClient();
    }

    /**
     * 
     * 方法名：initHttpClient 作者：zhouyh 创建时间：2014-9-4 上午08:53:19 描述：创建httpClient连接池，并初始化httpclient
     */
    private void initHttpClient() {
        // 创建httpclient连接池
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(1000); // 设置连接池线程最大数量
        httpClientConnectionManager.setDefaultMaxPerRoute(8); // 设置单个路由最大的连接线程数量
        // 创建http request的配置信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(300).setSocketTimeout(300).build();
        // 设置重定向策略
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
        // 初始化httpclient客户端
        httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager).setDefaultRequestConfig(requestConfig).setUserAgent("User-Agent: Mozilla/5.0 (Linux; X11)")
                .setRedirectStrategy(redirectStrategy).build();
    }

    /******** 单例模式声明开始 ********************/
    // 类初始化时，自行实例化，饿汉式单例模式
    private static final Test2 test = new Test2();

    /**
     * 
     * 方法名：getNewsInfoLoadInstance 作者：zhouyh 创建时间：2014-9-4 上午08:52:19 描述：单例的静态方法
     * 
     * @return
     */
    public static Test2 getTest2Instance() {
        return test;
    }

    /************************ 单例模式声明结束 ********/

    /**
     * 
     * 方法名：loadSrc 作者：zhouyh 创建时间：2014-9-4 上午09:04:40 描述：根据给定的url下载信息
     * 
     * @param url
     * @return
     */
    public String loadSrc(String url) {
        String src = "";
        if (null == url || "".equals(url)) {
            return src; // 如果url为空或者null，则返回src空值
        }
        // 创建httpclient请求方式
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 获得响应的消息实体
            HttpEntity entity = response.getEntity();
            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            // 根据响应状态码进行处理
            switch (statusCode) {
                case 200:
                    // 请求返回成功
                    /**
                     * inputstream转换成byte数组，然后将这个byte数组转成字符串（随便哪种编码方式） 然后解析字符串中的编码方式。再利用这种编码方式将之前的byte数组转成正确的网页字符串
                     */
                    InputStream inputStream = entity.getContent();
                    byte[] contentBytes = IOUtils.toByteArray(inputStream);
                    src = new String(contentBytes, "gb2312");
                    // 获得响应字符集编码
                    ContentType contentType = ContentType.getOrDefault(entity);
                    String charSet = "";
                    try {
                        Charset charset = contentType.getCharset();
                        if (charset != null) {
                            charSet = charset.toString();
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    // 如果没有获取到字符编码则从meta标签中获取
                    if (charSet == null || charSet.equals("")) {
                        // 判断页面的编码方式
                        Document document = Jsoup.parse(src);

                        Elements elements = document.select("meta");
                        for (Element metaElement : elements) {
                            if (metaElement != null && StringUtils.isNotBlank(metaElement.attr("http-equiv")) && metaElement.attr("http-equiv").toLowerCase().equals("content-type")) {
                                String content = metaElement.attr("content");
                                charSet = getCharSet(content);
                                break;
                            }
                        }
                    }
                    // 用获取的编码对contentBytes进行重新编码
                    if (!charSet.equalsIgnoreCase("gb2312")) {
                        src = new String(contentBytes, charSet);
                    }
                    // 去除特殊标签
                    src = replaceStr(src);

                    break;

                default:
                    break;
            }

            // 关闭httpEntity流
            EntityUtils.consume(entity);

        } catch (ClientProtocolException e) {
            // TODO 这里写异常处理的代码
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 这里写异常处理的代码
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    // 关闭response
                    response.close();
                } catch (IOException e) {
                    // TODO 这里写异常处理的代码
                    e.printStackTrace();
                }
            }
        }

        return src;
    }

    /**
     * 
     * 方法名：getCharSet 作者：zhouyh 创建时间：2014-9-4 上午09:57:58 描述：根据正则获取正文编码方式
     * 
     * @param content
     * @return
     */
    private String getCharSet(String content) {
        String regex = ".*charset=([^;]*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find())
            return matcher.group(1);
        else
            return null;
    }

    /**
     * 
     * 方法名：replaceStr 作者：zhouyh 创建时间：2014-9-4 上午09:03:53 描述：去除源码中的特殊字符
     * 
     * @param src
     * @return
     */
    public String replaceStr(String src) {
        if (Objects.isNull(src) || src.isEmpty())
            return null;
        src = src.replaceAll("<!--", "");
        src = src.replaceAll("-->", "");
        src = src.replaceAll("<", "<");
        src = src.replaceAll(">", ">");
        src = src.replaceAll("\"", "\"");
        src = src.replaceAll(" ", " ");
        src = src.replaceAll("&", "&");
        return src;
    }

    /**
     * 方法名：main 作者：zhouyh 创建时间：2014-9-4 上午08:47:06 描述：TODO(这里用一句话描述这个方法的作用)
     * 
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "http://www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd=%E6%AF%9B%E5%AE%81%E5%90%B8%E6%AF%92%E8%A2%AB%E6%8A%93&rsv_idx=2";

        Test2 newsLoad = Test2.getTest2Instance();

        System.out.println(newsLoad.loadSrc(url));
    }

}
