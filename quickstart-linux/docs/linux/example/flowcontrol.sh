#!/bin/bash  
# author:yangzl


echo "if-elif-else语句："
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi


#if else语句经常与test命令结合使用，如下所示：
num1=$[2*3]
num2=$[1+5]
if test $[num1] -eq $[num2]
then
    echo '两个数字相等!'
else
    echo '两个数字不相等!'
fi


echo -e "\n\nfor循环："

for loop in 1 2 3 4 5
do
    echo "The value is: $loop"
done

#顺序输出字符串中的字符：
for str in 'This is a string'
do
    echo $str
done


echo -e "\n\nwhile循环："

#以下是一个基本的while循环，测试条件是：如果int小于等于5，那么条件返回真。int从0开始，每次循环处理时，int加1。运行上述脚本，返回数字1到5，然后终止。
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done
#使用中使用了 Bash let 命令，它用于执行一个或多个表达式，变量计算中不需要加上 $ 来表示变量，具体可查阅：Bash let 命令

#while循环可用于读取键盘信息。下面的例子中，输入信息被设置为变量FILM，按<Ctrl-D>结束循环。

echo '按下 <CTRL-D> 退出（整个shell退出） 或者 输入break'
echo -n '输入你最喜欢的网站名: '
while read FILM
do
	if test "break" = $FILM
	then
		break
	fi
    echo "是的！$FILM 是一个好网站"
done

echo -e "\n\n无限循环："
<<"comment"
无限循环
无限循环语法格式：
while :
do
    command
done
或者
while true
do
    command
done
或者
for (( ; ; ))
comment


echo -e "\n\nuntil 循环："




echo -e "\n\ncase多选择语句："

echo '输入 1 到 4 之间的数字:'
echo '你输入的数字为:'
read aNum
case $aNum in
    1)  echo '你选择了 1'
    ;;
    2)  echo '你选择了 2'
    ;;
    3)  echo '你选择了 3'
    ;;
    4)  echo '你选择了 4'
    ;;
    *)  echo '你没有输入 1 到 4 之间的数字'
    ;;
esac

echo -e "\n\nbreak命令："
while :
do
    echo -n "输入 1 到 5 之间的数字:"
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
            break
        ;;
    esac
done


echo -e "\n\ncontinue命令："

#运行代码发现，当输入大于5的数字时，该例中的循环不会结束，语句 echo "Game is over!" 永远不会被执行。

while :
do
    echo -n "输入 1 到 5 之间的数字: "
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的!"
            continue
            echo "游戏结束"
        ;;
    esac
done



