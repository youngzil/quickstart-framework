


---------------------------------------------------------------------------------------------------------------------
## xargs命令

xargs命令的作用，是将标准输入转为命令行参数。


$ echo "hello world" | xargs echo #输出hello world
上面的代码将管道左侧的标准输入，转为命令行参数hello world，传给第二个echo命令。


xargs命令的格式如下。
$ xargs [-options] [command]
真正执行的命令，紧跟在xargs后面，接受xargs传来的参数。


xargs的作用在于，大多数命令（比如rm、mkdir、ls）与管道一起使用时，都需要xargs将标准输入转为命令行参数。

$ echo "one two three" | xargs mkdir
上面的代码等同于mkdir one two three。如果不加xargs就会报错，提示mkdir缺少操作参数。


xargs后面的命令默认是echo。
$ xargs
等同于
$ xargs echo





[xargs 命令教程](https://www.ruanyifeng.com/blog/2019/08/xargs-tutorial.html)  


---------------------------------------------------------------------------------------------------------------------


## wc命令


wc 命令统计给定文件中的字节数、字数、行数。如果没有给出文件名，则从标准输入读取。wc同时也给出所有指定文件的总统计数。字是由空格字符区分开的最大字符串。

wc - l 统计行数
wc - w 统计字数
wc - c 统计字节数

这些选项可以组合使用。输出列的顺序和数目不受选项的顺序和数目的影响。


总是按下述顺序显示并且每项最多一列。
行数、字数、字节数、文件名

```aidl
 Controllers git:(master) ✗ wc -wcl PlazaController.php TestController.php
      65     139    1935 PlazaController.php
      30      51     572 TestController.php
      95     190    2507 total
```
注意：如果命令行中没有文件名，则输出中不出现文件名。





[Linux统计文件行数wc](https://www.jianshu.com/p/ef8e252914cb)



---------------------------------------------------------------------------------------------------------------------

## ll命令

1，按照时间升序
```aidl
命令:ls -ltr

详细解释:
-l     use a long listing format  以长列表方式显示（详细信息方式）
-t     sort by modification time 按修改时间排序（最新的在最前面）
-r     reverse order while sorting （反序）
```

2，按照时间降序（最新修改的排在前面）
```aidl
命令:ls -lt

详细解释:
-l     use a long listing format  以长列表方式显示（详细信息方式）
-t     sort by modification time 按修改时间排序（最新的在最前面）

```



[linux中文件夹的文件按照时间倒序或者升序排列](https://www.cnblogs.com/wangkongming/p/3994962.html)


---------------------------------------------------------------------------------------------------------------------


