#!/bin/bash
ip="$1"
username="$2"
password="$3"
command1="gsh delete_event_rec_session -msisdn 86${4}"
(sleep 1;echo "$username";sleep 1 ;echo "$password" ;sleep 1 ; echo "$command1" ;sleep 1 ; echo "exit") | telnet $ip