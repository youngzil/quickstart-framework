#!/bin/bash

function usage(){
cat << HELP

Usage: sh copyaifgwlog.sh aifgwback 132 01 15

第一个参数是路径根目录文件夹名字
第二个参数为host节点信息，必须是132/133/159/34
第三个参数为月份，必须是01、02。。。11、12
第四个参数为天数，必须是01、02。。。

Example:
    sh copyaifgwlog.sh aifgwback 132 01 15
    sh copyaifgwlog.sh aifgwback 133 01 15
    sh copyaifgwlog.sh aifgwback 159 01 15
    sh copyaifgwlog.sh aifgwback 34 01 15

HELP
}

if [ $# -lt 4 ]; then
    usage
    exit 0;
fi

path=$1
host=$2
month=$3
day=$4

HOST_LIST="132 133 159 34"   ###定义list
if [[ -z $host ]] || [[ ! $HOST_LIST =~ $host ]] ; then
  usage
  exit 0;
fi

#if [[ $month == *[^0-9]* ]] || [[ $month == 0* ]]; then
#  usage
#  exit 0;
#fi
if [ $month -le 0 ] 2>/dev/null ;then
  usage
  exit 0;
fi
if [ $month -gt 12 ] 2>/dev/null ;then
  usage
  exit 0;
fi

if [ $day -le 0 ] 2>/dev/null ;then
  usage
  exit 0;
fi
if [ $day -gt 31 ] 2>/dev/null ;then
  usage
  exit 0;
fi


ftp -v -n 20.26.28.168<<EOF
user ailog4x ailog4x

cd /home/ailog4x

mkdir $path
cd $path

mkdir $host
cd $host

mkdir node0
mkdir node1

binary
cd /home/ailog4x/$path/$host/node0
lcd /app/aifgw/aifgw-backend-1.0/logs/gateway
prompt
mput *2020-$month-$day*.log
put gateway-back-error.log
put gateway-back.log

cd /home/ailog4x/$path/$host/node1
lcd /app/aifgw/aifgw-backend-1.1/logs/gateway
mput *2020-$month-$day*.log
put gateway-back-error.log
put gateway-back.log

bye
EOF
