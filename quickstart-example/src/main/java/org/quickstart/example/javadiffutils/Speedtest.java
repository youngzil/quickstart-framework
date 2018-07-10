// Copyright 2010 Google Inc. All Rights Reserved.

/**
 * Diff Speed Test
 *
 * Compile from google3/third_party/diff_match_patch/java with:
 * javac name/fraser/neil/plaintext/Speedtest.java name/fraser/neil/plaintext/diff_match_patch.java
 * Execute with:
 * java name/fraser/neil/plaintext/Speedtest
 *
 * @author fraser@google.com (Neil Fraser)
 */

package org.quickstart.example.javadiffutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.quickstart.example.javadiffutils.diff_match_patch.Diff;

public class Speedtest {

    public static void main(String args[]) {
        // String text1 = readFile("org/quickstart/example/javadiffutils/Speedtest1.txt");
        // String text2 = readFile("org/quickstart/example/javadiffutils/Speedtest2.txt");

        // String filename = Speedtest.class.getClass().getResource("").getPath();
        String filename = Speedtest.class.getClass().getResource("/").getPath();

        String text1 = readFile(filename + "/org/quickstart/example/javadiffutils/Speedtest1.txt");
        String text2 = readFile(filename + "/org/quickstart/example/javadiffutils/Speedtest2.txt");

        diff_match_patch dmp = new diff_match_patch();
        dmp.Diff_Timeout = 0;

        // Execute one reverse diff as a warmup.
        dmp.diff_main(text2, text1, false);
        System.gc();

        long start_time = System.currentTimeMillis();

        List<Diff> list = dmp.diff_main(text1, text2, false);
        System.out.println(list);
        long end_time = System.currentTimeMillis();
        System.out.printf("Elapsed time: %f\n", ((end_time - start_time) / 1000.0));
    }

    private static String readFile(String filename) {
        // Read a file from disk and return the text contents.
        StringBuffer strbuf = new StringBuffer();
        try {

            FileReader input = new FileReader(filename);
            BufferedReader bufRead = new BufferedReader(input);
            String line = bufRead.readLine();
            while (line != null) {
                strbuf.append(line);
                strbuf.append('\n');
                line = bufRead.readLine();
            }

            bufRead.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strbuf.toString();
    }
}
