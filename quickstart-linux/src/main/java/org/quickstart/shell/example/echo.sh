#!/bin/bash  
# author:yangzl

#1.显示普通字符串:
echo "It is a test"
#这里的双引号完全可以省略，以下命令与上面实例效果一致：
echo It is a test



#2.显示转义字符
echo "\"It is a test\""
#同样，双引号也可以省略
echo \"It is a test\"


#3.显示变量
#read 命令从标准输入中读取一行,并把输入行的每个字段的值指定给 shell 变量
read name 
echo "$name It is a test"


#4.显示换行
echo -e "OK! \n" # -e 开启转义
echo "It it a test"


#5.显示不换行
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"


#6.显示结果定向至文件
echo "It is a test" > myfile


#7.原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'


#8.显示命令执行结果，注意： 这里使用的是反引号 `, 而不是单引号 '。
echo `date`


