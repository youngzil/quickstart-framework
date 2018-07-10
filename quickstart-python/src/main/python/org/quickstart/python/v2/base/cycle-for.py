#!/usr/bin/python
# -*- coding: UTF-8 -*-

for letter in 'Python':     # 第一个实例
	print '当前字母 :', letter
 
fruits = ['banana', 'apple',  'mango']
for fruit in fruits:        # 第二个实例
	print '当前水果 :', fruit
 
print "Good bye!"

#内置函数 len() 和 range(),函数 len() 返回列表的长度，即元素的个数。 range返回一个序列的数。
fruits = ['banana', 'apple',  'mango']
for index in range(len(fruits)):
	print '当前水果 :', fruits[index]
 
print "Good bye!"


for num in range(10,20):  # 迭代 10 到 20 之间的数字
	for i in range(2,num): # 根据因子迭代
		if num%i == 0:      # 确定第一个因子
		 j=num/i          # 计算第二个因子
         print '%d 等于 %d * %d' % (num,i,j)
         break            # 跳出当前循环
	else:                  # 循环的 else 部分
		print num, '是一个质数'


#使用内置 enumerate 函数进行遍历
sequence = [12, 34, 34, 23, 45, 76, 89]
for i, j in enumerate(sequence):
	print i,j

#使用list.append()模块对质数进行输出。
# 输出 2 到 100 简的质数
prime = []
for num in range(2,100):  # 迭代 2 到 100 之间的数字
	for i in range(2,num): # 根据因子迭代
		if num%i == 0:      # 确定第一个因子
			break            # 跳出当前循环
		else:                  # 循环的 else 部分
			prime.append(num)
print prime


# 打印空心等边三角形 
rows = int(raw_input('输入行数：'))
for i in range(0, rows):
	for k in range(0, 2 * rows - 1):
		if (i != rows - 1) and (k == rows - i - 1 or k == rows + i - 1):
			print " * ",
		elif i == rows - 1:
			if k % 2 == 0:
				print " * ",
			else:
				print "   ",
			else:
				print "   ",
    print "\n"


#打印1-9三角形阵列:
	for i in range(1,11):
		for k in range(1,i):
			print k,
        k +=1
    i +=1
    print "\n"

'''在python中，for循环后的in跟随一个序列的话，循环每次使用的序列元素，而不是序列
的下标'''
s = 'qazxswedcvfr'
for i in range(0,len(s),2):
	print s[i]
'''enumerate() :
    在每次循环中，可以同时得到下标和元素
    际上，enumerate(),在每次循环中返回的是包含每个元素的定值表，两个元素分别赋值
 index，char'''
	for (index,char) in enumerate(s):
		print "index=%s ,char=%s" % (index,char)


# 冒泡排序# 定义列表 list
arays = [1,8,2,6,3,9,4]
for i in range(len(arays)):
	for j in range(i+1):
		if arays[i] < arays[j]:
			# 实现连个变量的互换
			arays[i],arays[j] = arays[j],arays[i]
print arays

#更多实例：python 打印菱形、三角形、矩形的代码感觉，写的有点复杂了，如果让你画圆或者其他图形呢？
#其实运用数学公式，就可以了。比如菱形 |x - w/2| + |y - w/2| = w/2 轻松搞定。
width = int(raw_input('输入对角线长度： '))
for row in range(width + 1):
	for col in range(width + 1):
		if ((abs(row - width/2) + abs(col - width/2)) == width/2):
			print "*",
		else:
			print " ",
    print " "

#python 打印菱形、三角形、矩形
rows = int(raw_input('输入列数： '))
i = j = k = 1 #声明变量，i用于控制外层循环（图形行数），j用于控制空格的个数，k用于控制*的个数
#等腰直角三角形1
print "等腰直角三角形1"
for i in range(0, rows):
	for k in range(0, rows - i):
		print " * ", #注意这里的","，一定不能省略，可以起到不换行的作用
        k += 1
    i += 1
    print "\n"
 
#打印实心等边三角形
print "打印空心等边三角形，这里去掉if-else条件判断就是实心的"
for i in range(0, rows + 1):#变量i控制行数
	for j in range(0, rows - i):#(1,rows-i)
		print " ",
        j += 1
    for k in range(0, 2 * i - 1):#(1,2*i)
    	if k == 0 or k == 2 * i - 2 or i == rows:
    		if i == rows:
    			if k % 2 == 0:#因为第一个数是从0开始的，所以要是偶数打印*，奇数打印空格
    				print "*",
    			else:
    				print " ", #注意这里的","，一定不能省略，可以起到不换行的作用
    			else:
    				print "*",
    			else:
    				print " ",
        k += 1
    print "\n"
    i += 1
 
#打印菱形
print "打印空心等菱形，这里去掉if-else条件判断就是实心的"
for i in range(rows):#变量i控制行数
	for j in range(rows - i):#(1,rows-i)
		print " ",
        j += 1
    for k in range(2 * i - 1):#(1,2*i)
    	if k == 0 or k == 2 * i - 2:
    		print "*",
    	else:
    		print " ",
        k += 1
    print "\n"
    i += 1
    #菱形的下半部分
for i in range(rows):
	for j in range(i):#(1,rows-i)
		print " ",
        j += 1
    for k in range(2 * (rows - i) - 1):#(1,2*i)
    	if k == 0 or k == 2 * (rows - i) - 2:
    		print "*",
    	else:
    		print " ",
        k += 1
    print "\n"
    i += 1
#实心正方形
print "实心正方形"
for i in range(0, rows):
	for k in range(0, rows):
		print " * ", #注意这里的","，一定不能省略，可以起到不换行的作用
        k += 1
    i += 1
    print "\n"
 
#空心正方形
print "空心正方形"
for i in range(0, rows):
	for k in range(0, rows):
		if i != 0 and i != rows - 1:
			if k == 0 or k == rows - 1:
				#由于视觉效果看起来更像正方形，所以这里*两侧加了空格，增大距离
				print " * ", #注意这里的","，一定不能省略，可以起到不换行的作用
			else:
				print "   ", #该处有三个空格
			else:
				print " * ", #这里*两侧加了空格
        k += 1
    i += 1
    print "\n"



