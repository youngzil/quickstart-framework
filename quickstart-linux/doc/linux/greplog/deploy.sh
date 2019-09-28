#!/bin/bash

menu()
{
cat <<EOF
    1. 打包上传gateway
    2. 打包上传security
    3. 打包上传webApp
    4. all(gateway\security\webApp)
    5. 上传tengine/nginx
    q. 退出
EOF
}

DeployGateway()
{
  #  因为网络不通，可以加执行部署脚本的操作
  #deploy gateway
cd $HOME/workspace/aifgateway/aifgw-backend-parent/

mvn -Prelease-all -DskipTests clean install -U

cd aifgw-distribution/target

ftp -n<<!
open 20.26.85.230
user aifgw aifgw
binary
cd /home/aifgw
prompt
delete aifgw-backend-1.0.tar.gz
put aifgw-backend-1.0.tar.gz
close
bye
!
}

DeploySecurity()
{
#deploy security
cd $HOME/workspace/aifgateway/aifgw-security-parent/

mvn -Pbuild-test -DskipTests clean install -U

cd aifgw-security-distribution/target

ftp -n<<!
open 20.26.85.232
user aifgw aifgw
binary
cd /home/aifgw
prompt
delete aifgw-security-1.0.tar.gz
put aifgw-security-1.0.tar.gz
close
bye
!
}

DeployWebapp()
{
	#deploy web
cd $HOME/workspace/aifgateway/aifgw-web-app-parent/

mvn -Prelease-all -DskipTests clean install -U

cd aifgw-web-app-distribution/target

ftp -n<<!
open 20.26.85.234
user aifgw aifgw
binary
cd /home/aifgw
prompt
delete gateway-console-server.tar.gz
put gateway-console-server.tar.gz
close
bye
!
}

DeployTengine()
{
	#deploy web
cd $HOME/mysoft

ftp -n<<!
open 20.26.85.230
user aifgw aifgw
binary
cd /home/aifgw
prompt
delete tengine-2.3.2.tar.gz
put tengine-2.3.2.tar.gz
close
bye
!
}

chmod 755 -R *
while true
do
    menu
    read -p "DEPLOY->输入选项:" ch
    case $ch in
    1)
	  DeployGateway
    ;;
    2)
      DeploySecurity
    ;;
    3)
      DeployWebapp
    ;;
    4)
      DeployGateway
      DeploySecurity
      DeployWebapp
    ;;
    5)
	  DeployTengine
    ;;
    q)
      exit 1
    ;;
      esac
done
