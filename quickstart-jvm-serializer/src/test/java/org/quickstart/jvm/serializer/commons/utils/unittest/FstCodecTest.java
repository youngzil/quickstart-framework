/**
 * 项目名称：quickstart-jvm-serializer 
 * 文件名：FstCodecTest.java
 * 版本信息：
 * 日期：2018年1月17日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.jvm.serializer.commons.utils.unittest;

import java.io.IOException;

import org.nustaq.serialization.FSTConfiguration;

/**
 * FstCodecTest
 * 
 * @author：youngzil@163.com
 * @2018年1月17日 下午8:30:41
 * @since 1.0
 */
public class FstCodecTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 对字符串Hello World进行反序列化
        String str = "Hello World !";
        // TemplateItem item = new TemplateItem();
        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

        byte[] bytes = conf.asByteArray(str);
        System.out.println(bytes.length);

        //
        //
        str = (String) conf.asObject(bytes);

        System.out.println(str);
    }

}
