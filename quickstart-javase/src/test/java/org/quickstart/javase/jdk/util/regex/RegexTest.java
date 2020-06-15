package org.quickstart.javase.jdk.util.regex;

import org.junit.Test;

/**
 * @description TODO
 *
 * @author yangzl
 * @createTime 2020/6/12 11:22
 */
public class RegexTest {

    @Test
    public void testRegex() {

        //CPU 利用率将近 100%

        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";

        String bugUrl =
            "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";

        if (bugUrl.matches(badRegex)) {

            System.out.println("match!!");

        } else {

            System.out.println("no match!!");

        }

    }

    @Test
    public void testRegex2() {

        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~_%\\\\/])+$";

        String bugUrl =
            "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";

        if (bugUrl.matches(badRegex)) {

            System.out.println("match!!");

        } else {

            System.out.println("no match!!");

        }

    }
}
