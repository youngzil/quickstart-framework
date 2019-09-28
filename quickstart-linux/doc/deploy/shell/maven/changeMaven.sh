#!/bin/bash

rm -f maven;
case $1 in
        3.6.2)
          ln -s /home/aifgw/apache-maven-3.6.2 maven
          echo "change to maven 3.6.2"
          ;;
        3.5.4)
          ln -s /home/aifgw/apache-maven-3.5.4 maven
          echo "change to maven 3.5.4"
          ;;
        *)
          ln -s /home/aifgw/apache-maven-3.6.2 maven
          echo "change to maven 3.6.2"
          ;;
esac
