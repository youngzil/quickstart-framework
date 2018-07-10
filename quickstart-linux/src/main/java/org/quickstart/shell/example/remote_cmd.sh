#!/bin/bash  
# author:yangzl

#ssh的-t参数：中文翻译一下：就是可以提供一个远程服务器的虚拟tty终端，加上这个参数我们就可以在远程服务器的虚拟终端上输入自己的提权密码了，非常安全 
#命令格式：ssh -t -p $port $user@$ip  'cmd'  
#这个方法还是很方便的，-t虚拟出一个远程服务器的终端，在多台服务器同时部署时确实节约了不少时间啊！

#变量定义  
ip_array=("192.168.1.1" "192.168.1.2" "192.168.1.3")  
user="test1"  
remote_cmd="/home/test/1.sh"  

#本地通过ssh执行远程服务器的脚本  
for ip in ${ip_array[*]}  
do  
    if [ $ip = "192.168.1.1" ]; then  
        port="7777"  
    else  
        port="22"  
    fi  
    ssh -t -p $port $user@$ip "remote_cmd"  
done  
