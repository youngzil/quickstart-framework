#!/bin/bash  
# author:yangzl

#使用 . 号来引用test1.sh 文件
. ./included1.sh

# 或者使用以下包含文件代码
# source ./included1.sh

echo "百度官网地址：$url"



#注：被包含的文件 included1.sh 不需要可执行权限。


