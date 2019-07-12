package org.quickstart.javase.jdk.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class Date {

  public Date() {
    // TODO Auto-generated constructor stub
  }

  public static void main(String[] args) throws ParseException {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    long now = System.currentTimeMillis();

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(now);

    System.out.println(now + " = " + formatter.format(calendar.getTime()));

    // 日期转换为毫秒 两个日期想减得到天数

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String start = "2011-09-20 12:30:45";
    String end = "2011-10-20 6:30:00";
    // 得到毫秒数
    long timeStart = sdf.parse(start).getTime();
    long timeEnd = sdf.parse(end).getTime();
    // 两个日期想减得到天数
    long dayCount = (timeEnd - timeStart) / (24 * 3600 * 1000);
    System.out.println(dayCount);
  }

    @Test
  public void testDateBeforeAndAfter() throws InterruptedException {


    java.util.Date date1 = new java.util.Date();
    TimeUnit.SECONDS.sleep(3);

    java.util.Date date2 = new java.util.Date();
    TimeUnit.SECONDS.sleep(3);

    java.util.Date date3 = new java.util.Date();

    System.out.println(date2.after(date1));
    System.out.println(date2.before(date3));

  }

}
