#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
使用except而不带任何异常类型
你可以不带任何异常类型使用except，如下实例：
try:
	正常的操作
   ......................
except:
	发生异常，执行这块代码
   ......................
else:
	如果没有异常执行这块代码
以上方式try-except语句捕获所有发生的异常。但这不是一个很好的方式，我们不能通过该程序识别出具体的异常信息。因为它捕获所有的异常。

"""
"""
使用except而带多种异常类型
你也可以使用相同的except语句来处理多个异常信息，如下所示：
try:
	正常的操作
   ......................
except(Exception1[, Exception2[,...ExceptionN]]]):
	发生以上多个异常中的一个，执行这块代码
   ......................
else:
	如果没有异常执行这块代码
"""

"""
try-finally 语句
try-finally 语句无论是否发生异常都将执行最后的代码。
try:
	<语句>
finally:
	<语句>    #退出try时总会执行
raise
"""

try:
	fh = open("testfile", "w")
	fh.write("这是一个测试文件，用于测试异常!!")
except IOError:
	print "Error: 没有找到文件或读取文件失败"
else:
	print "内容写入文件成功"
	fh.close()

"""
当在try块中抛出一个异常，立即执行finally块代码。
finally块中的所有语句执行后，异常被再次触发，并执行except块代码。
参数的内容不同于异常。
"""
try:
	fh = open("testfile", "w")#这里就报错，所以不会走内层的try-finally
	try:
		fh.write("这是一个测试文件，用于测试异常!!")
	finally:
		print "关闭文件"
		fh.close()
except IOError:
	print "外面: 没有找到文件或读取文件失败"

"""
try:
	fh = open("testfile", "w")
	fh.write("这是一个测试文件，用于测试异常!!")
finally:
	print "finally: 没有找到文件或读取文件失败"
"""
	
#现在我们可以先去掉 testfile 文件的写权限，命令如下：chmod -w testfile
#再执行代码以上代码，它打开一个文件，在该文件中的内容写入内容，但文件没有写入权限，发生了异常：


# 定义函数
def temp_convert(var):
	try:
		return int(var)
	except ValueError, Argument:
		print Argument
#		print "参数没有包含数字\n", Argument

# 调用函数
temp_convert("xyz");

#触发异常
# 定义函数
def mye( level ):
	if level < 1:
		raise Exception("Invalid level!", level)
        # 触发异常后，后面的代码就不会再执行

try:
	mye(0)                #  触发异常
#except "Invalid level2!":
#	print 1
except Exception:
	print "捕获异常"
else:
	print 2

#用户自定义异常
class Networkerror(RuntimeError):
	def __init__(self, arg):
		self.args = arg
#在你定义以上类后，你可以触发该异常，如下所示：

try:
	raise Networkerror("Bad hostname")
except Networkerror,e:
	print e.args
	print str(e.args)


#0 作为除数：

try:
	1 / 0
except Exception:
#except Exception as e:
	'''异常的父类，可以捕获所有的异常'''
	print "0不能被除"
else:
	'''保护不抛出异常的代码'''
	print "没有异常"
finally:
	print "最后总是要执行我"


"""
#异常处理代码执行说明：
#This is note foe exception

try：
	code    #需要判断是否会抛出异常的代码，如果没有异常处理，python会直接停止执行程序
except:  #这里会捕捉到上面代码中的异常，并根据异常抛出异常处理信息
	#except ExceptionName，args：    #同时也可以接受异常名称和参数，针对不同形式的异常做处理
	code  #这里执行异常处理的相关代码，打印输出等
else：  #如果没有异常则执行else
	code  #try部分被正常执行后执行的代码
finally：
	code  #退出try语句块总会执行的程序
"""

#函数中做异常检测
def try_exception(num):
	try:
		return int(num)
	except ValueError,arg:
		print arg,"is not a number"
	else:
		print "this is a number inputs"

try_exception('xxx')
#输出异常值
#Invalide literal for int() with base 10: 'xxx' is not a number























