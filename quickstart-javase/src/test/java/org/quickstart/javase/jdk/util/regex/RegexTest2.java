package org.quickstart.javase.jdk.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-10 22:51
 */
public class RegexTest2 {

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
    Pattern p = Pattern.compile("\\ba\\b");
    // Pattern p = Pattern.compile("a*b");
    // 通过模式对象得到匹配器对象，这个时候需要的是被匹配的字符串
    Matcher m = p.matcher("aaaaab11111");
    // 调用匹配器对象的功能
    boolean b = m.matches();
    System.out.println(b);

    while (m.find()) {
      System.out.println(m.group());
    }

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
    String regex = "o\\b";
    // String regex = "\\b\\w{3}\\b";

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

    boolean ss = s.matches(regex);
    System.out.println("ss=" + ss);

    while (m.find()) {
      System.out.println(m.group());
    }

    // 注意：一定要先find()，然后才能group()
    // IllegalStateException: No match found
    // String ss = m.group();
    // System.out.println(ss);
  }

  @Test
  public void testRegex2() {
    // 按指定模式在字符串查找
    String line = "This order was placed for QT3000! OK?";
    String pattern = "(\\D*)(\\d+)(.*)";

    // 创建 Pattern 对象
    Pattern r = Pattern.compile(pattern);

    // 现在创建 matcher 对象
    Matcher m = r.matcher(line);
    if (m.find()) {
      System.out.println("Found value: " + m.group(0));
      System.out.println("Found value: " + m.group(1));
      System.out.println("Found value: " + m.group(2));
      System.out.println("Found value: " + m.group(3));
    } else {
      System.out.println("NO MATCH");
    }

    System.out.println(line.matches(pattern));

  }

  @Test
  public void testRegex3() {
    final String REGEX = "\\bcat\\b";
    final String INPUT = "cat cat cat cattie cat";

    Pattern p = Pattern.compile(REGEX);
    Matcher m = p.matcher(INPUT); // 获取 matcher 对象
    int count = 0;

    while (m.find()) {
      count++;
      System.out.println("Match number " + count);
      System.out.println("start(): " + m.start());
      System.out.println("end(): " + m.end());
    }

    System.out.println(INPUT.matches(REGEX));

  }

  @Test
  public void testRegex4() {

    // matches 和 lookingAt 方法都用来尝试匹配一个输入序列模式。它们的不同是 matches 要求整个序列都匹配，而lookingAt 不要求。
    // lookingAt 方法虽然不需要整句都匹配，但是需要从第一个字符开始匹配。

    final String REGEX = "foo";
    final String INPUT = "fooooooooooooooooo";
    final String INPUT2 = "ooooofoooooooooooo";
    Pattern pattern;
    Matcher matcher;
    Matcher matcher2;

    pattern = Pattern.compile(REGEX);
    matcher = pattern.matcher(INPUT);
    matcher2 = pattern.matcher(INPUT2);

    System.out.println("Current REGEX is: " + REGEX);
    System.out.println("Current INPUT is: " + INPUT);
    System.out.println("Current INPUT2 is: " + INPUT2);

    System.out.println("lookingAt(): " + matcher.lookingAt());
    System.out.println("matches(): " + matcher.matches());

    System.out.println("matches(): " + matcher2.matches());
    System.out.println("lookingAt(): " + matcher2.lookingAt());

  }

  @Test
  public void testRegex5() {

    // replaceFirst 和 replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst 替换首次匹配，replaceAll 替换所有匹配。
    String REGEX = "dog";
    String INPUT = "The dog says meow. " + "All dogs say meow.";
    String REPLACE = "cat";

    Pattern p = Pattern.compile(REGEX);
    // get a matcher object
    Matcher m = p.matcher(INPUT);
    INPUT = m.replaceAll(REPLACE);
    System.out.println(INPUT);

  }

}
