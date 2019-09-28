#!/bin/bash

function install()
{
  echo "install $username@$host"

  scp $HOME/deploy/pkg/webopr/tengine-opr.tar.gz $username@$host:~
  scp $HOME/deploy/pkg/webopr/webopr.war $username@$host:~

  scp $HOME/deploy/config/webopr/nginx.conf $username@$host:~
  scp $HOME/deploy/config/webopr/baseUrl.js $username@$host:~

	scp $HOME/deploy/shell/webopr/deploy.sh $username@$host:~
	ssh -n $username@$host "sh deploy.sh;rm -f deploy.sh"
}

cat hostlist|while read line
   do
	  host=`echo "$line"|awk '{print $1}'`
	  username=`echo "$line"|awk '{print $2}'`
	  password=`echo "$line"|awk '{print $3}'`
	  install
   done
