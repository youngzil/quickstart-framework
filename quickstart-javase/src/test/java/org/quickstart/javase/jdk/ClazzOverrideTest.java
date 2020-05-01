package org.quickstart.javase.jdk;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/4/26 02:18
 */
public class ClazzOverrideTest {

    void function(String s) {
        System.out.println(s);
    }

    void function(Integer i) {
        System.out.println(i);
    }

    void gate(double d) {
        System.out.println(d);
    }

    void gate(Integer i) {
        System.out.println(i);
    }

    public static void main(String[] args) {

        ClazzOverrideTest clazzOverrideTest = new ClazzOverrideTest();

        //        clazzOverrideTest.function(null);

        clazzOverrideTest.gate(1);

        String a = null;

        switch (a) {
            case "null":
                //语句
                break; //可选
            case "s":
                //语句
                break; //可选
            //你可以有任意数量的case语句
            default: //可选
                //语句
        }

    }

    <String, T, Alibaba> String get(String string, T t) {
        return string;
    }

}
