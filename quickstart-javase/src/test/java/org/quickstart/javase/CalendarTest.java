/**
 * 项目名称：quickstart-javase 
 * 文件名：CalendarTest.java
 * 版本信息：
 * 日期：2018年8月16日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * CalendarTest
 * 
 * @author：youngzil@163.com
 * @2018年8月16日 下午8:40:10
 * @since 1.0
 */
public class CalendarTest {

    public static void main(String[] args) {
        // 使用Calendar
        Calendar now = Calendar.getInstance();
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        
        // 循环获取每个整点小时内的数据
        for (int i = 0; i < 24; i++) {
            Date endDate = now.getTime();
            now.add(Calendar.HOUR, -1);// taskPeriod之前的时间
            Date startDate = now.getTime();

            System.out.println("当前时间endDate：" + sdf.format(endDate));
            System.out.println("当前时间startDate：" + sdf.format(startDate));
            System.out.println("当前时间startDate年月日：" + sdf2.format(startDate));
            System.out.println("当前时间startDate年月日：" + sdf2.format(endDate));
            System.out.println("===========");
        }

        now.setTime(new Date());// 设置参数时间
        System.out.println("年：" + now.get(Calendar.YEAR));
        System.out.println("月：" + (now.get(Calendar.MONTH) + 1));
        System.out.println("日：" + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + now.get(Calendar.MINUTE));
        System.out.println("秒：" + now.get(Calendar.SECOND));
        
        // 使用Date
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

}
