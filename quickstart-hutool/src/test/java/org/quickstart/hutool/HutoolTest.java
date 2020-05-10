package org.quickstart.hutool;

import cn.hutool.cron.CronUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HtmlUtil;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/1 17:42
 */
public class HutoolTest {

    @Test
    public void testSecureUtil(){
        System.out.println(SecureUtil.md5("loginPwd"));
    }

    @Test
    public void testHtmlUtil(){

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
