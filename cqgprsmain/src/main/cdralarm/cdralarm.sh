#!/bin/bash
ip="10.190.94.7"
username="hdsjj"
password="hdsjj@123"
#datetime=`date +"%Y%m%d%H%M"`
today="$1"
command1="ls -lt /var/opt/BGw/ServerGroup1/Server1/BGwTTStorage/STS/working|head -2"
command2="ls -lt /var/opt/BGw/ServerGroup1/Server1/BGwTTStorage/STS/$today-GGSNCQ02.J20R4|head -2"
(sleep 1;echo "$username";sleep 1 ;echo "$password" ;sleep 1 ; echo "$command1" ;sleep 1 ; echo "$command2" ;sleep 1 ;echo "exit") | telnet $ip