#!/bin/bash

menu()
{
cat <<EOF
    1. deploy gateway
    2. deploy security
    3. deploy webApp
    4. deploy tengineGateway
    5. deploy tengineWebdev
    6. deploy tengineWebopr
    7. deploy all(gateway\security\webApp)
    8. deploy all tengine(gateway/webdev/webopr/)
    9. deploy all(7+8)
    q. 退出
EOF
}

DeployGateway()
{
  cd $HOME/deploy/shell/gateway
  sh install.sh
}

DeploySecurity()
{
  cd $HOME/deploy/shell/oauth2
  sh install.sh
}

DeployWebapp()
{
  cd $HOME/deploy/shell/webapp
  sh install.sh
}

DeployTengineGateway()
{
  cd $HOME/deploy/shell/tengine
  sh install.sh
}

DeployTengineWebdev()
{
  cd $HOME/deploy/shell/webdev
  sh install.sh
}

DeployTengineWebopr()
{
  cd $HOME/deploy/shell/webopr
  sh install.sh
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
      DeployTengineGateway
    ;;
    5)
      DeployTengineWebdev
    ;;
    6)
      DeployTengineWebopr
    ;;
    7)
      DeployGateway
      DeploySecurity
      DeployWebapp
    ;;
    8)
	    DeployTengineGateway
	    DeployTengineWebdev
	    DeployTengineWebopr
    ;;
    9)
	    DeployGateway
      DeploySecurity
      DeployWebapp
      DeployTengineGateway
	    DeployTengineWebdev
	    DeployTengineWebopr
    ;;
    q)
      exit 1
    ;;
      esac
done
