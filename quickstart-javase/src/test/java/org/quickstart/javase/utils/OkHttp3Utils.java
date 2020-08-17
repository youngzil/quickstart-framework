package org.quickstart.javase.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:29
 * @version v1.0
 */
public class OkHttp3Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttp3Utils.class);

    public static final MediaType APPLICATION_JSON_CHARSET_UTF_8 = MediaType.get("application/json;charset=utf8");

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
        .callTimeout(60, TimeUnit.SECONDS)    // 设置请求超时时间
        .connectTimeout(60, TimeUnit.SECONDS)    // 设置连接超时时间
        .readTimeout(60, TimeUnit.SECONDS)    // 设置socket读写超时时间
        .writeTimeout(60, TimeUnit.SECONDS)
        .build();

    public static String get(String url) {

        System.out.println("HTTP GET URL——" + url);

        Request getRequest = new Request.Builder().get().url(url).build();

        Call call = OK_HTTP_CLIENT.newCall(getRequest);

        try (Response getResponse = call.execute()) {
            return handle(getResponse);
        } catch (IOException e) {
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("URL: {%s}", url), e);
        }
    }

    public static String post(String url, String body) {

        System.out.println("HTTP POST URL——" + url);

        RequestBody requestBody = RequestBody.create(APPLICATION_JSON_CHARSET_UTF_8, body);

        Request postRequest = new Request.Builder().post(requestBody).url(url).build();

        Call call = OK_HTTP_CLIENT.newCall(postRequest);

        try (Response postResponse = call.execute()) {
            return handle(postResponse);
        } catch (IOException e) {
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("URL: {%s}", url), e);
        }
    }

    private static String handle(Response httpResponse) throws IOException {

        // 从响应模型中获取响应实体
        ResponseBody responseBody = httpResponse.body();

        if (httpResponse.isSuccessful()) {
            if (Objects.nonNull(responseBody)) {
                LOGGER.info("Success");
                LOGGER.error(String.format("Response Content Length: {%d}", responseBody.contentLength()));
                return responseBody.string();
            } else {
                throw new AppException(HandleExceptionEnum.HTTP_ERROR, "Response: {NULL}");
            }
        } else {
            LOGGER.error("Failure");
            if (Objects.nonNull(responseBody)) {
                LOGGER.error(String.format("Response Content Length: {%d}", responseBody.contentLength()));
                LOGGER.error(responseBody.string());
            }
            throw new AppException(HandleExceptionEnum.HTTP_ERROR, String.format("ResponseStatus: {%s}", httpResponse.code()));
        }
    }

    public static void main(String[] args) {
        String resp = OkHttp3Utils.get("http://192.168.8.15:8088/ws/v1/cluster/scheduler");
        LOGGER.info(resp);
    }

}
