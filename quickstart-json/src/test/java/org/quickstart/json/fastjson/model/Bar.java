/**
 * 项目名称：quickstart-json 
 * 文件名：Bar.java
 * 版本信息：
 * 日期：2017年12月13日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.json.fastjson.model;

import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * Bar
 * 
 * SerializeConfig：是对序列化过程中一些序列化过程的特殊配置，这里用作日期格式的定义。 有关需要带类型的全类型序列化过程，需要调用JSON.toJSONStringZ()方法。 需要美化输出时候，需要打开序列化美化开关，在方法中true那个参数。
 * 
 * @author：youngzil@163.com
 * @2017年12月13日 下午4:55:35
 * @since 1.0
 */
public class Bar {

    public static SerializeConfig mapping = new SerializeConfig();
    private String barName;
    private int barAge;
    private Date barDate = new Date();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }
    {
        Random r = new Random();
        barName = "sss_" + String.valueOf(r.nextFloat());
        barAge = r.nextInt();
    }

    public static void main(String[] args) {
        Object obj = JSON.toJSON(new Bar());
        String x1 = JSON.toJSONString(new Bar(), true);
        System.out.println(x1);
        String x2 = JSON.toJSONString(new Bar(), mapping);
        System.out.println(x2);
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public int getBarAge() {
        return barAge;
    }

    public void setBarAge(int barAge) {
        this.barAge = barAge;
    }

    public Date getBarDate() {
        return barDate;
    }

    public void setBarDate(Date barDate) {
        this.barDate = barDate;
    }

    @Override
    public String toString() {
        return "Bar{" + "barName='" + barName + '\'' + ", barAge=" + barAge + ", barDate=" + barDate + '}';
    }

}
