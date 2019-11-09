#!/bin/bash

NODE_PORT=41301

function writeCode()
{
  NODE_COUNT=10
  for((i=1;i<=$NODE_COUNT;i++));
  do
      echo $host
      echo $NODE_PORT
      echo "telnet $host $NODE_PORT" >> sshph.sh
      NODE_PORT=$(($NODE_PORT+1))
  done
}

echo "#!/bin/bash" > sshph.sh

cat hostlist|while read line
   do
          host=`echo "$line"|awk '{print $1}'`
          username=`echo "$line"|awk '{print $2}'`
          password=`echo "$line"|awk '{print $3}'`
          echo $host
          writeCode
   done

chmod 755 -R *
sh sshph.sh
rm sshph.sh
