#!/usr/bin/python
# -*- coding: UTF-8 -*-

import re

print(re.match('www', 'www.runoob.com').span())  # 在起始位置匹配
print(re.match('com', 'www.runoob.com'))  # 不在起始位置匹配

line = "Cats are smarter than dogs"
matchObj = re.match(r'(.*) are (.*?) .*', line, re.M | re.I)
if matchObj:
    print "matchObj.group() : ", matchObj.group()
    print "matchObj.group(1) : ", matchObj.group(1)
    print "matchObj.group(2) : ", matchObj.group(2)
else:
    print "No match!!"

print(re.search('www', 'www.runoob.com').span())  # 在起始位置匹配
print(re.search('com', 'www.runoob.com').span())  # 不在起始位置匹配

line = "Cats are smarter than dogs";
searchObj = re.search(r'(.*) are (.*?) .*', line, re.M | re.I)
if searchObj:
    print "searchObj.group() : ", searchObj.group()
    print "searchObj.group(1) : ", searchObj.group(1)
    print "searchObj.group(2) : ", searchObj.group(2)
else:
    print "Nothing found!!"

    '''
    正则表达式：
    r'(.*) are (.*?) .*'
    解析:
line首先，这是一个字符串，前面的一个 r 表示字符串为非转义的原始字符串，让编译器忽略反斜杠，也就是忽略转义字符。但是这个字符串里没有反斜杠，所以这个 r 可有可无。 = "Cats are smarter than dogs";
matchObj = re.match(r'dogs', line, re.M | re.I)
if m (.*) 第一个匹配分组，.* 代表匹配除换行符之外的所有字符。atchObj:
     (.*?) 第二个匹配分组，.*? 后面多个问号，代表非贪婪模式，也就是说只匹配符合条件的最少字符print "match --> matchObj.group() : ", matchObj.group()
else 后面的一个 .* 没有括号包围，所以不是分组，匹配效果和第一个一样，但是不计入匹配结果中。:
    matchObj.group() 等同于 matchObj.group(0)，表示匹配到的完整文本字符print "No match!!"
    
matcmatchObj.group(1) 得到第一组匹配结果，也就是(.*)匹配到的hObj = re.search(r'dogs', line, re.M | re.I)
if matchObj:
    matchObj.group(2) 得到第二组匹配结果，也就是(.*?)匹配到的print "search --> matchObj.group() : ", matchObj.group()
else:
    因为只有匹配结果中只有两组，所以如果填 3 时会报错。print "No match!!"
    '''

phone = "2004-959-559 # 这是一个国外电话号码"
# 删除字符串中的 Python注释
num = re.sub(r'#.*$', "", phone)
print "电话号码是: ", num


# repl 参数是一个函数
# 以下实例中将字符串中的匹配的数字乘于 2：

# 将匹配的数字乘于 2
def double(matched):
    value = int(matched.group('value'))
    return str(value * 2)


s = 'A23G4HFD567'
print(re.sub('(?P<value>\d+)', double, s))
