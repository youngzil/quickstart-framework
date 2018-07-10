#!/bin/bash  
# author:yangzl


my_array=(A B "C" D)

echo "第一个元素为: ${my_array[0]}"
echo "第二个元素为: ${my_array[1]}"
echo "第三个元素为: ${my_array[2]}"
echo "第四个元素为: ${my_array[3]}"


my_array[0]=a
my_array[1]=b
my_array[2]=c
my_array[3]=d

echo "数组的元素为: ${my_array[*]}"
echo "数组的元素为: ${my_array[@]}"


echo "-- \$* 演示 ---"
for i in ${my_array[*]}; do
    echo $i
done

echo "-- \$@ 演示 ---"
for i in ${my_array[@]}; do
    echo $i
done

echo "获取数组长度的方法与获取字符串长度的方法相同"
echo "数组元素个数为: ${#my_array[*]}"
echo "数组元素个数为: ${#my_array[@]}"

echo "数组的值也可以写入变量"
A=1
my_array=($A B C D)
echo "第一个元素为: ${my_array[0]}"
echo "第二个元素为: ${my_array[1]}"
echo "第三个元素为: ${my_array[2]}"
echo "第四个元素为: ${my_array[3]}"

echo "根据数组元素索引获取该数组元素值时，数组下标可为变量"
arr=(a b c d)
i=2
echo ${arr[i]}


echo "遍历数组、变量自增几个写法"
my_arry=(a b "c","d" abc)
echo "-------FOR循环遍历输出数组--------"
for i in ${my_arry[@]};
do
  echo $i
done

echo "-------::::WHILE循环输出 使用 let i++ 自增:::::---------"
j=0
while [ $j -lt ${#my_arry[@]} ] #在就新手学习的时候注意一下 [ 和 $ 之间要有空格，没有空格运行会报错
do
  echo ${my_arry[$j]}
  let j++
done

echo "--------:::WHILE循环输出 使用 let  "n++ "自增: 多了双引号，其实不用也可以:::---------"
n=0
while [ $n -lt ${#my_arry[@]} ] #在就新手学习的时候注意一下 [ 和 $ 之间要有空格，没有空格运行会报错
do
  echo ${my_arry[$n]}
  let "n++"
done

echo "---------::::WHILE循环输出 使用 let m+=1 自增,这种写法其他编程中也常用::::----------"
m=0
while [ $m -lt ${#my_arry[@]} ] #在就新手学习的时候注意一下 [ 和 $ 之间要有空格，没有空格运行会报错
do
  echo ${my_arry[$m]}
  let m+=1
done

echo "-------::WHILE循环输出 使用 a=$[$a+1] 自增,个人觉得这种写法比较麻烦::::----------"
a=0
while [ $a -lt ${#my_arry[@]} ] #在就新手学习的时候注意一下 [ 和 $ 之间要有空格，没有空格运行会报错
do
 echo ${my_arry[$a]}
 a=$[$a+1]
done
