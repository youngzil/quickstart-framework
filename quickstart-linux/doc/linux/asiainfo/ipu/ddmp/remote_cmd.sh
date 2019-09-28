
#!/bin/bash

#ssh的-t参数：中文翻译一下：就是可以提供一个远程服务器的虚拟tty终端，加上这个参数我们就可以在远程服务器的虚拟终端上输入自己的提权密码了，非常安全
#命令格式：ssh -t -p $port $user@$ip 'cmd'
#这个方法还是很方便的，-t虚拟出一个远程服务器的终端，在多台服务器同时部署时确实节约了不少时间啊！

#变量定义

#ip_array=("192.168.1.1" "192.168.1.2" "192.168.1.3")

ip_array2=(
10.112.56.100
10.112.56.101
10.112.56.102
10.112.56.103
10.112.56.104
10.112.56.105
10.112.56.106
10.112.56.107
10.112.56.108
10.112.56.109
10.112.56.118
10.112.56.119
10.112.56.120
10.112.56.121
10.112.56.122
10.112.56.133
10.112.56.134
10.112.56.136
10.112.56.135)

ip_array=(
10.112.56.100
10.112.56.101
)

outerNetPort=22
outerNetIp="112.35.44.42"
outerNetUser=aitest

#本地通过ssh执行远程服务器的脚本
<<'COMMENT'
COMMENT

function doAction(){
	#ssh -t -p $outerNetPort $outerNetUser@$outerNetIp "mkdir test" 
	echo "kernel.pid_max=409601" >> /etc/sysctl.conf 
	sysctl -p
}


#for ip in ${ip_array[@]}
for ip in ${ip_array[*]}
	do
		#ssh -t $ip doAction
	ssh -n -p $outerNetPort $outerNetUser@$ip "mkdir test"
	done  

