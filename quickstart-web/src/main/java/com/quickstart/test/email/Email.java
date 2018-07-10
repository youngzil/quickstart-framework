package com.quickstart.test.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class Email {
    // 发送普通文件的mail
    public void mySend() {
        try {
            SimpleEmail email = new SimpleEmail(); // 构造一个mail对象
            // email.setHostName("hostname");// 设置主机名
            email.addTo("mail", "my");// 设置发对象
            email.setFrom("yahu@.cn.com", "my");// 设置发送人
            email.setSubject("邮件测试");// 设置主题
            email.setCharset("GBK");// 设置发送使用的字符集
            String content = "测试内容是我自己的";// 内容

            email.setContent(content, "text/plain;charset=GBK");// 设置内容

            email.send();// 发送
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Email email = new Email();
        email.mySend();
    }

    // 付注:附件可以发送多个,只需要构靠多个EmailAttachment即可
    // 发送带附件的mail
    public void myAttachment() {
        MultiPartEmail email = new MultiPartEmail();// 构造一个mail对象
        email.setHostName("hostname");// 设置服务器名
        try {
            EmailAttachment attachment = new EmailAttachment();// 构造一个发送附件
            attachment.setPath("C:\\2.jpg");// 设置附件路径
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("中文");// 描述
            attachment.setName(MimeUtility.encodeText("美女.png"));

            email.addTo("yahu@.cn.com", "name");// 发送对象
            email.setFrom("yahu@.cn.com", "name");// 发送人
            email.setSubject("测试");// 标题
            email.setCharset("GBK");// 使用的字符集

            String content = "美女";// 内容
            email.setMsg(content);// 设置内容
            email.attach(attachment);// 发送附件
            email.send();// 发送

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

    // 发送html格式的mail
    public void htmlEmail() {
        HtmlEmail email = new HtmlEmail();// 构造一个html mail对象

        email.setHostName("hope.cn");// 设置服务器名
        try {
            email.addTo("yahu@.cn.com", "my");// 设置发送对象
            email.setFrom("yahu@.cn.com", "my");// 设置发送人
            email.setSubject("测试");// 设置主题
            String mag = "红色";// 这里可以写你的html文档,因为本页面我是用xml文件做数据存储的,不能写标签,这里我就不写了
            email.setHtmlMsg(mag);// 设置内容
            email.setCharset("GBK");// 设置字符集
            email.send();// 发送
        } catch (EmailException e) {

            e.printStackTrace();
        }

    }

    public void hEmail() {

        EmailAttachment attachment = new EmailAttachment();

        try {
            attachment.setURL(new URL("url"));// 设置附件的ＵＲＬ
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("附件.pdf");// 设置附件描述
            try {
                attachment.setName(MimeUtility.encodeText("附件.pdf"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            HtmlEmail hmail = new HtmlEmail();// 构造mail对象
            hmail.setHostName("hope.cn");// 设置服务器
            hmail.addTo("yahu@.cn.com", "my");// 设置发送对象
            hmail.setFrom("yahu@.cn.com", "my");// 设置发送人
            hmail.setSubject("测试");// 设置标题

            URL url = new URL("url");// 构造一个UTL

            InputStream in = (InputStream) url.getContent();// 从URL中获得输出流
            String msg = IOUtils.toString(in);// 获得输出流的内容

            hmail.setHtmlMsg(msg);// 设置html内容
            hmail.setCharset("GBK");// 设置字符集
            hmail.attach(attachment);// 设置附件
            hmail.send();// 发送

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }

    }
}
