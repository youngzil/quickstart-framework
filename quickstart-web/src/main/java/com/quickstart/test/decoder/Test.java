package com.quickstart.test.decoder;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        String ss = "少女锯6根肋骨";

        String encode = URLEncoder.encode(ss);// 等于前台js的encodeURI（）

        System.out.println(encode);

        String encode2 = URLEncoder.encode(encode);// 等于前台js的encodeURI（）
        System.out.println("code2:" + encode2);

        String encode3 = URLEncoder.encode(encode2);// 等于前台js的encodeURI（）
        System.out.println("code23:" + encode3);

        String decode = URLDecoder.decode(encode);

        System.out.println(decode);

        String dd = "http://www.baidu.com/link?url=XapxGiocuEFKbduZy-gQK5k1DGidry0xYct5_zXYB5KOc9TLIY_pZz3K7QSbJb5Xh8IClxUPYQPueZ0pth2eAK";
        String sd = URLDecoder.decode(dd);
        System.out.println("do:" + sd);
    }
}
