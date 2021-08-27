package org.quickstart.proxy.bytebuddy;

public class Log4j {

    private String testName;

    public Log4j() {
        System.out.println("Log4j constructor");
    }

    /**
     * 注意代理类要和原实现类的方法声明保持一致
     * @param a
     */
    public static void log2(String a) {
        System.err.println("Log4j: " + a);
    }

}
