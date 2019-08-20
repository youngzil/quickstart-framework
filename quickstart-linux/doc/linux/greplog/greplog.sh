#!/bin/bash

set -e
echo "#!/bin/bash">>temp.sh

date=$1
str=$2
nodeCount=5;

function trusthost()
{
        commandStr="";
        for((integer = 1; integer <= nodeCount; integer++))
        do
                commandStr="${commandStr} echo ${host}/esb_${integer}/app/oppf/log/oppf_esb-$date.log >> ${host}.log;"
                commandStr="${commandStr} grep $str esb_${integer}/app/oppf/log/oppf_esb-$date.log >> ${host}.log;"
        done
        echo "ssh -t ${username}@$host \" ${commandStr} \" &>/dev/null">>temp.sh
        echo "scp ${username}@$host:/app/aiesb/${host}.log .;">>temp.sh
        echo "ssh -t ${username}@$host \" rm ${host}.log  \" &>/dev/null">>temp.sh
}

cat hostlist|while read line
   do
          host=`echo "$line"|awk '{print $1}'`
          username=`echo "$line"|awk '{print $2}'`
          passwd=`echo "$line"|awk '{print $3}'`
          trusthost
   done

echo "cat *.log > merge.log ">>temp.sh
echo "rm 10.*.log ">>temp.sh

chmod +x *   
sh temp.sh
rm temp.sh