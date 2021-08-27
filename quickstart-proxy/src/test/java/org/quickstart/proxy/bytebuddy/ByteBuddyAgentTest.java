package org.quickstart.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static net.bytebuddy.dynamic.loading.ClassLoadingStrategy.Default.INJECTION;
import static net.bytebuddy.matcher.ElementMatchers.*;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyAgentTest {

    public static void main(String[] args) {
        ByteBuddyAgent.install();
        new ByteBuddy()//
            .redefine(Foo.class)//
            .method(named("sayHelloFoo"))//
            .intercept(FixedValue.value("Hello Foo Redefined"))//
            .make()//
            .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        Foo f = new Foo();
        System.out.println(f.sayHelloFoo());


        //代理有个优先级顺序的，可以指定各种匹配条件，不指定条件会有个默认的匹配顺序

        /*new ByteBuddy().redefine(Log.class)
            .method(ElementMatchers.named("log"))
            .intercept(MethodDelegation.to(Log4j.class))
            .make()
            .load(Thread.currentThread().getContextClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
*/

        new ByteBuddy().redefine(Log.class)
            .method(ElementMatchers.named("log"))
            .intercept(MethodDelegation.to(Log4j.class))
            .method(ElementMatchers.isConstructor())
            .intercept(MethodDelegation.to(Log4j.class))
            .make()
            .load(Thread.currentThread().getContextClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Log log =  new Log();
        // 调用
        Log.log("hello");

    }

    @Test
    public void testOrder() throws InstantiationException, IllegalAccessException {
        Foo dynamicFoo = new ByteBuddy()
            .subclass(Foo.class)
            .method(isDeclaredBy(Foo.class)) // 匹配 Foo中所有的方法
            .intercept(FixedValue.value("One!"))
            .method(named("foo")) // 匹配名为 foo的方法
            .intercept(FixedValue.value("Two!"))
            .method(named("foo").and(takesArguments(1))) // 匹配名为foo且只有一个
            // 参数的方法
            .intercept(FixedValue.value("Three!"))
            .make()
            .load(getClass().getClassLoader(), INJECTION)
            .getLoaded()
            .newInstance();

        System.out.println(dynamicFoo.bar());
        System.out.println(dynamicFoo.foo());
        System.out.println(dynamicFoo.foo(null));
    }

    @Test
    public void testDynamic() throws InstantiationException, IllegalAccessException {
        ByteBuddyAgent.install();
        // 方法拦截
        String helloWorld = new ByteBuddy()
            .subclass(DB.class)
            .method(named("hello"))
            // 拦截Bar.sayHelloBar()方法，并委托给 Interceptor中的静态方法处理
            .intercept(MethodDelegation.to(Interceptor.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), INJECTION)
            .getLoaded()
            .newInstance()
            .hello("World");
        System.out.println(helloWorld);
        // 委托类

    }

    @Test
    public void testconstruct() throws NoSuchMethodException {

        new ByteBuddy()
            .subclass(SampleClass.class, ConstructorStrategy.Default.NO_CONSTRUCTORS)
            .defineConstructor(Visibility.PUBLIC)
            .withParameters(int.class)
            // .defineConstructor((ModifierContributor.ForMethod)Arrays.<Class<?>>asList(int.class), Visibility.PUBLIC)
            .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor()))
            .make()
            .load(ClassLoader.getSystemClassLoader(), INJECTION);

    }

    // 对比testConstruct2和testConstruct3，代码一样，分开写就不行，可能是内部使用队列处理的，乱了
    @Test
    public void testConstruct2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends Sample> clazz = new ByteBuddy()
            .subclass(Sample.class, ConstructorStrategy.Default.NO_CONSTRUCTORS)
            .name("SampleSon")
            .defineField("c", String.class, Visibility.PRIVATE)
            .defineConstructor(Visibility.PUBLIC)
            .withParameters(String.class, String.class, String.class)
            .intercept(MethodCall.invoke(Sample.class.getConstructor(String.class, String.class))
                .withArgument(0, 1)
                .andThen(FieldAccessor.ofField("c").setsArgumentAt(2)))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded();

        // clazz最终是SampleSon这个类
        System.out.println(clazz);

        Sample sample2 = clazz.getConstructor(String.class, String.class, String.class).newInstance("a", "b", "c");
        System.out.println(sample2);

    }

    @Test
    public void testConstruct3() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        DynamicType.Builder<? extends Sample> classBuilder = new ByteBuddy()
            .subclass(Sample.class, ConstructorStrategy.Default.NO_CONSTRUCTORS)
            .name("SampleSon");

        classBuilder.defineField("c", String.class, Visibility.PRIVATE)
            .defineConstructor(Visibility.PUBLIC)
            .withParameters(String.class, String.class, String.class)
            .intercept(MethodCall.invoke(Sample.class.getConstructor(String.class, String.class))
                .withArgument(0, 1)
                .andThen(FieldAccessor.ofField("c").setsArgumentAt(2)));

        Class<? extends Sample> newSampleClass= classBuilder.make().load(ClassLoader.getSystemClassLoader()).getLoaded();
        System.out.println(newSampleClass);

        Sample sample = newSampleClass.getConstructor(String.class, String.class, String.class).newInstance("a", "b", "c");
        System.out.println(sample);

    }

}
