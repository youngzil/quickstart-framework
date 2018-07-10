package com.quickstart.test.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    /**
     * 截取日期
     *
     * 去掉时分秒，使时间为当日零点正
     *
     * @param inDate
     * @return
     */
    public static Date truncateDate(Date inTime) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(inTime);
        int millSeconds = cal.get(Calendar.MILLISECOND);
        int seconds = cal.get(Calendar.SECOND);
        int minutes = cal.get(Calendar.MINUTE);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        long trival = 0L + (((60L * hours) + minutes) * 60L + seconds) * 1000L + millSeconds;
        long outTime = inTime.getTime() - trival;
        return new Date(outTime);
    }

    public static Date addDate(Date inTime, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(inTime);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }

    public static void main(String[] args) {
        Date date = DateHelper.addDate(DateHelper.truncateDate(new Date()), 1);
        System.out.println(date);
    }
}
