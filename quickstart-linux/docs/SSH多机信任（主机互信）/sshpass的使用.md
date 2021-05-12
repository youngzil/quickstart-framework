SSH免密码 或者 通过sshpass直接在参数中加入ssh的登录密码


ssh登录时如何直接在参数中加入登录密码

sshpass -p my_password ssh m_username@hostname
sshpass -p [passwd] ssh root@192.168.X.X -p [port]


1. 安装命令，对于Mac，需要安装xcode和命令行工具，然后使用Homewbrew命令：

brew install https://raw.githubusercontent.com/kadwanev/bigboybrew/master/Library/Formula/sshpass.rb

安装不了就把上面链接的内容保存到本地文件sshpass.rb
然后brew install sshpass.rb



2. 连接到/usr/local/bin (如果提示 command not found)
   cd /usr/local/bin
   ln -s ../Cellar/sshpass/1.05/bin/sshpass sshpass



[macOS 安装 sshpass](https://wsgzao.github.io/post/sshpass/)

[ssh登录时在参数中加入密码的解决方案](https://www.cnblogs.com/senlinyang/p/7833249.html)  
[ssh登录时如何直接在参数中加入登录密码](https://www.cnblogs.com/linxiong945/p/4226211.html)

[iTerm2快速SSH连接并保存密码](https://juejin.cn/post/6844903842660745224)  
[iterm2保存ssh连接信息](https://www.dyxmq.cn/other/save-ssh-profile-in-iterm2.html)  



