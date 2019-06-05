/**
 * 项目名称：quickstart-javase 
 * 文件名：DateTimeTest.java
 * 版本信息：
 * 日期：2018年8月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jodatime;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
/**
 * DateTimeTest 
 *  
 * @author：youngzil@163.com
 * @2018年8月17日 下午10:10:56 
 * @since 1.0
 */

 
public class DateTimeTest {
 
    @Test
    public void testFormat() {
 
        System.out.println(format(new Date()));     //今天
 
        System.out.println(format(new DateTime(new Date()).minusDays(1).toDate())); //昨天
 
        System.out.println(format(new DateTime(new Date()).minusDays(2).toDate())); //2天前
 
        System.out.println(format(new DateTime(new Date()).plusDays(1).toDate())); //1天后
    }
 
    private String format(Date date) {
        DateTime now = new DateTime();
        DateTime today_start = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
        DateTime today_end = today_start.plusDays(1);
        DateTime yesterday_start = today_start.minusDays(1);
 
        if(date.after(today_start.toDate()) && date.before(today_end.toDate())) {
            return String.format("今天 %s", new DateTime(date).toString("HH:mm"));
        } else if(date.after(yesterday_start.toDate()) && date.before(today_start.toDate())) {
            return String.format("昨天 %s", new DateTime(date).toString("HH:mm"));
        }
 
        return new DateTime(date).toString("yyyy-MM-dd HH:mm");
    }
}

