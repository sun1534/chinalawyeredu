#!/bin/bash
ip="$1"
username="$2"
password="$3"
command1="more /logs/er_data_log/tmp/er_data_log.* | grep ${4}"
(sleep 1;echo "$username";sleep 1 ;echo "$password" ;sleep 1 ; echo "$command1" ;sleep 2 ; echo "exit") | telnet $ip