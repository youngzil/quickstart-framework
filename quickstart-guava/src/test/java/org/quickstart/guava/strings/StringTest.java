package org.quickstart.guava.strings;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:01
 */
public class StringTest {

  @Test
  public void teststring() {

    // 去除null
    Joiner joiner = Joiner.on("; ").skipNulls();
    String string = joiner.join("Harry", null, "Ron", "Hermione");
    System.out.println("str=" + string);

    // 连接字符串
    String joinstr = Joiner.on(",").join(Arrays.asList(1, 5, 7)); // returns "1,5,7"
    System.out.println("joinstr=" + joinstr);

    // 拆分字符串

    Iterable<String> splitstr = Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
    splitstr.forEach(System.out::println);


    // 可能版本问题，没有下面这些方法
    // String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); // 移除control字符
    // String theDigits = CharMatcher.DIGIT.retainFrom(string); // 只保留数字字符
    // String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
    // // 去除两端的空格，并把中间的连续空格替换成单个空格
    // String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); // 用*号替换所有数字
    // String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
    // // 只保留数字和小写字母

   String formatStr = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"); // returns "constantName"
    System.out.println("formate=" + formatStr);

  }

}
