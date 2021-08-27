package org.quickstart.proxy.bytebuddy;

public class Log {

    public Log() {
        System.out.println("Log constructor");
    }

    public static void log(String a) {

        System.out.println("Log: " + a);
    }

}
