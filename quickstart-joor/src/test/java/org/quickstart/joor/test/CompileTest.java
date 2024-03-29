/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.quickstart.joor.test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* [java-8] */

import org.joor.Reflect;
import org.joor.ReflectException;
import org.junit.Test;
import org.quickstart.joor.test.CompileTest.J;

/**
 * @author Lukas Eder
 */
public class CompileTest {

    @Test
    public void testCompileLocalInterfaceHierarchy() throws Exception {
        I i = Reflect.compile("org.joor.test.CompileTest1", "package org.joor.test; public class CompileTest1 implements org.joor.test.I {}").create().get();
        assertEquals("I.m()", i.m());

        J j = Reflect.compile("org.joor.test.CompileTest2", "package org.joor.test; public class CompileTest2 implements org.joor.test.CompileTest.J {}").create().get();
        assertEquals("J.m()", j.m());
    }

    @Test
    public void testCompileSamePackage() {
        Supplier<String> supplier = Reflect.compile(
            "org.joor.test.CompileTest3",
            "package org.joor.test;\n" +
            "class CompileTest3 implements java.util.function.Supplier<String> {\n" +
            "  public String get() {\n" +
            "    return \"Hello World!\";\n" +
            "  }\n" +
            "}\n"
        ).create().get();

        assertEquals("Hello World!", supplier.get());
    }

    @Test
    public void testCompileDifferentPackage() {
        Supplier<String> supplier = Reflect.compile(
            "com.example.CompileTestDifferentPackage",
            "package com.example;\n" +
            "class CompileTestDifferentPackage implements java.util.function.Supplier<String> {\n" +
            "  public String get() {\n" +
            "    return \"Hello World!\";\n" +
            "  }\n" +
            "}\n"
        ).create().get();

        assertEquals("Hello World!", supplier.get());
    }

    @Test
    public void testRecompileSameClassName() {

        // The class loader will cache the class name by default, so a new content shouldn't affect the type
        Object o1 = Reflect.compile(
            "org.joor.test.CompileSameClassName",
            "package org.joor.test;" +
            "class CompileSameClassName { public String toString() { return \"a\"; } }")
            .create()
            .get();

        assertEquals("a", o1.toString());
        Object o2 = Reflect.compile(
            "org.joor.test.CompileSameClassName",
            "package org.joor.test;" +
            "class CompileSameClassName { public String toString() { return \"b\"; } }")
            .create()
            .get();

        assertEquals("a", o2.toString());
    }

    @Test
    public void testCompileEnums() {
        Class<Enum<?>> e = Reflect.compile(
            "org.joor.test.CompiledEnum",
            "package org.joor.test;" +
            "enum CompiledEnum { a, b, c }")
            .get();

        assertEquals(Arrays.asList("a", "b", "c"), Stream.of(e.getEnumConstants()).map(Enum::name).collect(Collectors.toList()));
    }

    @Test
    public void testCompilationError() {
        try {
            Reflect.compile(
                "org.joor.test.CompilationError",
                "package org.joor.test;" +
                "class CompilationError { a }"
            );
        }
        catch (ReflectException e) {
            assertTrue(e.getMessage(), e.getMessage().contains("Compilation error:"));
        }
    }

    interface J {
        default String m() {
            return "J.m()";
        }
    }
}

interface I extends J {
    @Override
    default String m() {
        return "I.m()";
    }
}

/* [/java-8] */