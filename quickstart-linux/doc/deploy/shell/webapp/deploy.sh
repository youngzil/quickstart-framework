#!/bin/bash

#配置节点数量，并且修改port
NODE_COUNT=3
NODE_START_PORT=28080
GATEWAY_CONSOLE_HOME=$HOME/gateway-console-server

function deploySingle()
{
  tempHome=$GATEWAY_CONSOLE_HOME$1
  echo $tempHome

  #已经存在目录先停止进程再删除
  if [[ -d "$tempHome" ]]; then

    cd $tempHome
    sh bin/stop.sh

    cd $HOME
    rm -rf gateway-console-server$1

  fi

  cd $HOME
  #  解压并且重命名
  tar -xzvf gateway-console-server.tar.gz
  mv gateway-console-server gateway-console-server$1

  #  复制配置文件并替换配置
  #  \cp $HOME/nginx.conf $tempHome/conf

  #  修改配置
  tempPort=$(($NODE_START_PORT+$1-1))
#  sed "s/20010/$tempPort/g" $HOME/config/application-devzj.yml  > $tempHome/conf/nginx.conf
  sed -i "s/20010/$tempPort/g" $tempHome/config/application-test.yml

  sleep 5

  #启动
  cd $tempHome
  sh bin/startaifgw-web.sh
}

for((i=1;i<=$NODE_COUNT;i++));
do
    deploySingle $i
done

#清理
cd $HOME
rm -f gateway-console-server.tar.gz
#rm -f  $HOME/config/application-devzj.yml
