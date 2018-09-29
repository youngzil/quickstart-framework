/**
 * 项目名称：quickstart-javase 
 * 文件名：SerializablePredicateFilterTest.java
 * 版本信息：
 * 日期：2018年9月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * SerializablePredicateFilterTest
 * 
 * @author：yangzl@asiainfo.com
 * @2018年9月12日 下午10:41:44
 * @since 1.0
 */
public class SerializablePredicateFilterTest {

    public String VALUE = "Bob";

    public interface SerializablePredicate<T> extends Predicate<T>, Serializable {
    }

    public <T> void filter(SerializablePredicate<T> sp, T value) throws IOException, ClassNotFoundException {
        sp.getClass().isLocalClass();
        File tempFile = File.createTempFile("labmda", "set");

        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            oo.writeObject(sp);
        }

        try (ObjectInput oi = new ObjectInputStream(new FileInputStream(tempFile))) {
            SerializablePredicate<T> p = (SerializablePredicate<T>) oi.readObject();

            System.out.println(p.test(value));
        }
    }

    // @Test(expected = NotSerializableException.class)
    // public void testAnonymousDirect() throwsIOException, ClassNotFoundException {
    //
    // String value = VALUE;
    //
    // filter(newSerializablePredicate<String>() {
    //
    // @Override
    // public boolean test(String t) {
    // return value.length() > t.length();
    // }
    // }, "Bob");
    //
    // }

    @Test(expected = NotSerializableException.class)
    public void testLocalClass() throws IOException, ClassNotFoundException {

        class LocalPredicate implements SerializablePredicate<String> {
            @Override
            public boolean test(String t) {
                // TODO Implement this method
                return false;
            }
        }

        filter(new LocalPredicate(), "Bobby");

    }

    public static class LengthPredicate implements SerializablePredicate<String> {

        private String value;

        public LengthPredicate(String value) {
            super();
            this.value = value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean test(String t) {
            // TODO Implement this method
            return false;
        }
    }

    @Test
    public void testStaticInnerClass() throws IOException, ClassNotFoundException {

        filter(new LengthPredicate(VALUE), "Bobby");

    }

    @Test(expected = NotSerializableException.class)
    public void testLambdaDirect() throws IOException, ClassNotFoundException {

        filter((String s) -> VALUE.length() > s.length(), "Bobby");

    }

    @Test
    public void testLambdaInDirect() throws IOException, ClassNotFoundException {

        String value = VALUE;

        filter((String s) -> value.length() > s.length(), "Bobby");

    }

    @Test
    public void testLambdaParameter() throws IOException, ClassNotFoundException {

        invokeWithParameter(VALUE);

    }

    private void invokeWithParameter(String value) throws java.lang.ClassNotFoundException, java.io.IOException {
        filter((String s) -> value.length() > s.length(), "Bobby");
    }

}
