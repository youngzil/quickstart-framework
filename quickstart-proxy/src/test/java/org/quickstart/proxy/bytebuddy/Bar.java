package org.quickstart.proxy.bytebuddy;

public class Bar {

    public static String sayHelloBar() {
        return "Hollo in Bar!";
    }

    public static String intercept(String name) {
        return "Hollo " + name + "in test Bar";
    }

}
