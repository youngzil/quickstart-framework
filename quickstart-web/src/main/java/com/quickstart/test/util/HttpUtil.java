package com.quickstart.test.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yonghui.mq.constant.RocketMqConstant;

/**
 * http协议 远程调用（RPC）
 * 
 * @author sheng.han
 *
 */
@Component
public class HttpUtil {

    /**
     * 日志处理
     */
    private static final Logger LOG = Logger.getLogger(HttpUtil.class);

    /**
     * 最大连接数和路由数
     */
    final static int THREAD_DEFAULT = 100;

    final static int ROUTE_DEFAULT = 100;

    /**
     * get 请求调用
     * 
     * @param rpcKey
     * @param url
     * @return
     */
    public static boolean requestByGetMethod(String rpcKey, String url) {

        // 声明返回值
        boolean result = false;

        // 创建默认的httpClient实例
        if (httpClient == null) {
            httpClient = getHttpClient();
        }

        try {
            // 用get方法发送http请求
            HttpGet get = new HttpGet(url);

            // 设置超时时间
            setConfig(get);
            // base64处理
            String mergra = username + ":" + password;
            String head = Base64.encodeBase64String(mergra.getBytes()).toString();
            get.addHeader("Authorization", "Basic " + head);
            // 设置请求头信息
            get.addHeader("X-tenantId", "single");
            get.addHeader("Content-Type", "application/json;charset=UTF-8");

            CloseableHttpResponse httpResponse = null;
            // 发送get请求
            httpResponse = httpClient.execute(get);
            try {
                // response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    result = true;
                    LOG.info("调用【" + rpcKey + "】接口成功！" + "响应状态码:" + httpResponse.getStatusLine());
                } else {
                    LOG.error("调用【" + rpcKey + "】接口失败！" + "响应状态码:" + httpResponse.getStatusLine());
                }
            } catch (Exception e) {
                // 追加一个 取得状态码的异常处理
                LOG.error("调用【" + rpcKey + "】接口 GET调用" + "处理返回结果异常 ：" + e.getMessage());
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            LOG.error("调用【" + rpcKey + "】接口 " + "GET CLIENT 远程调用异常 ：" + e.getMessage());
        }
        // finally{
        // try{
        // closeHttpClient(httpClient);
        // } catch (IOException e){
        // LOG.error("调用【"+rpcKey+ "】接口 " + "关闭GET CLIENT 失败 ：" + e.getMessage());
        // }
        // }

        return result;

    }

    /**
     * Post远程调用（rpc）
     * 
     * @param rpcKey
     * @param url
     * @param list
     */
    public static boolean requestByPostMethod(String rpcKey, String url, String body) {

        // 声明返回值
        boolean result = false;
        // 伪sington
        if (httpClient == null) {
            httpClient = getHttpClient();
        }
        try {
            HttpPost post = new HttpPost(url);

            // 设置超时时间
            setConfig(post);
            // base64处理
            String mergra = username + ":" + password;
            String head = Base64.encodeBase64String(mergra.getBytes()).toString();
            post.addHeader("Authorization", "Basic " + head);
            // 设置请求头信息
            post.addHeader("X-tenantId", "single");

            StringEntity entityString = new StringEntity(body);
            entityString.setContentType("application/json;charset=UTF-8");

            post.setEntity(entityString);
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (httpResponse.getStatusLine().getStatusCode() == RocketMqConstant.OMS_RPC_SUCCESS) {
                    // 返回值设定
                    result = true;

                    LOG.info("调用【" + rpcKey + "】接口成功！ 消息行： 【" + body + "】 执行POST 打印返回实体:...." + EntityUtils.toString(entity));
                } else {
                    LOG.error("调用【" + rpcKey + "】接口失败！ 返回状态码：  【" + httpResponse.getStatusLine().getStatusCode() + "】 执行POST 打印消息实体:...." + body);
                }
            } catch (Exception e) {
                // 追加一个 取得状态码的异常处理
                LOG.error("调用【" + rpcKey + "】接口  POST调用" + "处理返回结果异常 ：" + e.getMessage());
            } finally {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            LOG.error("调用【" + rpcKey + "】接口 " + "POST CLIENT 远程调用异常 ：" + e.getMessage());
        } catch (IOException e) {
            LOG.error("调用【" + rpcKey + "】接口 " + "POST CLIENT 远程调用异常 ：" + e.getMessage());
        }
        // 不关闭客户端
        // finally{
        // try{
        // closeHttpClient(httpClient);
        // } catch(Exception e){
        // LOG.error("调用【"+rpcKey+ "】接口 " + "关闭POST CLIENT 失败 ：" + e.getMessage());
        // }
        // }

        return result;
    }

