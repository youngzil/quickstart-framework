package org.quickstart.javase.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:25
 * @version v1.0
 */
public class ZipUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * GZip压缩
     *
     * @param primalString
     * @return
     */
    public static String gZip(String primalString) {

        if (StringUtils.isBlank(primalString)) return null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            try (GZIPOutputStream gZipStream = new GZIPOutputStream(out)) {
                gZipStream.write(primalString.getBytes());
            }

            return new sun.misc.BASE64Encoder().encode(out.toByteArray());
        } catch (Exception e) {
            LOGGER.error("GZip Failed: " + primalString);
            return null;
        }
    }

    /**
     * GZip解压缩
     *
     * @param compressedString
     * @return
     */
    public static String unGZip(String compressedString) {

        if (StringUtils.isBlank(compressedString)) return null;

        byte[] compressed;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedString);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                GZIPInputStream unGZipStream = new GZIPInputStream(new ByteArrayInputStream(compressed))) {

                byte[] buffer = new byte[1024];
                int offset;
                while ((offset = unGZipStream.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }

                return out.toString();
            }
        } catch (IOException e) {
            LOGGER.error("UnGZip Failed: " + compressedString);
            return null;
        }
    }

    /**
     * zip压缩
     *
     * @param primalString
     * @return
     */
    public static String zip(String primalString) {

        if (StringUtils.isBlank(primalString)) return null;

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
            ZipOutputStream zipStream = new ZipOutputStream(out)) {

            zipStream.putNextEntry(new ZipEntry("0"));
            zipStream.write(primalString.getBytes());
            zipStream.closeEntry();

            return new sun.misc.BASE64Encoder().encodeBuffer(out.toByteArray());
        } catch (IOException e) {
            LOGGER.error("Zip Failed: " + primalString);
            return null;
        }
    }

    /**
     * zip解压缩
     *
     * @param compressedString
     * @return
     */
    public static String unZip(String compressedString) {

        if (StringUtils.isBlank(compressedString)) return null;

        byte[] compressed;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedString);
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                ZipInputStream unZipStream = new ZipInputStream(new ByteArrayInputStream(compressed))) {

                unZipStream.getNextEntry();
                byte[] buffer = new byte[1024];
                int offset;
                while ((offset = unZipStream.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }

                return out.toString();
            }
        } catch (IOException e) {
            LOGGER.error("UnZip Failed: " + compressedString);
            return null;
        }
    }
}
