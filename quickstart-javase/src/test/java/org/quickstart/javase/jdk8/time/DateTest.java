package org.quickstart.javase.jdk8.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
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

    @Test
    public void testZonedDateTime() {

        // ZonedDateTime格式化的pattern，一定要是带有时区的标识

        String time = "2021-02-24T14:28:33.000+0000";
        String timeFormatter = "yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS";
        String myFormatter = "yyyy-MM-dd HH:mm:ss";
        String test = getFormatterTime(time, timeFormatter, myFormatter);
        System.out.println(test);

        // 创建一个 ZonedDateTime
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.of(2018, 01, 01, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));

        // ZonedDateTime格式化的pattern，一定要是带有时区的标识

        // 如果我们想显示时区偏移量，我们可以使用格式化程序“Z”或“X”：
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        String formattedString = zonedDateTime.format(formatter);
        System.out.println(formattedString);

        // 要包含时区名称，我们可以使用小写的“z”：
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        String formattedString2 = zonedDateTime.format(formatter2);
        System.out.println(formattedString2);

        // 这个过程也可以反过来进行。我们可以获取一个字符串并将其转换回ZonedDateTime。

        // 一种选择是使用 ZonedDateTime类的静态parse()方法：
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("2011-12-03T10:15:30+01:00");

        // 从String获取ZonedDateTime的第二个选项涉及 2 个步骤：将 String 转换为LocalDateTime，然后将此对象转换为ZonedDateTime：
        // 这种间接方法只是将日期时间与区域 ID 结合起来：
        ZoneId timeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime3 = LocalDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_DATE_TIME).atZone(timeZone);

        log.info(zonedDateTime3.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

    }

    @Test
    public void testMaxAndMinOfDay() {

        Date date = new Date();

        System.out.println("今天开始时间：" + getStartOfDay(date));
        System.out.println("今天结束时间：" + getEndOfDay(date));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(getStartOfDay(date)));
        System.out.println(sdf.format(getEndOfDay(date)));

        // 第一种：直接获取当前时间包含毫秒数
        LocalDateTime time = LocalDateTime.now();
        System.out.println(time);

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(zonedDateTime.toLocalDate().format(dateTimeFormatter2));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        // System.out.println(dateTimeFormatter.format(time));
        System.out.println(dateTimeFormatter.format(zonedDateTime));

        // 前一天和前两天的时间
        System.out.println(dateTimeFormatter.format(zonedDateTime.minusDays(1)));
        System.out.println(dateTimeFormatter.format(zonedDateTime.minusDays(2)));

        // 前一天和前两天的最大时间，也就是23:59:59
        System.out.println(dateTimeFormatter.format(zonedDateTime.with(LocalTime.MAX).minusDays(1)));
        System.out.println(dateTimeFormatter.format(zonedDateTime.with(LocalTime.MAX).minusDays(2)));

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
        // LocalDateTime.now().format(FORMATTER)
        String ddd = ZonedDateTime.now().format(FORMATTER);
        System.out.println(ddd);

    }

    @Test
    public void testPlusAndMinusDays() {

        //2018-12-04
        LocalDate localDate = LocalDate.now();

        //16:14:56.892
        LocalTime localTime = LocalTime.now();

        //2018-12-04T16:14:56.892
        LocalDateTime localDateTime = LocalDateTime.now();

        //16:14:5（去除纳秒）
        LocalTime time = LocalTime.now().withNano(0);

        //2018-12-04 16:17:55
        String now1 = LocalDateTime.now().withNano(0).toString().replace("T", " ");
        String now2 = LocalDate.now() + " " + LocalTime.now().withNano(0);

        // 日期加减
        //三天后
        LocalDate afterThreeDays = LocalDate.now().plusDays(3);

        //三天前
        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);

        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.parse("2020-01-30");
        long day1 = now.toEpochDay();
        long day2 = date.toEpochDay();
        //相差多少天
        long day = day2 - day1;

    }

    /**
     * ZonedDateTime转换 带时区日期转换
     *
     * @param time 时区日期 如：2021-02-24T14:28:33.000+0000
     * @param timeFormatter 时区日期格式 如 yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS
     * @param myFormatter 我的日期格式 默认：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String getFormatterTime(String time, String timeFormatter, String myFormatter) {
        //转换指定时区
        DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern(timeFormatter).withZone(ZoneId.of("Asia/Shanghai"));
        //自己所需格式
        myFormatter = Optional.ofNullable(myFormatter).orElse("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(myFormatter);
        ZonedDateTime zoneTime = ZonedDateTime.parse(time, formatter0);
        return zoneTime.withFixedOffsetZone().format(formatter);
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    // Java 8 获取某天最大(23:59:59)最小(00:00:00)时间
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        ;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    // Java 8 获取某天最大(23:59:59)最小(00:00:00)时间
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

}