    /**
     * http put 请求
     * 
     * @param rpcKey
     * @param url
     * @param body
     * @return
     */
    public static boolean requestByPutMethod(String rpcKey, String url, String body) {
        // 声明返回值
        boolean result = false;
        // 伪sington
        if (httpClient == null) {
            httpClient = getHttpClient();
        }
        try {
            HttpPut put = new HttpPut(url);
            // 设置响应时间
            setConfig(put);
            // base64处理
            String mergra = username + ":" + password;
            String head = Base64.encodeBase64String(mergra.getBytes()).toString();
            put.addHeader("Authorization", "Basic " + head);
            // 设置请求头信息
            put.addHeader("X-tenantId", "single");

            StringEntity entityString = new StringEntity(body);
            entityString.setContentType("application/json;charset=UTF-8");

            put.setEntity(entityString);
            // 执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(put);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (httpResponse.getStatusLine().getStatusCode() == RocketMqConstant.OMS_RPC_SUCCESS) {
                    // 返回值设定
                    result = true;

                    LOG.info("调用【" + rpcKey + "】接口成功！ 消息行： 【" + body + "】 执行PUT 打印返回实体:...." + EntityUtils.toString(entity));
                } else {
                    LOG.error("调用【" + rpcKey + "】接口失败！ 返回状态码：  【" + httpResponse.getStatusLine().getStatusCode() + "】 执行POST 打印消息实体:...." + body);
                }
            } catch (Exception e) {
                // 追加一个 取得状态码的异常处理
                LOG.error("调用【" + rpcKey + "】接口  PUT调用" + "处理返回结果异常 ：" + e.getMessage());
            } finally {
                httpResponse.close();
            }

        } catch (UnsupportedEncodingException e) {
            LOG.error("调用【" + rpcKey + "】接口 " + "PUT CLIENT 远程调用异常 ：" + e.getMessage());
        } catch (IOException e) {
            LOG.error("调用【" + rpcKey + "】接口 " + "PUT CLIENT 远程调用异常 ：" + e.getMessage());
        }

        return result;
    }

    /**
     * 获取默认客户端
     * 
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom().setConnectionManager(manager).build();
    }

    /**
     * 关闭客户端
     * 
     * @param client
     * @throws IOException
     */
    private static void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 客户端声明
     */
    private static CloseableHttpClient httpClient;

    /**
     * 用户名
     */
    private static String username;

    /**
     * 密码
     */
    private static String password;

    /**
     * 超时参数设置
     * 
     * @return
     */
    private static RequestConfig getConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000) // 连接超时
                .setSocketTimeout(5000) // 请求数据超时
                .build();

        return requestConfig;
    }

    /**
     * 设置超时处理
     * 
     * @param http
     */
    private static void setConfig(HttpRequestBase http) {
        http.setConfig(getConfig());
    }

    // 这里追加httpclient连接池 07292016 by hansheng
    /**
     * 线程池管理
     */
    private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    // 初始化模块
    static {
        username = PropertiesUtil.getValueByKey("username");
        password = PropertiesUtil.getValueByKey("password");

        manager.setMaxTotal(THREAD_DEFAULT);
        manager.setDefaultMaxPerRoute(ROUTE_DEFAULT);
    }

}
