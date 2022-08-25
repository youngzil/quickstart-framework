package org.quickstart.javase.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.junit.jupiter.api.Test;

public class JNATest {

    public interface LibC extends Library {
        long getpid();
    }

    @Test
    public void testGetPidJNA() {
        LibC libc = Native.loadLibrary("c", LibC.class);
        System.out.println(libc.getpid());
    }

}
