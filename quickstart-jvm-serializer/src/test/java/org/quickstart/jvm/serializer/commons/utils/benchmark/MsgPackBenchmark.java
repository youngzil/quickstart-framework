package org.quickstart.jvm.serializer.commons.utils.benchmark;

import java.io.IOException;

import org.openjdk.jmh.annotations.Benchmark;
import org.quickstart.jvm.serializer.commons.utils.unittest.MsgPackCodecTest;

/**
 * @author xinyuzhou.zxy
 */
public class MsgPackBenchmark extends MsgPackCodecTest {
    @Benchmark
    public void msgPackCodecMultiTest() throws IOException {
        msgPackEncodeAndDecode();
    }
}
