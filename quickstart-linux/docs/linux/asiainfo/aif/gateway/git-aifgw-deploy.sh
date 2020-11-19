#!/bin/bash

set -e

function usage(){
cat << HELP

Usage: sh git-aifgw-deploy.sh 项目名 分支名称 环境

第一个参数是编译任务名（aifgw/oauth/webapp/webdev/webopr/all)
第二个参数是分支名称（master/develop-shandong-unicom/develop-zhejiang-mobile/。。。)
第三个参数为环境参数，必须是dev/test/prod

Example:
    sh git-aifgw-deploy.sh aifgw master dev
    sh git-aifgw-deploy.sh aifgw develop-shandong-unicom test
    sh git-aifgw-deploy.sh aifgw develop-zhejiang-mobile prod
    sh git-aifgw-deploy.sh oauth develop-zhejiang-mobile prod
    sh git-aifgw-deploy.sh all develop-zhejiang-mobile test

HELP
}

if [ $# -lt 3 ]; then
    usage
    exit 0;
fi

ARG1=$1
BRANCH_NAME=$2
PROFILE_ENV=$3

if [[ "$ARG1" =~ "-h" ]];then
    usage
    exit 0
fi

MODULE_ENV_LIST="service ui aifgw oauth webapp webdev webopr all"   ###定义list
if [[ -z $ARG1 ]] || [[ ! $MODULE_ENV_LIST =~ $ARG1 ]] ; then
  usage
  exit 0;
fi

ospversion=`cat ${HOME}/deploy_oppf/xml/oppf_version | grep 'oppf.version' | awk -F"=" '{print $2}'`
CURR_PATH=`pwd`

PROFILE_ENV_LIST="dev test prod"   ###定义list
#PROFILE_ENV=${PROFILE_ENV:-test}
if [[ -z $PROFILE_ENV ]] || [[ ! $PROFILE_ENV_LIST =~ $PROFILE_ENV ]] ; then
  usage
  exit 0;
fi

#全局变量
CODE_PATH=${HOME}/deploy_oppf/git_gateway/src
MAVEN_HOME=${HOME}/deploy_oppf/bin/maven/bin
TAR_PACKAGE_PATH=${HOME}/deploy_oppf/dist_gateway_git/$BRANCH_NAME
PROJECT_VERSION='1.0'
ERROR_FLAG="ERROR"

#创建目录
mkdir -p $CODE_PATH
mkdir -p $TAR_PACKAGE_PATH

gitClone(){
  echo "[echo] 开始下载源码..."
  cd $CODE_PATH
  rm -rf * .[!.]*
  #git clone http://yangzl:nihao%40124@10.19.14.241/OSP/gateway.git $CODE_PATH
  git clone http://10.19.14.241/OSP/gateway.git  $CODE_PATH
  CURRENT_BRANCH_NAME=`git branch | sed -n '/\* /s///p'`
  #echo $CURRENT_BRANCH_NAME
  if [[ $CURRENT_BRANCH_NAME != $BRANCH_NAME ]];then
    #echo "$CURRENT_BRANCH_NAME!=$BRANCH_NAME"
    git checkout -b $BRANCH_NAME origin/$BRANCH_NAME
  fi
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

setProjectVersion(){

  # 接受的是传递给函数的参数
  PROJECT_PATH=$1
  PROJECT_NAME=$2

  cd $PROJECT_PATH

  PROJECT_VERSION=$($MAVEN_HOME/mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout)
  if [[ $PROJECT_VERSION == *$ERROR_FLAG* ]]
  then
      echo "项目${PROJECT_NAME}的POM配置文件有误，请检查"
      echo $PROJECT_VERSION
  else
      echo "当前正在打包[分支=$BRANCH_NAME,工程=$PROJECT_NAME,版本=$PROJECT_VERSION]"
  fi
}

aifgw()
{
  PROJECT_NAME=aifgw-backend-parent

  setProjectVersion $CODE_PATH/$PROJECT_NAME $PROJECT_NAME

  TARGET_NAME=aifgw-backend-${PROJECT_NAME}.tar.gz

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi

  $MAVEN_HOME/mvn -Prelease-all -Pbuild-$PROFILE_ENV -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-distribution/target/$TARGET_NAME ];then
          echo "打包失败,请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-distribution/target/$TARGET_NAME  $TAR_PACKAGE_PATH/$TARGET_NAME
  # cd $TAR_PACKAGE_PATH/;mkdir temp;cd temp;tar zxf ../aifgw-backend-1.0.tar.gz;cd aifgw-backend-1.0/conf;jar xf ../lib/aifgw-backend-boot-1.0.jar
  # cd $TAR_PACKAGE_PATH/temp;tar czf aifgw-backend-1.0.tar.gz aifgw-backend-1.0;mv aifgw-backend-1.0.tar.gz ~/deploy_oppf/dist_gateway/
  # cd $TAR_PACKAGE_PATH;rm -rf $TAR_PACKAGE_PATH/temp
  echo "[echo] 项目${PROJECT_NAME}编译完成,打包文件的文件请查看$TAR_PACKAGE_PATH/$TARGET_NAME..."
}

oauth()
{
  PROJECT_NAME=aifgw-security-parent

  setProjectVersion $CODE_PATH/$PROJECT_NAME $PROJECT_NAME

  TARGET_NAME=aifgw-security-${PROJECT_NAME}.tar.gz

  cd $CODE_PATH/$PROJECT_NAME
  echo "[echo] 开始编译$PROJECT_NAME..."

  if [ -d $CODE_PATH/local/$PROJECT_NAME ]; then
    echo "[echo] 开始替换代码$PROJECT_NAME..."
    cp -rf $CODE_PATH/local/$PROJECT_NAME $CODE_PATH/$PROJECT_NAME
  fi

  $MAVEN_HOME/mvn -Pbuild-$PROFILE_ENV -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-security-distribution/target/$TARGET_NAME ];then
          echo "打包失败,请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-security-distribution/target/$TARGET_NAME   $TAR_PACKAGE_PATH/$TARGET_NAME
  echo "[echo] 项目${PROJECT_NAME}编译完成,打包文件的文件请查看$TAR_PACKAGE_PATH/$TARGET_NAME..."
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

#  替换启动脚本
  ENV_VARI=$PROFILE_ENV
  if [ "test" = "$ENV_VARI" ];then
    ENV_VARI="testzj"
  fi
  sed -i "s/spring.profiles.active=dev/spring.profiles.active=$ENV_VARI/g" $CODE_PATH/$PROJECT_NAME/aifgw-web-app-distribution/sbin/startaifgw-web.sh

  $MAVEN_HOME/mvn -Prelease-all -DskipTests clean install -U

  if [ ! -f $CODE_PATH/$PROJECT_NAME/aifgw-web-app-distribution/target/$TARGET_NAME ];then
          echo "打包失败,请确认$PROJECT_NAME打包是否成功"
          exit 1
  fi
  rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
  mv  $CODE_PATH/$PROJECT_NAME/aifgw-web-app-distribution/target/$TARGET_NAME  $TAR_PACKAGE_PATH/$TARGET_NAME
  echo "[echo] 项目${PROJECT_NAME}编译完成,打包文件的文件请查看$TAR_PACKAGE_PATH/$TARGET_NAME..."
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
  cp -rf $CODE_PATH/$PROJECT_NAME/src/modules/osp-dev/routes.js $CODE_PATH/$PROJECT_NAME/src/

  aid build -N
  if [ -d "dist" ];then
  echo "开始打包$TARGET_NAME的war包..."
      cd dist
      jar cf $TARGET_NAME *
      rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
      mv $TARGET_NAME $TAR_PACKAGE_PATH/
  fi
  echo "${TARGET_NAME}打包完成,打包文件的文件请查看$TAR_PACKAGE_PATH/$TARGET_NAME..."
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
  cp -rf $CODE_PATH/$PROJECT_NAME/src/modules/osp-opr/routes.js $CODE_PATH/$PROJECT_NAME/src/

  aid build -N
  if [ -d "dist" ];then
  echo "开始打包$TARGET_NAME的war包..."
      cd dist
      jar cf $TARGET_NAME *
      rm -rf $TAR_PACKAGE_PATH/$TARGET_NAME
      mv $TARGET_NAME $TAR_PACKAGE_PATH/
  fi
  echo "${TARGET_NAME}打包完成,打包文件的文件请查看$TAR_PACKAGE_PATH/$TARGET_NAME..."
}


gitClone

START_DATE=$(date +%s)

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
  service)
     aifgw
     oauth
     webapp
  ;;
  ui)
     webdev
     webopr
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

echo "#####aifgw 版本:${ospversion}#####"
echo "打包完成,耗时=${STAMP_DIFF}秒，打包后的压缩包请查看$TAR_PACKAGE_PATH..."
