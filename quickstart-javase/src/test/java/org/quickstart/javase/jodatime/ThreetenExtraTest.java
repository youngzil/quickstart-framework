package org.quickstart.javase.jodatime;

import org.junit.Test;
import org.threeten.extra.AmPm;
import org.threeten.extra.DayOfMonth;
import org.threeten.extra.DayOfYear;
import org.threeten.extra.Days;
import org.threeten.extra.Hours;
import org.threeten.extra.Interval;
import org.threeten.extra.Minutes;
import org.threeten.extra.Months;
import org.threeten.extra.PeriodDuration;
import org.threeten.extra.Seconds;
import org.threeten.extra.Temporals;
import org.threeten.extra.Weeks;
import org.threeten.extra.Years;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ThreetenExtraTest {

    LocalDateTime first = LocalDateTime.parse("2017-07-03T10:15:30", DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime second = LocalDateTime.parse("2011-08-08T12:15:10", DateTimeFormatter.ISO_DATE_TIME);

    @Test
    public void testAmPm(){
        System.out.println(AmPm.from(LocalTime.now()));
    }

    @Test
    public void testDayOfMonth(){
        System.out.println(DayOfMonth.from(LocalDate.now()));
    }

    @Test
    public void testDayOfYear(){
        System.out.println(DayOfYear.from(LocalDate.now()));
    }

    @Test
    public void testYears(){
        System.out.println(Years.between(first,second));
    }

    @Test
    public void testMonths(){
        System.out.println(Months.between(first,second));
    }

    @Test
    public void testWeeks(){
        System.out.println(Weeks.between(first,second));
    }

    @Test
    public void testDays(){
        System.out.println(Days.between(LocalDate.parse("2017-08-01"),LocalDate.parse("2017-08-03")));
    }

    @Test
    public void testHours(){
        System.out.println(Hours.between(first,second).getAmount());
    }

    @Test
    public void testMinutes(){
        System.out.println(Minutes.between(first,second));
    }

    @Test
    public void testSeconds(){
        System.out.println(Seconds.between(first,second));
    }

    @Test
    public void testTemporals(){
        System.out.println(first.with(Temporals.previousWorkingDay()));
        System.out.println(first.with(Temporals.nextWorkingDay()));
    }

    @Test
    public void testDuration(){
        System.out.println(PeriodDuration.between(first,second).getPeriod());
        System.out.println(PeriodDuration.between(first,second).getDuration());
    }

    @Test
    public void testInterval(){

        Interval interval = Interval.of(Instant.parse("2016-01-02T01:01:01.000Z"), Instant.parse("2016-01-02T23:59:59.999Z"));
        System.out.println(interval.getStart());

        // 例如：判断是否有交集（Overlaps）
        Instant startA = Instant.parse("2018-08-01T00:00:00Z");
        Instant stopA = Instant.parse("2018-08-10T00:00:00Z");

        Instant startB = Instant.parse("2018-07-30T00:00:00Z");
        Instant stopB = Instant.parse("2018-08-02T00:00:00Z");

        Interval areaA = Interval.of(startA, stopA);

        Interval areaB = Interval.of(startB, stopB);

        boolean flag1 = areaA.overlaps(areaB);
    }
}
