package org.quickstart.javase.jdk8.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;

import static org.junit.Assert.assertEquals;

@Slf4j
public class PeriodTest {

    // [介绍java 8 的 Period 和 Duration 类](https://blog.csdn.net/neweastsun/article/details/88770592)

    //年、月、星期、日

    @Test
    public void testPeriod() {
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
