package org.quickstart.javase.jdk.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/12/3 19:29
 */

public class FooClassLoader extends ClassLoader {

    private static final String NAME = "/Users/lican/git/test/foo/";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            String s = name.substring(name.lastIndexOf(".") + 1) + ".class";
            File file = new File(NAME + s);
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] b = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedClass;
    }
}
