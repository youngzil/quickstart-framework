package org.quickstart.jvm.serializer.commons.utils.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.quickstart.jvm.serializer.commons.utils.unittest.HessianCodecTest;

/**
 * @author xinyuzhou.zxy
 */
public class HessianBenchmark extends HessianCodecTest {
    @Benchmark
    public void hessianCodecMultiTest() throws Throwable {
        hessianEncodeAndDecode();
    }
}
