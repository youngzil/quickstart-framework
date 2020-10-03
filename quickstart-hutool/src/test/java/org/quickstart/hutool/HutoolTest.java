package org.quickstart.hutool;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/1 17:42
 */
public class HutoolTest {

    @Test
    public void testMail() {
        MailAccount account = new MailAccount();
        account.setHost("smtp.yeah.net");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("hutool@yeah.net");
        account.setUser("hutool");
        account.setPass("q1w2e3");

        MailUtil.send(account, CollUtil.newArrayList("hutool@foxmail.com"), "测试", "邮件来自Hutool测试", false);

    }

    @Test
    public void testUUID() {
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();

        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();

        System.out.println("uuid=" + uuid);
        System.out.println("simpleUUID=" + simpleUUID);

    }

    @Test
    public void testHttpUtil() {
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        String result1 = HttpUtil.get("https://www.baidu.com");
        // 当无法识别页面编码的时候，可以自定义请求页面的编码
        String result2 = HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", "北京");
        String result3 = HttpUtil.get("https://www.baidu.com", paramMap);

        System.out.println("result1=" + result1);
        System.out.println("result2=" + result2);
        System.out.println("result3=" + result3);
    }

    @Test
    public void testCache() {

        /*Hutool 提供了常见的几种缓存策略的实现：
        FIFO(first in first out) ：先进先出策略。
        LFU(least frequently used) ：最少使用率策略。
        LFU(least frequently used) ：最少使用率策略。
        定时缓存 ：对被缓存的对象定义一个过期时间，当对象超过过期时间会被清理。
        ...*/

        //        FIFO(first in first out) 策略缓存使用:

        Cache<String, String> fifoCache = CacheUtil.newFIFOCache(3);

        //加入元素，每个元素可以设置其过期时长，DateUnit.SECOND.getMillis()代表每秒对应的毫秒数，在此为3秒
        fifoCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据FIFO规则，最先放入的对象将被移除
        fifoCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //value1为null
        String value1 = fifoCache.get("key1");

        System.out.println("value1=" + value1);

    }

    @Test
    public void testConsole() {

        /*一般的System.out.println("Hello World");
        不支持参数，对象打印需要拼接字符串
        不能直接打印数组，需要手动调用Arrays.toString*/

        String[] a = {"java", "c++", "c"};
        Console.log(a);//控制台输出：[java, c++, c]

        Console.log("This is Console log for {}.", "test");//控制台输出：This is Console log for test.

    }

    @Test
    public void testSecureUtil() {
        System.out.println(SecureUtil.md5("loginPwd"));
    }

    @Test
    public void testHtmlUtil() {

        /*HtmlUtil.restoreEscaped 还原被转义的HTML特殊字符

        HtmlUtil.encode 转义文本中的HTML字符为安全的字符

        HtmlUtil.cleanHtmlTag 清除所有HTML标签

        HtmlUtil.removeHtmlTag 清除指定HTML标签和被标签包围的内容

        HtmlUtil.unwrapHtmlTag 清除指定HTML标签，不包括内容

        HtmlUtil.removeHtmlAttr 去除HTML标签中的属性

        HtmlUtil.removeAllHtmlAttr 去除指定标签的所有属性

        HtmlUtil.filter 过滤HTML文本，防止XSS攻击

        CronUtil（定时任务）*/
    }

}
