/**
 * 项目名称：quickstart-xml 
 * 文件名：PullTest.java
 * 版本信息：
 * 日期：2018年5月20日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package com.quickstart.xml.xstream.xmlpull;

/**
 * PullTest 
 *  
 * @author：yangzl@asiainfo.com
 * @2018年5月20日 下午11:48:42 
 * @since 1.0
 */
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PullTest {

    public static void main(String[] args) {
        
        //*************初始化List列表集合****开始**************
        ArrayList<Wisdom> wisdomList = new ArrayList<>();
        
        Wisdom w = new Wisdom();
        w.setId(1);
        w.setContent("此刻打盹，你将做梦；而此刻学习，你将圆梦");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(2);
        w.setContent("我荒废的今日，正是昨日殒身之人祈求的明日");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(3);
        w.setContent("觉得为时已晚的时候，恰恰是最早的时候");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(4);
        w.setContent("勿将今日之事拖到明日");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(5);
        w.setContent("学习时的苦痛是暂时的，未学到的痛苦是终生的");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(6);
        w.setContent("学习这件事，不是缺乏时间，而是缺乏努力");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(7);
        w.setContent("幸福或许不排名次，但成功必排名次");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(8);
        w.setContent("学习并不是人生的全部。但既然连人生的一部分——学习也无法征服，还能做什么呢？");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(9);
        w.setContent("请享受无法回避的痛苦");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(10);
        w.setContent("只有比别人更早、更勤奋地努力，才能尝到成功的滋味");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(11);
        w.setContent("谁也不能随随便便成功，它来自彻底的自我管理和毅力");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(12);
        w.setContent("时间在流逝");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(13);
        w.setContent("现在流的口水，将成为明天的眼泪");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(14);
        w.setContent("狗一样地学，绅士一样地玩");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(15);
        w.setContent("今天不走，明天要跑");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(16);
        w.setContent("投资未来的人，是忠于现实的人");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(17);
        w.setContent("受教育程度代表收入");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(18);
        w.setContent("一天过完，不会再来");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(19);
        w.setContent("即使现在，对手也不停地翻动书页");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        
        w = new Wisdom();
        w.setId(20);
        w.setContent("没有艰辛，便无所获");
        w.setAuthor("哈佛图书馆");
        wisdomList.add(w);
        //*************初始化List列表集合****结束**************
        
        //新建PullDemo对象
        PullDemo pd = new PullDemo();
        //生成xml文件
        pd.createXML(wisdomList);
        
        try {
            File file = new File("src/main/resources/wisdoms.xml");
            //读取文件流
            FileInputStream fis = new FileInputStream(file);
            //调用解析xml方法获得结果集合
            List<Wisdom> list = pd.parseXml(fis);
            //循环打印
            for (Wisdom wisdom : list) {
                System.out.println(wisdom.getContent());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
}
