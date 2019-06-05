/**
 * 项目名称：quickstart-dropwizard 
 * 文件名：HolaRestResourceV1.java
 * 版本信息：
 * 日期：2018年10月22日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.dropwizard.resources;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * HolaRestResourceV1
 * 
 * @author：youngzil@163.com
 * @2018年10月22日 下午4:53:03
 * @since 1.0
 */
@Path("/api")
public class HolaRestResourceV1 {

    @Path("/holaV1")
    @GET
    public String hola() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return "Hola Dropwizard @ " + hostname;
    }
}
