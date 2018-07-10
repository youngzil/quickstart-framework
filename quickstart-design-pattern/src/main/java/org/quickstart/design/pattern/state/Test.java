/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：Test.java
 * 版本信息：
 * 日期：2018年1月27日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.state;

/**
 * Test
 * 
 * @author：yangzl@asiainfo.com
 * @2018年1月27日 上午10:50:14
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) {

        // 状态切换顺序
        // push：blue-->green-->black-->red-->blue
        // pull：blue-->red-->black-->green-->blue

        Context context = new Context();
        context.setState(new BlueState());

        System.out.println("push----");
        context.push();
        // System.out.println(context.getCurrentColor());
        context.push();
        context.push();
        context.push();
        context.push();

        System.out.println("pull----在push的最后的结果上pull的");
        context.pull();
        context.pull();
        context.pull();
        context.pull();
        context.pull();
        context.pull();

    }

}
