#!/bin/bash  
# author:yangzl

var="http://www.runoob.com/linux/linux-shell-variable.html"

s1=${var%%t*}
s2=${var%t*}
s3=${var%%.*}
s4=${var#*/}
s5=${var##*/}
echo "关于字符串的截取%，#的使用方法"
echo "原字符串为："${var}
echo "%%t*的效果："${s1}
echo "%t*的效果："${s2}
echo "%%.*的效果："${s3}
echo "#*/的效果："${s4}
echo "##*/的效果："${s5}

