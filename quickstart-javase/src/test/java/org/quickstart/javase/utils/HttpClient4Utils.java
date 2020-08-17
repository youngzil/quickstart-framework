package org.quickstart.javase.utils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:31
 * @version v1.0
 */
public class HttpClient4Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient4Utils.class);

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
        // 设置连接超时时间(单位毫秒)
        .setConnectTimeout(1000 * 60)
        // 设置请求超时时间(单位毫秒)
        .setConnectionRequestTimeout(1000 * 60)
        // 设置socket读写超时时间(单位毫秒)
        .setSocketTimeout(1000 * 60)
        .build();

    public static String get(String url) {

        LOGGER.info("HTTP GET URL——" + url);

        // 创建GET请求
        HttpGet httpGet = new HttpGet(url);

        httpGet.setConfig(REQUEST_CONFIG);
        httpGet.setHeader("Content-Type", APPLICATION_JSON_CHARSET_UTF_8);

        try (
            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 客户端执行(发送)GET请求
            CloseableHttpResponse httpGetResponse = httpClient.execute(httpGet)
        ) {
            return handle(httpGetResponse);
        } catch (IOException e) {
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("URL: {%s}", url), e);
        }
    }

    public static String post(String url) {

        LOGGER.info("HTTP POST URL——" + url);

        // 创建POST请求
        HttpPost httpPost = new HttpPost(url);

        httpPost.setConfig(REQUEST_CONFIG);
        httpPost.setHeader("Content-Type", APPLICATION_JSON_CHARSET_UTF_8);

        try (
            // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 客户端执行(发送)POST请求
            CloseableHttpResponse httpPostResponse = httpClient.execute(httpPost)
        ) {
            return handle(httpPostResponse);
        } catch (IOException e) {
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("URL: {%s}", url), e);
        }
    }

    private static String handle(CloseableHttpResponse httpResponse) throws IOException {
        StatusLine responseStatusLine = httpResponse.getStatusLine();
        LOGGER.info(String.format("Response Status Line: {%s}", responseStatusLine));

        // 从响应模型中获取响应实体
        HttpEntity responseEntity = httpResponse.getEntity();

        int statusCode = responseStatusLine.getStatusCode();
        if (isSuccessful(statusCode)) {
            if (Objects.nonNull(responseEntity)) {
                LOGGER.info("Success");
                LOGGER.info(String.format("Response Content Length: {%d}", responseEntity.getContentLength()));
                return EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            } else {
                throw new AppException(HandleExceptionEnum.HTTP_ERROR, "Response: {NULL}");
            }
        } else {
            LOGGER.error("Failure");
            if (Objects.nonNull(responseEntity)) {
                LOGGER.info(String.format("Response Content Length: {%d}", responseEntity.getContentLength()));
                LOGGER.error(EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));
            }
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("ResponseStatus: {%s}", statusCode));
        }
    }

    private static boolean isSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    public static void main(String[] args) {
        String resp = HttpClient4Utils.get("http://192.168.8.15:8088/ws/v1/cluster/scheduler");
        LOGGER.info(resp);
    }
}
