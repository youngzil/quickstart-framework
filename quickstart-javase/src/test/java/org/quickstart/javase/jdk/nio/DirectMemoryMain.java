package org.quickstart.javase.jdk.nio;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/22 11:21
 */
public class DirectMemoryMain {
    public static void main(String[] args) throws InterruptedException {

        Unsafe unsafe = getUnsafe();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                long address = unsafe.allocateMemory(10000);
                // System.out.println(address);
                // unsafe.freeMemory(address);
            }
            Thread.sleep(1);
        }
    }

    // Unsafe无法直接使用，需要通过反射来获取
    private static Unsafe getUnsafe() {
        try {
            Class clazz = Unsafe.class;
            Field field = clazz.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}

