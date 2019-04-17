#!/bin/bash和#!/bin/sh的区别，source命令和exec命令
https://blog.csdn.net/u014788227/article/details/78420762



总结：
1、#!/bin/bash和#!/bin/sh的区别：两种不同的shell script解释器
2、sh和source命令即点(.)命令区别：是否在子shell中执行，导致是否会在当前shell生效【当shell脚本具有可执行权限时，用sh filepath与./filepath是没有区别的，此时的"."是用来表示当前目录的】
3、exec与source/fork的差异：exec关闭当前shell进程，重新开一个shell进程（不是子shell），source是当前shell进程，fork是创建子进程的命令，原有进程会否终止，就是exec与source/fork的最大差异了。



#!/bin/sh是指此脚本使用/bin/sh来解释执行，#!是特殊的表示符，其后面根的是此解释此脚本的shell的路径。
其实第一句的#!是对脚本的解释器程序路径，脚本的内容是由解释器解释的，我们可以用各种各样的解释器来写对应的脚本。
比如说/bin/csh脚本，/bin/perl脚本，/bin/awk脚本，/bin/sed脚本，甚至/bin/echo等等。
#!/bin/bash同理。




Linux source命令：通常用法：source filepath 或 . filepath
功能：使当前shell读入路径为filepath的shell文件并依次执行文件中的所有语句，通常用于重新执行刚修改的初始化文件，使之立即生效，而不必注销并重新登录。例如，当我们修改了/etc/profile文件，并想让它立刻生效，而不用重新登录，就可以使用source命令，如source /etc/profile。



source filepath 与 sh filepath 、./filepath的区别：
    当shell脚本具有可执行权限时，用sh filepath与./filepath是没有区别的。./filepath是因为当前目录没有在PATH中，所有"."是用来表示当前目录的。
    sh filepath 会重新建立一个子shell，在子shell中执行脚本里面的语句，该子shell继承父shell的环境变量，但子shell是新建的，其改变的变量不会被带回父shell，除非使用export。
    source filename其实只是简单地读取脚本里面的语句依次在当前shell里面执行，没有建立新的子shell。那么脚本里面所有新建、改变变量的语句都会保存在当前shell里面。




exec和source都属于bash内部命令(builtins commands)，在bash下输入man exec或man source可以查看所有的内部命令信息。
　　bash shell的命令分为两类：外部命令和内部命令。外部命令是通过系统调用或独立的程序实现的，如sed、awk等等。内部命令是由特殊的文件格式(.def)所实现，如cd、history、exec等等。


fork是linux的系统调用，用来创建子进程(child process)。子进程是父进程(parent process)的一个副本，从父进程那里获得一定的资源分配以及继承父进程的环境。子进程与父进程唯一不同的地方在于pid(process id)。
环境变量(传给子进程的变量，遗传性是本地变量和环境变量的根本区别)只能单向从父进程传给子进程。不管子进程的环境变量如何变化，都不会影响父进程的环境变量。



shell script:
　　有两种方法执行shell scripts，一种是新产生一个shell，然后执行相应的shell scripts;一种是在当前shell下执行，不再启用其他shell。
　　新产生一个shell然后再执行scripts的方法是在scripts文件开头加入以下语句#!/bin/sh
    一般的script文件(.sh)即是这种用法。这种方法先启用新的sub-shell(新的子进程),然后在其下执行命令。
　　另外一种方法就是上面说过的source命令，不再产生新的shell，而在当前shell下执行一切命令。source:source命令即点(.)命令。
    source命令是在当前进程中执行参数文件中的各个命令，而不是另起子进程(或sub-shell)。



exec命令在执行时会把当前的shell process关闭，然后换到后面的命令继续执行。
系统调用exec是以新的进程去代替原来的进程，但进程的PID保持不变。因此，可以这样认为，exec系统调用并没有创建新的进程，只是替换了原来进程上下文的内容。原进程的代码段，数据段，堆栈段被新的进程所代替。


也就是简而言之：原有进程会否终止，就是exec与source/fork的最大差异了。


source命令即点(.)命令。在bash下输入man source，找到source命令解释处，可以看到解释"Read and execute commands from filename in the current shell environment and ..."。从中可以知道，source命令是在当前进程中执行参数文件中的各个命令，而不是另起子进程(或sub-shell)。

exec:
在bash下输入man exec，找到exec命令解释处，可以看到有"No new process is created."这样的解释，这就是说exec命令不产生新的子进程。那么exec与source的区别是什么呢？
exec命令在执行时会把当前的shell process关闭，然后换到后面的命令继续执行。




一个进程主要包括以下几个方面的内容:
　　(1)一个可以执行的程序
　　(2) 与进程相关联的全部数据(包括变量，内存，缓冲区)
　　(3)程序上下文(程序计数器PC,保存程序执行的位置)






