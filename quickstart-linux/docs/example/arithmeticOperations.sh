#!/bin/bash
# author:yangzl


#((i=$j+$k))    等价于 i=`expr $j + $k`
#((i=$j-$k))     等价于   i=`expr $j -$k`
#((i=$j*$k))     等价于   i=`expr $j \*$k`
#((i=$j/$k))     等价于   i=`expr $j /$k`


# $(())、expr、let，只有expr方式的乘法需要加上\

# 三、使用(( ... )) 的形式
echo $((8+4))
echo $((8-4))
echo $((8*4))
echo $((8/4))

sum=$((3+5))           # sum <- 8
sum=$(( 3 - 5 ))       # sum <- -2
sum=$(( 3 * 5 ))       # sum <- 15
sum=$(( 7 / 5 ))       # sum <- 1
sum=$(( 7 % 5 ))       # sum <- 2
sum=$(( (1 - 2 )  * 4 ))   # sum <- -4


# 二、使用expr命令
echo `expr 6 + 5`
echo `expr 6 - 5`
echo `expr 6 \* 5`
echo `expr 6 / 5`

sum=`expr 2 - 5`     # sum <- -3
sum=`expr 2 + 5`     # sum <- 7
sum=`expr 3 \* 5`    # sum <- 15
sum=`expr 3 / 5`     # sum <- 0
sum=`expr 7 / 5`     # sum <- 1
sum=`expr \( 2 - 3 \) \* 6`   # sum <- -6
sum=`expr 2+4`       # sum <- 2+4
sum=`expr 2-4*6·     # sum <- 2-4*6
sum=`expr 1-(5-8)`   # sum <- 1-(5-8)


#一、使用let命令
let "sum=3+5"
echo "let方式计算："$sum

let "sum=3-5"
echo "let方式计算："$sum

let "sum=3*5"
echo "let方式计算："$sum

let "sum=20/4"
echo "let方式计算："$sum

let "sum=3+5"    # sum <- 8
let "sum=3*5"    # sum <- 15
let "sum=2/5"    # sum <- 0
let "sum=11/5"   # sum <- 2
let "sum=11%5"   # sum <- 1
let "sum=-6-9"   # sum <- -15
let "sum=(-6-9)*5"  # sum <- -75


#参考
#[shell运算(数字[加减乘除，比较大小]，字符串，文件)](https://blog.csdn.net/shimazhuge/article/details/38703523)