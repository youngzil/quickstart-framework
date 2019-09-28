#!/bin/bash

rm -f gradle;
case $1 in
        5.6.2)
          ln -s /home/aifgw/gradle-5.6.2 gradle
          echo "change to gradle 5.6.2"
          ;;
        4.10.3)
          ln -s /home/aifgw/gradle-4.10.3 gradle
          echo "change to gradle 4.10.3"
          ;;
        *)
          ln -s /home/aifgw/gradle-5.6.2 gradle
          echo "default to gradle 5.6.2"
          ;;
esac