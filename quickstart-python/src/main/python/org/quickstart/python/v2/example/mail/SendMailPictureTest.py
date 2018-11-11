#!/usr/bin/python
# -*- coding: UTF-8 -*-

import smtplib
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.header import Header

# 第三方 SMTP 服务
mail_host = "smtp.163.com"  # 设置服务器
mail_port = 25  # 设置服务器port，端口必须是25
# mail_port = 465  # 设置服务器port，SMTP_SSL的一般默认端口465
mail_user = "youngzil@163.com"  # 用户名
mail_pass = ""  # 口令

# Python 发送带附件的邮件
# 发送带附件的邮件，首先要创建MIMEMultipart()实例，然后构造附件，如果有多个附件，可依次构造，最后利用smtplib.smtp发送。

sender = 'youngzil@163.com'
receivers = ['youngzil@163.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱
# 收件人只能是163邮箱，不知道为什么
# receivers = ['youngzil@163.com','youngzil@163.com','youngzil@vip.qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

msgRoot = MIMEMultipart('related')
msgRoot['From'] = Header("菜鸟教程", 'utf-8')
msgRoot['To'] = Header("测试", 'utf-8')
subject = 'Python SMTP 邮件测试'
msgRoot['Subject'] = Header(subject, 'utf-8')

msgAlternative = MIMEMultipart('alternative')
msgRoot.attach(msgAlternative)

mail_msg = """
<p>Python 邮件发送测试...</p>
<p><a href="http://www.runoob.com">菜鸟教程链接</a></p>
<p>图片演示：</p>
<p><img src="cid:image1"></p>
"""
msgAlternative.attach(MIMEText(mail_msg, 'html', 'utf-8'))

# 指定图片为当前目录
fp = open('test.png', 'rb')
msgImage = MIMEImage(fp.read())
fp.close()

# 定义图片 ID，在 HTML 文本中引用
msgImage.add_header('Content-ID', '<image1>')
msgRoot.attach(msgImage)

try:
    smtpObj = smtplib.SMTP()
    # smtpObj = smtplib.SMTP_SSL()
    smtpObj.connect(mail_host, mail_port)  # mail_port 为 SMTP 端口号，
    smtpObj.login(mail_user, mail_pass)
    smtpObj.sendmail(sender, receivers, msgRoot.as_string())
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
