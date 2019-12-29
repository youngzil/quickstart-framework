#!/bin/bash

rm -f ant;
case $1 in
        10.7)
          ln -s /home/aifgw/apache-ant-1.10.7 ant
          echo "change to ant 1.10.7"
          ;;
        9.14)
          ln -s /home/aifgw/apache-ant-1.9.14 ant
          echo "change to ant 1.10.7"
          ;;
        *)
          ln -s /home/aifgw/apache-ant-1.10.7 ant
          echo "default to ant 1.10.7"
          ;;
esac
