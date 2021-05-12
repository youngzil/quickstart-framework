#!/bin/bash

set -e

VARS=$#
if [ $VARS -lt 1 ];
then
        echo "必须传入1个参数
第一个参数是编译任务名（gatewaytest/oauth/webapp/webdev/webopr/all)
usage: $0  all"
        exit 0;
fi

CURR_PATH=`pwd`

PROFILE_ENV_LIST="dev test prod"   ###定义list
PROFILE_ENV=$2
#PROFILE_ENV=${PROFILE_ENV:-test}

if [[ -z $PROFILE_ENV ]] || [[ ! "$PROFILE_ENV_LIST" =~ "$PROFILE_ENV" ]] ; then
  echo "请确认打包是否成功"
  exit 1
fi

#if [[ -z $PROFILE_ENV ]] || [[ ! "$PROFILE_ENV_LIST" =~ "$PROFILE_ENV" ]] ; then
#  echo "请确认打包是否成功"
#  exit 1
#fi