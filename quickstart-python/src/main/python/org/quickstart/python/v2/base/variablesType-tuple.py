#!/usr/bin/python
# -*- coding: utf-8 -*-

tup1 = ('physics', 'chemistry', 1997, 2000);
tup2 = (1, 2, 3, 4, 5 );
tup3 = "a", "b", "c", "d";
print tup1
print tup2
print tup3


#创建空元组
tup1 = ();
print tup1;
#元组中只包含一个元素时，需要在元素后面添加逗号
tup1 = (50,);
print tup1;

#元组可以使用下标索引来访问元组中的值，
tup1 = ('physics', 'chemistry', 1997, 2000);
tup2 = (1, 2, 3, 4, 5, 6, 7 );

print "tup1[0]: ", tup1[0]
print "tup2[1:5]: ", tup2[1:5]


#元组中的元素值是不允许修改的，但我们可以对元组进行连接组合，
tup1 = (12, 34.56);
tup2 = ('abc', 'xyz');

# 以下修改元组元素操作是非法的。
# tup1[0] = 100;

# 创建一个新的元组
tup3 = tup1 + tup2;
print tup3;


#元组中的元素值是不允许删除的，但我们可以使用del语句来删除整个元组，如下实例:
tup = ('physics', 'chemistry', 1997, 2000);
print tup;
del tup;
print "After deleting tup : "
#print tup;	#NameError: name 'tup' is not defined


#无关闭分隔符
#任意无符号的对象，以逗号隔开，默认为元组，如下实例：
print 'abc', -4.24e93, 18+6.6j, 'xyz';
x, y = 1, 2;
print "Value of x , y : ", x,y;


#因为括号()既可以表示tuple，又可以表示数学公式中的小括号。
#所以，如果元组只有1个元素，就必须加一个逗号，防止被当作括号运算：
tup1 = ("all")
print tup1	#输出字符串 all

tup1 = ("all",)
print tup1	#输出tuple




