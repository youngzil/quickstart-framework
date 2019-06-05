/**
 * 项目名称：quickstart-jython 
 * 文件名：CalculatorFuncTest.java
 * 版本信息：
 * 日期：2019年4月17日
 * Copyright yangzl Corporation 2019
 * 版权所有 *
 */
package org.quickstart.jython;

import java.io.IOException;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * CalculatorFuncTest
 * 
 * @author：youngzil@163.com
 * @2019年4月17日 下午3:49:03
 * @since 1.0
 */
public class CalculatorFuncTest {

    public static void main(String[] args) throws IOException {
        System.setProperty("python.home", "/Users/yangzl/mysoft/jython2.7.0");

        // （3）在Java中单向调用Python程序中的方法，需要传递参数，并接收返回值。Python既支持面向函数式编程，也支持面向对象编程。因此，调用Python程序中的方法也分别以面向函数式编程和面向对象式编程进行说明。

        // 1. Python面向函数式编程: 在Java中调用Python函数
        String pythonFunc = "src/main/java/org/quickstart/jython/calculator_func.py";

        PythonInterpreter pi1 = new PythonInterpreter();
        // 加载python程序
        pi1.execfile(pythonFunc);
        // 调用Python程序中的函数
        PyFunction pyf = pi1.get("power", PyFunction.class);
        PyObject dddRes = pyf.__call__(Py.newInteger(2), Py.newInteger(3));
        System.out.println(dddRes);
        pi1.cleanup();
        pi1.close();

        // 2. 面向对象式编程: 在Java中调用Python对象实例的方法
        // String pythonClass = "src/main/java/org/quickstart/jython/test2.py";
        String pythonClass = "src/main/java/org/quickstart/jython/calculator_clazz.py";
        // python对象名
        String pythonObjName = "cal";
        // python类名
        String pythonClazzName = "Calculator";
        PythonInterpreter pi2 = new PythonInterpreter();
        // 加载python程序
        pi2.execfile(pythonClass);
        // 实例化python对象
        pi2.exec(pythonObjName + "=" + pythonClazzName + "()");
        // 获取实例化的python对象
        PyObject pyObj = pi2.get(pythonObjName);
        // 调用python对象方法,传递参数并接收返回值
        PyObject result = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)});
        double power = Py.py2double(result);
        System.out.println(power);

        pi2.cleanup();
        pi2.close();
    }

}
