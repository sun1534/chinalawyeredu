#! /bin/bash
deepls() { 
  cd $1
  path=$1;
  sgsn=${path##*/} 
  echo $sgsn
  for x in * 
  do 
    if [ -f $x ] 
    then 
       echo $PWD/$x 
       /export/home1/GPRS/sgsnbill/sgsnbill $PWD/$x /export/home1/GPRS/JAVABIN/files/chrdest/hw$2/"$3"_"$sgsn"_"$x".txt 
       mv $PWD/$x /export/home1/GPRS/JAVABIN/files/chrdest/hw$2/"$3"_"$x"
      #echo $2
    fi 
    
    if [ -d $x ] 
    then 
         (deepls "$PWD/$x") 
     fi 
  done
   #echo "ÍêÁË" 
}

export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/export/home/oracle/product/9.2/lib32:/export/home/oracle/product/9.2/lib:/export/home/oracle/product/9.2/lib:/opt/SUNWspro/lib:/LIB/lib:/LIB/lib:/usr/local/lib
deepls $1 $2 $3
echo $1"ÂÖÑ¯Íê±Ï" 
