#!/bin/bash

rm -f groovy;
case $1 in
        2.5.8)
          ln -s /home/aifgw/groovy-2.5.8 groovy
          echo "change to groovy 2.5.8"
          ;;
        2.4.17)
          ln -s /home/aifgw/groovy-2.4.17 groovy
          echo "change to groovy 2.4.17"
          ;;
        *)
          ln -s /home/aifgw/groovy-2.5.8 groovy
          echo "change to groovy 2.5.8"
          ;;
esac
