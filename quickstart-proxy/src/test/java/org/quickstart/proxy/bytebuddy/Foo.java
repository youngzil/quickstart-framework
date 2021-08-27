package org.quickstart.proxy.bytebuddy;

public class Foo {

    public String sayHelloFoo() {
        return "Hello in Foo!";
    }

    String bar() { return "bar"; }

    String foo() { return "foo"; }

    String foo(String name ) { return "foo " + name; }
}
