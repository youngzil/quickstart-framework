#!/bin/bash

rm -f java;
case $1 in
        1.5)
          ln -s /usr/java/jdk1.5.0_22 java
          ;;
        1.6)
          ln -s /opt/jdk1.6.0_45 java
          ;;
        1.8)
          ln -s /opt/jdk1.8.0_172 java
          echo "change to jdk 1.8"
          ;;
        1.7)
          ln -s /opt/jdk1.7.0_80 java
          echo "change to jdk 1.7"
          ;;
        *)
          ln -s /opt/jdk1.6.0_45 java
          echo "default to jdk 1.6"
          ;;
esac
#ssh aibuild45@10.1.243.22
