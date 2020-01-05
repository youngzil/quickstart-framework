package org.quickstart.javase.jdk8.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/1/5 18:06
 */
public class DateFormatTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = formatter.parse("2019-12-31 23:59:59");
        System.out.println(date1);

        SimpleDateFormat formatter2 = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        Date date2 = formatter2.parse("2019-12-31 23:59:59");
        System.out.println(date2);

        SimpleDateFormat formatter3 = new SimpleDateFormat("YYYY-MM-DD");
        Date date3 = formatter3.parse("2020-01-01");
        System.out.println(date3);

        try {
            String[] dates = {"2018-12-01", "2018-12-31", "2019-01-01"};
            for (String date: dates) {
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date d = dt.parse(date);

                SimpleDateFormat dtYYYY = new SimpleDateFormat("YYYY");
                SimpleDateFormat dtyyyy = new SimpleDateFormat("yyyy");

                System.out.println("For date " + date + " the YYYY year is " + dtYYYY.format(d) + " while for yyyy it's " + dtyyyy.format(d));
            }
        } catch (Exception e) {
            System.out.println("Failed with exception: " + e);
        }





    }
}
