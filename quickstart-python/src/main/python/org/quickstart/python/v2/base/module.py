#!/usr/bin/python
# -*- coding: utf-8 -*-

Money = 2000
def AddMoney():
	# 想改正代码就取消以下注释:
#UnboundLocalError: local variable 'Money' referenced before assignment
	global Money
	Money = Money + 1
 
print Money
AddMoney()
print Money

#dir()函数
#dir() 函数一个排好序的字符串列表，内容是一个模块里定义过的名字。
#返回的列表容纳了在一个模块里定义的所有模块，变量和函数。如下一个简单的实例：

# 导入内置math模块
import math

content = dir(math)
print content;	#在这里，特殊字符串变量__name__指向模块的名字，__file__指向该模块的导入文件名。


#系统相关的信息模块: import sys
import sys
print "sys信息"
print sys.argv # 是一个 list,包含所有的命令行参数.    
#print sys.stdout,sys.stdin ,sys.stderr # 分别表示标准输入输出,错误输出的文件对象.    
#print sys.stdin.readline() # 从标准输入读一行 sys.stdout.write("a") 屏幕输出a    
#print sys.exit(exit_code) # 退出程序    
print sys.modules # 是一个dictionary，表示系统中所有可用的module    
print sys.platform # 得到运行的操作系统环境    
print sys.path # 是一个list,指明所有查找module，package的路径.  




#操作系统相关的调用和操作: import os
import os
print "os信息"
print os.environ # 一个dictionary 包含环境变量的映射关系   
print os.environ["HOME"] # 可以得到环境变量HOME的值     
#print os.chdir(dir) # 改变当前目录 os.chdir('d:\\outlook')   注意windows下用到转义     
print os.getcwd() # 得到当前目录     
print os.getegid() # 得到有效组id os.getgid() 得到组id     
print os.getuid() # 得到用户id os.geteuid() 得到有效用户id     
#os.setegid,,os.seteuid(),os.setuid()     
#print os.getgruops() # 得到用户组名称列表   ,AttributeError: 'module' object has no attribute 'getgruops'  
print os.getlogin() # 得到用户登录名称     
print os.getenv # 得到环境变量     
print os.putenv # 设置环境变量     
print os.umask # 设置umask     
#print os.system(cmd) # 利用系统调用，运行cmd命令   ,NameError: name 'cmd' is not defined


#内置模块(不用import就可以直接使用)常用内置函数：

"""
print help([1,2,2]) #在线帮助, obj可是任何类型    
print callable(obj) #查看一个obj是不是可以像函数一样调用    
print repr(obj)# 得到obj的表示字符串，可以利用这个字符串eval重建该对象的一个拷贝    
print eval_r(str) #表示合法的python表达式，返回这个表达式    
print dir(obj) #查看obj的name space中可见的name    
print hasattr(obj,name)# 查看一个obj的name space中是否有name    
print getattr(obj,name) #得到一个obj的name space中的一个name    
print setattr(obj,name,value) #为一个obj的name   
#space中的一个name指向vale这个object    
print delattr(obj,name) #从obj的name space中删除一个name    
print vars(obj) #返回一个object的name space。用dictionary表示    
print locals() #返回一个局部name space,用dictionary表示    
print globals() #返回一个全局name space,用dictionary表示    
print type(obj) #查看一个obj的类型    
print isinstance(obj,cls) #查看obj是不是cls的instance    
print issubclass(subcls,supcls) #查看subcls是不是supcls的子类  
##################    类型转换  ##################
print chr(i) #把一个ASCII数值,变成字符    
print ord(i) #把一个字符或者unicode字符,变成ASCII数值    
print oct(x) #把整数x变成八进制表示的字符串    
print hex(x) #把整数x变成十六进制表示的字符串    
print str(obj) #得到obj的字符串描述    
print list(seq) #把一个sequence转换成一个list    
print tuple(seq) #把一个sequence转换成一个tuple    
print dict(),dict(list) #转换成一个dictionary    
print int(x) #转换成一个integer    
print long(x) #转换成一个long interger    
print float(x) #转换成一个浮点数    
print complex(x) #转换成复数    
print max(2,3,4) #求最大值    
print min(2,3,4) #求最小值  
"""


