#!/bin/bash

srcHome=/Users/yangzl/workspace/gateway

deployHost=20.26.85.227
username=gatewaytest
password=gatewaytest



DeploySecurity()
{
#deploy security
cd $srcHome/gatewaytest-security-parent/

mvn -Pbuild-test -DskipTests clean install -U

cd gatewaytest-security-distribution/target

ftp -n<<!
open $deployHost
user $username $password
binary
cd ~
prompt
rename gatewaytest-security-1.0.tar.gz gatewaytest-security-1.0-bak.tar.gz
put gatewaytest-security-1.0.tar.gz
close
bye
!
}

DeploySecurity

