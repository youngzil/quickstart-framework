package org.quickstart.compression.commons.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.junit.Test;

public class Bzip2Utils {
    public static byte[] compress(byte srcBytes[]) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
        bcos.write(srcBytes);
        bcos.close();
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(
                in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public static String uncompressToString(byte[] data) throws IOException {
        return new String(uncompress(data), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws IOException {
        String s = "AAAAAAAAAAAAAAAAAA";
        System.out.println("字符串长度：" + s.length());
        byte[] data = compress(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("压缩后：：" + data.length);
        System.out.println("解压后：" + uncompress(compress(s.getBytes(StandardCharsets.UTF_8))).length);
        System.out.println("解压后字符串：" + uncompressToString(data));
    }

    @Test
    public void test() throws IOException {
        FileInputStream fis = new FileInputStream(new File("/tmp/textfile.txt"));
        FileChannel channel = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate((int)channel.size());
        channel.read(bb);
        byte[] beforeBytes = bb.array();

        //compress
        System.out.println("before bzip2 compress size：" + beforeBytes.length + " bytes");
        long startTime1 = System.currentTimeMillis();
        byte[] afterBytes = compress(beforeBytes);
        long endTime1 = System.currentTimeMillis();
        System.out.println("after bzip2 compress size：" + afterBytes.length + " bytes");
        System.out.println("bzip2 compress time elapsed:" + (endTime1 - startTime1) + "ms");

        //uncompress
        long startTime2 = System.currentTimeMillis();
        byte[] resultBytes = uncompress(afterBytes);
        System.out.println("after bzip2 uncompress size：" + resultBytes.length + " bytes");
        long endTime2 = System.currentTimeMillis();
        System.out.println("bzip2 uncompress time elapsed：" + (endTime2 - startTime2) + "ms");
    }
}