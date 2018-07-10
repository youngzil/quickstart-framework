#!/usr/bin/python
# -*- coding: utf-8 -*-


dict = {'Alice': '2341', 'Beth': '9102', 'Cecil': '3258'}
print dict

dict1 = { 'abc': 456 };
dict2 = { 'abc': 123, 98.6: 37 };
print dict1
print dict2

dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};
print "dict['Name']: ", dict['Name'];
print "dict['Age']: ", dict['Age'];


dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};
#print "dict['Alice']: ", dict['Alice'];	#用字典里没有的键访问数据，会输出错误KeyError: 'Alice'


#向字典添加新内容的方法是增加新的键/值对，修改或删除已有键/值对如下实例:
print "dictionary update add deleete"
dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};

dict['Age'] = 8; # update existing entry
dict['School'] = "DPS School"; # Add new entry

print "dict['Age']: ", dict['Age'];
print "dict['School']: ", dict['School'];

#能删单一的元素也能清空字典，清空只需一项操作。
#显示删除一个字典用del命令，如下实例：

dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};

del dict['Name']; # 删除键是'Name'的条目
dict.clear();     # 清空词典所有条目
del dict ;        # 删除词典

#print "dict['Age']: ", dict['Age'];	#但这会引发一个异常，因为用del后字典不再存在：TypeError: 'type' object has no attribute '__getitem__'
#print "dict['School']: ", dict['School'];


#不允许同一个键出现两次。创建时如果同一个键被赋值两次，后一个值会被记住，
dict = {'Name': 'Zara', 'Age': 7, 'Name': 'Manni'};
print "dict['Name']: ", dict['Name'];

#2）键必须不可变，所以可以用数字，字符串或元组充当，所以用列表就不行，如下实例：
#dict = {['Name']: 'Zara', 'Age': 7};
#print "dict['Name']: ", dict['Name'];	#报错：TypeError: unhashable type: 'list'


#字典值可以是任意数值类型：
dict1 = {"a":[1,2]}      # 值为列表
print dict1["a"][1]

dict2 = {"a":{"c":"d"}}   # 值为字典
print dict2["a"]["c"]


#Python 中的字典相当于 C++ 或者 Java 等高级编程语言中的容器 Map，每一项都是由 Key 和 Value 键值对构成的，
#当我们去访问时，根据关键字就能找到对应的值。
#另外就是字典和列表、元组在构建上有所不同。列表是方括号 []，元组是圆括号 ()，字典是花括号 {}。

users = {
'A':{
'first':'yu',
'last':'lei',
'location':'hs',
},
'B':{
'first':'liu',
'last':'wei',
'location':'hs',    
},
}
# username,userinfo 相当于 key,value
for username,userinfo in users.items():
	print("username:"+username)
	print("userinfo"+str(userinfo))
	fullname=userinfo['first']+" "+userinfo['last']
	print("fullname:"+fullname)
	print("location:"+userinfo['location'])


