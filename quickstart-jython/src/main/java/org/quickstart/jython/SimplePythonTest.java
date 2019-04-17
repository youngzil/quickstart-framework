/**
 * 项目名称：quickstart-jython 
 * 文件名：SimplePythonTest.java
 * 版本信息：
 * 日期：2019年4月17日
 * Copyright asiainfo Corporation 2019
 * 版权所有 *
 */
package org.quickstart.jython;

import java.io.IOException;

import org.python.util.PythonInterpreter;

/**
 * SimplePythonTest
 * 
 * @author：yangzl@asiainfo.com
 * @2019年4月17日 下午3:46:36
 * @since 1.0
 */
public class SimplePythonTest {

    public static void main(String[] args) throws IOException {
        System.setProperty("python.home", "/Users/yangzl/mysoft/jython2.7.0");

        // 在Java中简单调用Python程序，不需要传递参数，也不需要获取返回值。

        String python = "src/main/java/org/quickstart/jython/simple_python.py";
        PythonInterpreter interp = new PythonInterpreter();
        interp.execfile(python);
        interp.cleanup();
        interp.close();
    }
}
