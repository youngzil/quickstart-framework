package org.quickstart.javase.jdk.time.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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

    private static String foramtyyyyMMddHHmmss2Rfc3339(java.util.Date time) {
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

    private static String formatUnixTimestamp2yyyyMMddHHmmss(String timestamp) {
        Double doubleTimestamp = Double.valueOf(timestamp) * 1000;
        long milliseconds = doubleTimestamp.longValue();
        return DateFormatUtils.format(milliseconds, "yyyy-MM-dd HH:mm:ss");
    }

    @Test
    public void testDate() {
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
        System.out.println(DateFormatUtils.format(milliseconds, "yyyy-MM-dd HH:mm:ss"));

        System.out.println(new java.util.Date().getTime());


        /*Map valuesMap = new HashMap();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        String templateString = "The ${animal} jumped over the ${target}. ${undefined.number:-1234567890}.";
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        String resolvedString = sub.replace(templateString);*/

    }

}
