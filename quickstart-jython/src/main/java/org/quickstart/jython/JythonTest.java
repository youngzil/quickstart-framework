package org.quickstart.jython;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * Hello world!
 *
 */
public class JythonTest {
    public static void main(String[] args) {
        System.setProperty("python.home", "/Users/yangzl/mysoft/jython2.7.0");
        PythonInterpreter interp = new PythonInterpreter();
        System.out.println("Hello, brave new world");

        // 直接执行Python程序语句
        interp.exec("import sys");
        interp.exec("print sys");
        interp.set("a", new PyInteger(42));
        interp.exec("print a");
        interp.exec("x = 2+2");
        PyObject x = interp.get("x");
        System.out.println("x: " + x);
        System.out.println("Goodbye, cruel world!");

        // 直接执行python文件
        interp.execfile("src/main/java/org/quickstart/jython/helloworld.py");

        /*System.out.println(System.getProperty("user.dir"));// user.dir指定了当前的路径
        File directory = new File("");// 设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());// 获取标准的路径
            System.out.println(directory.getAbsolutePath());// 获取绝对路径
            System.out.println(directory.getPath());// 获取绝对路径
        } catch (Exception e) {
        }
        System.out.println(Class.class.getClass().getResource("/").getPath());*/

        // 调用python文件中的函数，调用方法(不含参数)
        interp.execfile("src/main/java/org/quickstart/jython/test.py");
        PyFunction func_first = (PyFunction) interp.get("first", PyFunction.class);
        PyFunction func_second = (PyFunction) interp.get("second", PyFunction.class);
        PyObject pyobj = func_second.__call__();
        System.out.println(pyobj);

        // 调用python文件中的函数，调用方法(含参数)
        PyFunction func_third = (PyFunction) interp.get("third", PyFunction.class);
        PyObject pyobj2 = func_third.__call__(new PyInteger(4), new PyInteger(2));
        System.out.println(pyobj2);

        // 关于中文处理，这是一个很麻烦的方面，大家可以看下面的例子
        // 报编码错误，可以在文件头追加： # -*- coding: cp936 -*- 或者 # -*- coding: utf-8 -*
        String a = "你好";
        PyFunction func = (PyFunction) interp.get("word_process", PyFunction.class);
        PyObject pyobj3 = func.__call__(Py.newStringOrUnicode(a));
        // 使用下面的代码报错：Cannot create PyString with non-byte value
        // 可以使用PyString str = Py.newStringOrUnicode("颜军")
        // PyObject pyobj3 = func.__call__(new PyString(a));
        System.out.println(pyobj3.toString());

        interp.cleanup();
        interp.close();

    }
}
