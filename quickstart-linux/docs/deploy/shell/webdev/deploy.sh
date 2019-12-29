#!/bin/bash

TENGINE_DEV_HOME=$HOME/tengine-dev

if [[ -d "$TENGINE_DEV_HOME" ]]; then

  cd $TENGINE_DEV_HOME
  sbin/nginx -s stop

  cd $HOME
  rm -rf tengine-dev
fi

tar -xzvf tengine-dev.tar.gz
rm -f tengine-dev.tar.gz

mv $HOME/webdev.war $TENGINE_DEV_HOME/html
cd $TENGINE_DEV_HOME/html
unzip -o webdev.war

mv $HOME/nginx.conf $TENGINE_DEV_HOME/conf
mv $HOME/baseUrl.js $TENGINE_DEV_HOME/html/static/conf

sleep 5

cd $TENGINE_DEV_HOME
sbin/nginx
