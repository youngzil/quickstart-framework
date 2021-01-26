package org.quickstart.compression.snappy;

import org.junit.Test;
import org.xerial.snappy.BitShuffle;
import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SnappyJavaTest {

    public static void main(String[] args) throws IOException {
        // 预选解压缩类库使用介绍--Snappy
        // 压缩

        String s = "这是一个用于测试的字符串";
        System.out.println("str=" + s.getBytes().length);

        byte[] compressed = Snappy.compress(s.getBytes()); // --返回压缩后的字符串的字节数组
        System.out.println("compressed=" + compressed.length);

        // 解压
        byte[] uncompressed = Snappy.uncompress(compressed); // --返回解压缩后的字符串的字节数组
        System.out.println("uncompressed=" + new String(uncompressed));

    }

    @Test
    public void testSnappy() throws IOException {
        String input =
            "Hello snappy-java! Snappy-java is a JNI-based wrapper of " + "Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void testSnappyInputStream() throws IOException {

        String dataString = "The quick brown fox jumps over the lazy dog";
        byte[] compressedData = Snappy.compress(dataString.getBytes());
        System.out.println("Snappy.compress  压缩结果：" + bytes2hex(compressedData));
        byte[] compressedData2 = compressSnappy(dataString.getBytes());
        System.out.println("SnappyInputStream压缩结果：" + bytes2hex(compressedData2));

        /**
         * 输出如下：
         * Snappy.compress  压缩结果：2b a8 54 68 65 20 71 75 69 63 6b 20 62 72 6f 77 6e 20 66 6f 78 20 6a 75 6d 70 73 20 6f 76 65 72 20 74 68 65 20 6c 61 7a 79 20 64 6f 67
         * SnappyInputStream压缩结果：82 53 4e 41 50 50 59 00 00 00 00 01 00 00 00 01 00 00 00 2d 2b a8 54 68 65 20 71 75 69 63 6b 20 62 72 6f 77 6e 20 66 6f 78 20 6a 75 6d 70 73 20 6f 76 65 72 20 74 68 65 20 6c 61 7a 79 20 64 6f 67
         *                         |---------------------magic header(16bytes)-----|size(4bytes)|----compressed data-----
         */

        /**
         *
         * 区别如下：
         * 通过Snappy.compress()进行压缩，压缩后的数据没有magic header
         * 通过SnappyInputStream进行压缩，压缩后的数据有固定的header
         */

    }

    public static byte[] compressSnappy(byte[] data) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SnappyOutputStream sos = new SnappyOutputStream(os);
        int count;
        int BUFFER = 1024;
        byte temp[] = new byte[BUFFER];
        try {
            while ((count = is.read(temp)) != -1) {
                sos.write(temp, 0, count);
            }
            sos.flush();
            byte[] output = os.toByteArray();
            return output;
        } finally {
            sos.close();
            is.close();
        }
    }

    /**
     * 将byte数组按16进制的方式输出
     */
    public static String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes) {
            // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            sb.append(tmp).append(" ");
        }
        return sb.toString();
    }

    //    BitShuffle是一种算法，可对数据位（随机播放）重新排序以进行有效压缩（例如，整数序列，浮点值等）。
    @Test
    public void testBitShuffle() throws IOException {

        int[] data = new int[] {1, 3, 34, 43, 34};
        byte[] shuffledByteArray = BitShuffle.shuffle(data);
        byte[] compressed = Snappy.compress(shuffledByteArray);
        byte[] uncompressed = Snappy.uncompress(compressed);
        int[] result = BitShuffle.unshuffleIntArray(uncompressed);

        System.out.println(result);

    }

}
