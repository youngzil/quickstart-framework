#!/bin/bash

srcHome=/Users/yangzl/workspace/gateway

deployHost=20.26.85.227
username=aifgw
password=aifgw



DeploySecurity()
{
#deploy security
cd $srcHome/aifgw-security-parent/

mvn -Pbuild-test -DskipTests clean install -U

cd aifgw-security-distribution/target

ftp -n<<!
open $deployHost
user $username $password
binary
cd ~
prompt
rename aifgw-security-1.0.tar.gz aifgw-security-1.0-bak.tar.gz
put aifgw-security-1.0.tar.gz
close
bye
!
}

DeploySecurity

