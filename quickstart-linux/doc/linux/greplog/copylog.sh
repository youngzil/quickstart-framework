#!/bin/bash

set -e
echo "#!/bin/bash">>temp.sh

date=$1
nodeCount=5;

function trusthost()
{
        commandStr="";
        for((integer = 1; integer <= nodeCount; integer++))
        do
                dir="${host}-${integer}-$date";
                mkdir -p $dir
                echo "scp ${username}@$host:~/esb_${integer}/app/oppf/log/oppf_esb-$date.log $dir/" >>temp.sh
        done
}

cat hostlist|while read line
   do
          host=`echo "$line"|awk '{print $1}'`
          username=`echo "$line"|awk '{print $2}'`
          passwd=`echo "$line"|awk '{print $3}'`
          trusthost
   done

echo "tar -czvf $date-esb-log.tar.gz 10.* ">>temp.sh
echo "rm -r 10.* ">>temp.sh

chmod +x *   
sh temp.sh
rm temp.sh