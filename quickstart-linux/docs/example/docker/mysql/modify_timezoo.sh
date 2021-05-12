#!/bin/sh
  WORKDIR=`pwd`
  BASE_DIR=/data
  APP_NAME=mysql
  APP_VERSION=8
  APP_LOG_DIR=$BASE_DIR/logs

  if [ $# -ne 1 ]; then
    echo "usage: sh modify_timezoo port"
    echo "example: sh modify_timezoo 3306"
    exit 1
  fi
  PORT=$1
  CONTAINER_NAME=${APP_NAME}_$PORT
  echo "$CONTAINER_NAME modify_time zoo : first Asia no and then Shanghai no "
  docker exec $CONTAINER_NAME /bin/sh -c "exec dpkg-reconfigure tzdata"
