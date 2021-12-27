package org.quickstart.javase.jdk8.file;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReadWriteTest {

    @Test
    public void testReadWrite2() throws IOException {

        // 多次写会被覆盖

        Files.write(Paths.get("/Users/lengfeng/Desktop/topics2.txt"), "topics".getBytes());
        Files.write(Paths.get("/Users/lengfeng/Desktop/topics2.txt"), "client".getBytes());

    }

    @Test
    public void testReadWrite() throws IOException {
        List<String> topics = Files.readAllLines(Paths.get("/Users/lengfeng/Desktop/topics.txt"));
        topics.forEach(System.out::println);

        Files.write(Paths.get("/Users/lengfeng/Desktop/topics2.txt"), topics);

        String topic = String.join(",", topics);
        Files.write(Paths.get("/Users/lengfeng/Desktop/topics3.txt"), topic.getBytes());

        String topic2 = new String(Files.readAllBytes(Paths.get("/Users/lengfeng/Desktop/test.log")));
        String[] topics2 = topic2.split(",");
        System.out.println(topics2);

        String topic3 = new String(Files.readAllBytes(Paths.get("/Users/lengfeng/topics.log")));
        String[] topics3 = topic3.split(",");
        List list = Arrays.stream(topics3).map(t -> t.trim()).collect(Collectors.toList());
        Files.write(Paths.get("/Users/lengfeng/topics.txt"),list);

    }

    @Test
    public void testBufferedReaderWriter() {
        Path fpath = Paths.get("create.txt");
        //创建文件
        if (!Files.exists(fpath)) {
            try {
                Files.createFile(fpath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //创建BufferedWriter
        try {
            BufferedWriter bfw = Files.newBufferedWriter(fpath);
            bfw.write("Files类的API:newBufferedWriter");
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建BufferedReader
        try {
            BufferedReader bfr = Files.newBufferedReader(fpath);
            System.out.println(bfr.readLine());
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
