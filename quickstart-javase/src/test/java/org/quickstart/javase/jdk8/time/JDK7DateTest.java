package org.quickstart.javase.jdk8.time;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class JDK7DateTest {

    @Test
    public void testMaxAndMin() {

        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(getStartTime() + " ~ " + getEndTime());
        System.out.println("格式化后 ：" + timeFormat.format(getStartTime()) + " ~ " + timeFormat.format(getEndTime()));

        //    OUTPUT：
        //    1598544000813 ~ 1598630399813
        //    格式化后 ：2020-08-28 00:00:00 ~ 2020-08-28 23:59:59
    }

    @Test
    public void testDateMaxAndMin() {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        // 获取今天23:59:59
        Date max = calendar.getTime();
        System.out.println(max);

        // 获取前一天23:59:59
        //获取前几天的都可以，对-1进行改变即可
        //获取后一天的时间也可以，把-1改为1即可
        //后几天和前几天同理
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date max2 = calendar.getTime();
        System.out.println(max2);

        // java时间戳转换方法
        long n = max.getTime(); //13位毫秒值
        String a = String.valueOf(n);
        String q = a.substring(0, 10); // 裁剪掉后3位，转为秒
        Integer time = Integer.valueOf(q);
        System.out.println(time + "秒");

    }

    @Test
    public void test1() {
        Date currDate = new Date();
        System.out.println(currDate.toString());
        // 已经@Deprecated
        System.out.println(currDate.toLocaleString());
        // 已经@Deprecated
        System.out.println(currDate.toGMTString());

        // 第一个：标准的UTC时间（CST就代表了偏移量 +0800）
        // 第二个：本地时间，根据本地时区显示的时间格式
        // 第三个：GTM时间，也就是格林威治这个时候的时间，可以看到它是凌晨2点（北京时间是上午10点哦）

        // 第二个、第三个其实在JDK 1.1就都标记为@Deprecated过期了，基本禁止再使用。若需要转换为本地时间 or GTM时间输出的话，请使用格式化器java.text.DateFormat去处理。

    }

    @Test
    public void testDate1() {

        Date date1 = new Date();    // 调用无参数构造函数
        System.out.println(date1.toString());    // 输出：Wed May 18 21:24:40 CST 2016
        Date date2 = new Date(60000);    // 调用含有一个long类型参数的构造函数，此种形式表示从 GMT 时间（格林尼治时间）1970 年 1 月 1 日 0 时 0 分 0 秒开始经过参数 date 指定的毫秒数。

        System.out.println(date2);    // 输出：Thu Jan 0108:01:00 CST 1970

    }

    @Test
    public void testDate2() {

        Scanner input = new Scanner(System.in);
        System.out.println("请输入要做的事情：");
        String title = input.next();
        Date date1 = new Date(); // 获取当前日期
        System.out.println("[" + title + "] 这件事发生时间为：" + date1);
        try {
            Thread.sleep(60000);// 暂停 1 分钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        System.out.println("现在时间为：" + date2);
        if (date2.before(date1)) {
            System.out.println("你还有 " + (date2.getTime() - date1.getTime()) / 1000 + " 秒需要去完成【" + title + "】这件事！");
        } else {
            System.out.println("【" + title + "】事情已经过去了 " + (date2.getTime() - date1.getTime()) / 1000 + " 秒");
        }
    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance(); // 如果不设置时间，则默认为当前时间
        calendar.setTime(new Date()); // 将系统当前时间赋值给 Calendar 对象
        System.out.println("现在时刻：" + calendar.getTime()); // 获取当前时间
        int year = calendar.get(Calendar.YEAR); // 获取当前年份
        System.out.println("现在是" + year + "年");
        int month = calendar.get(Calendar.MONTH) + 1; // 获取当前月份（月份从 0 开始，所以加 1）
        System.out.print(month + "月");
        int day = calendar.get(Calendar.DATE); // 获取日
        System.out.print(day + "日");
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 获取今天星期几（以星期日为第一天）
        System.out.print("星期" + week);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 获取当前小时数（24 小时制）
        System.out.print(hour + "时");
        int minute = calendar.get(Calendar.MINUTE); // 获取当前分钟
        System.out.print(minute + "分");
        int second = calendar.get(Calendar.SECOND); // 获取当前秒数
        System.out.print(second + "秒");
        int millisecond = calendar.get(Calendar.MILLISECOND); // 获取毫秒数
        System.out.print(millisecond + "毫秒");
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 获取今天是本月第几天
        System.out.println("今天是本月的第 " + dayOfMonth + " 天");
        int dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 获取今天是本月第几周
        System.out.println("今天是本月第 " + dayOfWeekInMonth + " 周");
        int many = calendar.get(Calendar.DAY_OF_YEAR); // 获取今天是今年第几天
        System.out.println("今天是今年第 " + many + " 天");
        Calendar c = Calendar.getInstance();
        c.set(2012, 8, 8); // 设置年月日，时分秒将默认采用当前值
        System.out.println("设置日期为 2012-8-8 后的时间：" + c.getTime()); // 输出时间
    }

    @Test
    public void testCalendarPrint() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, 5, 1); // 实际的calendar对象所表示的日期为2016年6月1日
        // 判断2016年6月1日为一周中的第几天
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        char[] title = {'日', '一', '二', '三', '四', '五', '六'}; // 存放曰历的头部
        int daysArray[][] = new int[6][7];// 存放日历的数据
        int daysInMonth = 31; // 该月的天数
        int day = 1; // 自动增长
        for (int i = index; i < 7; i++) {
            // 填充第一周的日期数据，即日历中的第一行
            daysArray[0][i] = day++;
        }
        for (int i = 1; i < 6; i++) {
            // 填充其他周的日历数据，控制行
            for (int j = 0; j < 7; j++) {
                // 如果当前day表示的是本月最后一天，则停止向数组中继续赋值
                if (day > daysInMonth) {
                    i = 6;
                    break;
                }
                daysArray[i][j] = day++;
            }
        }
        System.out.println("------------------2016 年 6 月--------------------\n");
        for (int i = 0; i < title.length; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.print("\n");
        // 输出二元数组daysArray中的元素
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (daysArray[i][j] == 0) {
                    if (i != 0) {
                        // 如果到月末，则完成显示日历的任务，停止该方法的执行
                        return;
                    }
                    System.out.print("\t");
                    continue;
                }
                System.out.print(daysArray[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    /**
     * 获取当天00：00：00的时间戳
     *
     * @return 时间戳
     */
    public static Long getStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取当天23：59：59的时间戳
     *
     * @return 时间戳
     */
    public static Long getEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis();
    }

}
