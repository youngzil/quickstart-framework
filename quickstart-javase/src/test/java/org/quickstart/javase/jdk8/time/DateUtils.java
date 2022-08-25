package org.quickstart.javase.jdk8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

    public final static String DATE = "yyyy-MM-dd";
    public final static String DATE_SLASH = "yyyy/MM/dd";
    public final static String DATE_CHINESE = "yyyy年MM月dd日";

    public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_HOURS = "yyyy-MM-dd HH";
    public final static String DATE_TIME_MINUTES = "yyyy-MM-dd HH:mm";
    public final static String DATE_TIME_SLASH = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_TIME_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";

    public final static String DATE_TIME_MILLION = "yyyy-MM-dd HH:mm:ss:SSS";

    public final static String YEAR = "yyyy";
    public final static String YEAR_TO_MONTH = "yyyyMM";
    public final static String YEAR_TO_DATE = "yyyyMMdd";
    public final static String YEAR_TO_SECOND = "yyyyMMddHHmmss";
    public final static String YEAR_TO_MILLION = "yyyyMMddHHmmssSSS";

    public final static String ZERO_TIME = " 00:00:00";
    public final static String ZERO_TIME_MILLION = " 00:00:00:000";
    public final static String ZERO_TIME_WITHOUT_HOURS = ":00:00";
    public final static String ZERO_TIME_WITHOUT_MINUTES = ":00";


    /**
     * 字符串转成日期、时间格式
     * @param dateString 日期字符串
     * @param pattern 格式化类型，默认为yyyy-MM-dd，其它使用DateUtils.xxx
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateString, String pattern) throws ParseException{
        if(StringUtils.isBlank(dateString)){
            return null;
        }else{
            dateString = dateString.trim();
            if(StringUtils.isBlank(pattern)){
                pattern = DATE;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(dateString);
        }
    }

    /**
     * 字符串转成日期（yyyy-MM-dd）格式
     * @param dateString 日期字符串
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(String dateString) throws ParseException{
        return parse(dateString, null);
    }

    /**
     * 字符串转成时间（yyyy-MM-dd HH:mm:ss）格式
     * @param dateString 日期字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateString) throws ParseException{
        if(StringUtils.isBlank(dateString)){
            return null;
        }else{
            dateString = dateString.trim();
            if(dateString.length() == DATE_TIME_HOURS.length()){
                return parse(dateString, DATE_TIME_HOURS);
            }else if(dateString.length() == DATE_TIME_MINUTES.length()){
                return parse(dateString, DATE_TIME_MINUTES);
            }else if(dateString.length() == DATE_TIME_MILLION.length()){
                return parse(dateString, DATE_TIME_MILLION);
            }else{
                return parse(dateString, DATE_TIME);
            }
        }
    }

    /**
     * 时间转字符串
     * @param date 时间
     * @param pattern 格式化类型，默认为yyyy-MM-dd HH:mm:ss，其它使用DateUtils.xxx
     * @return
     */
    public static String format(Date date, String pattern){
        if(date == null){
            return "";
        }else{
            if(StringUtils.isBlank(pattern)){
                pattern = DATE_TIME;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 时间转日期字符串（yyyy-MM-dd）
     * @param date 时间
     * @return
     */
    public static String formatDate(Date date){
        return format(date, DATE);
    }

    /**
     * 时间转日期字符串（yyyy-MM-dd HH:mm:ss）
     * @param date 时间
     * @return
     */
    public static String formatDateTime(Date date){
        return format(date, null);
    }

    /**
     * 将日期格式转换成时间（yyyy-MM-dd HH:mm:ss）格式
     * @param dateString 日期字符串
     * @return
     */
    public static String dateToDateTime(String dateString){
        if(StringUtils.isBlank(dateString)){
            return "";
        }else{
            dateString = dateString.trim();
            if(dateString.length() == DATE.length()){
                return dateString + ZERO_TIME;
            }else if(dateString.length() == DATE_TIME_HOURS.length()){
                return dateString + ZERO_TIME_WITHOUT_HOURS;
            }else if(dateString.length() == DATE_TIME_MINUTES.length()){
                return dateString + ZERO_TIME_WITHOUT_MINUTES;
            }else if(dateString.length() == DATE_TIME_MILLION.length()){
                return dateString.substring(0, DATE_TIME.length());
            }else{
                return dateString;
            }
        }
    }

    /**
     * 将日期格式转换成时间（时分秒毫秒）格式
     * @param dateString 日期字符串
     * @return
     */
    public static String dateToDateTimeMillion(String dateString){
        if(StringUtils.isBlank(dateString)){
            return "";
        }else{
            dateString = dateString.trim();
            return dateString + ZERO_TIME_MILLION;
        }
    }


    /**
     * 将时间字（yyyy-MM-dd HH:mm:ss）符串转换成日期（yyyy-MM-dd）格式
     * @param dateTimeString 时间字符串
     * @return String
     */
    public static String dateTimeToDate(String dateTimeString){
        if(StringUtils.isBlank(dateTimeString)){
            return "";
        }else{
            dateTimeString = dateTimeString.trim();
            if(dateTimeString.length() >= DATE.length()){
                return dateTimeString.substring(0, DATE.length());
            }else{
                return dateTimeString;
            }
        }
    }

    /**
     * 将时间（yyyy-MM-dd HH:mm:ss）转换成日期（yyyy-MM-dd）
     * @param dateTime 时间
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException{
        if(dateTime == null){
            return null;
        }else{
            return parseDate(formatDate(dateTime));
        }
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     * @return Date
     */
    public static Date now(){
        return new Date();
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     * @return Date
     */
    public static Date dateTime(){
        return new Date();
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     * @return Date
     */
    public static Date getDateTime(){
        return dateTime();
    }

    /**
     * 获取当前日期（yyyy-MM-dd）
     * @return Date
     * @throws ParseException
     */
    public static Date date() throws ParseException{
        return dateTimeToDate(new Date());
    }

    /**
     * 获取当前日期（yyyy-MM-dd）
     * @return Date
     * @throws ParseException
     */
    public static Date getDate() throws ParseException{
        return date();
    }

    /**
     * 日期加减天数
     * @param date 日期，为空时默认当前时间，包括时分秒
     * @param days 加减的天数
     * @return
     * @throws ParseException
     */
    public static Date dateAdd(Date date, int days) throws ParseException{
        if(date == null){
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 日期加减多少月
     * @param date 日期，为空时默认当前时间，包括时分秒
     * @param months 加减的月数
     * @return
     * @throws ParseException
     */
    public static Date monthAdd(Date date, int months) throws ParseException{
        if(date == null){
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }


    /**
     * 时间比较
     * <p>如果date大于compareDate返回1，小于返回-1，相等返回0</p>
     * @param date
     * @param compareDate
     * @return
     * @throws ParseException
     */
    public static int dateCompare(Date date, Date compareDate) throws ParseException{
        Calendar cal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        cal.setTime(date);
        compareCal.setTime(date);
        return cal.compareTo(compareCal);
    }


    /**
     * 获取两个日期相差的天数，不包含今天
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException{
        Date dateStart = parse(format(startDate, DATE), DATE);
        Date dateEnd = parse(format(endDate, DATE), DATE);
        return (int)((dateEnd.getTime() - dateStart.getTime()) / 1000/60/60/24);
    }


    /**
     * 获取两个日期相差的天数，包含今天
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException{
        return dateBetween(startDate, endDate) + 1;
    }


    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        //System.out.println(dateToDatetime("2018-08-17"));
        //System.out.println(dateToDatetimeMillion("2018-08-17"));
        Date date = new Date();
        //System.out.println(parse("2018-08-17", null));
        //System.out.println(parseDate("2018-08-17"));
        //System.out.println(parseDate("2018-08-17 11:40"));
        //System.out.println(parseDateTime("2018-08-17 11:40"));
        //System.out.println(parseDateTime("2018-08-17 11:40:20"));
        //System.out.println(parseDateTime("2018-08-17 11:40:20:169"));

        //System.out.println(format(parseDateTime("2018-08-17 11:40:20:069"), DATE_TIME_MILLION));
        //System.out.println(format(date, null));
        //System.out.println(formatDate(date));
        //System.out.println(formatDateTime(date));

        //System.out.println(dateTimeToDate("2018-08-17 11:40"));
        //System.out.println(dateTimeToDate("2018-08-17"));
        //System.out.println(dateTimeToDate("2018-08-17 11"));

        //System.out.println(dateTimeToDate(date));
        //System.out.println(formatDate(dateTimeToDate(date)));
        //System.out.println(formatDateTime(dateTimeToDate(date)));

        /*
        System.out.println(dateToDateTime("2018-08-17"));
        System.out.println(dateToDateTime("2018-08-17 12"));
        System.out.println(dateToDateTime("2018-08-17 13:10"));
        System.out.println(dateToDateTime("2018-08-17 14:10:20"));
        System.out.println(dateToDateTime("2018-08-17 15:10:20:158"));
        */

        System.out.println(monthAdd(null, 12));
    }


}
