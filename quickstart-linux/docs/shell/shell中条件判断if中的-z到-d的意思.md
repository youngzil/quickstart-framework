shell中条件判断if中的-z到-d的意思 
[ -a FILE ] 如果 FILE 存在则为真。 
[ -b FILE ] 如果 FILE 存在且是一个块特殊文件则为真。
[ -c FILE ] 如果 FILE 存在且是一个字特殊文件则为真。 
[ -d FILE ] 如果 FILE 存在且是一个目录则为真。 
[ -e FILE ] 如果 FILE 存在则为真。
[ -f FILE ] 如果 FILE 存在且是一个普通文件则为真。 
[ -g FILE ] 如果 FILE 存在且已经设置了SGID则为真。 
[ -h FILE ] 如果 FILE 存在且是一个符号连接则为真。 
[ -k FILE ] 如果 FILE 存在且已经设置了粘制位则为真。 
[ -p FILE ] 如果 FILE 存在且是一个名字管道(F如果O)则为真。 
[ -r FILE ] 如果 FILE 存在且是可读的则为真。 
[ -s FILE ] 如果 FILE 存在且大小不为0则为真。  
[ -t FD ] 如果文件描述符 FD 打开且指向一个终端则为真。 
[ -u FILE ] 如果 FILE 存在且设置了SUID (set user ID)则为真。 
[ -w FILE ] 如果 FILE 如果 FILE 存在且是可写的则为真。 
[ -x FILE ] 如果 FILE 存在且是可执行的则为真。 
[ -O FILE ] 如果 FILE 存在且属有效用户ID则为真。 
[ -G FILE ] 如果 FILE 存在且属有效用户组则为真。 
[ -L FILE ] 如果 FILE 存在且是一个符号连接则为真。  
[ -N FILE ] 如果 FILE 存在 and has been mod如果ied since it was last read则为真。
[ -S FILE ] 如果 FILE 存在且是一个套接字则为真。  
[ FILE1 -nt FILE2 ] 如果 FILE1 has been changed more recently than FILE2,or 如果 FILE1 exists and FILE2 does not则为真。  
[ FILE1 -ot FILE2 ] 如果 FILE1 比 FILE2 要老, 或者 FILE2 存在且 FILE1 不存在则为真。  
[ FILE1 -ef FILE2 ] 如果 FILE1 和 FILE2 指向相同的设备和节点号则为真。 
[ -o OPTIONNAME ] 如果 shell选项 “OPTIONNAME” 开启则为真。 
[ -z STRING ] “STRING” 的长度为零则为真。  
[ -n STRING ] or [ STRING ] “STRING” 的长度为非零 non-zero则为真。 

[ ARG1 OP ARG2 ] “OP” is one of -eq, -ne, -lt, -le, -gt or -ge. These arithmetic binary operators return true if “ARG1” is equal to, not equal to, less than, less than or equal to, greater than, or greater than or equal to “ARG2”, respectively. “ARG1” and “ARG2” are integers. 


数字判断 
[ $count -gt "1"] 如果$count 大于1 为真
-gt  大于
-lt    小于
-ne  不等于
-eq  等于
-ge  大于等于
-le  小于等于

[ STRING1 == STRING2 ] 如果2个字符串相同。 “=” may be used instead of “==” for strict POSIX compliance则为真。  
[ STRING1 != STRING2 ] 如果字符串不相等则为真。  
[ STRING1 < STRING2 ] 如果 “STRING1” sorts before “STRING2” lexicographically in the current locale则为真。  
[ STRING1 > STRING2 ] 如果 “STRING1” sorts after “STRING2” lexicographically in the current locale则为真。  


在 Linux bash shell 中，可以使用 [[ 命令来进行判断。
其中，可以使用 [[ 命令的 =～ 操作符来判断某个字符串是否包含特定模式。

注意：只有 [[ 命令支持 =~ 操作符，test 命令和 [ 命令都不支持 =~ 操作符。






-b file             #判断文件是否为块设备文件
-c file             #判断文件是否为字符设备文件
-d file             #判断文件是否为目录
-e file             #判断文件是否存在
-f file             #判断文件是否为普通文件
-g file             #判断文件是否设置了SGID
-h file             #判断文件是否为符号链接
-p file             #判断文件是否为命名管道文件
-r file             #判断文件是否可读
-s file             #判断文件是否存在且内容不为空(也可以是目录)
-t fd               #判断文件描述符fd是否开启且指向终端
-u file             #判断文件是否设置SUID
-w file             #判断文件是否可写
-x file             #判断文件是否可执行
-S file             #判断文件是否为socket文件
file1 -nt file2     #判断文件file1是否比file2更新(根据mtime)，或者判断file1存在但file2不存在
file1 -ot file2     #判断文件file1是否比file2更旧，或者判断file2存在但file1不存在
file1 -ef file2     #判断文件file1和file2是否互为硬链接
-v name             #判断变量状态是否为set(见上一篇)
-z string           #判断字符串是否为空
-n string           #判断字符串是否非空
string1 == string2  #判断字符串是否相等
string1 = string2   #判断字符串是否相等
string1 != string2  #判断字符串是否不相等
string1 < string2   #判断字符串string1是否小于字符串string2(字典排序)，用于内置命令test中时，小于号需要转义：\<
string1 > string2   #判断字符串string1是否大于字符串string2(字典排序)，用于内置命令test中时，大于号需要转义：\>
NUM1 -eq NUM2       #判断数字是否相等
NUM1 -ne NUM2       #判断数字是否不相等
NUM1 -lt NUM2       #判断数字NUM1是否小于数字NUM2
NUM1 -le NUM2       #判断数字NUM1是否小于等于数字NUM2
NUM1 -gt NUM2       #判断数字NUM1是否大于数字NUM2
NUM1 -ge NUM2       #判断数字NUM1是否大于等于数字NUM2

! expr    #表示对表达式expr取反
( expr )  #表示提高expr的优先级
expr1 -a expr2  #表示对两个表达式进行逻辑与操作，只能用于 [ expr ] 和 test expr 中
expr1 && expr2  #表示对两个表达式进行逻辑与操作，只能用于 [[ expr ]] 中
expr1 -o expr2  #表示对两个表达式进行逻辑或操作，只能用于 [ expr ] 和 test expr 中
expr1 || expr2  #表示对两个表达式进行逻辑或操作，只能用于 [[ expr ]] 中




[SHELL(bash)脚本编程二：语法](https://segmentfault.com/a/1190000008080537)
[Bash技巧：使用命令的 =~ 操作符判断字符串的包含关系](https://segmentfault.com/a/1190000022102207)  
[24_Shell语言————if条件判断之字符测试 原创](https://blog.51cto.com/wuyelan/1530270)  




