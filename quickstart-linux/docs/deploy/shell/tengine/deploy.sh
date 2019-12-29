#!/bin/bash

TENGINE_GATEWAY_HOME=$HOME/tengine-gateway

if [[ -d "$TENGINE_GATEWAY_HOME" ]]; then

  cd $TENGINE_GATEWAY_HOME
  sbin/nginx -s stop

  cd $HOME
  rm -rf tengine-gateway
fi

tar -xzvf tengine-gateway.tar.gz
rm -f tengine-gateway.tar.gz

mv $HOME/nginx.conf $TENGINE_GATEWAY_HOME/conf

sleep 5

cd $TENGINE_GATEWAY_HOME
sbin/nginx
