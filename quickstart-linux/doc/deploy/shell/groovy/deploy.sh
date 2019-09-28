#!/bin/bash

echo "export GROOVY_HOME=$HOME/groovy" >> $HOME/.bash_profile
echo "export PATH=\$GROOVY_HOME/bin:\$PATH" >> $HOME/.bash_profile

source $HOME/.bash_profile

cd $HOME
unzip -o apache-groovy-sdk-2.5.8.zip
rm -f apache-groovy-sdk-2.5.8.zip

ln -s /home/aifgw/groovy-2.5.8 groovy

groovy -version
