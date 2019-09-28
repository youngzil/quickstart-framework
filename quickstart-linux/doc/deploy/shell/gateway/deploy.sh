#!/bin/bash

GATEWAY_BACKEND_HOME=$HOME/aifgw-backend-1.0

if [[ -d "$GATEWAY_BACKEND_HOME" ]]; then

  cd $GATEWAY_BACKEND_HOME
  sh bin/stop

  cd $HOME
  rm -rf aifgw-backend-1.0
fi

tar -xzvf aifgw-backend-1.0.tar.gz
rm -f aifgw-backend-1.0.tar.gz

sleep 5

cd $GATEWAY_BACKEND_HOME
sh bin/startup daemon debug
