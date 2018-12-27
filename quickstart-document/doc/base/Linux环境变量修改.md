操作系统级别
vim /etc/profile 添加
source /etc/profile

用户级别：ll -a 查看全部文件和目录
修改其个人用户主目录下的.bashrc文件
source /home/msgframe/.bashrc

shell级别：
直接执行export命令



临时性的：
打开Linux终端命令窗口，输入如下命令：
export PATH=$PATH:/usr/local/MATLAB/R2013b/bin
为了验证是否修改成功可以再继续输入命令eport进行查看。在下图中可以看到成功添加了matlab的环境变量。
上面修改后如果退出终端并重新打开再次查看环境变量时就会发现上面添加的环境变量又消失了。即这样只做到了临时性修改环境变量。

如果想要环境变量永久生效可以修改下面两个文件中的任何一个：
1 /etc/profile 
2 .bash_profile
其中，/etc/profile是全局的环境变量，对所有用户生效，而.bash_profile只对当前用户起作用。
上面操作完成后需要注销Linux才能使刚才的环境变量设置生效。



注意：
2、linux下用冒号”:”来分隔路径
3. $PATH / $CLASSPATH / $JAVA_HOME 等是用来引用原来的环境变量的值在设置环境变量时特别要注意不能把原来的值给覆盖掉了。
4. CLASSPATH中当前目录”.”不能丢掉。
5. export是把这三个变量导出为全局变量。
6. 大小写必须严格区分。




总的来说profile是在登录后执行一次
bashrc每次打开新的shell时,该文件被读取


============
/etc/profile
============
此文件为系统的每个用户设置环境信息,当用户第一次登录时,该文件被执行.并从/etc/profile.d目录的配置文件中搜集shell的设置.

===========
/etc/bashrc
===========
为每一个运行bash shell的用户执行此文件.当bash shell被打开时,该文件被读取.

===============
~/.bash_profile
===============
每个用户都可使用该文件输入专用于自己使用的shell信息,当用户登录时,该文件仅仅执行一次!默认情况下,他设置一些环境变量,执行用户的.bashrc文件.

=========
~/.bashrc
=========
该文件包含专用于你的bash shell的bash信息,当登录时以及每次打开新的shell时,该文件被读取.

==========
~/.profile
==========
在Debian中使用.profile文件代 替.bash_profile文件
.profile(由Bourne Shell和Korn Shell使用)和.login(由C Shell使用)两个文件是.bash_profile的同义词，目的是为了兼容其它Shell。在Debian中使用.profile文件代 替.bash_profile文件。

==============
~/.bash_logout
==============当每次退出系统(退出bash shell)时,执行该文件. 
--------------------- 
作者：JeanCheng 
来源：CSDN 
原文：https://blog.csdn.net/gatieme/article/details/45064705 
版权声明：本文为博主原创文章，转载请附上博文链接！




