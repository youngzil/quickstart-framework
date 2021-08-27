package org.quickstart.proxy.bytebuddy;

public class SampleSon2 extends Sample {
    private String c;
    public SampleSon2(String a, String b, String c) {
        super(a,b);
        this.c = c;
    }

    public String getC() { return c;}
}