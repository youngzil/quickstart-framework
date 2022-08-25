#!/bin/bash


port_check () {
  # $tempFile则调用该命令，并返回真正的临时文件名到tempFile,只有第一调用才会生成临时文件
  tempFile=$(mktemp -t tempFile.XXX)
  # telnet验证某域名端口是否能通，并将日志放入到临时文件中
  (telnet $1 $2 << EOF
quit
EOF
) &>$tempFile

  # 如果临时文件中含有  ^] 则说明是通的，否则是不通的
  if grep '\^]' $tempFile &>/dev/null; then
    echo "$1:$2 is open"
  else
    echo "$1:$2 is close"
  fi
  # 删除临时文件
#  rm -rf $tempFile
  trap 'rm -f "$tempFile"' EXIT
}

# 判定是否有telnet命令且必须要有两个参数，没有则叫用户安装，有则运行port_check方法
if man telnet &>/dev/null && [ $1 ] && [ $2 ] ; then
  port_check $1 $2
else
  # 想让echo输出换行符则需使用 -e    \n换行符
  echo -e "The following conditions must be met\n1. USAGE: sh $0 hostName|IP port  \n2. Please check whether Telnet is installed? if you not,  you can use command < yum -y install telnet >"
fi