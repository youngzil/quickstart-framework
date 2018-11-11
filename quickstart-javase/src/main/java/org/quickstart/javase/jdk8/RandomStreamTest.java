/**
 * 项目名称：quickstart-javase 
 * 文件名：RandomStreamTest.java
 * 版本信息：
 * 日期：2018年9月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8;

/**
 * RandomStreamTest 
 *  
 * @author：youngzil@163.com
 * @2018年9月22日 下午11:31:36 
 * @since 1.0
 */
import org.junit.Test;

import java.util.*;
import java.util.stream.*;

public class RandomStreamTest {

    @Test
    public void testRandomStream() {
        Random random = new Random();
        DoubleStream doubleStream = random.doubles(-1.0, 1.0);
        LinkedHashMap<Range, Integer> rangeCountMap = doubleStream.limit(1000000)
                .boxed()
                .map(Ranges::of)
                .collect(Ranges::emptyRangeCountMap, (m, e) -> m.put(e, m.get(e) + 1), Ranges::mergeRangeCountMaps);

        rangeCountMap.forEach((k, v) -> System.out.println(k.from() + "\t" + v));
    }

    @Test
    public void testGaussianRandomStream() {
        Random random = new Random();
        DoubleStream gaussianStream = Stream.generate(random::nextGaussian).mapToDouble(e -> e);
        LinkedHashMap<Range, Integer> gaussianRangeCountMap =
                gaussianStream
                        .filter(e -> (e >= -1.0 && e < 1.0))
                        .limit(1000000)
                        .boxed()
                        .map(Ranges::of)
                        .collect(Ranges::emptyRangeCountMap, (m, e) -> m.put(e, m.get(e) + 1), Ranges::mergeRangeCountMaps);

        gaussianRangeCountMap.forEach((k, v) -> System.out.println(k.from() + "\t" + v));
    }


    public static class Range {
        private final double from;
        private final double to;

        public Range(double from, double to) {
            this.from = from;
            this.to = to;
        }

        public double from() {
            return from;
        }

        public double to() {
            return to;
        }

        @Override
        public String toString() {
            return "From: " + from + " To: " + to;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Range range = (Range) o;

            if (Double.compare(range.from, from) != 0) return false;
            if (Double.compare(range.to, to) != 0) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(from);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(to);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

    public static class Ranges {
        private static LinkedHashMap<Integer, Range> rangeMap = new LinkedHashMap<>();
        static {
            rangeMap.put(-10, new Range(-1.0, -0.9));
            rangeMap.put(-9,  new Range(-0.9, -0.8));
            rangeMap.put(-8,  new Range(-0.8, -0.7));
            rangeMap.put(-7,  new Range(-0.7, -0.6));
            rangeMap.put(-6,  new Range(-0.6, -0.5));
            rangeMap.put(-5,  new Range(-0.5, -0.4));
            rangeMap.put(-4,  new Range(-0.4, -0.3));
            rangeMap.put(-3,  new Range(-0.3, -0.2));
            rangeMap.put(-2,  new Range(-0.2, -0.1));
            rangeMap.put(-1,  new Range(-0.1, 0.0 ));
            rangeMap.put(0,   new Range(0.0, 0.1  ));
            rangeMap.put(1,   new Range(0.1, 0.2  ));
            rangeMap.put(2,   new Range(0.2, 0.3  ));
            rangeMap.put(3,   new Range(0.3, 0.4  ));
            rangeMap.put(4,   new Range(0.4, 0.5  ));
            rangeMap.put(5,   new Range(0.5, 0.6  ));
            rangeMap.put(6,   new Range(0.6, 0.7  ));
            rangeMap.put(7,   new Range(0.7, 0.8  ));
            rangeMap.put(8,   new Range(0.8, 0.9  ));
            rangeMap.put(9,   new Range(0.9, 1.0  ));
        }

        public static Range of(double d) {
            int key =  (int) Math.floor(d * 10);
            return rangeMap.get(key);
        }

        public static LinkedHashMap<Range, Integer> emptyRangeCountMap() {
            LinkedHashMap<Range, Integer> rangeCountMap = new LinkedHashMap<>();
            for (Range range : rangeMap.values()) {
                rangeCountMap.put(range, 0);
            }
            return rangeCountMap;
        }

        public static void mergeRangeCountMaps(Map<Range, Integer> map1, Map<Range, Integer> map2) {
            for (Range range : rangeMap.values()) {
                map1.put(range, map1.get(range) + map2.get(range));
            }
        }
    }
}
