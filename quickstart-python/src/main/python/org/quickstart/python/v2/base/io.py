#!/usr/bin/python
# -*- coding: utf-8 -*-
#coding=utf-8
'''
Created on 2017年5月6日

@author: yangzl
'''

print "Python 是一个非常棒的语言，不是吗？";

#str = raw_input("raw_input请输入：");#请输入：Hello Python！
#print "你输入的内容是: ", str

#str = input("input请输入：");#请输入：[x*5 for x in range(2,10,2)]
#print "你输入的内容是: ", str


# 打开一个文件
fo = open("io.txt", "wb")
print "文件名: ", fo.name
print "是否已关闭 : ", fo.closed
print "访问模式 : ", fo.mode
print "末尾是否强制加空格 : ", fo.softspace

fo.write( "www.runoob.com!\nVery good site!\n");

# 关闭打开的文件
fo.close()

fo = open("io.txt", "r+")
str = fo.read(10);
print "读取的字符串是 : ", str

# 查找当前位置
position = fo.tell();
print "当前文件位置 : ", position

# 把指针再次重新定位到文件开头
position = fo.seek(0, 0);
str = fo.read(10);
print "重新读取字符串 : ", str

# 关闭打开的文件
fo.close()

#在 write 内容后，直接 read 文件输出会为空，是因为指针已经在内容末尾。
#两种解决方式: 其一，先 close 文件，open 后再读取，其二，可以设置指针回到文件最初后再 read

import os;

document = open("io.txt", "w+");
print "文件名: ", document.name;
document.write("这是我创建的第一个测试文件！\nwelcome!");
#输出当前指针位置
print "after write tell=:" ,document.tell();
#设置指针回到文件最初
document.seek(os.SEEK_SET);
print "after reset tell=:" ,document.tell();

context = document.read();
print context;
document.close();

import os
# 重命名文件test1.txt到test2.txt。
os.rename( "io.txt", "iotest.txt" )

# 删除一个已经存在的文件test2.txt
os.remove("iotest.txt")

# 创建目录test
os.rmdir('test')#删除目录
os.mkdir("test")#假如目录已经存在，OSError: [Errno 17] File exists: 'test'

# 将当前目录改为"/home/newdir"
print os.getcwd() # 得到当前目录   
#os.chdir(os.getcwd() + "/newdir")#切换当前目录，绝对路径
os.chdir("newdir")#切换当前目录，相对路径
print os.getcwd() # 得到当前目录   



