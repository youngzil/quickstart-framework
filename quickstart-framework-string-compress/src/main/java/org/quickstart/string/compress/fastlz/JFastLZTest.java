/**
 *
 */
package org.quickstart.string.compress.fastlz;

import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.Adler32;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfastlz.JFastLZ;
import org.jfastlz.JFastLZLevel;
import org.jfastlz.JFastLZPack;
import org.jfastlz.JFastLZUnpack;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class JFastLZTest {
    private final Log LOG = LogFactory.getLog(getClass());

    private static final String TEST_FILES_DIR = "testfiles";

    // randomize JFastLZLevel
    private Random r;
    // for file integrity check
    private Adler32 adler32;
    private int fileCount;

    @Before
    public void setup() throws Exception {
        r = new Random();
        adler32 = new Adler32();
    }

    @Test
    public void testPackUnpackUsingFiles() throws Exception {

        File dir = new File(TEST_FILES_DIR);
        if (!dir.exists() || !dir.isDirectory() || !dir.canRead()) {
            throw new RuntimeException("bad test files directory: " + TEST_FILES_DIR);
        }

        File tempDir = new File(".tmp." + System.currentTimeMillis());
        assertTrue(tempDir.mkdir());

        try {
            for (int i = 0; i < 5; i++) {
                File generatedFile = generateFile(dir);
                packUnpackFile(generatedFile, tempDir);
                generatedFile.delete();
            }
        } finally {
            if (null != tempDir && tempDir.exists()) {
                deleteDir(tempDir);
            }

        }
        LOG.info("Successfully packed, unpacked and checksum verified " + fileCount + " files");
    }

    private File generateFile(File tempDir) throws IOException {

        File f = new File(tempDir + File.separator + ".tempFile" + System.currentTimeMillis());

        assertTrue(f.createNewFile());

        byte[] data = new byte[r.nextInt(1024 * 1024 * 2) + 32];

        r.nextBytes(data);

        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));

        os.write(data, 0, data.length);

        os.close();

        LOG.info("generate file of size : " + data.length);

        return f;

    }

    @Test
    public void testPackUnpackUsingRandomBytes() throws Exception {

        Random r = new Random();
        JFastLZPack p = new JFastLZPack();
        JFastLZUnpack u = new JFastLZUnpack();

        byte[] original = null;
        byte[] packed = null;
        byte[] unpacked = null;
        int packedSize = 0;
        int unpackedSize = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {

            // new byte[r.nextInt(1024*1024*2)+16];
            original = new byte[1024 * 1024 * 2];
            r.nextBytes(original);

            packed = new byte[original.length * 6];
            unpacked = new byte[original.length * 6];

            packedSize = p.pack(original, 0, original.length, packed, 0, packed.length, (r.nextBoolean() ? JFastLZLevel.One : JFastLZLevel.Two));

            assertTrue(packedSize > 0);

            unpackedSize = u.unpack(packed, 0, packedSize, unpacked, 0, unpacked.length);

            assertTrue(unpackedSize > 0);

            assertTrue(unpackedSize == original.length);

            for (int j = 0; j < unpackedSize; j++) {
                assertTrue("bytes don't match", unpacked[j] == original[j]);
            }
        }
        LOG.info("pack/unpack performance: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testCompressDecompressUsingRandomBytes() throws Exception {

        Random r = new Random();
        JFastLZ jflz = new JFastLZ();

        byte[] original = null;
        byte[] packed = null;
        byte[] unpacked = null;
        int packedSize = 0;
        int unpackedSize = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {

            // new byte[r.nextInt(1024*1024*2)+16];
            original = new byte[1024 * 1024 * 2];
            r.nextBytes(original);

            packed = new byte[original.length * 6];
            unpacked = new byte[original.length * 6];

            packedSize = jflz.fastlzCompress((r.nextBoolean() ? JFastLZLevel.One : JFastLZLevel.Two), original, 0, original.length, packed, 0, packed.length);

            assertTrue(packedSize > 0);

            unpackedSize = jflz.fastlzDecompress(packed, 0, packedSize, unpacked, 0, unpacked.length);

            assertTrue(unpackedSize > 0);

            assertTrue(unpackedSize == original.length);

            for (int j = 0; j < unpackedSize; j++) {
                assertTrue("bytes don't match", unpacked[j] == original[j]);
            }
        }
        LOG.info("fastlz compress/decompress performance: " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testStreamUsingRandomBytes() throws Exception {

        JFastLZPack p = new JFastLZPack();
        JFastLZUnpack up = new JFastLZUnpack();
        Random r = new Random();
        byte[] original = null;
        byte[] unpacked = null;
        for (int i = 0; i < 100; i++) {
            original = new byte[r.nextInt(1024 * 1024 * 2) + 16];
            r.nextBytes(original);

            ByteArrayInputStream is = new ByteArrayInputStream(original);

            ByteArrayOutputStream os = new ByteArrayOutputStream(original.length * 4);

            p.packStream(is, original.length, "null", os, (r.nextBoolean() ? JFastLZLevel.One : JFastLZLevel.Two));

            ByteArrayInputStream is2 = new ByteArrayInputStream(os.toByteArray());
            ByteArrayOutputStream os2 = new ByteArrayOutputStream(original.length * 4);

            up.unpackStream(is2, os2);

            unpacked = os2.toByteArray();

            assertTrue(unpacked.length == original.length);

            for (int j = 0; j < unpacked.length; j++) {
                assertTrue("bytes don't match", unpacked[j] == original[j]);
            }
        }
    }

    private void packUnpackFile(File file, File tempDir) throws Exception {

        JFastLZPack jfastLZPack = new JFastLZPack();
        JFastLZUnpack jFastLZUnpack = new JFastLZUnpack();

        long compressTimestampBefore = 0l;
        long compressTimestampAfter = 0l;
        long uncompressTimestampBefore = 0l;
        long uncompressTimestampAfter = 0l;

        File compressed = null;
        File uncompressed = null;
        try {
            assertTrue(file.canRead());

            assertTrue(file.length() > 0);

            LOG.info("testing file: " + file + " of size: " + String.format("%.2f", (file.length() / 1024f)) + " KB");

            byte[] fileBytes;
            try {
                fileBytes = getBytesFromFile(file);
            } catch (FileNotFoundException e) {
                LOG.warn("file not found, likely Access Denied under Windows. Skipping...");
                return;
            }

            adler32.reset();
            adler32.update(fileBytes);
            long checksumBefore = adler32.getValue();

            // Compress to temp dir
            String fileNameCompressed = tempDir.getAbsolutePath() + File.separator + file.getName() + ".flz";

            compressed = new File(fileNameCompressed);
            if (compressed.exists()) {
                assertTrue(compressed.delete());
            }

            // Compress
            compressTimestampBefore = System.currentTimeMillis();
            jfastLZPack.packFile(file.getAbsolutePath(), fileNameCompressed, r.nextBoolean() ? JFastLZLevel.One : JFastLZLevel.Two);
            compressTimestampAfter = System.currentTimeMillis();

            compressed = new File(fileNameCompressed);

            assertTrue(compressed.exists());

            // Uncompress
            uncompressTimestampBefore = System.currentTimeMillis();
            jFastLZUnpack.unpackFile(compressed, tempDir.getAbsolutePath());
            uncompressTimestampAfter = System.currentTimeMillis();

            uncompressed = new File(tempDir.getAbsolutePath() + File.separator + file.getName());

            assertTrue(uncompressed.exists());

            fileBytes = getBytesFromFile(uncompressed);

            adler32.reset();
            adler32.update(fileBytes);
            long checksumAfter = adler32.getValue();

            LOG.info("Checking checksums for files: " + file.getAbsolutePath() + ", " + uncompressed.getAbsolutePath());
            assertTrue("checksums failed!", checksumBefore == checksumAfter);

            LOG.info("File PASSED. Packed " + String.format("%.2f", (file.length() / 1024f)) + "KB to " + String.format("%.2f", (compressed.length() / 1024f)) + "KB in "
                    + (compressTimestampAfter - compressTimestampBefore) + " ms. Unpack time: " + (uncompressTimestampAfter - uncompressTimestampBefore) + " ms.");

            fileCount++;
        } finally {
            if (null != compressed && compressed.exists()) {
                compressed.delete();
            }
            if (null != uncompressed && uncompressed.exists()) {
                uncompressed.delete();
            }
        }
    }

    private void deleteDir(File dir) throws IOException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                deleteDir(file);
            } else {
                if (!file.delete()) {
                    throw new RuntimeException("Couldn't delete file: " + file.getAbsolutePath());
                }
            }
        }
        if (!dir.delete()) {
            throw new RuntimeException("Couldn't delete dir: " + dir.getAbsolutePath());
        }
    }

    private byte[] getBytesFromFile(File file) throws IOException {
        FileInputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);

            int numberBytes = is.available();
            data = new byte[numberBytes];
            is.read(data);

            return data;
        } finally {
            try {
                is.close();
            } catch (Exception ignore) {
            }
        }

    }

}
