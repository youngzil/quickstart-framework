/**
 * 项目名称：quickstart-example 
 * 文件名：DiffTest.java
 * 版本信息：
 * 日期：2017年8月12日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.example.javadiffutils;

import java.util.List;

import org.quickstart.example.javadiffutils.diff_match_patch.Diff;

/**
 * DiffTest
 * 
 * @author：youngzil@163.com
 * @2017年8月12日 上午10:02:33
 * @since 1.0
 */
public class DiffTest {

    // https://code.google.com/archive/p/google-diff-match-patch/downloads
    // google-diff-match-patch的主页是：http://code.google.com/p/google-diff-match-patch/
    // http://code.google.com/p/google-diff-match-patch/wiki/LineOrWordDiffs
    // 这里做了详细的介绍，并且介绍了怎样将操作的单位变为单词。

    // 参考文章：http://qiuguo0205.iteye.com/blog/1127601

    // eclipse中也有compare的jar包，有空可以去看看

    public static void main(String[] args) {
        diff_match_patch dmp = new diff_match_patch();
        List<Diff> list = dmp.diff_main("123456789", "012356889");
        System.out.println(list);
    }

}
