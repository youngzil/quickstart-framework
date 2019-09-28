#!/bin/bash

TENGINE_OPR_HOME=$HOME/tengine-opr

if [[ -d "$TENGINE_OPR_HOME" ]]; then

  cd $TENGINE_OPR_HOME
  sbin/nginx -s stop

  cd $HOME
  rm -rf tengine-opr
fi

tar -xzvf tengine-opr.tar.gz
rm -f tengine-opr.tar.gz

mv $HOME/webopr.war $TENGINE_OPR_HOME/html
cd $TENGINE_OPR_HOME/html
unzip -o webopr.war

mv $HOME/nginx.conf $TENGINE_OPR_HOME/conf
mv $HOME/baseUrl.js $TENGINE_OPR_HOME/html/static/conf

sleep 5

cd $TENGINE_OPR_HOME
sbin/nginx
