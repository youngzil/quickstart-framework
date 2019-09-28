#!/bin/bash

GATEWAY_SECURITY_HOME=$HOME/aifgw-security-1.0

if [[ -d "$GATEWAY_SECURITY_HOME" ]]; then

  cd $GATEWAY_SECURITY_HOME
  sh bin/stopaifgw-security

  cd $HOME
  rm -rf aifgw-security-1.0
fi

tar -xzvf aifgw-security-1.0.tar.gz
rm -f aifgw-security-1.0.tar.gz

sleep 5

cd $GATEWAY_SECURITY_HOME
sh bin/startaifgw-security
