- [Java介绍和配置](#Java介绍和配置)
- [Linux下配置jdk环境变量的三种方法](#Linux下配置jdk环境变量的三种方法)
- [Mac下配置jdk环境变量的方法](#Mac下配置jdk环境变量的方法)



---------------------------------------------------------------------------------------------------------------------
## Java介绍和配置


https://java.com/zh_CN/


export JAVA_HOME=/root/jdk1.8.0_281
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH


su - aimsgtest
vi .bashrc  
输入$符号跳转到行尾

vi .bashrc
source .bash_profile

vi .bash_profile
source .bash_profile

scp jdk-8u221-linux-x64.tar.gz aifgw@20.26.85.230:~


export JAVA_HOME=/home/aimsgtest/jdk1.7.0_79
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH


操作系统级别
vim /etc/profile 添加
source /etc/profile

用户级别：ll -a 查看全部文件和目录
修改其个人用户主目录下的.bashrc文件
source /home/msgtest/.bashrc

shell级别：
直接执行export命令

java -version




---------------------------------------------------------------------------------------------------------------------

## Linux下配置jdk环境变量的三种方法

一、修改/etc/profile文件当本机仅仅作为开发使用时推荐使用这种方法，因为此种配置时所有用户的shell都有权使用这些环境变量，可能会给系统带来安全性问题。用文本编辑器打开/etc/profile，在profile文件末尾加入：
export JAVA_HOME=/home/msgtest/jdk1.8.0_152
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$JAVA_HOME/bin:$PATH
重新登录即可。



二、修改.bashrc文件这种方法更为安全，它可以把使用这些环境变量的权限控制到用户级别，如果需要给某个用户权限使用这些环境变量，只需要修改其个人用户主目录下的.bashrc文件就可以了。用文本编辑器打开用户目录下的.bashrc文件，在.bashrc文件末尾加入：

set JAVA_HOME=/usr/share/jdk1.5.0_05
export JAVA_HOME
set PATH=$JAVA_HOME/bin:$PATH
export PATH
set CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export CLASSPATH
重新登录。


三、直接在shell下设置变量不推荐使用这种方法，因为换个shell，该设置就无效了。这种方法仅仅是临时使用，以后要使用的时候又要重新设置，比较麻烦。只需在shell终端执行下列命令：

export JAVA_HOME=/usr/share/jdk1.5.0_05
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


注意：1.要将 /usr/share/jdk1.5.0_05jdk 改为jdk安装目录
2. linux下用冒号”:”来分隔路径
3. $PATH / $CLASSPATH / $JAVA_HOME 是用来引用原来的环境变量的值在设置环境变量时特别要注意不能把原来的值给覆盖掉了。
4. CLASSPATH中当前目录”.”不能丢掉。
5. export是把这三个变量导出为全局变量。
6. 大小写必须严格区分。







---------------------------------------------------------------------------------------------------------------------
## Mac下配置jdk环境变量的方法

#JDK下载：http://www.oracle.com/technetwork/java/javase/archive-139210.html
#指定默认版本: java -version
export JAVA_HOME=`/usr/libexec/java_home`    
export JAVA_HOME=$(/usr/libexec/java_home)    
#指定自定义版本,两种方式都可以
#export JAVA_HOME=`/usr/libexec/java_home -d 64 -v 1.6`    
#export JAVA_HOME=$(/usr/libexec/java_home -d 64 -v 1.6)
#export JAVA_HOME=`/usr/libexec/java_home -d 64 -v 1.7`    
#export JAVA_HOME=$(/usr/libexec/java_home -d 64 -v 1.7)
export JAVA_HOME=`/usr/libexec/java_home -d 64 -v 1.8`
export JAVA_HOME=$(/usr/libexec/java_home -d 64 -v 1.8)
#export JAVA_HOME=`/usr/libexec/java_home -d 64 -v 9`    
#export JAVA_HOME=$(/usr/libexec/java_home -d 64 -v 9)
#export JAVA_HOME=`/usr/libexec/java_home -d 64 -v 10`    
#export JAVA_HOME=$(/usr/libexec/java_home -d 64 -v 10)

#下面方式不生效，不知道为什么
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home
#export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-9.0.1.jdk/Contents/Home
#export PATH=$JAVA_HOME/bin:$PATH
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#export PATH=.:$PATH:$JAVA_HOME/bin
#export CLASSPATH=.:$JAVA_HOME/lib

---------------------------------------------------------------------------------------------------------------------

