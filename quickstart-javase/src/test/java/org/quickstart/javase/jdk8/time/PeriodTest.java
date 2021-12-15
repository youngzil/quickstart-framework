package org.quickstart.javase.jdk8.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;

@Slf4j
public class PeriodTest {

    // [介绍java 8 的 Period 和 Duration 类](https://blog.csdn.net/neweastsun/article/details/88770592)

    // 在Java8中，我们可以使用以下类来计算日期时间差异：
    //     1.Period
    //     2.Duration
    //     3.ChronoUnit

    //年、月、星期、日

    @Test
    public void testPeriod() {

        // Period类 主要是Period类方法getYears（），getMonths（）和getDays（）来计算.

        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);
        LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
        System.out.println("BirthDate : " + birthDate);

        Period p = Period.between(birthDate, today);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());

    }

    @Test
    public void testDuration() {

        // Duration类 提供了使用基于时间的值（如秒，纳秒）测量时间量的方法。

        // java.time.Duration类用于代表两个Instant对象之间的一段时间。该类同样是JDK8中新引入的。
        // Duration类是不可变类，意味着一个该类的对象一旦生成，该对象就是不可变的。
        // 如果需要创建Duration类的对象，直接使用该类的工厂方法就可以了，

        Instant inst1 = Instant.now();
        System.out.println("Inst1 : " + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        System.out.println("Inst2 : " + inst2);

        System.out.println("Difference in milliseconds : " + Duration.between(inst1, inst2).toMillis());

        System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());

    }

    @Test
    public void testChronoUnit() {

        // ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒。

        LocalDate startDate = LocalDate.of(1993, Month.OCTOBER, 19);
        System.out.println("开始时间  : " + startDate);

        LocalDate endDate = LocalDate.of(2017, Month.JUNE, 16);
        System.out.println("结束时间 : " + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);

    }

    @Test
    public void testPeriod2() throws InterruptedException {

        long start = System.currentTimeMillis();
        //business code
        Thread.sleep(1000);
        long over = System.currentTimeMillis();
        System.out.println("business used " + (over - start) + " ms");

        Instant now = Instant.now();
        //business code
        Thread.sleep(2000);
        long used = ChronoUnit.MILLIS.between(now, Instant.now());
        //或者
        //long used =now.until(Instant.now(), ChronoUnit.MILLIS)
        System.out.println("business used " + used + " ms");
        long used2 = ChronoUnit.NANOS.between(now, Instant.now());
        System.out.println("business used " + used2 + " ms");

    }

    @Test
    public void testPeriod3() {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);

        Period period = Period.between(startDate, endDate);

        log.info("Years:" + period.getYears() + " months:" + period.getMonths() + " days:" + period.getDays());

        Period fromUnits = Period.of(3, 10, 10);
        Period fromDays = Period.ofDays(50);
        Period fromMonths = Period.ofMonths(5);
        Period fromYears = Period.ofYears(10);
        Period fromWeeks = Period.ofWeeks(40);

        assertEquals(280, fromWeeks.getDays());

        // 我们也可以通过解析文本序列来创建Period，其格式为“PnYnMnD”:
        Period fromCharYears = Period.parse("P2Y");
        assertEquals(2, fromCharYears.getYears());

        Period fromCharUnits = Period.parse("P2Y3M5D");
        assertEquals(5, fromCharUnits.getDays());

        // period的值可以通过plusX()、minusX()方法进行增加或减少，其中X表示日期单元：
        assertEquals(56, period.plusDays(50).getDays());
        assertEquals(9, period.minusMonths(2).getMonths());

    }
}
