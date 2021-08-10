[Scala2官网](http://www.scala-lang.org/)  
[Scala2 Github](https://github.com/scala/scala)  
[Scala2官网下载](https://www.scala-lang.org/download/scala2.html)


Java 和 Kotlin、Groovy、Scala的代码和相互调用
/Users/yangzl/git/quickstart-framework/quickstart-kotlin





---------------------------------------------------------------------------------------------------------------------

## Scala安装

### Scala IDEA配置

1. 下载Scala解压或者安（Windows）
2. IDEA选择"File"------》"Project Structure"------》SDKs 添加Scala SDK，选择Scala的路径即可

在IDEA中直接搜索插件安装，这个应该是最新版本的，可能有时候跟开源代码使用的不一致，导致其他问题


[Scala开发环境的搭建教程-使用IntelliJ Idea](https://www.renfei.net/posts/1003294)  


### Eclipse scala插件安装

[Scala IDE For Eclipse](http://scala-ide.org/)

Eclipse scala插件安装
打开eclipse，并点击"Help"选择其中的"Eclipse Marketplace"，并输入scala，而后点击搜索
点击"Scala IDE 4.7.x"相对应的"Install"按钮，进行安装。




### 安装Maven-Scala插件

mvn scala:compile

有两种方法：
一是从http://alchim31.free.fr/m2e-scala/update-site/下载，
二是通过Eclipse配置安装。

安装m2e-scala
http://alchim31.free.fr/m2e-scala/update-site/

通过Eclipse配置安装。
第一步：添加远程的原型或模板目录：http://repo1.maven.org/maven2/archetype-catalog.xml
第二步：新建Archetype，因为maven默认没有Group Id: net.alchim31.maven Artifact Id: scala-archetype-simple Version:1.6

创建maven项目适合选择Select an Archetype时候选择scala-archetype-simple类型


Depend on Scala modules like a pro
https://github.com/scala/scala-module-dependency-sample

The scala-maven-plugin (previously maven-scala-plugin) is used for compiling/testing/running/documenting scala code in maven. http://davidb.github.io/scala-maven-plugin/
https://github.com/davidB/scala-maven-plugin




---------------------------------------------------------------------------------------------------------------------





