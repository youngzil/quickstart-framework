/**
 * 项目名称：quickstart-javase 文件名：DateTimeTest.java 版本信息： 日期：2018年8月17日 Copyright yangzl Corporation 2018 版权所有 *
 */
package org.quickstart.javase.jodatime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
  public void testSecondStamp() {
    // 获取完整的秒时间戳
    long secondLong = Instant.now().getEpochSecond();// 10

    // 毫秒级时间戳
      //得到毫秒表示的系统当前时间
    long millisLong = System.currentTimeMillis();

    System.out.println("secondLong=" + secondLong);
    System.out.println("millisLong=" + millisLong);

    // LocalTime.of(0,0)换成LocalTime.now()可获得完整的时间戳（13位毫秒)
    long timestamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0))).getTime();

    int len = 10;
    long tenBitLong = len == 10 ? timestamp / 1000 : timestamp;

    // 这个时间没看懂到底是什么时间
    System.out.println("timestamp=" + timestamp);
    System.out.println("tenBitLong=" + tenBitLong);

    long seconds = secondLong;
    int day = (int) TimeUnit.SECONDS.toDays(seconds);
    long hours = TimeUnit.SECONDS.toHours(seconds) - TimeUnit.SECONDS.toHours(TimeUnit.SECONDS.toDays(seconds));
    long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toMinutes(TimeUnit.SECONDS.toHours(seconds));
    long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toSeconds(TimeUnit.SECONDS.toMinutes(seconds));

    System.out.println("Day " + day + " Hour " + hours + " Minute " + minute + " Seconds " + second);

      // int day = (int)TimeUnit.SECONDS.toDays(seconds);
      // long hours = TimeUnit.SECONDS.toHours(seconds) - (day *24);
      // long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds)* 60);
      // long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) *60);

  }

  @Test
  public void testFormat() {

    System.out.println(format(new Date())); // 今天

    System.out.println(format(new DateTime(new Date()).minusDays(1).toDate())); // 昨天

    System.out.println(format(new DateTime(new Date()).minusDays(2).toDate())); // 2天前

    System.out.println(format(new DateTime(new Date()).plusDays(1).toDate())); // 1天后
  }

  private String format(Date date) {
    DateTime now = new DateTime();
    DateTime today_start = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
    DateTime today_end = today_start.plusDays(1);
    DateTime yesterday_start = today_start.minusDays(1);

    if (date.after(today_start.toDate()) && date.before(today_end.toDate())) {
      return String.format("今天 %s", new DateTime(date).toString("HH:mm"));
    } else if (date.after(yesterday_start.toDate()) && date.before(today_start.toDate())) {
      return String.format("昨天 %s", new DateTime(date).toString("HH:mm"));
    }

    return new DateTime(date).toString("yyyy-MM-dd HH:mm");
  }
}
