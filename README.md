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

