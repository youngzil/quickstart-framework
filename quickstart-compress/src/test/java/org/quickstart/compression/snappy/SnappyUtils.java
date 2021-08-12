package org.quickstart.compression.snappy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.xerial.snappy.Snappy;

/**
 * 字符串解压缩（Snappy）
 * @author suncht
 *
 */
public class SnappyUtils {
    public static byte[] compressHtml(String str) {
        try {
            return Snappy.compress(str.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decompressHtml(byte[] bytes) {
        try {
            return new String(Snappy.uncompress(bytes), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasdfasdfaaaaaaaaa12121sdgfas";
        System.out.println("字符串长度：" + s.length());
        System.out.println("压缩后：：" + compressHtml(s).length);
        System.out.println("解压后：" + decompressHtml(compressHtml(s)).length());

    }
}
