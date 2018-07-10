package org.quickstart.jvm.serializer.commons.utils.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.quickstart.jvm.serializer.commons.utils.unittest.JdkCodecTest;

/**
 * @author xinyuzhou.zxy
 */
public class JdkCodecBencmark extends JdkCodecTest {
    @Benchmark
    public void javaCodecMultiTest() throws Exception {
        javaEncodeAndDecode(msg1);
        javaEncodeAndDecode(msg2);
        javaEncodeAndDecode(msg3);
    }
}
