#!/bin/bash

echo "export MAVEN_HOME=$HOME/maven" >> $HOME/.bash_profile
echo "export PATH=\$MAVEN_HOME/bin:\$PATH" >> $HOME/.bash_profile

source $HOME/.bash_profile

cd $HOME
tar -xzvf apache-maven-3.6.2-bin.tar.gz
rm -f apache-maven-3.6.2-bin.tar.gz

ln -s /home/aifgw/apache-maven-3.6.2 maven

mvn -v
