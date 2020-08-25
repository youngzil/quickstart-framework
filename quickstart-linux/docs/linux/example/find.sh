#!/bin/bash

TODAY=`date "+%Y%m%d"`
TODAYH=`date "+%Y-%m-%d"`
find /app/ -name "*oppf*log"|grep -v $TODAY|xargs -i -t rm -f {}
find /app/ -name "catalina.out" |xargs -i -t cp /dev/null {}
find /app/ -name  "*catalina*.log" -o -name "*localhost_access*.txt" -o  -name "*manager*.log"|grep -v $TODAY|xargs -i -t rm -f {}


#!/bin/sh
  WORKDIR=`pwd`
  BASE_DIR=/data
  APP_NAME=mysql
  APP_VERSION=8
  APP_LOG_DIR=$BASE_DIR/logs

  if [ $# -ne 3 ]; then
    echo "usage: sh create_database.sh port root_passwd database"
    echo "example: sh create_database.sh 3306 acc aiacp"
    exit 1
  fi
  PORT=$1
  CONTAINER_NAME=${APP_NAME}_$PORT
  ROOT_PASSWD=$2
  DATABASE=$3
  echo "$CONTAINER_NAME drop database $DATABASE..."
  docker exec $CONTAINER_NAME /bin/sh -c "exec mysql -uroot -p$ROOT_PASSWD -e 'DROP DATABASE IF EXISTS "$DATABASE";'"
  echo "$CONTAINER_NAME create database $DATABASE..."
  docker exec $CONTAINER_NAME /bin/sh -c "exec mysql -uroot -p$ROOT_PASSWD -e 'CREATE DATABASE "$DATABASE" DEFAULT CHARACTER SET utf8;'"