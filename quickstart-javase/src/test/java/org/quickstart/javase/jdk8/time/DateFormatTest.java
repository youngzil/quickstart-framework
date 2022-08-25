package org.quickstart.javase.jdk8.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/1/5 18:06
 */
public class DateFormatTest {

    @Test
    public void testDateFormat1() {

        // 格式化样式主要通过 DateFormat 常量设置。将不同的常量传入到表 1 所示的方法中，以控制结果的长度。DateFormat 类的常量如下。
        // SHORT：完全为数字，如 12.5.10 或 5:30pm。
        // MEDIUM：较长，如 May 10，2016。
        // LONG：更长，如 May 12，2016 或 11:15:32am。
        // FULL：是完全指定，如 Tuesday、May 10、2012 AD 或 11:l5:42am CST。

        // 获取不同格式化风格和中国环境的日期
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA);
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
        DateFormat df4 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);

        // 获取不同格式化风格和中国环境的时间
        DateFormat df5 = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.CHINA);
        DateFormat df6 = DateFormat.getTimeInstance(DateFormat.FULL, Locale.CHINA);
        DateFormat df7 = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA);
        DateFormat df8 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);

        // 将不同格式化风格的日期格式化为日期字符串
        String date1 = df1.format(new Date());
        String date2 = df2.format(new Date());
        String date3 = df3.format(new Date());
        String date4 = df4.format(new Date());

        // 将不同格式化风格的时间格式化为时间字符串
        String time1 = df5.format(new Date());
        String time2 = df6.format(new Date());
        String time3 = df7.format(new Date());
        String time4 = df8.format(new Date());

        // 输出日期
        System.out.println("SHORT：" + date1 + " = " + time1);
        System.out.println("FULL：" + date2 + " = " + time2);
        System.out.println("MEDIUM：" + date3 + " = " + time3);
        System.out.println("LONG：" + date4 + " = " + time4);

    }

    @Test
    public void testSimpleDateFormat() {

        Date now = new Date(); // 创建一个Date对象，获取当前时间
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("今天是 " + "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒");
        System.out.println(f.format(now)); // 将当前时间袼式化为指定的格式


        /*
         * 日期转期望格式的字符串
         */
        //HH 和 hh 的差别：前者是24小时制，后者是12小时制。
        StringBuilder sb = new StringBuilder();
        sb.append("yyyy年MM月dd日 HH:mm:ss")//
            .append(" 上下午标志 a")//
            .append(" E")//
            .append(" 一年中的第D天")//
            .append(" 一月中的第F个星期")//
            .append(" 一年中的第w个星期")//
            .append(" 一月中的第W个星期")//
            .append(" Z")//
            .append(" z");
        SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
        String dateString = sdf.format(new Date());
        System.out.println(dateString);
        /*
         * 字符串转日期
         */
        Date date;
        try {
            date = sdf.parse(dateString);
            System.out.println(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }

    @Test
    public void testSimpleDateFormat2() {
        // 日期转自己想要的字符串格式
        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳：" + ss.getTime());
        //Date aw = Calendar.getInstance().getTime();//获得时间的另一种方式，测试效果一样
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
        System.out.println("格式化结果0：" + time);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        time = format1.format(ss.getTime());
        System.out.println("格式化结果1：" + time);
    }

    @Test
    public void testSimpleDateFormat3() {
        // 字符串转日期类型
        String s = "2017-05-25";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(date);
    }

    @Test
    public void testSimpleDateFormat4() throws ParseException {

        // +08:00（或者其他时区）在解析或者格式化的时候应该使用XXX来表示;
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2022-02-22T15:50:18+08:00");
        System.out.println(date);

        // 如果是要解析或者格式化 +0800（中间没有冒号）,+0800（或者其他时区）在解析或者格式化的时候应该使用Z来表示。
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date()));
        Date date2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse("2022-02-22T15:50:18+0800");
        System.out.println(date2);

    }

    @Test
    public void testTime() {

        // yyyy-MM-dd’T’HH:mm:ss.SSSZ 后面的三个SSS指的是毫秒，Z代表的时区，中间的T代表可替换的任意字符。

        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'测试'HH:mm:ss.SSSZ");
        String str = df.format(date);
        String str1 = df1.format(date);
        System.out.println(str);
        System.out.println(str1);
    }

    @Test
    public void testTime1() throws ParseException {

        // 将2017-05-18T10:26:10.488Z转化为yyyy-MM-dd HH:mm:ss或者yyyyMMddHHmmss的格式

        String dateStr = "2017-05-18T10:26:10.488Z";
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);//输入的被转化的时间格式
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//需要转化成的时间格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = dff.parse(dateStr);
        String str1 = df1.format(date1);
        String str2 = df2.format(date1);
        System.out.println("str1 is " + str1);
        System.out.println("str2 is " + str2);
    }

    @Test
    public void testUTC() throws ParseException {

        String dateTime = "2020-01-01T21:30:03+08:00";

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
        Date date = dateformat.parse(dateTime);
        System.out.println(date);


        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX:00");
        Date date2= dateformat2.parse(dateTime);
        System.out.println(date2);

    }

    @Test
    public void testUTC2() throws ParseException {
        String dateTime = "2019-10-14T13:41:45.223Z";

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date= dateformat.parse(dateTime);
        System.out.println(date);

        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms'Z'");
        Date date2= dateformat2.parse(dateTime);
        System.out.println(date2);

    }

    @Test
    public void testGMT() throws ParseException {

        String dataTime = "Sat Oct 12 2019 14:19:40 GMT+0800 (中国标准时间)";
        // 该pattern中的 E 标识星期，MMM标识月份
        String data = dataTime.replace("GMT", "").replaceAll("\\(.*\\)", "");
        // 将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z", Locale.ENGLISH);
        Date dateTrans = format.parse(data);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将其转化解析可的日期为：2019-10-12 14:19:40
        String beijingTimeStr = formatDate.format(dateTrans);
        System.out.println(beijingTimeStr);

    }

    @Test
    public void testGMT2() throws ParseException {
        // 和上面的时间格式相比少了 + 号
        String dataTime = "Sat Oct 12 2019 14:19:40 GMT 0800 (中国标准时间)";
        // 该pattern中的 E 标识星期，MMM标识月份
        SimpleDateFormat dateformat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        // 此处解析获得的时间为伦敦时间，即格林尼治时间，若获取北京时间还需要加8个小时
        Date date= dateformat.parse(dataTime);
        System.out.println(date);

        // 上面的示例中采用 Locale指定时区，进行时区之间的时间转换。 Locale 表示地区，每一个Locale对象都代表了一个特定的地理、政治和文化地区。在操作 Date, Calendar等表示日期/时间的对象时，经常会用到；因为不同的区域，时间表示方式都不同。
        // 同理，也可以指定其他时区进行时间转换。如 CHINA，JAP，UK, FRENCH 等等。
    }

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

    @Test
    public void testDateTimeFormat1() throws ParseException {

        Long millisecond = Instant.now().toEpochMilli();  // 精确到毫秒
        Long second = Instant.now().getEpochSecond();// 精确到秒

        System.out.println(millisecond);
        System.out.println(second);
        System.out.println(new Date().getTime());

        String stringDate = "2022-04-27 17:00:30";

        // JDK8之前
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = inputFormat.parse(stringDate);
        long timestamp1 = date.getTime();
        System.out.println(timestamp1);

        // JDK8
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(stringDate, ftf);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        long mills = zonedDateTime.toInstant().toEpochMilli();
        System.out.println(mills);

        LocalDateTime dd2 = dateTime.plusHours(8);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(dd2, ZoneId.systemDefault());
        String after8hour = zonedDateTime2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        System.out.println(after8hour);

        long mills2 = zonedDateTime2.toInstant().toEpochMilli();
        System.out.println(mills2);
        System.out.println(DateFormatUtils.format(mills2, "yyyy-MM-dd HH:mm:ss"));

        // 这个东西没用，不会改变时间
        LocalDateTime dateTime3 = LocalDateTime.parse(stringDate, ftf);
        ZonedDateTime zonedDateTime3 = ZonedDateTime.of(dateTime3, ZoneOffset.of("+8"));
        long mills3 = zonedDateTime3.toInstant().toEpochMilli();
        System.out.println(mills3);


        /*//字符串时间，带T带Z带毫秒值
        String stime =  "2020-11-18T04:31:40.886Z";
        //创建对应的pattern，注意T和Z两个字符使用单引号引起来，毫秒值使用大写S表示
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //字符串时间转换成时间类型
        LocalDateTime date2 = LocalDateTime.parse(stime, pattern);
        //时间类型转时间戳类型
        long ts = date.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(date+" \nts:"+ts);*/

        String fffff = DateFormatUtils.formatUTC(date, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");//-8小时
        System.out.println(fffff);

        String dddd = DateFormatUtils.format(date, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");//只是修改了格式
        System.out.println(dddd);

        // String timestamp = "1651021230.781";
        // String timestamp = "1651021230.781";
        // String timestamp = "1651048003.391";
        // String timestamp = "1650873630.781";
        // String timestamp = "1651050030.781";
        String timestamp = "1651021230.781";

        timestamp = StringUtils.replace(timestamp, ".", "");
        long time = Double.valueOf(timestamp).longValue();
        System.out.println(DateFormatUtils.formatUTC(time, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss"));

    }

    @Test
    public void testDateTimeFormat() {
        String stringDate = "2022-04-27 15:56:30";
        System.out.println(foramtyyyyMMddHHmmss2Rfc3339(stringDate));

        System.out.println(TimeZone.getTimeZone("GMT+08:00").getID());
        System.out.println(TimeZone.getDefault().getID());

        // 纽约时间
        System.out.println(TimeZone.getTimeZone("GMT-05:00").getID());
        System.out.println(TimeZone.getTimeZone("America/New_York").getID());

        String timestamp = "1651050030.781";
        Double doubleTimestamp = Double.valueOf(timestamp) * 1000;
        long milliseconds = doubleTimestamp.longValue();
        System.out.println(milliseconds);

        String dateStr = DateFormatUtils.format(milliseconds, "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateStr);

        System.out.println(new Date().getTime());

        org.joda.time.format.DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String fromTime = DateTime.parse(dateStr, dateTimeFormatter).toString("yyyy-MM-dd'T'HH:mm:ss+08:00");
        // String fromTime = DateTime.parse(dateStr, dateTimeFormatter).toString("yyyy-MM-dd'T'HH:mm:ssXXX"); //直接报错，不识别此种pattern
        System.out.println(fromTime);
        System.out.println(foramtyyyyMMddHHmmss2Rfc3339(dateStr));
    }

    private static String foramtyyyyMMddHHmmss2Rfc3339(Date time) {
        // UTC时间格式转换
        return DateFormatUtils.format(time.getTime(), "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    private static String foramtyyyyMMddHHmmss2Rfc3339(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, ftf);
        // LocalDateTime utcDateTime = dateTime.minusHours(8);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        long mills = zonedDateTime.toInstant().toEpochMilli();
        // return DateFormatUtils.format(mills, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return DateFormatUtils.format(mills, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }
}
