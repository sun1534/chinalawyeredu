#!/bin/bash
ftpip=10.190.93.168
srcdir=/export/home1/smlog/IPSERVICE
logdir=/export/home1/smlog/IPSERVICE/log
ftpuser=hdsjj
ftppwd=hdsjj^&*
ftplog=$logdir/auto_getipservice_`date +%Y%m%d`.log


#Ftp to get files
ftp -n<<! >> $ftplog
open $ftpip
user $ftpuser $ftppwd
binary
cd /var/log/ggsn
#lcd $srcdir
#get list_gras_$2.txt
#get list_wras_$2.txt
#get list_ip_service_address_$2.txt
bye
!
echo '####Finish to Get chsLog####' >>$ftplog