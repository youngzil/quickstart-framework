#!/bin/sh
  WORKDIR=`pwd`
  BASE_DIR=/data
  APP_NAME=mysql
  APP_VERSION=8
  APP_LOG_DIR=$BASE_DIR/logs

  if [ $# -ne 3 ]; then
    echo "usage: sh import.sh port root_passwd database"
    echo "example: sh import.sh 3306 acc aiacp"
    exit 1
  fi
  PORT=$1
  CONTAINER_NAME=${APP_NAME}_$PORT
  ROOT_PASSWD=$2
  SQL_PATH=$WORKDIR
  DATABASE=$3
  echo "$CONTAINER_NAME import..."
  cd $SQL_PATH; 
  docker cp sql $CONTAINER_NAME:/ ;
  for dir in $SQL_PATH/sql/trunc $SQL_PATH/sql/data ;do
      cd $dir
      for file in `ls` ;do
        docker exec $CONTAINER_NAME /bin/sh -c "exec mysql -uroot -p$ROOT_PASSWD $DATABASE -e 'set names utf8;set foreign_key_checks=0;source /sql/${dir##*sql}/$file;set foreign_key_checks=1 ;'"
      done
  done
