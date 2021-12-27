package org.quickstart.javase.jdk8.time;

import org.apache.tools.ant.taskdefs.Java;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class DateTest {

    // [Java 8 日期、时间与格式化](https://blog.csdn.net/cuixianlong/article/details/90177840)
    // [JDK中日期和时间的几个常用类浅析(四)](https://blog.51cto.com/u_13609606/3148451)
    // [Java 8 新特性（三）新的日期时间类](https://www.jianshu.com/p/3208f0b31dd5)

    @Test
    public void testDate() {

        Instant date = Instant.now();//代替date
        System.out.println("instant:" + date);
        LocalDate date2 = LocalDate.now();
        System.out.println("LocalDate:" + date2);
        LocalDateTime date3 = LocalDateTime.now();//代替calendar
        System.out.println("LocalDateTime:" + date3);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//代替simpleDateFormat
        System.out.println("DateTimeFormatter:" + dtf.format(date3));

        System.out.println("--------------------------");

        String ddd = Instant.now().truncatedTo(ChronoUnit.MICROS).toString();
        System.out.println("毫秒值=" + ddd);

        long microsenconds = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        System.out.println("毫秒值2=" + microsenconds);

        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void testInstant() {
        // java.time.Instant类对应的是时间线上的一个时间点。该类通过保存着从格林威治的起始时间(1970年一月一日零点零分)开始计算所经过的纳妙数来表示时间点。
        // 注意：该类是JDK8中新引入的一系列日期时间相关API中的最基础类。如果需要创建该类的一个实例，需要使用该类的工厂方法，示例如下:
        // 一个Instant实例中包含有两个域，分别代表着秒数和纳秒数。
        Instant now = Instant.now();

    }

    @Test
    public void testLocalDate() {

        // java.time.LocalDate类同样是在JDK8中新引入的日期时间类。该类用来表示不含时区信息的日期，比如用来表示生日，节日等具体某天，但并不表示该天的具体时间。
        // java.time.LocalTime类同样是在JDK8中新引入的日期时间类。该类用来表示不含时区信息的时间，比如用来表示上午10点，下午5点等。

        // 第一种：直接生成当前时间
        LocalDate date = LocalDate.now();
        System.out.println(date);

        // 第二种：使用 LocalDate.of 构建时间
        LocalDate date2 = LocalDate.now();
        date2 = LocalDate.of(2020, 9, 20);
        System.out.println(date2);

        // 第三种：使用 LocalDate.parse 构建时间
        LocalDate date3 = LocalDate.now();
        date3 = LocalDate.parse("2020-08-20");
        System.out.println(date3);

    }

    @Test
    public void testLocalTime() {

        // 第一种：直接获取当前时间包含毫秒数

        // 获取当前时间,包含毫秒数
        LocalTime now = LocalTime.now();
        System.out.println(now);

        // 第二种：构建时间
        LocalTime localTime = LocalTime.of(13, 30, 59);
        System.out.println(localTime);

        // 第三种：获取当前时间不包含毫秒数

        LocalTime now3 = LocalTime.now();
        LocalTime localTime3 = now3.withNano(0);
        System.out.println(localTime3);

        // 第四种：将字符串转成时间
        LocalTime localTime4 = LocalTime.parse("11:05:20");
        System.out.println(localTime4);

        // 第五种：获取时、分、秒、纳秒
        LocalTime time = LocalTime.now();
        System.out.println("当前时间" + time);
        // 获取 时，分，秒，纳秒
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        int nano = time.getNano();
        System.out.println(hour + "时" + minute + "分" + second + "秒" + nano + "纳秒");

    }

    @Test
    public void testLocalDateTime() {

        // 第一种：直接获取当前时间包含毫秒数
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        // 第二种：将字符串转成时间
        String date = "2020-08-20 11:08:10";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time2 = LocalDateTime.parse(date, dateTimeFormatter);
        System.out.println(time2);

        // 第三种：将时间转成时间戳
        String date3 = "2020-08-20 11:08:10";
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time3 = LocalDateTime.parse(date3, dateTimeFormatter3);
        long l = time3.toEpochSecond(ZoneOffset.of("+9"));
        System.out.println(l);

        // 第四种：将时间进行格式化为字符串
        DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time4 = dateTimeFormatter4.format(LocalDateTime.now());
        System.out.println(time4);

        // 第五种：获取、年、月、日、时、分、秒、纳秒

        /** 时间 **/
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("LocalDateTime：" + dateTime);
        // LocalDateTime实际上就是 日期类+时间类的组合，所以也可以LocalDate和LocalTime的一些方法
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        int second = dateTime.getSecond();
        int nano = dateTime.getNano();
        System.out.println(year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒" + nano + "纳秒");

    }

}
