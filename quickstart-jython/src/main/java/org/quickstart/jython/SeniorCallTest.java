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
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * CalculatorFuncTest
 * 
 * @author：youngzil@163.com
 * @2019年4月17日 下午3:49:03
 * @since 1.0
 */
public class SeniorCallTest {

    // 另外，对于在eclipse中运行时控制台报错：
    // Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0
    // 请添加VM参数：-Dpython.console.encoding=UTF-8，详见：http://blog.csdn.net/xfei365/article/details/50955731

    public static void main(String[] args) throws IOException {
        System.setProperty("python.home", "/Users/yangzl/mysoft/jython2.7.0");

        // （4）高级调用，也是在Java中调用Python程序最常见的用法：Python程序可以实现Java接口，在Python中也可以调用Java方法。
        // Python程序路径
        String python = "src/main/java/org/quickstart/jython/fruit_controller.py";
        // Python实例对象名
        String pyObjName = "pyController";
        // Python类名
        String pyClazzName = "FruitController";

        Fruit apple = new Apple();
        Fruit orange = new Orange();

        PythonInterpreter interpreter = new PythonInterpreter();
        // 如果在Python程序中引用了第三方库,需要将这些被引用的第三方库所在路径添加到系统环境变量中
        // 否则,在执行Python程序时将会报错: ImportError: No module named xxx
        PySystemState sys = interpreter.getSystemState();
        sys.path.add("/Library/Frameworks/Python.framework/Versions/2.7");

        // 加载Python程序
        interpreter.execfile(python);
        // 实例 Python对象
        interpreter.exec(pyObjName + "=" + pyClazzName + "()");

        // 1.在Java中获取Python对象,并将Python对象转换为Java对象
        // 为什么能够转换? 因为Python类实现了Java接口,通过转换后的Java对象只能调用接口中定义的方法
        GroovyController controller = (GroovyController) interpreter.get(pyObjName).__tojava__(GroovyController.class);
        controller.controllFruit(apple);
        controller.controllFruit(orange);

        // 2.在Java直接通过Python对象调用其方法
        // 既可以调用实现的Java接口方法,也可以调用Python类自定义的方法
        PyObject pyObject = interpreter.get(pyObjName);
        pyObject.invoke("controllFruit", Py.java2py(apple));
        pyObject.invoke("controllFruit", Py.java2py(orange));
        pyObject.invoke("printFruit", Py.java2py(apple));
        pyObject.invoke("printFruit", Py.java2py(orange));

        // 3.在Java中获取Python类进行实例化对象: 没有事先创建 Python对象
        PyObject pyClass = interpreter.get("FruitController");
        PyObject pyObj = pyClass.__call__();
        pyObj.invoke("controllFruit", Py.java2py(apple));
        pyObj.invoke("controllFruit", Py.java2py(orange));

        PyObject power = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)});
        if (power != null) {
            double p = Py.py2double(power);
            System.out.println(p);
        }

        interpreter.cleanup();
        interpreter.close();
    }

}
