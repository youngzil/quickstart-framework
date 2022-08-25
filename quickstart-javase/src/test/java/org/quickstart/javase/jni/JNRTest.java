package org.quickstart.javase.jni;

import jnr.ffi.LibraryLoader;
import jnr.posix.POSIX;
import jnr.posix.POSIXFactory;
import org.junit.jupiter.api.Test;

public class JNRTest {

    public interface LibC { // A representation of libC in Java
        int puts(String s); // mapping of the puts function, in C `int puts(const char *s);`

        long getpid();
    }

    @Test
    public void testGetPidJnr() {
        LibC libc = LibraryLoader.create(LibC.class).load("c"); // load the "c" library into the libc variable

        libc.puts("Hello World!"); // prints "Hello World!" to console

        System.out.println(libc.getpid());
    }

    private static POSIX posix = POSIXFactory.getPOSIX();

    @Test
    public void testGetPidJnrPosix() {
        System.out.println(posix.getcwd());
        posix.chdir("..");
        System.out.println(posix.getcwd());
        System.out.println(posix.getpid());
    }
}
