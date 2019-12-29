#!/bin/bash
cd /app/aifgw/aifgw-security-1.0
tar -czvf logs-0.tar.gz logs/
mv logs-0.tar.gz ~/

cd /app/aifgw/aifgw-security-1.1
tar -czvf logs-1.tar.gz logs/
mv logs-1.tar.gz ~/

cd /app/aifgw/aifgw-security-1.2
tar -czvf logs-2.tar.gz logs/
mv logs-2.tar.gz ~/

cd ~
tar -czvf log.tar.gz logs*
rm logs-*
