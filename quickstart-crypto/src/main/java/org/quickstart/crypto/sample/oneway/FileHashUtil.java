/**
 * 项目名称：quickstart-javase 
 * 文件名：FileHashUtil.java
 * 版本信息：
 * 日期：2018年10月18日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.crypto.sample.oneway;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * FileHashUtil 
 *  
 * @author：youngzil@163.com
 * @2018年10月18日 下午8:55:46 
 * @since 1.0
 */
public class FileHashUtil {
 
    public static final char[] hexChar = { 
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static final String[] hashTypes = new String[] { "MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512" };
    
    public void MD5File(String fileName) throws Exception{
        //String fileName = args[0];
        System.out.println("需要获取hash的文件为：　" + fileName);
        java.util.List<MessageDigest> mds = new java.util.ArrayList<MessageDigest>();
        for (String hashType : hashTypes) {
            MessageDigest md = MessageDigest.getInstance(hashType);
            mds.add(md);
        }
        InputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                for (MessageDigest md : mds) {
                    md.update(buffer, 0, numRead);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        for (MessageDigest md : mds) {
            System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
        }
    }
    
 
    public static void main(String[] args) throws Exception {
        String[] fileName = new String[] {"D:/hapfish/ShellFolder.java","D:/hapfish/ShellFolder - 副本.java",
                  "E:/ShellFolder - 副本.java","E:/ShellFolder.txt","D:/hapfish/ShellFolder.jpg",
                  "E:/ShellFolder增加字符.txt","D:/hapfish/birosoft.jar"};
        FileHashUtil files  = new FileHashUtil();
        for(int i=0;i<fileName.length;i++){
            files.MD5File(fileName[i]);
        } 
        
        /*"D:/hapfish/ShellFolder.java",
        "D:/hapfish/ShellFolder - 副本.java",
        "E:/ShellFolder - 副本.java",
        "E:/ShellFolder.txt",
        "D:/hapfish/ShellFolder.jpg",
        以上五个文件是同一文件经过复制、改扩展名的，最后计算哈希结果是一致的。
        "E:/ShellFolder增加字符.txt" 增加了几个字符串，就不一样了
        "D:/hapfish/birosoft.jar" 完全不相关的另外一个文件*/
        
        
    }
 
    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
 
}

