package org.quickstart.javase.jdk.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileToByteTest {

    // [文件和byte数组之间相互转换](https://www.jianshu.com/p/b8b8f1ded401)

    @Test
    public void testFileToByte() throws IOException {
        File file = new File("/temp/abc.txt");
        //init array with file length
        byte[] bytesArray = new byte[(int)file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();

        // return bytesArray;
    }

    @Test
    public void testFileToByte2() throws IOException {
        try {

            // convert file to byte[]
            byte[] bFile = readBytesFromFile("test.txt");

            //java nio
            //byte[] bFile = Files.readAllBytes(new File("test.txt").toPath());
            //byte[] bFile = Files.readAllBytes(Paths.get("test.txt"));

            // save byte[] into a file
            Path path = Paths.get("test2.txt");
            Files.write(path, bFile);

            System.out.println("Done");

            //Print bytes[]
            for (int i = 0; i < bFile.length; i++) {
                System.out.print((char)bFile[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileToByteByNIO() throws IOException {

        String filePath = "/temp/abc.txt";

        byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
        //or this
        byte[] bFile2 = Files.readAllBytes(Paths.get(filePath));

    }

    @Test
    public void testByteToFile1() throws IOException {
        FileOutputStream fos = new FileOutputStream("fileDest");

        byte[] bytesArray = new byte[2048];
        fos.write(bytesArray);
        fos.close();
    }

    @Test
    public void testByteToFileByNIO() throws IOException {
        Path path = Paths.get("fileDest");
        byte[] bytesArray = new byte[2048];
        Files.write(path, bytesArray);
    }

    private static final String UPLOAD_FOLDER = "/";

    @Test
    public void testByteToFile() {

        FileInputStream fileInputStream = null;

        try {

            File file = new File("test.txt");
            byte[] bFile = new byte[(int)file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);

            //save bytes[] into a file
            writeBytesToFile(bFile, UPLOAD_FOLDER + "test1.txt");
            writeBytesToFileClassic(bFile, UPLOAD_FOLDER + "test2.txt");
            writeBytesToFileNio(bFile, UPLOAD_FOLDER + "test3.txt");

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static byte[] readBytesFromFile(String filePath) {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {

            File file = new File(filePath);
            bytesArray = new byte[(int)file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return bytesArray;

    }

    //Classic, < JDK7
    private static void writeBytesToFileClassic(byte[] bFile, String fileDest) {

        FileOutputStream fileOuputStream = null;

        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //Since JDK 7 - try resources
    private static void writeBytesToFile(byte[] bFile, String fileDest) {

        try (FileOutputStream fileOuputStream = new FileOutputStream(fileDest)) {
            fileOuputStream.write(bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Since JDK 7, NIO
    private static void writeBytesToFileNio(byte[] bFile, String fileDest) {

        try {
            Path path = Paths.get(fileDest);
            Files.write(path, bFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
