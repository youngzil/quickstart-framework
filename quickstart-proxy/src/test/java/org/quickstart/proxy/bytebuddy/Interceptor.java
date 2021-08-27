package org.quickstart.proxy.bytebuddy;

public class Interceptor {

    public static String intercept(String name) { return "String"; }
    public static String intercept(int i) { return "int"; }
    public static String intercept(Object o) { return "Object";}
}
