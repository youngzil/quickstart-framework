查看防火墙状态
systemctl status firewalld

打开防火墙
systemctl start firewalld

关闭防火墙
systemctl stop firewalld


查看防火墙某个端口是否开放
firewall-cmd --query-port=80/tcp
firewall-cmd --query-port=41363/tcp
firewall-cmd --query-port=26908/tcp

开放防火墙端口80：要重新加载下防火墙
firewall-cmd --zone=public --add-port=80/tcp --permanent
firewall-cmd --zone=public --add-port=9092/tcp --permanent

关闭80端口
firewall-cmd --zone=public --remove-port=80/tcp --permanent  

配置立即生效
firewall-cmd --reload 

开放一段端口
firewall-cmd --zone=public --add-port=8121-8124/tcp --permanent

查看开放的端口列表
firewall-cmd --zone=public --list-ports




参考
https://blog.csdn.net/u013514928/article/details/80411110
https://blog.csdn.net/qq_32656561/article/details/105619911



