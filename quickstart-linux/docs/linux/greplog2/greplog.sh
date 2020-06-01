#!/bin/bash

set -e
echo "#!/bin/bash">>temp.sh

mainAcct=$1
token=$2

function trusthost()
{
        commandStr="cd /data/logs/aifgw-dmz-security;";
        commandStr="${commandStr} grep -rn $mainAcct oauth2-server-error*.log >> ${host}.log;"
        commandStr="${commandStr} grep -rn 'Token无效' oauth2-server-error*.log | grep $token >> ${host}.log;"
        echo "ssh -t $host \" ${commandStr} \" &>/dev/null">>temp.sh
        echo "scp $host:/data/logs/aifgw-dmz-security/${host}.log .;">>temp.sh
        echo "ssh -t $host \"cd /data/logs/aifgw-dmz-security;rm -rf ${host}.log;\" &>/dev/null">>temp.sh
}

cat hostlist|while read line
   do
          host=`echo "$line"|awk '{print $1}'`
          trusthost
   done

echo "cat 10.72.*.log > merge.log ">>temp.sh
echo "rm 10.72.*.log ">>temp.sh

chmod +x *.sh
sh temp.sh
rm temp.sh
