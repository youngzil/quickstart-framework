1.下载lombok.jar包https://projectlombok.org/download.html
2.运行Lombok.jar
下载Lombok.jar最新版，双击或java -jar Lombok.jar启动安装界面，选择eclipse的安装路径，mac选择的不是eclipse.app路径，而是Eclipse.app/Contents/MacOS/eclipse，这里和Windows选择的exe文件不同。
3.确认完eclipse的安装路径后，点击install/update按钮，即可安装完成
4.安装完成之后，请确认eclipse安装路径下是否多了一个lombok.jar包，并且其
配置文件eclipse.ini中是否 添加了如下内容: </code>
    -javaagent:lombok.jar 
    -Xbootclasspath/a:lombok.jar 
那么恭喜你已经安装成功，否则将缺少的部分添加到相应的位置即可 </code>
5.重启eclipse或myeclipse

或者

直接把下载的lombok.jar拷贝至eclipse安装目录下，eclipse.ini所在目录下，并在eclipse.ini文件最后加上如下
-javaagent:/Applications/Eclipse.app/Contents/Eclipse/lombok.jar
找到Eclipse应用（在Mac上就是Eclipse.app文件），选择打开包内容，找到eclipse.ini
重启eclipse

在Maven中加入引用：
<dependency>
<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>
<version>1.16.20</version>
<scope>provided</scope>
</dependency>
这样就可以在程序中使用了，非常方便。

主页是：http://www.projectlombok.org/
详细使用说明文档：http://jnb.ociweb.com/jnb/jnbJan2010.html
从此告别了大量写get、set方法，虽然eclipse可以自动生成，但是大量的冗余代码使得程序可读性下降。






