/**
 * 项目名称：quickstart-xbean 
 * 文件名：NoCacheClassPathResource.java
 * 版本信息：
 * 日期：2017年3月19日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xbean.hot.loading.xml;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;

/**
 * NoCacheClassPathResource 
 *  
 * @author：yangzl@asiainfo.com
 * @2017年3月19日 下午7:47:10 
 * @version 1.0
 */
/**
 * declaration： 路径指定资源文件，通过路径去获得资源文件的流 author wenkangqiang date 2016年3月5日
 */
public class NoCacheClassPathResource extends ClassPathResource {

    public NoCacheClassPathResource(String path, ClassLoader classLoader) {
        super(path, classLoader);
    }

    public InputStream getInputStream() throws IOException {
        InputStream is = null;
        is = this.getClassLoader().getResource(this.getPath()).openStream();
        return is;
    }

}
