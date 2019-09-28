#!/bin/bash

echo "export GRADLE_HOME=$HOME/gradle" >> $HOME/.bash_profile
echo "export PATH=\$GRADLE_HOME/bin:\$PATH" >> $HOME/.bash_profile

source $HOME/.bash_profile

cd $HOME
unzip -o gradle-5.6.2-bin.zip
rm -f gradle-5.6.2-bin.zip

ln -s /home/aifgw/gradle-5.6.2 gradle

gradle -v
