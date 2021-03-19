package org.quickstart.javase.jdk8.time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DurationTest {

    // [介绍java 8 的 Period 和 Duration 类](https://blog.csdn.net/neweastsun/article/details/88770592)

    // 天、小时、分钟、秒、毫秒、纳秒
    @Test
    public void testDuration() {

        Instant start = Instant.parse("2017-10-03T10:15:30.00Z");
        Instant end = Instant.parse("2017-10-03T10:16:30.00Z");

        Duration duration = Duration.between(start, end);
        assertEquals(60, duration.getSeconds());

        LocalTime start2 = LocalTime.of(1, 20, 25, 1024);
        LocalTime end2 = LocalTime.of(3, 22, 27, 1544);
        Duration.between(start2, end2).getSeconds();
        assertFalse(duration.isNegative());

        Duration fromDays = Duration.ofDays(1);
        assertEquals(86400, fromDays.getSeconds());
        Duration fromMinutes = Duration.ofMinutes(60);

        // 当然也可以通过文本序列创建Duration对象，格式为 “PnDTnHnMn.nS”:
        Duration fromChar1 = Duration.parse("P1DT1H10M10.5S");
        Duration fromChar2 = Duration.parse("PT10M");

        // 可以使用toDays(), toHours(), toMillis(), toMinutes()方法把Duration对象可以转成其他时间单元：
        assertEquals(1, fromMinutes.toHours());

        // 也可以通过 plusX()、minusX()方法增加或减少Duration对象，其中X表示days, hours, millis, minutes, nanos 或 seconds:
        assertEquals(120, duration.plusSeconds(60).getSeconds());
        assertEquals(30, duration.minusSeconds(30).getSeconds());

        // 也可以使用plus()和minus()方法带TemporalUnit 类型参数进行加减：
        assertEquals(120, duration.plus(60, ChronoUnit.SECONDS).getSeconds());
        assertEquals(30, duration.minus(30, ChronoUnit.SECONDS).getSeconds());

    }
}
