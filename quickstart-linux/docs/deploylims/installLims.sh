#!/bin/bash

# 【这里要修改下主机用户名和IP】
username=root
host=test

#全局变量
CODE_PATH=${HOME}/src/lims

menu()
{
cat <<EOF
    1. deploy 后台
    2. deploy 前台
    3. deploy all
    q. 退出
EOF
}

gitClone(){
  echo "[INFO] 开始下载源码..."
  cd $CODE_PATH
  # 【这里要修改下地址、账号、密码】
  # git clone https://youngzil%40163.com:密码@gitee.com/yougw/lims-service.git $CODE_PATH
  cd lims-service
  git pull
}

installBack()
{
  cd $CODE_PATH/lims-service/lims
  mvn -Pdev -DskipTests clean install -U

  cd lims-distribution/target
  scp -P 9000 lims-0.0.1-SNAPSHOT.tar.gz $username@$host:~
  ssh -p 9000 -n $username@$host "sh deployLims.sh"
  echo "[INFO] 恭喜后台部署成功..."
}

installFront()
{
  echo "[ERROR] 暂时不支持"
}

gitClone

while true
do
    menu
    read -p "DEPLOY->输入选项:" ch
    case $ch in
    1)
	    installBack
    ;;
    2)
      installFront
    ;;
    3)
      installBack
      installFront
    ;;
    q)
      exit 1
    ;;
      esac
done
