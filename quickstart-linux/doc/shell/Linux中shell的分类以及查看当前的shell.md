1、Shell分类：bash、csh、tcsh、ksh、zsh
2、Linux中查看可以使用的shell
3、Linux中查看当前使用的shell
4、linux中shell的切换


---------------------------------------------------------------------------------------------------------------------
DOS/Windows 与 Linux 的最重要的区别之一是 Linux 的命令 shell 是与操作系统相分离的一层。



Shell分类

目前流行的shell有ash、bash、ksh、csh、zsh，如下介绍：

bash：bash shell 是 Bourne shell 的一个免费版本，它是最早的 Unix shell，也是很多linux版本默认的shell。

csh ：C shell 使用的是“类C”语法，借鉴了 Bourne shell 的许多特点，它由以William Joy为代表的共计47位作者编成，共有52个内部命令。该shell其实是指向/bin/tcsh这样的一个shell，也就是说，csh其实就是tcsh。

ksh：Korn shell 的语法与 Bourne shell 相同，同时具备了 C shell 的易用特点。由Eric Gisin编写，共有42条内部命令。该shell最大的优点是几乎和商业发行版的ksh完全兼容，这样就可以在不用花钱购买商业版本的情况下尝试商业版本的性能了。

zsh ：Z shell 是 Korn shell 的一个增强版本，是Linux最大的shell之一，由Paul Falstad完成，共有84个内部命令。如果只是一般的用途，是没有必要安装这样的shell

ash：ash shell是由Kenneth Almquist编写的，Linux中占用系统资源最少的一个小shell，它只包含24个内部命令，因而使用起来很不方便。

tcsh ：TC shell 是 C shell 的一个增强版本，与 C shell 完全兼容。




Linux中查看可以使用的shell

查看/etc/shells文件可得到系统中可用的shell的完整路径

例如： more  /etc/shells

或者使用 ： chsh  -l    （在我的ubuntu中是无法显示的，man帮助信息中是可以查到这个命令的）




Linux中查看当前使用的shell

方法一：echo  $SHELL

方法二：输入系统不认识的命令，获取提示（在某些shell中没有这样的提示）

方法三：一般来讲/etc/passwd文件中最后一项是每个用户使用的shell,但不见得每个都是shell，可以通过以下命令来查看：

tail  5  /etc/passwd

方法四：使用命令echo $0  (在bash中和ksh中验证过)

方法五：通过ps命令查看（建议使用，使用于多个linux系统）



linux中shell的切换

usermod -s /bin/csh aiesb
chsh -s /bin/csh

可以直接输入shell的名字如，我要启动sh

直接输入：sh 或者 /bin/sh  这样有启动了一个Shell，这个Shell在最初登录的那个Shell之后，称为下级的Shell或子Shell。使用命令：exit就可以退出这个子shell。

linux中修改默认shell

输入 chsh 命令，会提示输入密码：



输入你要改变shell的绝对路径名，注销后重新登录即可改变默认shell。

或者直接使用 chsh -s /bin/dash

这样下次我们启动系统的时候，通过ps命令查看，默认shell已经改变了。


--------------------- 
版权声明：本文为CSDN博主「远洪」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/lyhDream/article/details/17100505


