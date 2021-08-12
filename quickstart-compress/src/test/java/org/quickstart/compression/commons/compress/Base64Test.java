package org.quickstart.compression.commons.compress;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

    /**
     * 使用org.apache.commons.codec.binary.Base64压缩字符串
     * @param str 要压缩的字符串
     * @return
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return Base64.encodeBase64String(str.getBytes());
    }
    /**
     * 使用org.apache.commons.codec.binary.Base64解压缩
     * @param compressedStr 压缩字符串
     * @return
     */
    public static byte[] uncompress(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }
        return Base64.decodeBase64(compressedStr);
    }

}
