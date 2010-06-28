order1="LGI: OP=\"$1\",PWD=\"$2\";"     
ip="$3"
order2="DSP USPUPDP:IMSI=\"${4}\";%%"
(sleep 1;echo "$order1" ;sleep 1 ;echo "$order2" ;sleep 2 ;echo "exit") | telnet $ip