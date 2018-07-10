package com.quickstart.test.reflect;

class Hello {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(new Demo().getClass().getName());
        System.out.println(Demo.class.getName());
        System.out.println(Class.forName("com.yang.test.reflect.Demo").getName());
    }
}


class Demo {

}
