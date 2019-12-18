#!/bin/bash

set -e

VARS=$#
if [ $VARS -lt 1 ];
then
        echo "必须传入1个参数
第一个参数是编译任务名（aifgw/oauth/webapp/webdev/webopr/all)
usage: $0  all"
        exit 0;
fi

ospversion=`cat ${HOME}/deploy_oppf/xml/oppf_version | grep 'oppf.version' | awk -F"=" '{print $2}'`
CURR_PATH=`pwd`

#全局变量
CODE_PATH=${HOME}/deploy_oppf/git_gateway/src
MAVEN_HOME=${HOME}/deploy_oppf/bin/maven/bin
TAR_PACKAGE_PATH=${HOME}/deploy_oppf/dist_gateway

gitClone(){
  echo "[echo] 开始下载源码..."
  cd $CODE_PATH
  rm -rf * .[!.]*
  git clone http://yangzl:nihao%40124@10.19.14.241/OSP/gateway.git $CODE_PATH
}

checkVueEnvironment(){
  echo "[echo] 开始检查Vue环境..."
  export NVM_DIR="$HOME/.nvm"
  [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm
  [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"  # This loads nvm bash_completion

  if [ "v6.11.0" != `nvm current` ];then
        wget -qO- https://raw.githubusercontent.com/creationix/nvm/v0.33.2/install.sh
        nvm install v6.11.0
        nvm use v6.11.0
  fi

#  npm i -g aid-cli@2.0.0 --sass-binary-site=http://npm.taobao.org/mirrors/node-sass/

  echo "[echo] NVM版本:" `nvm --version`
  echo "[echo] NodeJS版本:" `node -v`
  echo "[echo] npm版本:" `npm -v`
  echo "[echo] aid版本:" `aid -v`
}

webapp()
{
  PROJECT_NAME=aifgw-web-app-parent
  TARGET_NAME=gateway-console-server.tar.gz

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi

  $MAVEN_HOME/mvn  -Prelease-all -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-web-app-distribution/target/$TARGET_NAME ];then
          echo "请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-web-app-distribution/target/$TARGET_NAME  $TAR_PACKAGE_PATH/$TARGET_NAME
  echo "[echo] 项目$PROJECT_NAME编译完成..."
}

aifgw()
{
  PROJECT_NAME=aifgw-backend-parent
  TARGET_NAME=aifgw-backend-1.0.tar.gz

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi

  $MAVEN_HOME/mvn -Prelease-all -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-distribution/target/$TARGET_NAME ];then
          echo "请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-distribution/target/$TARGET_NAME  $TAR_PACKAGE_PATH/$TARGET_NAME
  # cd $TAR_PACKAGE_PATH/;mkdir temp;cd temp;tar zxf ../aifgw-backend-1.0.tar.gz;cd aifgw-backend-1.0/conf;jar xf ../lib/aifgw-backend-boot-1.0.jar
  # cd $TAR_PACKAGE_PATH/temp;tar czf aifgw-backend-1.0.tar.gz aifgw-backend-1.0;mv aifgw-backend-1.0.tar.gz ~/deploy_oppf/dist_gateway/
  # cd $TAR_PACKAGE_PATH;rm -rf $TAR_PACKAGE_PATH/temp
  echo "[echo] 项目$PROJECT_NAME编译完成..."
}

oauth()
{
  PROJECT_NAME=aifgw-security-parent
  TARGET_NAME=aifgw-security-1.0.tar.gz

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi

  $MAVEN_HOME/mvn -Pbuild-test -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-security-distribution/target/$TARGET_NAME ];then
          echo "请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-security-distribution/target/$TARGET_NAME   $TAR_PACKAGE_PATH/$TARGET_NAME
  echo "[echo] 项目$PROJECT_NAME编译完成..."
}

webdev()
{
  checkVueEnvironment
  PROJECT_NAME=aifgw-web-front
  TARGET_NAME=webdev.war

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  cp ${HOME}/deploy_oppf/git_gateway/node_modules.tar.gz .
  tar -xzvf node_modules.tar.gz  > /dev/null

#  npm i -g aid-cli@2.0.0 --sass-binary-site=http://npm.taobao.org/mirrors/node-sass/
#  echo "[echo] aid版本:" `aid -v`

  #code 覆盖
  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi
  #修改配置文件：替换routes.js文件
  cp -rf ${HOME}/deploy_oppf/config_test/webdev_gateway/dist/src/routes.js $CODE_PATH/$PROJECT_NAME/src/

  aid build -N
  if [ -d "dist" ];then
  echo "开始打包$TARGET_NAME的war包..."
      cd dist
      jar cf $TARGET_NAME *
      rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
      mv $TARGET_NAME $TAR_PACKAGE_PATH/
  fi
  echo "$TARGET_NAME打包完成..."
}

webopr()
{
  checkVueEnvironment
  PROJECT_NAME=aifgw-web-front
  TARGET_NAME=webopr.war

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  cp ${HOME}/deploy_oppf/git_gateway/node_modules.tar.gz .
  tar -xzvf node_modules.tar.gz  > /dev/null

#  npm i -g aid-cli@2.0.0 --sass-binary-site=http://npm.taobao.org/mirrors/node-sass/
#  echo "[echo] aid版本:" `aid -v`

  #code 覆盖
  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi
  #替换routes.js文件
  cp -rf ${HOME}/deploy_oppf/config_test/webopr_gateway/dist/src/routes.js $CODE_PATH/$PROJECT_NAME/src/

  aid build -N
  if [ -d "dist" ];then
  echo "开始打包$TARGET_NAME的war包..."
      cd dist
      jar cf $TARGET_NAME *
      rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
      mv $TARGET_NAME $TAR_PACKAGE_PATH/
  fi
  echo "$TARGET_NAME打包完成..."
}

gitClone

case $1 in
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
  echo "$1 请输入任务名: aifgw/oauth/webapp/webdev/webopr/all"
  exit 1
  ;;
esac

echo "#####aifgw 版本:${ospversion}#####"
