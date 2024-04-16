Restaurar database:

restore: mariadb -u root -p'pass' db < bkp.sql

dump: mariadb-dump -u root -p'pass'  db > ./bkp/bkp.sql
