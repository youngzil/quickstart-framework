package org.quickstart.jvm.serializer.commons.utils.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.quickstart.jvm.serializer.commons.utils.unittest.KryoCodecTest;

/**
 * @author xinyuzhou.zxy
 */
public class KryoBenchmark extends KryoCodecTest {

    @Benchmark
    public void kryoCodecMultiTest() throws Exception {
        kryoEncodeAndDecode();
    }
}
