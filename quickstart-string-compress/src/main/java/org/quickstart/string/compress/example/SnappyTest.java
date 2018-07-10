package org.quickstart.string.compress.example;

import java.io.IOException;
import java.io.OutputStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.xerial.snappy.SnappyFramedOutputStream;
import org.xerial.snappy.SnappyOutputStream;

/**
 * Snappy library tests
 */
public class SnappyTest extends TestParent {

    @Benchmark
    public int snappyNormalOutput() throws IOException
    {
        return baseBenchmark(new StreamFactory() {
            @Override
            public OutputStream getStream(OutputStream underlyingStream) throws IOException {
                return new SnappyOutputStream( underlyingStream, 65536 );
            }
        });
    }

    @Benchmark
    public int snappyFramedOutput() throws IOException
    {
        return baseBenchmark(new StreamFactory() {
            @Override
            public OutputStream getStream(OutputStream underlyingStream) throws IOException {
                return new SnappyFramedOutputStream( underlyingStream );
            }
        });
    }
}
