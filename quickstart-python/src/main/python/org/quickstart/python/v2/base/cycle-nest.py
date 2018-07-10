#!/usr/bin/python
# -*- coding: UTF-8 -*-

#for和while互相嵌套

i = 2
while(i < 100):
	j = 2
	while(j <= (i/j)):
		if not(i%j): break
		j = j + 1
	if (j > i/j) : print i, " 是素数"
	i = i + 1
print "Good bye!"


#使用循环嵌套来获取100以内的质数
num=[];
i=2
for i in range(2,100):
	j=2
   for j in range(2,i):
   	if(i%j==0):
   		break
   	else:
   		num.append(i)
print(num)

#使用嵌套循环实现×字塔的实现
#*字塔
i=1
#j=1
while i<=9:
	if i<=5:
		print ("*"*i)

	elif i<=9 :
		j=i-2*(i-5)
      print("*"*j)
   i+=1
else :
	print("")


#冒泡排序
array = [9,2,7,4,5,6,3,8,1,10]
L = len(array)
for i in range(L):
	for j in range(L-i):
		if array[L-j-1]<array[L-j-2]:
			array[L-j-1],array[L-j-2]=array[L-j-2],array[L-j-1]
for i in range(L):
	print array[i],


#求区间[a,b]内的质数

a = 1000 #起始
b = 10000 #结束

E = []
for num in range(a,b+1):
	snum = int(num*0.5+1)
   for i in range(2,snum): 
   	if num%i == 0: 
   		break 
   	else: 
   		E.append(num)
print a,'到',b,'的质数有',E
print a,'到',b,'有',len(E),'个质数' 




