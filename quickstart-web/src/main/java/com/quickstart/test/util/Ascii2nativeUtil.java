package com.quickstart.test.util;

/**
 * 
 * @author ziliang.yang
 *
 */
public class Ascii2nativeUtil {

    /**
     * 将ascii转换为本地语言
     * 
     * @param ascii
     * @return
     */
    public static String ascii2native(String ascii) {
        int n = ascii.length() / 6;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0, j = 2; i < n; i++, j += 6) {
            String code = ascii.substring(j, j + 4);
            char ch = (char) Integer.parseInt(code, 16);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ascii = "19968";
        String s = Ascii2nativeUtil.ascii2native(ascii);
        System.out.println("ssssssssss-------------->" + s);
    }

}
