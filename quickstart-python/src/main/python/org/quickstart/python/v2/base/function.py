#!/usr/bin/python
# -*- coding: utf-8 -*-

#以下为一个简单的Python函数，它将一个字符串作为传入参数，再打印到标准显示设备上。
# 定义函数
def printme( str ):
	"打印传入的字符串到标准显示设备上"
	print str
	return

# 调用函数
printme("我要调用用户自定义函数!");
printme("再次调用同一函数");

"""
在 python 中，类型属于对象，变量是没有类型的：
a=[1,2,3]
a="Runoob"
以上代码中，[1,2,3] 是 List 类型，"Runoob" 是 String 类型，而变量 a 是没有类型，她仅仅是一个对象的引用（一个指针），可以是 List 类型对象，也可以指向 String 类型对象。
"""

"""
python 传不可变对象实例
实例中有 int 对象 2，指向它的变量是 b，在传递给 ChangeInt 函数时，按传值的方式复制了变量 b，a 和 b 都指向了同一个 Int 对象，
在 a=10 时，则新生成一个 int 值对象 10，并让 a 指向它。
"""
def ChangeInt( a ):
	a = 10
b = 2
ChangeInt(b)
print b # 结果是 2


"""
传可变对象实例
实例中传入函数的和在末尾添加新内容的对象用的是同一个引用，
"""
# 可写函数说明
def changeme( mylist ):
	"修改传入的列表"
	mylist.append([1,2,3,4]);
	print "函数内取值: ", mylist
	return

# 调用changeme函数
mylist = [10,20,30];
changeme( mylist );
print "函数外取值: ", mylist


"""
必备参数
必备参数须以正确的顺序传入函数。调用时的数量必须和声明时的一样。
调用printme()函数，你必须传入一个参数，不然会出现语法错误：
"""
#可写函数说明
def printme( str ):
	"打印任何传入的字符串"
	print str;
	return;
 
# 必备参数
#调用printme函数
#printme();	#报错，TypeError: printme() takes exactly 1 argument (0 given)

#关键字参数
#调用printme函数
printme( str = "My string");


#可写函数说明
def printinfo( name, age ):
	"打印任何传入的字符串"
	print "Name: ", name;
	print "Age ", age;
	return;
 
#关键字参数
#调用printinfo函数
printinfo( age=50, name="miki" );


#可写函数说明
def printinfo2( name, age = 35 ):
	"打印任何传入的字符串"
	print "Name: ", name;
	print "Age ", age;
	return;
 
#缺省参数
#调用printinfo函数
printinfo2( age=50, name="miki" );
printinfo2( name="miki" );


#不定长参数
# 可写函数说明
def printinfo3( arg1, *vartuple ):
	"打印任何传入的参数"
	print "输出: "
	print arg1
	for var in vartuple:
		print var
	return;

#不定长参数
# 调用printinfo 函数
printinfo3( 10 );
printinfo3( 70, 60, 50 );


#python 使用 lambda 来创建匿名函数。
# 可写函数说明
sum = lambda arg1, arg2: arg1 + arg2;

# 调用sum函数
print "相加后的值为 : ", sum( 10, 20 )
print "相加后的值为 : ", sum( 20, 20 )


# 可写函数说明
def sum( arg1, arg2 ):
	# 返回2个参数的和."
	total = arg1 + arg2
	print "函数内 : ", total
	return total;

#return 语句
# 调用sum函数
total = sum( 10, 20 );
print total


total = 0; # 这是一个全局变量
# 可写函数说明
def sum( arg1, arg2 ):
	#返回2个参数的和."
	total = arg1 + arg2; # total在这里是局部变量.
	print "函数内是局部变量 : ", total
	return total;

#调用sum函数
sum( 10, 20 );
print "函数外是全局变量 : ", total 


#全局变量想作用于函数内，需加 global
#1、global---将变量定义为全局变量。可以通过定义为全局变量，实现在函数内部改变变量值。
#2、一个global语句可以同时定义多个变量，如 global x, y, z。

globvar = 0

def set_globvar_to_one():
	global globvar    # 使用 global 声明全局变量
	globvar = 1

def print_globvar():
	print(globvar)     # 没有使用 global

set_globvar_to_one()
print  globvar        # 输出 1
print_globvar()       # 输出 1，函数内的 globvar 已经是全局变量


#列表反转函数:
def reverse(li):
	for i in range(0, len(li)/2):
		temp = li[i]
		li[i] = li[-i-1]
		li[-i-1] = temp

l = [1, 2, 3, 4, 5]
reverse(l)
print(l)


#列表反转函数二:
def reverse2(ListInput):
	RevList=[]
	for i in range (len(ListInput)):
		RevList.append(ListInput.pop())
	return RevList
l = [1, 2, 3, 4, 5]
l = reverse2(l)
print(l)


#简化列表反转：
def reverse3(li):
	for i in range(0, len(li)/2):
		li[i], li[-i - 1] = li[-i - 1], li[i]
l = [1, 2, 3, 4, 5]
reverse(l)
print(l)


#关于 return fun 和 return fun() 的区别：
def funx(x):
	def funy(y):
		return x * y
	return funy    #return funy返回的是一个对象，可理解为funx是funy的一个对象

print funx(7)(8)

def funx(x):
	def funy(y):
		return x * y
	return funy()    #return funy()返回的是funy的函数返回值，所以此处报错

#print funx(7)(8)	#报错，TypeError: funy() takes exactly 1 argument (0 given)


def funx(x):
	def funy(y):
		return x * y
	return funy(8)    

print funx(7)

def funx(x):
	def funy(y):
		return x * y
	return funy(y)    

#print funx(7)(7)		#报错NameError: global name 'y' is not defined




