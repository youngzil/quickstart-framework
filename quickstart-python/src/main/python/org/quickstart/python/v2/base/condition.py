#!/usr/bin/python
# -*- coding: UTF-8 -*-

# 例1：if 基本用法

flag = False
name = 'luren'
if name == 'python':      # 判断变量否为'python'
	flag = True          # 条件成立时设置标志为真
	print 'welcome boss'    # 并输出欢迎信息
else:
	print name              # 条件不成立时输出变量名称


# 例2：elif用法
num = 5     
if num == 3:            # 判断num的值
	print 'boss'        
elif num == 2:
	print 'user'
elif num == 1:
	print 'worker'
elif num < 0:           # 值小于零时输出
	print 'error'
else:
	print 'roadman'     # 条件均不成立时输出

# 例3：if语句多个条件
# 当if有多个条件时可使用括号来区分判断的先后顺序，括号中的判断优先执行，
# 此外 and 和 or 的优先级低于>（大于）、<（小于）等判断符号，即大于和小于在没有括号的情况下会比与或要优先判断。
num = 9
if num >= 0 and num <= 10:    # 判断值是否在0~10之间
	print 'hello'
# 输出结果: hello
 
num = 10
if num < 0 or num > 10:    # 判断值是否在小于0或大于10
	print 'hello'
else:
	print 'undefine'
# 输出结果: undefine
 
num = 8
# 判断值是否在0~5或者10~15之间
if (num >= 0 and num <= 5) or (num >= 10 and num <= 15):    
	print 'hello'
else:
	print 'undefine'
# 输出结果: undefine

var = 100 
if ( var  == 100 ) : print "变量 var 的值为100" 
print "Good bye!"

#python 复合布尔表达式计算采用短路规则，即如果通过前面的部分已经计算出整个表达式的值，则后面的部分不再计算。如下面的代码将正常执行不会报除零错误：
a=0
b=1
if ( a > 0 ) and ( b / a > 2 ):
	print "yes"
else :
	print "no"


# 一个简单的条件循环语句实现汉诺塔问题
"""
def my_print(args):
	print args

def move(n, a, b, c):
	my_print ((a, '-->', c))
	if n==1
		(move(n-1,a,c,b) or move(1,a,b,c) or move(n-1,b,a,c))
	else 
move (3, 'a', 'b', 'c')
"""







































