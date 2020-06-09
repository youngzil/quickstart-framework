#!/bin/bash

set -e

function usage(){
cat << HELP

Usage: sh virinstall.sh 项目名 分支名称 环境 VPN密码

第一个参数是编译任务名（aifgw/oauth/webapp/webdev/webopr/all)
第二个参数是分支名称（master/develop-shandong-unicom/develop-zhejiang-mobile/。。。)
第三个参数为环境参数，必须是dev/test/prod
第四个参数为VPN密码

Example:
    sh virinstall.sh aifgw master dev 123456
    sh virinstall.sh aifgw develop-shandong-unicom test 123456
    sh virinstall.sh aifgw develop-zhejiang-mobile prod 123456
    sh virinstall.sh oauth develop-zhejiang-mobile prod 123456
    sh virinstall.sh all develop-zhejiang-mobile test 123456
    sh virinstall.sh oauth develop-zhejiang-mobile test 266005
HELP
}

function checkVPNState(){

  nohup /Applications/MotionPro.app/Contents/MacOS/MotionPro -connectwithui sslnj.asiainfo.com 443 yangzl $VPN_PASSWORD 0 0 0 0 >/dev/null &

  line=-1
  total=0
  site="10.1.243.22"
  pingResult=false
  while [ 1 ]; do
    line=`ping '10.1.243.22' -c 1 -s 1 -W 1 | grep "100.0% packet loss" | wc -l`

    if [ $line != 0 ]; then
      echo "VPN is DOWN"
      total=$((total+1))
    else
      echo "VPN is UP"
      pingResult=true
      break
    fi

    if [ $total == 6 ]; then
      echo "超过最大检测次数$total,VPN is DOWN"
      break
    fi

    sleep 5

  done

  if [ $pingResult = false ]; then
    echo "VPN Connection Exception Exit"
    exit 0;
  fi

}

if [ $# -lt 4 ]; then
    usage
    exit 0;
fi

ARG1=$1
BRANCH_NAME=$2
PROFILE_ENV=$3
VPN_PASSWORD=$4

if [[ "$ARG1" =~ "-h" ]];then
    usage
    exit 0
fi

MODULE_ENV_LIST="aifgw oauth webapp webdev webopr all"   ###定义list
if [[ -z $ARG1 ]] || [[ ! $MODULE_ENV_LIST =~ $ARG1 ]] ; then
  usage
  exit 0;
fi

PROFILE_ENV_LIST="dev test prod"   ###定义list
#PROFILE_ENV=${PROFILE_ENV:-test}
if [[ -z $PROFILE_ENV ]] || [[ ! $PROFILE_ENV_LIST =~ $PROFILE_ENV ]] ; then
  usage
  exit 0;
fi

REMOTE_TAR_PACKAGE_PATH=/home/aibuild45/deploy_oppf/dist_gateway_git/$BRANCH_NAME
LOCAL_TAR_PACKAGE_PATH=/Users/yangzl/Documents/$BRANCH_NAME
mkdir -p $LOCAL_TAR_PACKAGE_PATH

aifgw(){
  scp aibuild45@10.1.243.22:$REMOTE_TAR_PACKAGE_PATH/aifgw-backend-1.0.tar.gz $LOCAL_TAR_PACKAGE_PATH
}

oauth()
{
  scp aibuild45@10.1.243.22:$REMOTE_TAR_PACKAGE_PATH/aifgw-security-1.0.tar.gz $LOCAL_TAR_PACKAGE_PATH
}

webapp()
{
  scp aibuild45@10.1.243.22:$REMOTE_TAR_PACKAGE_PATH/gateway-console-server.tar.gz $LOCAL_TAR_PACKAGE_PATH
}

webdev()
{
  scp aibuild45@10.1.243.22:$REMOTE_TAR_PACKAGE_PATH/webdev.war $LOCAL_TAR_PACKAGE_PATH
}

webopr()
{
  scp aibuild45@10.1.243.22:$REMOTE_TAR_PACKAGE_PATH/webopr.war $LOCAL_TAR_PACKAGE_PATH
}


START_DATE=$(date +%s)

checkVPNState

ssh -n aibuild45@10.1.243.22 "cd ~/deploy_oppf/bin;sh git-aifgw-deploy2.sh $ARG1 $BRANCH_NAME $PROFILE_ENV"

case $ARG1 in
  webapp)
     webapp
  ;;
  webdev)
      webdev
  ;;
  webopr)
      webopr
  ;;
  aifgw)
      aifgw
  ;;
  oauth)
      oauth
  ;;
  all)
      aifgw
      oauth
      webapp
      webdev
      webopr
  ;;
  *)
  usage
  exit 0
  ;;
esac

END_DATE=$(date +%s)
STAMP_DIFF=`expr $END_DATE - $START_DATE`

echo "打包完成,耗时=$STAMP_DIFF 秒，打包后的压缩包请查看$LOCAL_TAR_PACKAGE_PATH..."
