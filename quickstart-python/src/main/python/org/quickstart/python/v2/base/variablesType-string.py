#!/usr/bin/python
# -*- coding: utf-8 -*-

var1 = 'Hello World!'
var2 = "Python Runoob"

print "var1[0]: ", var1[0]
print "var2[1:5]: ", var2[1:5]

print "更新字符串 :- ", var1[:6] + 'Runoob!'


a = "Hello"
b = "Python"

print "a + b 输出结果：", a + b 
print "a * 2 输出结果：", a * 2 
print "a[1] 输出结果：", a[1] 
print "a[1:4] 输出结果：", a[1:4] 

if( "H" in a) :
	print "H 在变量 a 中" 
else :
	print "H 不在变量 a 中" 

if( "M" not in a) :
	print "M 不在变量 a 中" 
else :
	print "M 在变量 a 中"

print r'\n'
#print R'\n' #报错，不知道为什么

#Python 字符串格式化
print "My name is %s and weight is %d kg!" % ('Zara', 21) 


hi = '''hi 
there'''
print hi   # repr()
print hi  # str()


errHTML = '''
<HTML><HEAD><TITLE>
Friends CGI Demo</TITLE></HEAD>
<BODY><H3>ERROR</H3>
<B>%s</B><P>
<FORM><INPUT TYPE=button VALUE=Back
ONCLICK="window.history.back()"></FORM>
</BODY></HTML>
'''
print errHTML

"""
#python中cursor操作数据库
import MySQLdb
cursor.execute('''
CREATE TABLE users (  
login VARCHAR(8), 
uid INTEGER,
prid INTEGER)
''')
"""

#Unicode 字符串
#Python 中定义一个 Unicode 字符串和定义一个普通字符串一样简单：
#引号前小写的"u"表示这里创建的是一个 Unicode 字符串。如果你想加入一个特殊字符，可以使用 Python 的 Unicode-Escape 编码。如下例所示：
#被替换的 \u0020 标识表示在给定位置插入编码值为 0x0020 的 Unicode 字符（空格符）。

print u'Hello World !'
print u'Hello\u0020World !'


