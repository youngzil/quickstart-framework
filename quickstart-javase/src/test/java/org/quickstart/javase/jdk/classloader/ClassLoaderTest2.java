package org.quickstart.javase.jdk.classloader;

import java.lang.reflect.Method;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/12/3 19:30
 */
public class ClassLoaderTest2 {

    private Object fooTestInstance;
    private final static FooClassLoader fooClassLoader = new FooClassLoader();

    public static void main(String[] args) throws Exception {
        ClassLoaderTest2 classLoaderTest = new ClassLoaderTest2();

        Class<?> clazz = fooClassLoader.findClass("java.lang.String");
        System.out.println(clazz);


        classLoaderTest.initAndLoad();
        Object fooTestInstance = classLoaderTest.getFooTestInstance();
        System.out.println(fooTestInstance.getClass().getClassLoader());

        Method getFoo = fooTestInstance.getClass().getMethod("getFoo");
        System.out.println(getFoo.invoke(fooTestInstance));

        System.out.println(classLoaderTest.getClass().getClassLoader());
    }

    private void initAndLoad() throws Exception {
        Class<?> aClass = Class.forName("com.example.test.FooTest", true, fooClassLoader);
        fooTestInstance = aClass.newInstance();
    }

    public Object getFooTestInstance() {
        return fooTestInstance;
    }
}

