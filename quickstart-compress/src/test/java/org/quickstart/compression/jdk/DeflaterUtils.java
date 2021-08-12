package org.quickstart.compression.jdk;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflaterUtils {
    public static byte[] compress(byte input[]) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    public static byte[] uncompress(byte[] input) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }

    public static String uncompressToString(byte[] input) throws DataFormatException {
        return new String(uncompress(input), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws DataFormatException {
        String s = "AAAAAAAAAAAAAAAAAA";
        System.out.println("字符串长度：" + s.length());
        byte[] data = compress(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("压缩后：：" + data.length);
        System.out.println("解压后：" + uncompress(compress(s.getBytes(StandardCharsets.UTF_8))).length);
        System.out.println("解压后字符串：" + uncompressToString(data));
    }
}