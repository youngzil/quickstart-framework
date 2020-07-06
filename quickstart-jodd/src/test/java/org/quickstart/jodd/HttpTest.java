package org.quickstart.jodd;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.junit.Test;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/7/5 11:40
 */
public class HttpTest {

    @Test
    public void testGet() {
        HttpRequest httpRequest = HttpRequest.get("http://jodd.org");
        HttpResponse response = httpRequest.send();
        System.out.println(response);

        //        fluent interface
        HttpResponse response2 = HttpRequest.get("http://jodd.org").send();
        System.out.println(response2);

        HttpRequest request = new HttpRequest();
        request.method("GET").protocol("http").host("srv").port(8080).path("/api/jsonws/user/get-user-by-id");
    }

}
