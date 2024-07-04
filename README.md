Bem vindo ao IManage (Back End)

![image](https://github.com/conradocjo/IManage/assets/29169349/03e68d90-01eb-47fa-b51b-aaade70f6519)


Restaurar database:

restore: mariadb -u root -p'pass' db < bkp.sql

dump: mariadb-dump -u root -p'pass'  db > ./bkp/bkp.sql


Rotina de backupt:

#!/bin/bash

now=$(date +"%m-%d-%Y".sql)

echo $now

mariadb-dump -u imanage -p'senha' imanageprod > ./bkp/$now



Instalando crontab:

pkg install cronie termux-services
sv-enable crond
crontab -e 

Install mysql amd init:

yes | pkg upgrade && pkg in git python -y && cd && git clone "https://github.com/anay-p/mysql-for-termux.git" && cd mysql-for-termux && python installer.py && source ~/../usr/etc/bash.bashrc

subir o mysql
cd mysql-for-termux && python installer.py && source ~/../usr/etc/bash.bashrc


O Front end da aplicação é o:

https://github.com/conradocjo/imanage-frt
