package org.quickstart.compression.commons.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

public class ZipTest {

    @Test
    public void testZipCompress() throws IOException {
        File archive = new File("/root/xx.zip");
        try (ZipArchiveOutputStream outputStream = new ZipArchiveOutputStream(archive)) {
            ZipArchiveEntry entry = new ZipArchiveEntry("testdata/test1.xml");
            // 可以设置压缩等级
            outputStream.setLevel(5);
            // 可以设置压缩算法，当前支持ZipEntry.DEFLATED和ZipEntry.STORED两种
            outputStream.setMethod(ZipEntry.DEFLATED);
            // 也可以为每个文件设置压缩算法
            entry.setMethod(ZipEntry.DEFLATED);
            // 在zip中创建一个文件
            outputStream.putArchiveEntry(entry);
            // 并写入内容
            outputStream.write("abcd\n".getBytes(Charset.forName("UTF-8")));
            // 完成一个文件的写入
            outputStream.closeArchiveEntry();

            entry = new ZipArchiveEntry("testdata/test2.xml");
            entry.setMethod(ZipEntry.STORED);
            outputStream.putArchiveEntry(entry);
            outputStream.write("efgh\n".getBytes(Charset.forName("UTF-8")));
            outputStream.closeArchiveEntry();
        }
    }

    /**
     * 解压特定文件
     * 根据名字解压特定文件，比如将/root/test.zip文件中，名称为targetFile的文件，解压为/tmp/output/targetFile文件
     * @throws IOException
     */
    @Test
    public void testZipUncompressFile() throws IOException {

        ZipFile zipFile = new ZipFile("/root/test.zip");
        ZipArchiveEntry entry = zipFile.getEntry("targetFile"); // 我们可以根据名字，直接找到要解压的文件
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            // 这里inputStream就是一个正常的IO流，按照正常IO流的方式读取即可，这里简单给个例子
            byte[] buffer = new byte[4096];
            File outputFile = new File("/tmp/output/targetFile");
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                while (inputStream.read(buffer) > 0) {
                    fos.write(buffer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解压全部文件
     * 解压全部文件，比如要将/root/test.zip中的全部文件，解压到/tmp/output目录下
     * @throws IOException
     */
    @Test
    public void testZipUncompressAllFile() throws IOException {

        ZipFile zipFile = new ZipFile(new File("/root/test.zip"));
        byte[] buffer = new byte[4096];
        ZipArchiveEntry entry;
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntries(); // 获取全部文件的迭代器
        InputStream inputStream;
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            if (entry.isDirectory()) {
                continue;
            }

            File outputFile = new File("/tmp/output/" + entry.getName());

            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            inputStream = zipFile.getInputStream(entry);
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                while (inputStream.read(buffer) > 0) {
                    fos.write(buffer);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
