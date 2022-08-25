- [文件处理](#文件处理)
  - [将文件中的多行转为一行](#将文件中的多行转为一行)
  - [将文件中的一行awk变多行](#将文件中的一行awk变多行)
  - [文件中的多行去重](#文件中的多行去重)
  - [替换字符串](#替换字符串)




# 文件处理


## 将文件中的多行转为一行


[Shell中将多行合并成一行的小技巧](https://blog.csdn.net/Jerry_1126/article/details/82262634)  
[linux shell 将多行文件转换为一行](https://blog.csdn.net/hjxhjh/article/details/17264739)  
[将文件中的多行转为一行（Linux、Windows）](https://www.jianshu.com/p/636224273805)  
[将多行输入转换成单行输出](https://blog.51cto.com/fei007/834894)  





## 将文件中的一行awk变多行


```
分隔后逐个写入文件中，每个占一行
cat test.txt | awk -F ';' '{for(i=1;i<=NF;i++){print $i;}}' > test2.txt

分隔后逐个写入文件中，不换行，每行都是重复的，不好
cat test.txt | awk -F ';' '{for(i=1;i<=NF;i++){to=to"||"$i};print to}' > test3.txt

分隔后逐个写入文件中并去重，每个占一行
cat test.txt | awk -F ';' '{for(i=1;i<=NF;i++){print $i;}}' | awk '!a[$0]++' > test4.txt

```

[AWK 使用循环取出所有的字段](https://blog.csdn.net/weihongrao/article/details/12851771)  
[awk 一行变多行实践](https://blog.csdn.net/weixin_35834894/article/details/109396339)  
[Linux命令去重统计排序（awk命令去重，sort, uniq命令去重统计）](https://blog.csdn.net/feng973/article/details/73849586)  





## 文件中的多行去重

```
awk '!a[$0]++' filename
```


文件去重统计
sort test2.txt | uniq -c > test5.txt

文件去重
sort test2.txt | uniq > test6.txt

awk先默认空格分隔，再=分隔，再:分隔，再去重，统计、排序
cat ip.log| awk '{print $2}' | awk -F '=' '{print $2}' | awk -F ':' '{print $1}' |sort|uniq -c | sort -nr



```
(1)排序
由于uniq命令只能对相邻行进行去重复操作，所以在进行去重前，先要对文本行进行排序，使重复行集中到一起。
$ sort test.txt

(2)去掉相邻的重复行
$ sort test.txt | uniq

2、文本行去重并按重复次数排序
(1)首先，对文本行进行去重并统计重复次数(uniq命令加-c选项可以实现对重复次数进行统计。)。
$ sort test.txt | uniq -c

(2)对文本行按重复次数进行排序。
sort -n可以识别每行开头的数字，并按其大小对文本行进行排序。默认是按升序排列，如果想要按降序要加-r选项(sort -rn)。
$ sort test.txt | uniq -c | sort -rn

(3)每行前面的删除重复次数。
cut命令可以按列操作文本行。可以看出前面的重复次数占8个字符，因此，可以用命令cut -c 9- 取出每行第9个及其以后的字符。
$ sort test.txt | uniq -c | sort -rn | cut -c 9-
```

[Linux命令去重统计排序（awk命令去重，sort, uniq命令去重统计）](https://blog.csdn.net/feng973/article/details/73849586)





## 替换字符串

替换字符串，tr只能支持单个字符

另一个方式是变相的，awk在打印的时候拼接上其他的字符串

替换最后一个‘\n’ 为‘|’行间以|来分隔
cat data | tr '\n' '|'

换行：print
cat test6.txt | awk '{print "'\''"$0 "'\'',"}'

不换行：printf
cat test6.txt | awk '{printf "'\''"$0 "'\'',"}'



