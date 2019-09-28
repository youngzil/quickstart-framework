#!/bin/bash

deployHost=20.26.37.177
username=aideploy
password=1qaz!QAZ

tengineHost=20.26.85.233
tengineUsername=aifgw
tenginePassword=aifgw

menu()
{
cat <<EOF
    1. 打包上传gateway
    2. 打包上传security
    3. 打包上传webApp
    4. all(gateway\security\webApp)
    5. 上传tengine
    6. 上传nginx
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
open $deployHost
user $username $password
binary
cd /app/aideploy/deploy/pkg/gateway
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
open $deployHost
user $username $password
binary
cd /app/aideploy/deploy/pkg/oauth2
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
open $deployHost
user $username $password
binary
cd /app/aideploy/deploy/pkg/webapp
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
cd $HOME/workspace/aifgateway/deploy/pkg/tengine/

ftp -n<<!
open $tengineHost
user $tengineUsername $tenginePassword
binary
cd $HOME
prompt
delete tengine-2.3.2.tar.gz
put tengine-2.3.2.tar.gz

lcd $HOME/workspace/aifgateway/deploy/pkg/nginx-depend/
cd $HOME
prompt

delete pcre-8.43.tar.gz
delete openssl-1.1.1d.tar.gz
delete zlib-1.2.11.tar.gz

put pcre-8.43.tar.gz
put openssl-1.1.1d.tar.gz
put zlib-1.2.11.tar.gz

close
bye
!
}

DeployNginx()
{
	#deploy web
cd $HOME/mysoft

ftp -n<<!
open $deployHost
user $username $password
binary
cd /app/aideploy/deploy/pkg/nginx
prompt
delete nginx-1.17.4.tar.gz
put nginx-1.17.4.tar.gz
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
    6)
	    DeployNginx
    ;;
    q)
      exit 1
    ;;
      esac
done


