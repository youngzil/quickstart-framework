/**
 * 项目名称：quickstart-example 
 * 文件名：ConvertStringToHexadecimal.java
 * 版本信息：
 * 日期：2017年8月12日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;

/**
 * ConvertStringToHexadecimal
 * 
 * @author：youngzil@163.com
 * @2017年8月12日 上午10:14:55
 * @since 1.0
 */
public class ConvertStringToHexadecimal {
    // https://stackoverflow.com/questions/923863/converting-a-string-to-hexadecimal-in-java
    public static void main(String[] args) throws UnsupportedEncodingException {

        String myString = "hahhha";
        String charset = "UTF-8";

        // test1
        System.out.println(new ConvertStringToHexadecimal().toHex(myString));
        System.out.println(new ConvertStringToHexadecimal().toHex2(myString));

        // test2:apache.commons.codec
        String hexString = Hex.encodeHexString(myString.getBytes(/* charset */));
        System.out.println(hexString);

        // test3
        System.out.println(hexadecimal(myString, charset));

        // test4
        System.out.println(stringToHex(myString));

        // test5
        System.out.println(stringToHex2(myString));

        // test5

        String hexStr = toHexString(myString);
        String sourceStr = fromHexString(hexStr);
        System.out.println(hexStr);
        System.out.println("sourceStr=" + sourceStr);

        // test6
        System.out.println(toHexadecimal(myString));

        // test7
        // hex like: 0xfff7931e to int
        int hexInt = Long.decode(myString).intValue();
        System.out.println(hexInt);

    }

    public String toHex(String arg) throws UnsupportedEncodingException {
        return String.format("%040x", new BigInteger(1, arg.getBytes("UTF-8")));
    }

    public String toHex2(String arg) {
        return String.format("%x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }

    // ------------
    public static String hexadecimal(String input, String charsetName) throws UnsupportedEncodingException {
        if (input == null)
            throw new NullPointerException();
        return asHex(input.getBytes(charsetName));
    }

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public static String asHex(byte[] buf) {
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }

    // -------------
    static String stringToHex(String string) {
        StringBuilder buf = new StringBuilder(200);
        for (char ch : string.toCharArray()) {
            if (buf.length() > 0)
                buf.append(' ');
            buf.append(String.format("%04x", (int) ch));
        }
        return buf.toString();
    }

    // --------------
    static String stringToHex2(String string) {
        StringBuffer hex = new StringBuffer();
        char[] raw = string.toCharArray();
        for (int i = 0; i < raw.length; i++) {
            if (raw[i] <= 0x000F) {
                hex.append("000");
            } else if (raw[i] <= 0x00FF) {
                hex.append("00");
            } else if (raw[i] <= 0x0FFF) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(raw[i]).toUpperCase());
        }

        return hex.toString();
    }

    // --------------
    public static String toHexString(String myString) {
        byte[] ba = myString.getBytes();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }

    public static String fromHexString(String hex) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            str.append((char) Integer.parseInt(hex.substring(i, i + 2), 16));
        }
        return str.toString();
    }

    // --------------

    public static String toHexadecimal(String text) throws UnsupportedEncodingException {
        byte[] myBytes = text.getBytes("UTF-8");

        return DatatypeConverter.printHexBinary(myBytes);
    }

}
