#!/bin/bash
set -e
id=`cat ~/.ssh/id_rsa.pub`
echo "#!/bin/bash">>sshph.sh
echo "export PATH=${HOME}/trusthost/sshpass-1.06:\$PATH">>sshph.sh

function trusthost()
{
    echo "sshpass -p "$passwd" ssh -o StrictHostKeyChecking=no ${username}@$host \"mkdir -p ~/.ssh;echo $id >~/.ssh/authorized_keys;chmod 700 .ssh;chmod 600 .ssh/authorized_keys \" &>/dev/null">>sshph.sh
	echo " 
	 if [ \$? -eq 0 ];then
   		   echo   "Deploying pub_key for ${username}@$host......Success!" /bin/true
	   else
			echo  "Deploying pub_key for ${username}@$host......Failed!" /bin/false
	   fi
">>sshph.sh
}

cat hostlist|while read line
   do
	  host=`echo "$line"|awk '{print $1}'`
	  username=`echo "$line"|awk '{print $2}'`
	  passwd=`echo "$line"|awk '{print $3}'`
	  trusthost
   done
chmod 755 -R *   
 sh sshph.sh
 rm sshph.sh
