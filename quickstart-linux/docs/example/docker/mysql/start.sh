#!/bin/sh
  WORKDIR=`pwd`
  BASE_DIR=/data
  APP_DATA_DIR=$BASE_DIR/mysql
  APP_LOG_DIR=$BASE_DIR/logs
  APP_VERSION=5.7.19
  APP_TAG=mysql:$APP_VERSION

  create(){
    if [ $# -eq 1 -a "$1"x != x ]; then
        for distdir in $1 ;do
          if [ ! -f $distdir -a ! -d $distdir ] ;then
                 mkdir -p $distdir
          fi
        done
    fi
  }
  if [ $# -ne 2 ]; then
    echo "usage: sh start.sh port root_passwd "
    echo "example: sh start.sh 3306 acc " 
    exit 1
  fi
  PORT=$1
  CONTAINER_NAME=mysql_$PORT
  CONFIG_PATH=$WORKDIR
  MYSQL_ROOT_PASSWORD=$2
  create "$WORKDIR/$APP_LOG_DIR $WORKDIR/$APP_DATA_DIR/$CONTAINER_NAME"
  cat /dev/null > $LOG_DIR/$CONTAINER_NAME.log
  echo "$CONTAINER_NAME start ..."
  nohup docker run \
         -v $CONFIG_PATH/conf.d:/etc/mysql/conf.d \
         -v $APP_DATA_DIR/$CONTAINER_NAME:/var/lib/mysql \
         -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
         -p $PORT:3306 \
         --restart=always \
         --name $CONTAINER_NAME \
         $APP_TAG --character-set-server=utf8 --collation-server=utf8_general_ci --wait_timeout=31536000 --interactive_timeout=31536000 --lower-case-table-names=1 --sql-mode="ALLOW_INVALID_DATES" --explicit_defaults_for_timestamp > $APP_LOG_DIR/$CONTAINER_NAME.log 2>&1 &
