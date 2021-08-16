package org.quickstart.javase.tool;

import eu.medsea.mimeutil.MimeUtil;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class MimeTypeTest {

    String filename = "/Users/lengfeng/Desktop/img05.png";

    // byte[] data ： 传入字节数组的数据
    // File file ：传入一个file
    // boolean onlyMimeMatch：仅尝试获取mime类型，如果为true，则不处理子匹配
    // boolean extensionHints：是否使用扩展来优化内容测试的顺序

    // java-使用Jmimemagic判断文件格式
    @Test
    public void testJmimemagic() throws MagicMatchNotFoundException, MagicException, MagicParseException, IOException {
        MagicMatch magicMatch = Magic.getMagicMatch(new File(filename), true, false);
        System.out.println(magicMatch.getMimeType()); //image/jpeg
        System.out.println(magicMatch.getExtension()); //jpg

        // 文件转字节数组：common.io包中的FileUtils
        // FileUtils.readFileToByteArray(new File("a.jpg"));
        //
        // 字节数组转文件：org.springframework.util.FileCopyUtils
        // byte[] bytes = FileUtils.readFileToByteArray(new File("a.jpg"));
        // FileCopyUtils.copy(bytes,new File("a.jpg"));

        String mimeType = Magic.getMagicMatch(new File(filename), false).getMimeType();
        System.out.println("jMimeMagic mimeType=" + mimeType);

        // snippet for JMimeMagic lib
        //     http://sourceforge.net/projects/jmimemagic/

        Magic parser = new Magic();
        // getMagicMatch accepts Files or byte[],
        // which is nice if you want to test streams

        byte[] bFile1 = Files.readAllBytes(new File(filename).toPath());
        //or this
        byte[] bFile2 = Files.readAllBytes(Paths.get(filename));

        MagicMatch match = parser.getMagicMatch(bFile1);
        System.out.println("MagicMatchType=" + match.getMimeType());

        Path path = Paths.get(new File(filename).getAbsolutePath());
        byte[] data = Files.readAllBytes(path);
        MagicMatch match2 = Magic.getMagicMatch(data);
        mimeType = match2.getMimeType();
        System.out.println("mimeType=" + mimeType);

    }

    @Test
    public void testTika() {

    }

    @Test
    public void testMimeUtil() {
        Collection mimeTypes = MimeUtil.getMimeTypes(new File(filename));
        String s = mimeTypes.toString();
        System.out.println("sss=" + s);

    }

    @Test
    public void testDroid() {

    }

    @Test
    public void jdk() throws IOException {

        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        String mimeType = URLConnection.guessContentTypeFromStream(is);
        if (mimeType == null) {
            throw new IOException("can't get mime type of image");
        }

        System.out.println("mimeType=" + mimeType);

        System.out.println("getContentType=" + getContentType(filename));

        Path source = Paths.get(filename);
        System.out.println(Files.probeContentType(source));
        // output : image/tiff

        File f = new File(filename);
        System.out.println("Mime Type of " + f.getName() + " is " + new MimetypesFileTypeMap().getContentType(f));
        // expected output :
        // "Mime Type of gumby.gif is image/gif"

        System.out.println(getMimeType(filename));
        // output :  text/plain
    }

    public static String getMimeType(String fileUrl) throws java.io.IOException {
        String type = null;
        URL u = new URL(fileUrl);
        URLConnection uc = null;
        uc = u.openConnection();
        type = uc.getContentType();
        return type;
    }

    public static String getContentType(String filename) {
        String g = URLConnection.guessContentTypeFromName(filename);
        if (g == null) {
            g = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(filename);
        }
        return g;
    }

}
