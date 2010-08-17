today=`date +"%Y%m%d"`
path=/export/home1/GPRS/JAVABIN/files/chrdest/hw$today
flag=`date +"%Y%m%d%H%M"`
echo $flag
if [ -d $path ] 
	then 
		echo "$path已经存在"
	else 
		mkdir $path
fi
/export/home1/GPRS/JAVABIN/loopchr.sh /export/home1/smlog/SESSIONONTIMEHUAWEI/SGSN7 $today $flag
/export/home1/GPRS/JAVABIN/loopchr.sh /export/home1/smlog/SESSIONONTIMEHUAWEI/SGSN8 $today $flag
/export/home1/GPRS/JAVABIN/loopchr.sh /export/home1/smlog/SESSIONONTIMEHUAWEI/SGSN9 $today $flag
echo "执行java的sh,以解析这个文件"
/export/home1/GPRS/JAVABIN/readHWErrorApns.sh $today $flag
