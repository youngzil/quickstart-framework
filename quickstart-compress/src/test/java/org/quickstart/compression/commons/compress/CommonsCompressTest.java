package org.quickstart.compression.commons.compress;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

public class CommonsCompressTest {

    @Test
    public void test() throws IOException {
        FileInputStream fis = new FileInputStream(new File("/tmp/textfile.txt"));
        FileChannel channel = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate((int)channel.size());
        channel.read(bb);
        byte[] beforeBytes = bb.array();

        //compress
        System.out.println("before bzip2 compress size：" + beforeBytes.length + " bytes");
        long startTime1 = System.currentTimeMillis();
        byte[] afterBytes = compress(beforeBytes);
        long endTime1 = System.currentTimeMillis();
        System.out.println("after bzip2 compress size：" + afterBytes.length + " bytes");
        System.out.println("bzip2 compress time elapsed:" + (endTime1 - startTime1) + "ms");

        //uncompress
        long startTime2 = System.currentTimeMillis();
        byte[] resultBytes = uncompress(afterBytes);
        System.out.println("after bzip2 uncompress size：" + resultBytes.length + " bytes");
        long endTime2 = System.currentTimeMillis();
        System.out.println("bzip2 uncompress time elapsed：" + (endTime2 - startTime2) + "ms");
    }


}
