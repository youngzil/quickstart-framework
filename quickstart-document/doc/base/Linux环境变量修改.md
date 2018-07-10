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
其中，/etc/profile是全局的环境变量，对所有用户生效，而.bash_profile只对当前用户启作用。
上面操作完成后需要注销Linux才能使刚才的环境变量设置生效。



注意：
2、linux下用冒号”:”来分隔路径
3. $PATH / $CLASSPATH / $JAVA_HOME 等是用来引用原来的环境变量的值在设置环境变量时特别要注意不能把原来的值给覆盖掉了。
4. CLASSPATH中当前目录”.”不能丢掉。
5. export是把这三个变量导出为全局变量。
6. 大小写必须严格区分。


