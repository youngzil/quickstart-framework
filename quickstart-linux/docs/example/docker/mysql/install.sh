#!/bin/sh
  WORKDIR=`pwd`
  BASE_DIR=/data
  BASE_APP=acp
  APP_NAME=mysql
  APP_VERSION=5.7.19
  APP_DATA=$BASE_DIR/$APP_NAME
  LOG_DIR=$BASE_DIR/logs
  TAG=$APP_NAME:$APP_VERSION
  OLD_TAG=$APP_NAME:$APP_VERSION
  TAR=${APP_NAME}_${APP_VERSION}.tar.gz

  create(){
    if [ $# -eq 1 -a "$1"x != x ]; then
        for distdir in $1 ;do
          if [ ! -e $distdir ] ;then
                 mkdir -p $distdir
          fi
        done
    fi
  }
  rmcontainer(){
    if [ $# -ne 1 ]; then
      echo "usage: app_name "
      echo "example: rmcontainer acc_8088"
      exit 1
    fi
    app=$1 ;  hascontainer=`docker ps -a | grep $app |wc -l`
    if [ $hascontainer -ne 0 ]; then
        docker rm -f `docker ps -a | grep $app | awk '{print $1}'`
    fi
  }
  rmimage(){
    if [ $# -ne 2 ]; then
      echo "usage: app_name image_tag"
      echo "example: rmimage acc_8088 acp/acc:1.0.0"
      exit 1
    fi
    app=$1; tag=$2 ; hasimage=`docker images | grep $app |wc -l`
    if [ $hasimage -ne 0 ]; then
     rmcontainer $app ;  docker rmi -f $tag
    fi
  }
  create "$LOG_DIR $APP_DATA"
  cd $WORKDIR ; rmimage $APP_NAME $TAG ; docker load < $TAR
  if [ $OLD_TAG"x" != $TAG"x" ]; then  docker tag $OLD_TAG $TAG ; fi

