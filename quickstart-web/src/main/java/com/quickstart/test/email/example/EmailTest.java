package com.quickstart.test.email.example;

public class EmailTest {
    public static void main(String[] args) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUsername("xxx@163.com");
        mailInfo.setPassword("xxxxx");// 您的邮箱密码
        mailInfo.setFromAddress("xxxx@163.com");
        mailInfo.setToAddress("xxxx@163.com");
        mailInfo.setSubject("设置邮箱标题");

        // 附件
        String[] attachFileNames = {"d:/Sunset.jpg"};
        mailInfo.setAttachFileNames(attachFileNames);

        // 这个类主要来发送邮件
        // mailInfo.setContent("设置邮箱内容");
        // SimpleMail.sendTextMail(mailInfo);// 发送文体格式
        StringBuffer demo = new StringBuffer();
        demo.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">").append("<html>").append("<head>")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append("<title>测试邮件</title>").append("<style type=\"text/css\">")
                .append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}").append("</style>").append("</head>").append("<body>").append("<span class=\"test\">大家好，这里是测试Demo</span>")
                .append("</body>").append("</html>");
        mailInfo.setContent(demo.toString());
        SimpleMail.sendHtmlMail(mailInfo);// 发送html格式
    }
}
