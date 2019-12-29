#!/bin/bash

#配置节点数量，并且修改port
NODE_COUNT=3
NODE_START_PORT=21100
NODE_CONFIG_START_PORT=31100
GATEWAY_BACKEND_HOME=$HOME/aifgw-backend-1.0

function deploySingle()
{
  tempHome=$GATEWAY_BACKEND_HOME$1
  echo $tempHome

  #已经存在目录先停止进程再删除
  if [[ -d "$tempHome" ]]; then

    cd $tempHome
    sh bin/stop

    cd $HOME
    rm -rf aifgw-backend-1.0$1

  fi

  cd $HOME
  #  解压并且重命名
  tar -xzvf aifgw-backend-1.0.tar.gz
  mv aifgw-backend-1.0 aifgw-backend-1.0$1

  #  复制配置文件并替换配置
  #  \cp $HOME/nginx.conf $tempHome/conf

  #  修改配置
  tempPort=$(($NODE_START_PORT+$1-1))
  tempConfigPort=$(($NODE_CONFIG_START_PORT+$1-1))
#  sed "s/20010/$tempPort/g" $HOME/config/application-devzj.yml  > $tempHome/conf/nginx.conf
  sed -i "s/21100/$tempPort/g" $tempHome/conf/aifgw.yml
  sed -i "s/31100/$tempConfigPort/g" $tempHome/conf/aifgw.yml

  sleep 5

  #启动
  cd $tempHome
  sh bin/startup daemon
}

for((i=1;i<=$NODE_COUNT;i++));
do
    deploySingle $i
done

#清理
cd $HOME
rm -f aifgw-backend-1.0.tar.gz
#rm -f  $HOME/config/application-devzj.yml
