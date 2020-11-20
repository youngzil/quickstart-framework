#!/bin/bash

cd $HOME/gatewaytest
rm -rf gatewaytest-backend-parent/*
svn export --force --username XXXUSERNAME --password XXXPASSWORD http://IP:PORT/svn/AIOPENPLATFORM/aifgateway/gatewaytest-backend-parent/  gatewaytest-backend-parent

export MAVEN_HOME=$HOME/gatewaytest/apache-maven-3.6.1
export PATH=$PATH:$MAVEN_HOME/bin

#mvn -v
cd gatewaytest-backend-parent
mvn -Prelease-all -DskipTests clean install -U
