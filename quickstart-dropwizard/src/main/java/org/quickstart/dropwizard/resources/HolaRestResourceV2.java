/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HolaRestResourceV2.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.resources;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * HolaRestResourceV2
 * 
 * @author：yangzl@asiainfo.com
 * @2018年10月22日 下午5:29:45
 * @since 1.0
 */
@Path("/api")
public class HolaRestResourceV2 {

    private String saying;

    public HolaRestResourceV2(String saying) {
        this.saying = saying;
    }

    @Path("/holaV2")
    @GET
    public String hola() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return saying + " " + hostname;
    }
}
