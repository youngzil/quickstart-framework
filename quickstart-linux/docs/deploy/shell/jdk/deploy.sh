#!/bin/bash

echo "export JAVA_HOME=$HOME/java" >> $HOME/.bashrc
#echo "export JAVA_HOME=$HOME/java" >> $HOME/.bash_profile
#echo "export CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar" >> $HOME/.bash_profile
#echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> $HOME/.bash_profile
echo "export PATH=\$JAVA_HOME/bin:\$PATH" >> $HOME/.bashrc
source $HOME/.bashrc

JAVA_HOME_TEMP=$HOME/jdk1.8.0_221
if [[ -d "$JAVA_HOME_TEMP" ]]; then
  cd $HOME
  rm -rf jdk1.8.0_221
fi

cd $HOME
tar -xzvf jdk-8u221-linux-x64.tar.gz
rm -f  jdk-8u221-linux-x64.tar.gz

ln -s /home/aifgw/jdk1.8.0_221 java

java -version
