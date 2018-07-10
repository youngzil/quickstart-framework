#!/usr/bin/python
# -*- coding: UTF-8 -*-

import smtplib
from email.mime.text import MIMEText
from email.utils import formataddr

# 第三方 SMTP 服务
mail_host = "smtp.qq.com"  # 设置服务器
# mail_port = 25  # 设置服务器port，端口必须是25
mail_port = 465  # 设置服务器port，SMTP_SSL的一般默认端口465
mail_user = "youngzil@vip.qq.com"  # 用户名
mail_pass = ""  # 口令 ，发件人邮箱密码，QQ使用SMTP授权码

sender = 'youngzil@vip.qq.com'  # 发件人邮箱账号
receivers = ['youngzil@vip.qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱
# receivers = ['youngzil@163.com','yangzl@asiainfo.com','youngzil@vip.qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

def mail():
    ret = True
    try:
        msg = MIMEText('填写邮件内容', 'plain', 'utf-8')
        msg['From'] = formataddr(["FromRunoob", sender])  # 括号里的对应发件人邮箱昵称、发件人邮箱账号
        msg['To'] = formataddr(["FK", receivers])  # 括号里的对应收件人邮箱昵称、收件人邮箱账号
        msg['Subject'] = "菜鸟教程发送邮件测试"  # 邮件的主题，也可以说是标题

        server = smtplib.SMTP_SSL(mail_host, mail_port)  # 发件人邮箱中的SMTP服务器，端口是25
        server.login(mail_user, mail_pass)  # 括号中对应的是发件人邮箱账号、邮箱密码
        server.sendmail(sender, receivers, msg.as_string())  # 括号中对应的是发件人邮箱账号、收件人邮箱账号、发送邮件
        server.quit()  # 关闭连接
    except smtplib.SMTPException, e:
        ret = False
        print "Error: 无法发送邮件"
        print 'traceback.print_exc():';
        traceback.print_exc()
    except Exception, e:  # 如果 try 中的语句没有执行，则会执行下面的 ret=False
        ret = False
        print 'str(Exception):\t', str(Exception)
        print 'str(e):\t\t', str(e)
        print 'repr(e):\t', repr(e)
        print 'e.message:\t', e.message
        print 'traceback.print_exc():';
        traceback.print_exc()
        print 'traceback.format_exc():\n%s' % traceback.format_exc()
    return ret


ret = mail()
if ret:
    print("邮件发送成功")
else:
    print("邮件发送失败")
