#!/usr/bin/python
# -*- coding: utf-8 -*-
#coding=utf-8
# 文件名：HelloWorld.py
'''
Created on 2017年5月6日

@author: yangzl
'''

print "Hello World";
print "你好，世界";

print "first"; print "second";


if True:
	print "True"
else:
	print "False"


item_one=1
item_two=2
item_three=3
total = item_one + \
		item_two + \
		item_three
print total
	

days = ['Monday', 'Tuesday', 'Wednesday',
'Thursday', 'Friday']
print days


word = 'word'
sentence = "这是一个句子。"
paragraph = """这是一个段落。
包含了多个语句"""
print word
print sentence
print paragraph


# 第一个注释
print "Hello, Python!";  # 第二个注释


'''
这是多行注释，使用单引号。
这是多行注释，使用单引号。
这是多行注释，使用单引号。
'''

"""
这是多行注释，使用双引号。
这是多行注释，使用双引号。
这是多行注释，使用双引号。
"""

#"\n\n"在结果输出前会输出两个新的空行。一旦用户按下 enter(回车) 键退出，其它键显示
#raw_input("\n\nPress the enter key to exit.")	

x="a"
y="b"
# 换行输出
print x
print y

print '---------'
# 不换行输出
print x,
print y,

# 不换行输出
print x,y

counter = 100 # 赋值整型变量
miles = 1000.0 # 浮点型
name = "John" # 字符串

print counter
print miles
print name

a = b = c = 1
print a
print b
print c

a, b, c = 3, 4, "john"
print a
print b
print c


var1 = 1
var2 = 10

del var1
del var2

var_a = 1
var_b = 10
del var_a, var_b

s = 'ilovepython'
print s[1:5]


 
str = 'Hello World!'
print str           # 输出完整字符串
print str[0]        # 输出字符串中的第一个字符
print str[2:5]      # 输出字符串中第三个至第五个之间的字符串
print str[2:]       # 输出从第三个字符开始的字符串
print str * 2       # 输出字符串两次
print str + "TEST"  # 输出连接的字符串


list = [ 'runoob', 786 , 2.23, 'john', 70.2 ]
tinylist = [123, 'john']

print list               # 输出完整列表
print list[0]            # 输出列表的第一个元素
print list[1:3]          # 输出第二个至第三个元素 
print list[2:]           # 输出从第三个开始至列表末尾的所有元素
print tinylist * 2       # 输出列表两次
print list + tinylist    # 打印组合的列表


tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
tinytuple = (123, 'john')

print tuple               # 输出完整元组
print tuple[0]            # 输出元组的第一个元素
print tuple[1:3]          # 输出第二个至第三个的元素 
print tuple[2:]           # 输出从第三个开始至列表末尾的所有元素
print tinytuple * 2       # 输出元组两次
print tuple + tinytuple   # 打印组合的元组

#以下是元组无效的，因为元组是不允许更新的。而列表是允许更新的：
tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
list = [ 'runoob', 786 , 2.23, 'john', 70.2 ]
#tuple[2] = 1000    # 元组中是非法应用
#list[2] = 1000     # 列表中是合法应用


dict = {}
dict['one'] = "This is one"
dict[2] = "This is two"

tinydict = {'name': 'john','code':6734, 'dept': 'sales'}

print dict['one']          # 输出键为'one' 的值
print dict[2]              # 输出键为 2 的值
print tinydict             # 输出完整的字典
print tinydict.keys()      # 输出所有键
print tinydict.values()    # 输出所有值


#变量赋值
a = 1
b = "god"

#字符串赋值
str = 'this is string 1'

#列表串赋值
list = ['this', 'is', 'list', 2]

#元组赋值
tuple = ('this', 'is', 'tuple', 3)

#字典赋值
dict = {1:'this', 2:'is', 3:'dictionary', 4:4}


n=1
print type(n)

n="runoob"
print type(n)

a = 111
print isinstance(a, int)

#区别就是:
#	type()不会认为子类是一种父类类型。
# isinstance()会认为子类是一种父类类型。

class A:
	pass

class B(A):
	pass

print isinstance(A(), A)
print type(A()) == A
print isinstance(B(), A)
print type(B()) == A 






































	