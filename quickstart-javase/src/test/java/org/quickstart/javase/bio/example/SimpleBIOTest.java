/**
 * 项目名称：quickstart-javase 
 * 文件名：SimpleBIOTest.java
 * 版本信息：
 * 日期：2018年5月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.bio.example;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

/**
 * SimpleBIOTest
 * 
 * @author：youngzil@163.com
 * @2018年5月10日 下午5:05:01
 * @since 1.0
 */
public class SimpleBIOTest {

    public static void main(String[] args) throws IOException {
        // 区分是字符流还是字节流
        // 以InputStream或OutputStream结尾是字节流,以Reader或Writer结尾是字符流
        // 区分是输入还是输出
        // 以Input或Read开头是输入,以Output或Writer结尾时输出

        // byte stream
        // input stream

        String testStr = "heh";
        byte[] storeByte = new byte[10];
        System.out.println(new String(storeByte));

        StringBufferInputStream strBufferInputStream = new StringBufferInputStream(testStr); // 从String中读取数据到Stream中
        strBufferInputStream.read(storeByte); // 从Stream中逐个字节读取
        System.out.println(new String(storeByte));

        File testFile = new File("filePath");
        FileInputStream fileInputStream = new FileInputStream(testFile); // 从文件中读取数据到Stream中
        fileInputStream.read(storeByte); // 从Stream中逐个字节读取 // 文件结尾返回 -1

        byte[] testByte = new byte[10];
        ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(testByte); // 从ByteArray中读取数据到Stream中
        byteArrInputStream.read(testByte); // 从Stream中逐个字节读取

        // output stream
        File testFile2 = new File("filePath"); // 写入到哪个文件中去
        FileOutputStream fileOutputStream = new FileOutputStream(testFile2);
        byte[] srcByte = new byte[10];
        fileOutputStream.write(srcByte); // 将byteArray写入到指定文件中

        ByteArrayOutputStream byteArrOutputStream = new ByteArrayOutputStream();
        byte[] srcByte2 = new byte[10];
        byteArrOutputStream.write(srcByte2); // 将byteArray写入到Stream中

        // character stream
        String encoding = "utf-8";
        File testFile3 = new File("filePath");
        FileInputStream fileInputStream2 = new FileInputStream(testFile3);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream2, encoding);
        char[] characters = new char[1024];
        inputStreamReader.read(characters); // 逐个字符读取,从字节流中读取并根据编码转换成字符,存入字符数组中

        BufferedReader bufferReader = new BufferedReader(inputStreamReader);
        bufferReader.readLine(); // 按行读取字符 // 文件结尾返回null
    }

    /**
     * BIO模式 从输入流中读取指定长度的数据到字节数组中 如何确定字节数组长度：file.length()返回字节数组长度 如何保证文件内容全部读入：字节数组长度与文件内容长度相等
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static byte[] readByBytes(String fileName) throws IOException {
        File file = new File(fileName);
        InputStream fileInputStream = new FileInputStream(file);
        long length = file.length(); // return length in bytes
        System.out.println("file length:" + length);
        byte[] fileBytes = new byte[(int) length];
        int result = fileInputStream.read(fileBytes);
        fileInputStream.close(); // must close
        if (result != length) {
            throw new IOException("can't read all," + result + "!=" + length);
        }
        System.out.println("read length: " + result);
        return fileBytes;
    }

    /**
     * BIO模式 逐个字节读取 如何确定字节数组长度：file.length()返回字节数 如何保证文件内容全部被读取：比较文件长度与读取字节数组长度是否相等
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static byte[] readByOneByte(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        long length = file.length(); // return length in bytes
        System.out.println("file length:" + length);
        byte[] fileBytes = new byte[(int) length];
        int resultByte = fileInputStream.read();
        int i = 0; // array index
        while (resultByte != -1) {
            byte tempByte = (byte) resultByte;
            fileBytes[i] = tempByte;
            i++;
            resultByte = fileInputStream.read();
        }
        fileInputStream.close(); // must close
        if (fileBytes.length != length) {
            throw new IOException("can't read all," + fileBytes.length + "!=" + length);
        }
        System.out.println("read length: " + fileBytes.length);
        return fileBytes;
    }

    /**
     * BIO模式 FileReader逐个字符读取文件,FileReader extends InputStreamReader 读取文件中内容到字符数组中 如何确定字符数组长度： FileReader不能自定义编码读取 此方法也可以用于读取二进制文件,只不过读取出来有很多乱码
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static char[] readByOneCharWithDefaultEncoding(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file); // 不能自定义编码,内部默认采用系统的编码
        System.out.println("当前采用编码: " + fileReader.getEncoding());
        char[] charcters = new char[1024];
        int result = fileReader.read(); // 逐个字符读取，不能按行读取
        int i = 0;
        while (result != -1 && i < 1024) {
            char temp = (char) result;
            charcters[i] = temp;
            i++;
            result = fileReader.read();
        }
        fileReader.close();
        return charcters;
    }

    /**
     * BIO模式 逐个字符读取文件 读取文件中内容到字符数组中 如何确定字符数组长度： 此方法也可以用于读取二进制文件,只不过读取出来有很多乱码
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static char[] readByOneCharWithCustomEncoding(String fileName, String encoding) throws IOException {
        File file = new File(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), encoding);
        System.out.println("当前采用编码: " + inputStreamReader.getEncoding());
        char[] charcters = new char[1024];
        int result = inputStreamReader.read(); // 逐个字符读取，不能按行读取
        int i = 0;
        while (result != -1 && i < 1024) {
            char temp = (char) result;
            charcters[i] = temp;
            i++;
            result = inputStreamReader.read();
        }
        inputStreamReader.close();
        return charcters;
    }

    /**
     * BIO模式 使用InputStreamReader作为字节流与字符流之间的桥梁 使用BufferedReader提升读取效率
     * 
     * @param fileName
     * @throws IOException
     */
    public static void readByLine(String fileName) throws IOException {
        String encoding = "utf-8"; // 既然是读取字符,肯定会涉及到编码问题
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            InputStream inputStream = new FileInputStream(file);
            // inputStreamReader是连接字节流和字符流的桥梁
            // 读取指定数量的字节,并按照传入的编码转换成字符
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, encoding);
            // 使用BufferedReader的好处是可以一次读取多个字符（而不是一个字符）
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String readLine = bufferReader.readLine();
            while (readLine != null) {
                System.out.println(readLine);
                readLine = bufferReader.readLine();
            }
            bufferReader.close();
        }
    }

}
