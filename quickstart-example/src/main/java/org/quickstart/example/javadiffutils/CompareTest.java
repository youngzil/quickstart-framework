/**
 * 项目名称：quickstart-example 
 * 文件名：CompareTest.java
 * 版本信息：
 * 日期：2017年8月12日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.javadiffutils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import difflib.Delta;
import difflib.DiffRow;
import difflib.DiffRow.Tag;
import difflib.DiffRowGenerator;
import difflib.DiffUtils;
import difflib.Patch;

/**
 * CompareTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月12日 上午10:56:36
 * @since 1.0
 */
public class CompareTest {

    /**
     * 必须使用1.2.1版本 <dependency> <groupId>com.googlecode.java-diff-utils</groupId> <artifactId>diffutils</artifactId><version>1.2.1</version> </dependency>
     */

    @Test
    public void testCompare() throws IOException {
        List<String> original = FileUtils.readLines(new File("src/main/java/org/quickstart/example/javadiffutils/Speedtest1.txt"));
        List<String> revised = FileUtils.readLines(new File("src/main/java/org/quickstart/example/javadiffutils/Speedtest2.txt"));

        Patch patch = DiffUtils.diff(original, revised);

        for (Delta delta : patch.getDeltas()) {
            List<?> list = delta.getRevised().getLines();
            for (Object object : list) {
                System.out.println(object);
            }
        }

        DiffRowGenerator.Builder builder = new DiffRowGenerator.Builder();
        builder.showInlineDiffs(false);
        DiffRowGenerator generator = builder.build();
        for (Delta delta : patch.getDeltas()) {
            List<DiffRow> generateDiffRows = generator.generateDiffRows((List<String>) delta.getOriginal().getLines(), (List<String>) delta.getRevised().getLines());
            int leftPos = delta.getOriginal().getPosition();
            int rightPos = delta.getRevised().getPosition();
            for (DiffRow row : generateDiffRows) {
                Tag tag = row.getTag();
                if (tag == Tag.INSERT) {
                    System.out.println("Insert: ");
                    System.out.println("new-> " + row.getNewLine());
                    System.out.println("");
                } else if (tag == Tag.CHANGE) {
                    System.out.println("change: ");
                    System.out.println("old-> " + row.getOldLine());
                    System.out.println("new-> " + row.getNewLine());
                    System.out.println("");
                } else if (tag == Tag.DELETE) {
                    System.out.println("delete: ");
                    System.out.println("old-> " + row.getOldLine());
                    System.out.println("");
                } else if (tag == Tag.EQUAL) {
                    System.out.println("equal: ");
                    System.out.println("old-> " + row.getOldLine());
                    System.out.println("new-> " + row.getNewLine());
                    System.out.println("");
                } else {
                    throw new IllegalStateException("Unknown pattern tag: " + tag);
                }
            }
        }
    }
}
