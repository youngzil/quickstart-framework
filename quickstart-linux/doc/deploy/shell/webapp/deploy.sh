#!/bin/bash

GATEWAY_CONSOLE_HOME=$HOME/gateway-console-server

if [[ -d "$GATEWAY_CONSOLE_HOME" ]]; then

  cd $GATEWAY_CONSOLE_HOME
  sh bin/stop.sh

  cd $HOME
  rm -rf gateway-console-server
fi

tar -xzvf gateway-console-server.tar.gz
rm -f gateway-console-server.tar.gz

sleep 5

cd $GATEWAY_CONSOLE_HOME
nohup sh bin/startaifgw-web.sh &
