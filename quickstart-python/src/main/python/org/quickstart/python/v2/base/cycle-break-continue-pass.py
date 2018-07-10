#!/usr/bin/python
# -*- coding: UTF-8 -*- 


for letter in 'Python':     # 第一个实例
	if letter == 'h':
		break
   print '当前字母 :', letter
  
var = 10                    # 第二个实例
while var > 0:              
	print '当前变量值 :', var
   var = var -1
   if var == 5:   # 当变量 var 等于 5 时退出循环
   	break
 
print "Good bye!"


for letter in 'Python':     # 第一个实例
	if letter == 'h':
		continue
   print '当前字母 :', letter
 
var = 10                    # 第二个实例
while var > 0:              
	var = var -1
   if var == 5:
   	continue
   print '当前变量值 :', var
print "Good bye!"


#continue 语句是一个删除的效果，他的存在是为了删除满足循环条件下的某些不需要的成分:
var = 10
while var > 0:
	var = var -1
    if var == 5 or var == 8:
    	continue
    print '当前值 :', var
print "Good bye!"


#我们想只打印0-10之间的奇数，可以用continue语句跳过某些循环：
n = 0
while n < 10:
	n = n + 1
    if n % 2 == 0:      # 如果n是偶数，执行continue语句
    	continue        # continue语句会直接继续下一轮循环，后续的print()语句不会执行
    print(n)


# 输出 Python 的每个字母
for letter in 'Python':
	if letter == 'h':
		pass
		print '这是 pass 块'
#	else:
	print '当前字母 :', letter
print "Good bye!"