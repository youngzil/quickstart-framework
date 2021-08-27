package org.quickstart.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import static net.bytebuddy.matcher.ElementMatchers.*;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyProxyTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        new ByteBuddyProxyTest().main2(null);

        System.out.println(Bar.sayHelloBar());

        Foo foo = new Foo();
        System.out.println(foo.sayHelloFoo());
    }

    public void main2(String[] args) throws InstantiationException, IllegalAccessException {
        String r = new ByteBuddy()
            .subclass(Foo.class)
            .method(named("sayHelloFoo")
                .and(isDeclaredBy(Foo.class)
                    .and(returns(String.class))))
            .intercept(MethodDelegation.to(Bar.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance()
            .sayHelloFoo();
        System.out.println(r);

    }
}
