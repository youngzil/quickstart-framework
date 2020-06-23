package org.quickstart.javase.jdk.nio;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/22 11:24
 */
// java部分
public class NativeMain {
    public native void allocateMemory();

    static {
        System.setProperty("java.library.path", ".");
        System.loadLibrary("nativemain");
    }

    public static void main(String[] args) throws Exception {
        NativeMain nativeMain = new NativeMain();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                nativeMain.allocateMemory();
            }
            Thread.sleep(1);
        }
    }
}


