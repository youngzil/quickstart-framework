[Lua官网](https://www.lua.org/)  
[Lua下载](https://www.lua.org/download.html)  
[Lua Github](https://github.com/lua/lua)  



##Lua环境安装

Linux 系统上安装
curl -R -O http://www.lua.org/ftp/lua-5.3.5.tar.gz
tar zxf lua-5.3.5.tar.gz
cd lua-5.3.5
make linux test
make install


Mac OS X 系统上安装
curl -R -O http://www.lua.org/ftp/lua-5.3.5.tar.gz
tar zxf lua-5.3.5.tar.gz
cd lua-5.3.5
make macosx test
make install

或者
brew update
brew install lua


lua -v

Lua 交互式编程模式可以通过命令 lua -i 或 lua 来启用：


给lua脚本文件添加可执行权限
./HelloWorld.lua



### IDEA安装Lua插件
IntelliJ IDEA+EmmyLua插件
https://emmylua.github.io/zh_CN/installation/repo.html
https://blog.csdn.net/libaineu2004/article/details/80268178
https://www.jianshu.com/p/b70d41a2c2b7




参考  
[Lua 环境安装](https://www.runoob.com/lua/lua-environment.html)


