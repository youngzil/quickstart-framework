#!/usr/bin/python
# -*- coding: utf-8 -*-

list1 = ['physics', 'chemistry', 1997, 2000];
list2 = [1, 2, 3, 4, 5, 6, 7 ];

print "list1[0]: ", list1[0]
print "list2[1:5]: ", list2[1:5]


list = ['physics', 'chemistry', 1997, 2000];

print "Value available at index 2 : "
print list[2];
list[2] = 2001;
print "New value available at index 2 : "
print list[2];


list1 = ['physics', 'chemistry', 1997, 2000];

print list1;
del list1[2];
print "After deleting value at index 2 : "
print list1;
print list1[2];


L = ['Google', 'Runoob', 'Taobao']
print L[2]
print L[0]
print L[-1]	#从右往左下标是从-1开始
print L[-2]
print L[-3]
print L[1:]


print "二维数组"
#python 创建二维列表，将需要的参数写入 cols 和 rows 即可
#list_2d = [[0 for col in range(cols)] for row in range(rows)]

list_2d = [ [0 for i in range(5)] for i in range(5)]
list_2d[0].append(3)
list_2d[0].append(5)
list_2d[2].append(7)
list_2d
print list_2d


list01 = ['runoob', 786, 2.23, 'john', 70.2]
list02 = [123, 'john']

print list01
print list02

# 列表截取
print "列表截取"
print list01[0]
print list01[-1]
print list01[0:3]

# 列表重复
print "列表重复"
print list01 * 2

# 列表组合
print "列表组合"
print list01 + list02

# 获取列表长度
print "获取列表长度"
print len(list01)

# 删除列表元素
del list02[0]
print list02

# 元素是否存在于列表中
print 'john' in list02  # True

# 迭代
for i in list01:
	print i

# 比较两个列表的元素
#list01 = ['runoob', 786, 2.23, 'john', 70.2]
#list02 = [123, 'john']
print cmp(list01, list02)

list1, list2 = [123, 'xyz'], [456, 'abc']

print cmp(list1, list2);
print cmp(list2, list1);
list3 = list2 + [786];
print cmp(list2, list3)

# 列表最大/最小值
print max([0, 1, 2, 3, 4])
print min([0, 1])

# 将元组转换为列表
aTuple = (1,2,3,4)
#list03 = list(aTuple) #报错，不知道为什么：TypeError: 'list' object is not callable
#print list03		#报错，NameError: name 'list03' is not defined

# 在列表末尾添加新的元素
list03 = [1,2,3,4]
list03.append(5)
print list03

# 在列表末尾一次性追加另一个序列中的多个值（用新列表扩展原来的列表）
print "list01=",list01
print "list03=",list03
list03.extend(list01)
print list03

# 统计某个元素在列表中出现的次数
print list03.count(1)

# 从列表中找出某个值第一个匹配项的索引位置
print list03.index('john')

# 将对象插入列表
list03.insert(0, 'hello')
print list03

# 移除列表中的一个元素（默认最后一个元素），并且返回该元素的值
print list03.pop(0)
print list03

# 移除列表中某个值的第一个匹配项
list03.remove(1)
print list03

# 反向列表中元素

list03.reverse()
print list03

# 对原列表进行排序

list03.sort()
print list03

list4=[123,["das","aaa"],234]
print list4
print "aaa" in list4                  #in只能判断一个层次的元素
print "aaa" in list4[1]           #选中列表中的列表进行判断
print list4[1][1]
