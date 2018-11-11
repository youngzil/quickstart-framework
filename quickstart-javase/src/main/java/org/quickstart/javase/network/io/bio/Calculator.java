/**
 * 项目名称：quickstart-javase 
 * 文件名：Calculator.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.network.io.bio;

/**
 * Calculator 
 *  
 * @author：youngzil@163.com
 * @2018年5月10日 上午11:02:52 
 * @since 1.0
 */
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public final class Calculator {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }

    public static void main(String[] args) throws ScriptException {

        System.out.println(Calculator.cal("4-2"));
    }
}
