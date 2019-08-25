cd $HOME/aifgw
rm -rf aifgw-backend-parent/*
svn export --force --username USERNAME --password PASSWORD http://IP:PORT/svn/AIOPENPLATFORM/aifgateway/aifgw-backend-parent/  aifgw-backend-parent

export MAVEN_HOME=$HOME/aifgw/apache-maven-3.6.1
export PATH=$PATH:$MAVEN_HOME/bin

#mvn -v
cd aifgw-backend-parent
mvn -Prelease-all -DskipTests clean install -U
