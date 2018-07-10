#!/usr/bin/python
# -*- coding: UTF-8 -*-

import smtplib
from email.mime.text import MIMEText
from email.header import Header

strFrom = "1169816642@qq.com";
strTo = "1169816642@qq.com";
msg = MIMEText("Hello World");
msg['Content-Type'] = 'Text/HTML';
msg['Subject'] = Header("python test", 'gb2312');
msg['To'] = strTo;
msg['From'] = strFrom;

# mail_port = 25  # 设置服务器port，端口必须是25
# mail_port = 465  # 设置服务器port，SMTP_SSL的一般默认端口465
smtp = smtplib.SMTP_SSL("smtp.qq.com", 465)  # 发件人邮箱中的SMTP服务器，默认的SMTP服务端口是25
smtp.login(strFrom, "");  # QQ使用SMTP授权码
try:
    smtp.sendmail(strFrom, strTo, msg.as_string());
finally:
    smtp.close;
