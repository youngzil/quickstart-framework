#!/bin/bash

#132
mkdir -p node0
cd node0
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error.log .
cd ~

mkdir -p node1
cd node1
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error.log .
cd ~

tar -czvf aifgw-log-132.tar.gz node*

rm -rf node0
rm -rf node1


#!/bin/bash

#133
mkdir -p node0
cd node0
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error.log .
cd ~

mkdir -p node1
cd node1
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error.log .
cd ~

tar -czvf aifgw-log-133.tar.gz node*

rm -rf node0
rm -rf node1


#!/bin/bash

#159
mkdir -p node0
cd node0
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error.log .
cd ~

mkdir -p node1
cd node1
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error.log .
cd ~

tar -czvf aifgw-log-159.tar.gz node*

rm -rf node0
rm -rf node1


#!/bin/bash

#34
mkdir -p node0
cd node0
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.0/logs/gateway/gateway-back-error.log .
cd ~

mkdir -p node1
cd node1
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error-2020-01-15-* .
cp ~/aifgw-backend-1.1/logs/gateway/gateway-back-error.log .
cd ~

tar -czvf aifgw-log-34.tar.gz node*

rm -rf node0
rm -rf node1

