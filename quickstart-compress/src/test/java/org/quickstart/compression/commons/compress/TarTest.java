package org.quickstart.compression.commons.compress;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TarTest {

    /**
     * 解压全部文件
     * 解压全部文件时可以通过TarArchiveInputStream.getNextTarEntry遍历所有文件并进行解压，比如我要将位于/root/test.tar位置的文件，全部解压到/tmp/output目录下
     *
     * @throws IOException
     */
    @Test
    public void testTarUncompressFile() throws IOException {

        try (FileInputStream in = new FileInputStream("/root/test.tar");
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(in)){
            byte[] buffer = new byte[4096];
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                File outputFile = new File("/tmp/output/" + entry.getName());

                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    while (tarArchiveInputStream.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                }
            }
        }

    }

    /**
     * 解压特定文件
     * 可以在遍历时，根据entry判断是否是需要解压的文件，比如需要将/root/test.tar压缩包中，文件名为targetFile的文件，解压到/tmp/output/targetFile文件中
     *
     * @throws IOException
     */
    @Test
    public void testTarUncompressFile2() throws IOException {

        try (FileInputStream in = new FileInputStream("bla.tar");
            TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(in)) {
            byte[] buffer = new byte[4096];
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }

                if (entry.getName() != "targetFile") {
                    continue;
                }

                File outputFile = new File("/tmp/output/" + entry.getName());

                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    while (tarArchiveInputStream.read(buffer) > 0) {
                        fos.write(buffer);
                    }
                }
            }
        }

    }

    @Test
    public void testZipCompress() throws IOException {

    }

    @Test
    public void testZipCompress2() throws IOException {

    }
}
