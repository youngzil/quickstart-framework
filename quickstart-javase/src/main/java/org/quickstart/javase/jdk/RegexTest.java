package org.quickstart.javase.jdk;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-10 22:51
 */
public class RegexTest {

  // https://blog.csdn.net/hc1151310108/article/details/80274452

  /*
   * 获取功能 Pattern和Matcher类的使用
   *
   * 模式和匹配器的基本使用顺序
   */
  @Test
  public void testPatternAndMatcher() {

    // 模式和匹配器的典型调用顺序
    // 把正则表达式编译成模式对象
    Pattern p = Pattern.compile("a*b");
    // 通过模式对象得到匹配器对象，这个时候需要的是被匹配的字符串
    Matcher m = p.matcher("aaaaab");
    // 调用匹配器对象的功能
    boolean b = m.matches();
    System.out.println(b);

    // 这个是判断功能，但是如果做判断，这样做就有点麻烦了，我们直接用字符串的方法做
    String s = "aaaaab";
    String regex = "a*b";
    boolean bb = s.matches(regex);
    System.out.println(bb);

  }

  /*
   * 获取功能： 获取下面这个字符串中由三个字符组成的单词 da jia ting wo shuo,jin tian yao xia yu,bu shang wan zi xi,gao xing bu?
   */
  @Test
  public void testRegex() {

    // 定义字符串
    String s = "da jia ting wo shuo,jin tian yao xia yu,bu shang wan zi xi,gao xing bu?";
    // 规则
    String regex = "\\b\\w{3}\\b";

    // 把规则编译成模式对象
    Pattern p = Pattern.compile(regex);
    // 通过模式对象得到匹配器对象
    Matcher m = p.matcher(s);
    // 调用匹配器对象的功能
    // 通过find方法就是查找有没有满足条件的子串
    // public boolean find()
    // boolean flag = m.find();
    // System.out.println(flag);
    // // 如何得到值呢?
    // // public String group()
    // String ss = m.group();
    // System.out.println(ss);
    //
    // // 再来一次
    // flag = m.find();
    // System.out.println(flag);
    // ss = m.group();
    // System.out.println(ss);

    while (m.find()) {
      System.out.println(m.group());
    }

    // 注意：一定要先find()，然后才能group()
    // IllegalStateException: No match found
    // String ss = m.group();
    // System.out.println(ss);
  }

}
