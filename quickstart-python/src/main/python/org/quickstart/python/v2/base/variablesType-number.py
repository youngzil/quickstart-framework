#!/usr/bin/python
# -*- coding: utf-8 -*-

#range()函数

print range(1,5)        # 代表从1到5(不包含5)
print range(1,5,2)      # 代表从1到5，间隔2(不包含5)
print range(5)          # 代表从0到5(不包含5)
#注意：默认情况下，range() 的起始值是 0。

for i in range(5) :
	print(i)


#Python数字和对应ASCII 之间的转换， ord('a') 和chr(59) 
numb = ord("b")  # convert char to int  
print numb
print chr(numb)  # convert int to char  
print unichr(numb) # return a unicode byte  


'''
abs() 和 fabs() 区别
1、abs()是一个内置函数，而fabs()在math模块中定义的。
2、fabs()函数只适用于float和integer类型，而 abs() 也适用于复数。
'''
print "abs() 和 fabs() 区别"
print abs(-10)
#print fabs(-10)	#NameError: name 'fabs' is not defined
import math
print math.fabs(-10)
print  type(abs(-10))
print type(math.fabs(-10))


