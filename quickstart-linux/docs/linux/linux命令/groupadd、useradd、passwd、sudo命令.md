1、Linux 新建用户、用户组以及为新用户分配权限
2、linux普通用户添加root权限
3、linux如何查看所有的用户和组信息




参考
https://www.runoob.com/linux/linux-user-manage.html

---------------------------------------------------------------------------------------------------------------------

Linux 新建用户、用户组以及为新用户分配权限


用户组的添加和删除：
groupadd testgroup 组的添加
groupdel testgroup 组的删除
说明：组的增加和删除信息会在etc目录的group文件中体现出来。


添加用户：useradd -m 用户名  然后设置密码  passwd 用户名
删除用户：userdel  -r  用户名


新建用户、设置密码
useradd -md /home/aioppf -s /bin/csh aioppf
echo 'testroot' | passwd --stdin aioppf



# useradd –d /home/sam -m sam

passwd 选项 用户名


groupadd gatewaytestgroup

useradd gatewaytest
passwd gatewaytest


授权用户访问路径
sudo chown -R aifgw:aifgw /app


创建用户
useradd -d /app/aifgw -m aifgw
passwd aifgw

用户授权
chown -R aifgw:aifgw /app/aifgw
chmod 760 /app/aifgw



参考
https://blog.csdn.net/championhengyi/article/details/78293837
https://blog.csdn.net/yue7603835/article/details/73699258
https://blog.csdn.net/li_101357/article/details/69367457

---------------------------------------------------------------------------------------------------------------------

linux普通用户添加root权限


linux新建用户并增加sudo权限

修改sudoers文件，加入写权限：

chmod +w /etc/sudoers
vi /etc/sudoers
在root ALL=(ALL) ALL下面复制一行同样的只不过root改成你的用户名
敲:wq保存后，撤销sudoers文件写权限：
chmod -w /etc/sudoers



.增加sudo（root）权限
一般三种方法

一种是sudo usermod -aG sudo username
一种是修改/etc/sudoers文件，在root ALL=(ALL) ALL下面复制一行同样的只不过root改成你的用户名
一种是修改/etc/passwd 找到自己的用户一行吧里面的用户id改成0

————————————————
版权声明：本文为CSDN博主「默一鸣」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/yimingsilence/article/details/80738008


---------------------------------------------------------------------------------------------------------------------
linux如何查看所有的用户和组信息


cat /etc/passwd查看所有的用户信息
cat /etc/group查看所有组信息

roups 查看当前登录用户的组内成员
groups test 查看test用户所在的组,以及组内成员
whoami 查看当前登录用户名



---------------------------------------------------------------------------------------------------------------------







