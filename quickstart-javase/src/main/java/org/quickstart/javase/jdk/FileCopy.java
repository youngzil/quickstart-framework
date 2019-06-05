/**
 * 项目名称：quickstart-javase 
 * 文件名：FileCopy.java
 * 版本信息：
 * 日期：2018年3月19日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * FileCopy
 * 
 * @author：youngzil@163.com
 * @2018年3月19日 下午10:38:29
 * @since 1.0
 */
public class FileCopy {
    private final static Path copy_from = Paths.get("C:/rafaelnadal/tournaments/2009/videos/Rafa Best Shots.mp4");
    private final static Path copy_to = Paths.get("C:/Rafa Best Shots.mp4");
    private static long startTime, elapsedTime;
    private static int bufferSizeKB = 4;// also tested for 16, 32, 64, 128, 256 and 1024
    private static int bufferSize = bufferSizeKB * 1024;

    public static void main(String[] args) throws Exception {

        transferfrom();

        transferTo();

        nonDirectBuffer();

        directBuffer();

        mapperedBuffer();

        ioBufferedStream();

        ioUnBufferedStream();

        copyPath2Path();

        copyInputStream2Path();

        copyPath2OutputStream();

        // randomReadFile();

    }

    public static void transferfrom() {

        try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
                FileChannel fileChannel_to = (FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

            startTime = System.nanoTime();
            fileChannel_to.transferFrom(fileChannel_from, 0L, (int) fileChannel_from.size());
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        deleteCopied(copy_to);
    }

    public static void transferTo() throws Exception {

        try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
                FileChannel fileChannel_to = (FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

            startTime = System.nanoTime();

            fileChannel_from.transferTo(0L, fileChannel_from.size(), fileChannel_to);

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        deleteCopied(copy_to);

    }

    public static void nonDirectBuffer() {

        try (FileChannel fileChannel_from = FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ));
                FileChannel fileChannel_to = FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));) {

            startTime = System.nanoTime();
            ByteBuffer bytebuffer = ByteBuffer.allocate(bufferSize);
            int bytesCount;
            while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
                bytebuffer.flip();
                fileChannel_to.write(bytebuffer);
                bytebuffer.clear();
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        deleteCopied(copy_to);
    }

    public static void directBuffer() {
        try (FileChannel fileChannel_to = FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE));
                FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));) {

            startTime = System.nanoTime();
            ByteBuffer bytebuffer = ByteBuffer.allocateDirect(bufferSize);
            int bytesCount;
            while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
                bytebuffer.flip();
                fileChannel_to.write(bytebuffer);
                bytebuffer.clear();
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }

        deleteCopied(copy_to);
    }

    public static void mapperedBuffer() throws Exception {

        try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
                FileChannel fileChannel_to = (FileChannel.open(copy_to, EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

            startTime = System.nanoTime();
            int i = 0;
            long size = fileChannel_from.size() / 30;
            ByteBuffer rr, ww = null;
            while ((i < fileChannel_from.size()) && ((fileChannel_from.size() - i)) > size) {
                rr = fileChannel_from.map(MapMode.READ_ONLY, i, size);
                ww = fileChannel_to.map(MapMode.READ_WRITE, i, size);
                ww.put(rr);
                rr.clear();
                ww.clear();
                i += size;
            }

            rr = fileChannel_from.map(MapMode.READ_ONLY, i, fileChannel_from.size() - i);
            ww = fileChannel_to.map(MapMode.READ_WRITE, i, fileChannel_from.size() - i);
            ww.put(rr);
            rr.clear();
            ww.clear();
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

        } catch (IOException ex) {
            System.err.println(ex);
        }

        deleteCopied(copy_to);

    }

    public static void ioBufferedStream() {

        File inFileStr = copy_from.toFile();
        File outFileStr = copy_to.toFile();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr)); BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
            startTime = System.nanoTime();
            byte[] byteArray = new byte[bufferSize];
            int bytesCount;
            while ((bytesCount = in.read(byteArray)) != -1) {
                out.write(byteArray, 0, bytesCount);
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        deleteCopied(copy_to);
    }

    public static void ioUnBufferedStream() {

        File inFileStr = copy_from.toFile();
        File outFileStr = copy_to.toFile();
        try (FileInputStream in = new FileInputStream(inFileStr); FileOutputStream out = new FileOutputStream(outFileStr)) {
            startTime = System.nanoTime();
            byte[] byteArray = new byte[bufferSize];
            int bytesCount;
            while ((bytesCount = in.read(byteArray)) != -1) {
                out.write(byteArray, 0, bytesCount);
            }

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        deleteCopied(copy_to);
    }

    public static void copyPath2Path() {
        try {
            startTime = System.nanoTime();

            Files.copy(copy_from, copy_to, NOFOLLOW_LINKS);

            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException e) {
            System.err.println(e);
        }
        deleteCopied(copy_to);
    }

    public static void copyInputStream2Path() {
        try (InputStream is = new FileInputStream(copy_from.toFile())) {

            startTime = System.nanoTime();
            Files.copy(is, copy_to);
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException e) {
            System.err.println(e);
        }
        deleteCopied(copy_to);
    }

    public static void copyPath2OutputStream() {
        try (OutputStream os = new FileOutputStream(copy_to.toFile())) {
            startTime = System.nanoTime();
            Files.copy(copy_from, os);
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public static void randomReadFile() {

        try (RandomAccessFile read = new RandomAccessFile("C:\\Users\\asus\\Desktop\\cn_windows_7_ultimate_with_sp1_x86_dvd_618763.iso", "r");
                RandomAccessFile writer = new RandomAccessFile("C:\\Users\\asus\\Desktop\\dwTest\\cn_windows_7_ultimate_with_sp1_x86_dvd_618763.iso", "rw");) {
            startTime = System.nanoTime();
            byte[] b = new byte[200 * 1024 * 1024];
            while (read.read(b) != -1) {
                writer.write(b);
            }
            elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");

        } catch (Exception e) {
            System.err.println(e);
        }
        deleteCopied(copy_to);
    }

    public static void deleteCopied(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
