package org.quickstart.jvm.serializer.commons.utils.benchmark;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.quickstart.jvm.serializer.commons.utils.unittest.FastJsonCodecTest;

/**
 * @author xinyuzhou.zxy
 */
public class FastJsonBenchmark extends FastJsonCodecTest {
    @Benchmark
    public void jsonCodecMultiTest() throws IOException {
        jsonEncodeAndDecode();
    }
}
