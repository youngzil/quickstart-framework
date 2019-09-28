#!/bin/bash

echo "export ANT_HOME=$HOME/ant" >> $HOME/.bash_profile
echo "export PATH=\$ANT_HOME/bin:\$PATH" >> $HOME/.bash_profile

source $HOME/.bash_profile

cd $HOME
tar -xzvf apache-ant-1.10.7-bin.tar.gz
rm -f apache-ant-1.10.7-bin.tar.gz

ln -s /home/aifgw/apache-ant-1.10.7 ant

ant -version
