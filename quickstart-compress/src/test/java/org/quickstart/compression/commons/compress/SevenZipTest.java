package org.quickstart.compression.commons.compress;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class SevenZipTest {

    /**
     * 解压全部文件
     * 解压全部文件时可以通过sevenZFile.getNextEntry遍历所有文件并进行解压，比如我要将位于/root/test.7zip位置的文件，全部解压到/tmp/output目录下
     *
     * @throws IOException
     */
    @Test
    public void test7ZipUncompress() throws IOException {
        try (SevenZFile sevenZFile = new SevenZFile(new File("/root/test.7zip"))) {
            byte[] buffer = new byte[4096];
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                File outputFile = new File("/tmp/output/" + entry.getName());

                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    while (sevenZFile.read(buffer) > 0) {
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

    /**
     * 解压特定文件
     * 可以通过sevenZFile.getEntries()，获得所有文件后，通过遍历找到需要解压的zip文件，调用SevenZFile.getInputStream获取其InputStream，进行解压，比如需要将/root/test.7zip压缩包中，文件名为targetFile的文件，解压到/tmp/output/targetFile文件中
     */
    @Test
    public void test7ZipUncompress2() throws IOException {

        SevenZFile sevenZFile = new SevenZFile(new File("/root/test.7zip"));
        final Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
        InputStream inputStream = null;
        for (SevenZArchiveEntry entry : entries) {
            if (entry.getName().equals("targetFile")) {
                inputStream = sevenZFile.getInputStream(entry);
            }
        }

        // 读取input stream即可完成解压
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

    //    Compress压缩7z文件，是通过xz utils实现的，并且xz在Compress的POM中是可选依赖，因此，如果要使用Compress压缩7z文件，需要在POM中手动依赖xz utils
    @Test
    public void test7ZipCompress() throws IOException {

        File dir = new File("/root/dir");
        File output = new File(dir, "test.7z");

        final Date accessDate = new Date();
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        final Date creationDate = cal.getTime();

        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            // 在7z中创建一个文件夹
            SevenZArchiveEntry entry = outArchive.createArchiveEntry(dir, "foo/");
            outArchive.putArchiveEntry(entry);
            outArchive.closeArchiveEntry();

            // 创建一个新的文件entry
            entry = new SevenZArchiveEntry();
            // 文件命名
            entry.setName("foo/bar");
            // 文件创建时间、最后修改时间
            entry.setCreationDate(creationDate);
            entry.setAccessDate(accessDate);
            outArchive.putArchiveEntry(entry);
            // 这里是文件要写入的数据，可能是从其他文件中读取到的，也可以自己操作
            byte[] data = "ssss".getBytes("UTF-8");
            outArchive.write(new byte[0]);
            outArchive.closeArchiveEntry();

            // 写入第二个文件，与第一个类似
            entry = new SevenZArchiveEntry();
            entry.setName("xyzzy");
            outArchive.putArchiveEntry(entry);
            outArchive.write(0);
            outArchive.closeArchiveEntry();

            // 完成7z文件写入后，需要调用finish
            outArchive.finish();
        }
    }
}
