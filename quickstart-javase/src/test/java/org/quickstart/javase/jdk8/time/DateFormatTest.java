package org.quickstart.javase.jdk8.time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/1/5 18:06
 */
public class DateFormatTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = formatter.parse("2019-12-31 23:59:59");
        System.out.println(date1);

        SimpleDateFormat formatter2 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        Date date2 = formatter2.parse("2019-12-31 23:59:59");
        System.out.println(date2);

        SimpleDateFormat formatter3 = new SimpleDateFormat("YYYY-MM-DD");
        Date date3 = formatter3.parse("2020-01-01");
        System.out.println(date3);

        try {
            String[] dates = {"2018-12-01", "2018-12-31", "2019-01-01"};
            for (String date : dates) {
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date d = dt.parse(date);

                SimpleDateFormat dtYYYY = new SimpleDateFormat("YYYY");
                SimpleDateFormat dtyyyy = new SimpleDateFormat("yyyy");

                System.out.println("For date " + date + " the YYYY year is " + dtYYYY.format(d) + " while for yyyy it's " + dtyyyy.format(d));
            }
        } catch (Exception e) {
            System.out.println("Failed with exception: " + e);
        }

    }

    // Java8之前的版本，格式化日期常使用SimpleDateFormat，但这个类并不是线程安全的，通常要用作局部变量或者使用ThreadLocal包装。或者使用额外的joda time 依赖来对日期进行操作。
    // 但是到了Java8，这类烦恼都没有了，全新的日期api可以快速实现日期格式化。

    @Test
    public void testDateFormat() {
        LocalDateTime now = LocalDateTime.now();

        String s1 = now.format(DateTimeFormatter.ISO_DATE);
        String s2 = now.format(DateTimeFormatter.ISO_DATE_TIME);
        String s3 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        String s4 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String s5 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        String s6 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String s7 = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SSS"));

        System.out.println("ISO_DATE:  " + s1);
        System.out.println("ISO_DATE_TIME:  " + s2);
        System.out.println("FULL:  " + s3);
        System.out.println("LONG:  " + s4);
        System.out.println("MEDIUM:  " + s5);
        System.out.println("SHORT:  " + s6);
        System.out.println("yyyy/MM/dd HH:mm:ss:SSS:  " + s7);

        LocalDateTime time = LocalDateTime.parse(s2);
        System.out.println(time);

        // 3种格式化
        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        String strDate = dateTimeFormatter.format(localDateTime);
        System.out.println(strDate);

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ISO_DATE;
        String strDate2 = dateTimeFormatter2.format(localDateTime);
        System.out.println(strDate2);

        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("yyyyddMM");
        String strDate3 = dateTimeFormatter3.format(localDateTime);
        System.out.println(strDate3);

        // string -> Date
        String str1 = "2014/04/12 01:06:09";
        String str2 = "20190301";
        // 根据需要解析的日期、时间字符串定义解析所用的格式器
        DateTimeFormatter fomatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        DateTimeFormatter fomatter2 = DateTimeFormatter.ofPattern("yyyyddMM");
        LocalDateTime dt1 = LocalDateTime.parse(str1, fomatter1);
        LocalDate dt2 = LocalDate.parse(str2, fomatter2);
        System.out.println(dt1);
        System.out.println(dt2);

    }

    @Test
    public void testDateFormat2() {

        // 注意，使用LocalDate + DateTimeFormatter仅仅能够转换不带时分秒的日期格式！

        String output1 = formattedDate("2019年07月15日", "yyyy年MM月dd日", "yyyy-MM-dd");
        String output2 = formattedDate("2019/07/16", "yyyy/MM/dd", "yyyy-MM-dd");
        String output3 = formattedDate("2019-07-16", "yyyy-MM-dd", "yyyy/MM/dd");

        System.out.println(output1);
        System.out.println(output2);
        System.out.println(output3);

    }

    @Test
    public void testDateFormat3() {

        // 注意：使用LocalDateTime +  DateTimeFormatter 可以将指定日期类型转换成任意形式的日期类型。

        String output1 = formattedDateTime("20190713000000", "yyyyMMddHHmmss", "yyyy-MM-dd");
        String output2 = formattedDateTime("20190713000000", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");

        String output3 = formattedDateTime("2019年07月14日00时00分00秒", "yyyy年MM月dd日HH时mm分ss秒", "yyyy/MM/dd");
        String output4 = formattedDateTime("2019年07月14日00时00分00秒", "yyyy年MM月dd日HH时mm分ss秒", "yyyyMMddHHmmss");

        String output5 = formattedDateTime("2019-07-15 00:00:00", "yyyy-MM-dd HH:mm:ss", "yyyyMMdd");
        String output6 = formattedDateTime("2019-07-15 00:00:00", "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss");

        System.out.println(output1);
        System.out.println(output2);
        System.out.println(output3);
        System.out.println(output4);
        System.out.println(output5);
        System.out.println(output6);

    }

    /**
     * 不带时分秒的日期字符串转化
     *
     * @param input 输入的日期
     * @param inputFormat 输入日期的格式
     * @param outputFormat 输出日期的格式
     * @return 输出的日期，不带时分秒
     */
    public static String formattedDate(String input, String inputFormat, String outputFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        LocalDate localDate = LocalDate.parse(input, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        return localDate.format(outputFormatter);
    }

    /**
     * 带时分秒的日期字符串转换
     *
     * @param input 输入的日期
     * @param inputFormat 输入日期的格式
     * @param outputFormat 输出日期的格式
     * @return 输出指定格式的日期，可以带时分秒，也可以不带
     */
    public static String formattedDateTime(String input, String inputFormat, String outputFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(input, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        return localDateTime.format(outputFormatter);
    }

    // Date和LocalDateTime互转

    /**
     * LocalDateTime -> Date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Date -> LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

}
