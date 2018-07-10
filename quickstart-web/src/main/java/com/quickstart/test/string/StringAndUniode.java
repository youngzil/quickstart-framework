package com.quickstart.test.string;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StringAndUniode {

    /**
     * 将字符串转成unicode
     * 
     * @param str 待转字符串
     * @return unicode字符串
     */
    public String convertUnicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); // 取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); // 取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * 将unicode 字符串
     * 
     * @param str 待转字符串
     * @return 普通字符串
     */
    public String revertString(String str) {
        str = (str == null ? "" : str);
        if (str.indexOf("\\u") == -1)// 如果不是unicode码则原样返回
            return str;

        StringBuffer sb = new StringBuffer(1000);

        for (int i = 0; i < str.length() - 6;) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }

                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }

    /*public static void main(String[] args) throws IOException {
    	Properties prop = new Properties();
    	//prop.load(StringAndUniode.class.getResource("ErrorMessages.properties").openStream());
    	InputStream dd = StringAndUniode.class.getClassLoader().getResourceAsStream("ErrorMessages.properties");
    	
    	prop.load(dd);
    	String value = prop.getProperty("EXCEL_EXT_INVIDATE");
    	String name = new String(value.getBytes("ISO8859-1"), "UTF-8");
    	System.out.println(name);
    	
    }*/
    public static void main(String[] args) throws IOException {
        Properties prop1 = new Properties();
        // prop.load(StringAndUniode.class.getResource("ErrorMessages.properties").openStream());
        InputStream dd = StringAndUniode.class.getClassLoader().getResourceAsStream("ErrorMessages.properties");
        InputStream dd2 = StringAndUniode.class.getResource("ErrorMessages.properties").openStream();
        prop1.load(dd2);
        String value = prop1.getProperty("ACCOUNT_TYPE_IS_PROCEEDS");
        String name = new String(value.getBytes("ISO8859-1"), "UTF-8");
        System.out.println(value);
        System.out.println(name);

        String ss = new String(value.getBytes("UTF-8"), "ISO8859-1");
        StringAndUniode sau = new StringAndUniode();
        String aa = sau.convertUnicode(ss);
        System.out.println(aa);
    }

}
