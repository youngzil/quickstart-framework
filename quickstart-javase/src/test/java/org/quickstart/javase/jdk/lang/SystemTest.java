package org.quickstart.javase.jdk.lang;

import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class SystemTest {

    @Test
    public void testgetProperties() {
        // System.getProperty()读取的是当前用户、系统、JVM等相关信息，以及在运行Java程序时以
        Properties properties = System.getProperties();
        Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            System.out.println(entry.getKey() + "===" + entry.getValue());
        }
    }

    @Test
    public void testgetenv() {
        // System.getenv()读取的是当前环境的环境变量。
        Map<String, String> map = System.getenv();
        for (Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            System.out.println(entry.getKey() + "===" + entry.getValue());
        }
    }

    @Test
    public void testgetenv2() throws IOException {

        // 此外，我们可以在应用中创建一个新进程，并向其上下文环境中添加新的环境变量。
        // Java 中，我们使用 ProcessBuilder 类来创建新进程，该类有一个名为 environment 的方法，此方法返回一个 Map，不过这个映射不是只读的，这样就可以向其添加新元素：

        ProcessBuilder pb = new ProcessBuilder("test");
        Map<String, String> env = pb.environment();
        env.put("log_dir", "/tmp/log");
        Process process = pb.start();
    }

}
