#!/usr/bin/python
# -*- coding: UTF-8 -*-

import traceback
import smtplib
from email.mime.text import MIMEText
from email.header import Header
from email.utils import formataddr

# 第三方 SMTP 服务
mail_host = "smtp.163.com"  # 设置服务器
mail_port = 25  # 设置服务器port，端口必须是25
# mail_port = 465  # 设置服务器port，SMTP_SSL的一般默认端口465
mail_pass = ""  # 口令

sender = 'youngzil@163.com'
receivers = ['youngzil@163.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱
# 收件人只能是163邮箱，不知道为什么
# receivers = ['youngzil@163.com','yangzl@asiainfo.com','youngzil@vip.qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

message = MIMEText('Python 邮件发送测试...', 'plain', 'utf-8')

# Python发送HTML格式的邮件与发送纯文本消息的邮件不同之处就是将MIMEText中_subtype设置为html。
mail_msg = """
<p>Python 邮件发送测试...</p>
<p><a href="http://www.runoob.com">这是一个链接</a></p>
"""
# message = MIMEText(mail_msg, 'html', 'utf-8')

# message['From'] = Header("菜鸟教程", 'utf-8')
# message['To'] = Header("测试", 'utf-8')
# subject = 'Python SMTP 邮件测试'
# message['Subject'] = Header(subject, 'utf-8')

message['From'] = formataddr(["FromRunoob", sender])  # 括号里的对应发件人邮箱昵称、发件人邮箱账号
message['To'] = formataddr(["FK", receivers])  # 括号里的对应收件人邮箱昵称、收件人邮箱账号
message['Subject'] = "菜鸟教程发送邮件测试"  # 邮件的主题，也可以说是标题

try:
    smtpObj = smtplib.SMTP()
    # smtpObj = smtplib.SMTP_SSL()
    smtpObj.connect(mail_host, mail_port)  # mail_port 为 SMTP 端口号，
    smtpObj.login(mail_user, mail_pass)
    smtpObj.sendmail(sender, receivers, message.as_string())
    print "邮件发送成功"
except smtplib.SMTPException, e:
    print "Error: 无法发送邮件"
    print 'traceback.print_exc():';
    traceback.print_exc()
except Exception, e:
    print 'str(Exception):\t', str(Exception)
    print 'str(e):\t\t', str(e)
    print 'repr(e):\t', repr(e)
    print 'e.message:\t', e.message
    print 'traceback.print_exc():';
    traceback.print_exc()
    print 'traceback.format_exc():\n%s' % traceback.format_exc()
