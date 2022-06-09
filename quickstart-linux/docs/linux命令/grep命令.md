
grep命令是一种强大的文本搜索工具，它能使用正则表达式搜索文本，并把匹配的 行打印出来。grep全称是Global Regular Expression Print，表示全局正则表达 式版本，它的使用权限是所有用户。





递归搜索，搜索当前目录和子目录
grep -r "hello world" *.txt

显示行号
grep -n "hello world" *.txt

匹配时忽略字母的大小写
grep -i "hello world" *.txt

只列出含有匹配的文本行的文件的文件名，而不显示具体的匹配内容
grep -l "hello world" *.txt




[Linux grep 命令](https://www.runoob.com/linux/linux-comm-grep.html)  
[grep命令](http://intetnet.github.io/html/grep.1.html)



grep: Gloabal Search Regular Expression and Print out the line，意为全局搜索正则表达式并打印文本行。所以

grep是一个强大的文本搜索工具
grep与正则表达式联系紧密

grep命令的基本语法如下：
grep [options] pattern [file...]

[options]表示选项，具体的命令选项见下表。
pattern表示要匹配的模式（包括目标字符串、变量或者正则表达式），
file表示要查询的文件名，可以是一个或者多个。pattern后面所有的字符串参数都会被理解为文件名。


-c  只打印匹配的文本行的行数，不显示匹配的内容
-i  匹配时忽略字母的大小写
-h  当搜索多个文件时，不显示匹配文件名前缀
-n  列出所有的匹配的文本行，并显示行号
-l  只列出含有匹配的文本行的文件的文件名，而不显示具体的匹配内容
-s  不显示关于不存在或者无法读取文件的错误信息
-v  只显示不匹配的文本行
-w  匹配整个单词
-x  匹配整个文本行
-r  递归搜索，搜索当前目录和子目录
-q  禁止输出任何匹配结果，而是以退出码的形式表示搜索是否成功，其中0表示找到了匹配的文本行
-b  打印匹配的文本行到文件头的偏移量，以字节为单位
-E  支持扩展正则表达式
-P  支持Perl正则表达式
-F  不支持正则表达式，将模式按照字面意思匹配

-A N 或 --after-context=N
除了列出符合行之外，并且列出后N行

-B N 或 --before-context=N
除了列出符合行之外，并且列出前N行

-C N 或 --context[=N]
列出符合行之外并列出上下各N行，默认值是两行



-A -B -C 后面都跟阿拉伯数字
-A是显示匹配后和它后面的n行。
-B是显示匹配行和它前面的n行。
-C是匹配行和它前后各n行。
总体来说，-C覆盖面最大。用它保险些。哈哈。这3个开关都是关于匹配行的上下文的（context）。

于是
  grep -A 4 wikipedia 密码文件.txt 
就是搜索密码文件，找到匹配“wikipedia”字串的行，显示该行后后面紧跟的4行。




grep 命令支持正则表达式匹配模式。要使用多单词搜索，请使用如下语法：
grep 'word1\|word2\|word3' /path/to/file
grep 'warning\|error\|critical' /var/log/messages

仅仅只是要匹配单词（即该词两侧是单词分界符，针对西方以空格分隔的语言而言）的话，可以加上 -w 选项参数：
$ grep -w 'warning\|error\|critical' /var/log/messages

egrep 命令可以跳过上面的语法格式，其使用的语法格式如下：
$ egrep -w 'warning|error|critical' /var/log/messages

我建义您们加上 -i (忽略大小写) 和 --color 选项参数，如下示：
$ egrep -wi --color 'warning|error|critical' /var/log/messages

多文件查询，file之间用空格隔开
grep -i "hello world" test1.txt test2.txt


多模式匹配，模式之间为“逻辑或”的关系，匹配任意一个
#方法1：使用-e选项
grep -e "hello world" -e "mailx" -r /home/tyrone

#方法2：使用正则表达式，-E
grep -E "hello world|mailx" -r /home/tyrone

#方法3：使用正则表达式，egrep，同grep -E等效
egrep "hello world|mailx" -r /home/tyrone

（3）多模式匹配，模式之间为“逻辑与”的关系，匹配所有模式。
这个问题我查阅了很多方法，并逐一试验了一下。基本思想大致相同：先匹配一个模式，然后grep下面一个模式，将前一次grep的结果作为要查询文件路径依次向后传递。
注意：前一次grep的结果必须加上-l选项，否则会把匹配成功的文件内容作为要查询的文件名向后传递。
我希望做到的是能够显示出同时包含模式的文件，并且跟随显示匹配每个模式的内容。可惜我现在找到的方法都仅仅能够显示匹配最后一个模式的内容。

#方法1：比较笨，将其按一次的结果重定向到一个文件，然后xargs grep来依次读取。
grep -i "hello world" -rl /home/tyrone >> reslut.txt
cat result.txt | xargs grep -i "mailx" 

#输出结果
/home/tyrone/test1.txt:mailx

#方法2：直接利用管道
grep -i "hello world" -rl /home/tyrone | xargs grep -i "mailx"

#输出结果
/home/tyrone/test1.txt:mailx

#方法3：使用find，适合于需要灵活判断条件的场景。例如查找指定路径下，同时匹配多个模式的txt文件。
#注意：本例中“mailx”后面的命令必须加上反引号 ` ，否则会被当作要查询的文件名。
grep -i "mailx" `find /home/tyrone -type f -name "*.txt" -exec grep -l "hello world"  {} \;`

#输出结果
/home/tyrone/test1.txt:mailx

#方法4:同3
find /home/tyrone -name "*.txt" -exec grep -l "hello world" {} \; | xargs grep -i "mailx"

#输出结果
/home/tyrone/test1.txt:mailx

查找指定用户的进程
ps是查看当前进程的指令，e表示所有进程，f表示全格式。

ps -ef | grep "tyrone"


现在要搞一个脚本把前面总结的命令一锅炖了。先搜索同时匹配多个模式的文件，把它们先备份之后，替换目标字符串：
#----------------------/home/tyrone/myshell.ksh---------------------
#! /bin/ksh

grep "hello world" -rl /home/tyrone | xargs grep -l "mailx" > /home/tyrone/result5.txt
cat result5.txt | while read line
do
    cp ${line} ${line}.bak20150616
    sed -i "s/hello world/letitia/g" `grep "hello world" -rl ${line}`
done

#输出结果：只有test1.txt满足条件，将"hello world"替换成了"letitia"
#哦对了。。还有我的shell文件也同时包含了这两个模式，可以通过find限定文件后缀，不表
#----------------------/home/tyrone/text1.txt---------------------
letitia
mailx
uuen
letitia
#----------------------/home/tyrone/text2.txt---------------------
hello world,this is for grep test 
#----------------------/home/tyrone/text3.txt---------------------
hello world
tyrone
#----------------/home/tyrone/text1.txt.bak20150616---------------
hello world
mailx
uuen
letitia

作者：tyrone_li
链接：https://www.jianshu.com/p/652b4975b242
来源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。



