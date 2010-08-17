#!/bin/bash
ip="10.190.93.168"
username="hdsjj"
password="hdsjj^&*"
command1="show system storage"
(sleep 1;echo "$username";sleep 1 ;echo "$password" ;sleep 1 ; echo "$command1" ;sleep 1;echo "exit") | telnet $ip