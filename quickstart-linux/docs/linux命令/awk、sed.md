



---------------------------------------------------------------------------------------------------------------------


grep 更适合单纯的查找或匹配文本
sed 更适合编辑匹配到的文本
awk 更适合格式化文本，对文本进行较复杂格式处理


---------------------------------------------------------------------------------------------------------------------

sed -i "s/待匹配的串/新的串/g" /app/config/application-test.yml



awk是行处理器: 相比较屏幕处理的优点，在处理庞大文件时不会出现内存溢出或是处理缓慢的问题，通常用来格式化文本信息
awk处理过程: 依次对每一行进行处理，然后输出
esbdir=`pwd|awk -F'/' '{print $4}'`
pwd作为输入，-F是以什么为分隔符，然后获取第5个参数，$0 是全行数据，$1 是分割后的第1个数据


---------------------------------------------------------------------------------------------------------------------

## awk详解

awk默认以空白字符为分隔符对每一行进行分割
awk的内置变量
awk的内置函数
awk允许指定输出条件，只输出符合条件的行
awk提供了if结构，用于编写复杂的条件。




### awk默认以空白字符为分隔符对每一行进行分割

输入分割符，默认是空白字符(即空格)，awk默认以空白字符为分隔符对每一行进行分割。
awk有哪些分隔符，awk的默认分割符是空格，但是，这样描述并不精确，因为，awk的分隔符还分为两种，”输入分隔符” 和 “输出分隔符” 。

输入分隔符，英文原文为field separator，此处简称为FS  
输入分割符，默认是空白字符(即空格)，awk默认以空白字符为分隔符对每一行进行分割。  

输出分割符，英文原文为output field separator，此处简称为OFS  
awk将每行分割后，输出在屏幕上的时候，以什么字符作为分隔符，awk默认的输出分割符也是空格。  

awk ‘{print $1 $2}’ 表示每行分割后，将第一列（第一个字段）和第二列（第二个字段）连接在一起输出。  
awk ‘{print $1,$2}’ 表示每行分割后，将第一列（第一个字段）和第二列（第二个字段）以输出分隔符隔开后显示。  



### awk的内置变量

awk -F ':' '{print $1}' /etc/passwd
awk '{print $0}' demo.txt
awk -F ':' '{ print $1 }' demo.txt

echo 'this is a test' | awk '{print $NF}'
awk -F ':' '{print $1, $(NF-1)}' demo.txt
变量NF表示当前行有多少个字段，因此$NF就代表最后一个字段。
$(NF-1)代表倒数第二个字段。

awk -F ':' '{print NR ") " $1}' demo.txt
变量NR表示当前处理的是第几行。
上面代码中，print命令里面，如果原样输出字符，要放在双引号里面。


awk的其他内置变量如下。
- FILENAME：当前文件名
- FS：字段分隔符，默认是空格和制表符。
- RS：行分隔符，用于分割每一行，默认是换行符。
- OFS：输出字段的分隔符，用于打印时分隔字段，默认为空格。
- ORS：输出记录的分隔符，用于打印时分隔记录，默认为换行符。
- OFMT：数字输出的格式，默认为％.6g。



### awk的内置函数

awk -F ':' '{ print toupper($1) }' demo.txt

其他常用函数如下。
- toupper()用于将字符转为大写
- tolower()：字符转为小写。
- length()：返回字符串长度。
- substr()：返回子字符串。
- sin()：正弦。
- cos()：余弦。
- sqrt()：平方根。
- rand()：随机数。



### awk允许指定输出条件，只输出符合条件的行

awk -F ':' '/usr/ {print $1}' demo.txt
print命令前面是一个正则表达式，只输出包含usr的行

awk -F ':' 'NR % 2 == 1 {print $1}' demo.txt
输出奇数行

awk -F ':' 'NR >3 {print $1}' demo.txt
输出第三行以后的行


下面的例子输出第一个字段等于指定值的行。
awk -F ':' '$1 == "root" {print $1}' demo.txt
awk -F ':' '$1 == "root" || $1 == "bin" {print $1}' demo.txt




### awk提供了if结构，用于编写复杂的条件。


awk -F ':' '{if ($1 > "m") print $1}' demo.txt
上面代码输出第一个字段的第一个字符大于m的行。

$ awk -F ':' '{if ($1 > "m") print $1; else print "---"}' demo.txt
if结构还可以指定else部分。



#sort默认的排序方式是升序，如果想改成降序，就加个-r。
netstat -anp |grep 6383 | awk '{print $5}' | awk  -F ':'  '{print $1}' | sort | uniq -c | sort

netstat -anp |grep 6383 | awk '{print $5}' | awk  -F ':'  '{print $1}'  | uniq -c | sort


意思是提取,分隔的第一列第二列
awk -F ',,' '{print $1$2}'>res.txt


打印分隔的第一列第二列，并用|分隔
awk -F ',,' '{print $1"|"$2}' res.txt>res.txt

cat res.txt | awk -F ',,' '{print $1"|"$2}' >res.txt


cat gatewaytest-service-qa-0f5659cecfdb.txt|grep TotalTime|awk -F ' - ' '{print $2}'>res.txt
cat gatewaytest-service-qa-0f5659cecfdb.txt|grep TotalTime|awk -F ' - ' '{print $2}'|awk -F ',' '{print $1","$2","$3}' >res.txt

cat gatewaytest-service-qa-0f5659cecfdb.txt 读取文件内容
grep TotalTime 筛选有TotalTime的行
awk -F ' - ' '{print $2}' 按照-分隔，提取第二列
awk -F ',' '{print $1","$2","$3}'  按照,分隔，提取123列
>res.txt 重定向到文件res.txt中




[awk 入门教程](https://www.ruanyifeng.com/blog/2018/11/awk.html)  
[awk 处理文本](https://einverne.github.io/post/2018/01/awk.html)  
[linux awk命令详解](https://www.cnblogs.com/ggjucheng/archive/2013/01/13/2858470.html)  
[linux awk命令统计排名单词出现次数](https://love61v.github.io/2017/07/12/awk%E7%BB%9F%E8%AE%A1%E6%8E%92%E5%90%8D%E5%8D%95%E8%AF%8D%E5%87%BA%E7%8E%B0%E6%AC%A1%E6%95%B0/)  
[awk统计命令(求和、求平均、求最大值、求最小值)](https://blog.csdn.net/csCrazybing/article/details/52594989)  

[awk从放弃到入门（2）：awk分隔符](https://www.zsythink.net/archives/1357)  
[awk的默认字段分隔符](https://codingdict.com/questions/44167)  
[]()  


---------------------------------------------------------------------------------------------------------------------







