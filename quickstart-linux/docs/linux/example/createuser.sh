#!/bin/sh  
user=$1
password=''$2''
path=$3
hostip=$4
group=$5
respool_deploy_file=createuser.log
WriteInfo()
{
    echo -e "$(date +"%Y-%m-%d") $(date +"%H:%M:%S")[INFO] $1" | tee -a $respool_deploy_file 2>&1
}


if [ "root" = `whoami` ] && [ x"${hostip}" != x ];then
	if [ x"${hostip}" = x`hostname -i` ] || [ ${hostip} = "127.0.0.1" ];then
		if [ x"$5" = x ];then
			group=aiapp
		fi
		  
		#create group if not exists  
		egrep "^$group" /etc/group >& /dev/null  
		if [ $? -ne 0 ]  
		then  
		    groupadd $group  
		fi  
		  
		#create user if not exists  
		egrep "^$user" /etc/passwd >& /dev/null  
		if [ $? -ne 0 ]  
		then  
		    useradd -g $group -md $path -s /bin/bash $user  
		    wait
		    echo $password |passwd --stdin $user
		    WriteInfo "#####USER:$user 用户创建成功#####"
		else
		    WriteInfo "#####USER:$user 已经存在#####"
		fi
	else
		WriteInfo "#####请检查/etc/hosts的主机名与主机IP是否一致#####"	
	fi
fi
