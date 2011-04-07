package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Hibernate;

import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;

/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM
 * 命令名称：Gb链路状态查看
命令位置：在菜单SGSN—标准维护命令下

原始命令
在SGSN上 cd /Core/coreUser,运行脚本 ./gbcheck.sh

cd  /Core/courUser
./gbcheck.sh                

原始输出结果举例如下：
-----------------------------------------------------------------------------------------
BSC     NSEI    NSVCI   BOARD   TRUNK   FRC     DLCI    BLOCKING-ST     OP-ST   Comment
-----------------------------------------------------------------------------------------
CQGM042B4        411     41101  3,6,1,1   6      1      411     blocked         dead    frPvcShow "wan161",411
CQGM042B4        411     41102  3,4,1,1   7      1      412     blocked         dead    frPvcShow "wan193",412
CQGM042B4        411     41103  3,7,1,1   6      1      413     blocked         dead    frPvcShow "wan161",413
CQGM042B4        411     41104  2,6,1,1   2      1      915     blocked         dead    frPvcShow "wan33",915
CQGM042B4        411     41105  2,6,1,1   5      1      916     blocked         dead    frPvcShow "wan129",916
CQGM121B4        416     41601  3,2,1,1   1      1      454     deblocked       alive
CQGM121B4        416     41602  3,3,1,1   1      1      455     deblocked       alive
CQGM121B4        416     41603  3,4,1,1   1      1      456     deblocked       alive
CQGM121B4        416     41604  3,5,1,1   1      1      457     deblocked       alive
CQGM121B4        416     41605  3,2,1,1   7      1      485     deblocked       alive
CQGM121B4        416     41606  3,3,1,1   7      1      486     deblocked       alive
CQGM121B4        416     41607  3,6,1,1   1      1      458     deblocked       alive
CQGM121B4        416     41608  3,7,1,1   1      1      459     deblocked       alive
CQGM121B4        416     41609  3,8,1,1   1      1      460     deblocked       alive
CQGM121B4        416     41610  3,2,1,1   2      1      461     deblocked       alive
CQGM121B4        416     41611  3,2,1,1   4      1      930     deblocked       alive
CQGM121B4        416     41612  3,3,1,1   4      1      931     deblocked       alive
CQGM121B4        416     41613  3,6,1,1   3      1      932     deblocked       alive
CQGM121B5        420     42001  3,3,1,1   2      1      462     deblocked       alive
CQGM121B5        420     42002  3,4,1,1   2      1      463     deblocked       alive
CQGM121B5        420     42003  3,5,1,1   2      1      464     deblocked       alive
CQGM121B5        420     42004  3,2,1,1   5      1      487     deblocked       alive
CQGM121B5        420     42005  3,3,1,1   5      1      488     deblocked       alive
CQGM121B5        420     42006  3,4,1,1   6      1      495     deblocked       alive
CQGM121B5        420     42007  2,7,1,1   6      1      919     deblocked       alive
CQGM121B5        420     42008  2,8,1,1   5      1      920     deblocked       alive
CQGM121B5        420     42009  2,5,1,1   6      1      921     deblocked       alive
CQGM042B2        432     43201  2,3,1,1   2      1      432     blocked         dead    frPvcShow "wan33",432
CQGM042B2        432     43202  2,4,1,1   2      1      433     blocked         dead    frPvcShow "wan33",433
CQGM042B2        432     43203  2,5,1,1   2      1      441     blocked         dead    frPvcShow "wan33",441
CQGM042B2        432     43204  2,7,1,1   2      1      442     blocked         dead    frPvcShow "wan33",442
CQGM042B2        432     43205  2,8,1,1   2      1      443     blocked         dead    frPvcShow "wan33",443
CQGM042B2        432     43206  2,9,1,1   2      1      499     blocked         dead    frPvcShow "wan33",499
CQGM042B2        432     43207  2,4,1,1   1      1      402     blocked         dead    frPvcShow "wan1",402
CQGM042B2        432     43208  2,8,1,1   1      1      403     blocked         dead    frPvcShow "wan1",403
CQGM042B2        432     43209  2,5,1,1   1      1      404     blocked         dead    frPvcShow "wan1",404
CQGM231B4        433     43301  2,8,1,1   6      1      409     deblocked       alive
CQGM231B4        433     43302  2,9,1,1   5      1      410     deblocked       alive
CQGM231B4        433     43303  2,9,1,1   6      1      421     deblocked       alive
CQGM231B4        433     43304  2,2,1,1   8      1      445     deblocked       alive
CQGM231B4        433     43305  2,3,1,1   7      1      446     deblocked       alive
CQGM231B4        433     43306  2,3,1,1   8      1      447     deblocked       alive
CQGM231B4        433     43307  3,5,1,1   6      1      405     deblocked       alive
CQGM231B4        433     43308  2,9,1,1   1      1      491     deblocked       alive
CQGM231B4        433     43309  2,9,1,1   8      1      492     deblocked       alive
CQGM231B4        433     43310  3,2,1,1   8      1      493     deblocked       alive
CQGM231B4        433     43311  3,3,1,1   8      1      494     deblocked       alive
CQGM231B4        433     43312  3,4,1,1   8      1      495     deblocked       alive
CQGM231B4        433     43313  3,5,1,1   8      1      496     deblocked       alive
GM231B5  434     43401  2,4,1,1   7      1      448     deblocked       alive
GM231B5  434     43402  2,4,1,1   8      1      449     deblocked       alive
GM231B5  434     43403  2,5,1,1   7      1      450     deblocked       alive
GM231B5  434     43404  2,5,1,1   8      1      451     deblocked       alive
GM231B5  434     43405  2,6,1,1   7      1      452     deblocked       alive
GM231B5  434     43406  2,6,1,1   8      1      453     deblocked       alive
GM231B5  434     43407  3,4,1,1   5      1      489     deblocked       alive
GM231B5  434     43408  3,5,1,1   5      1      490     deblocked       alive
GM231B5  434     43409  3,6,1,1   8      1      497     deblocked       alive
GM231B5  434     43410  3,7,1,1   8      1      498     deblocked       alive
GM231B5  434     43411  3,8,1,1   8      1      499     deblocked       alive
GM042B6  435     43501  3,3,1,1   3      1      454     deblocked       alive
GM042B6  435     43502  3,4,1,1   3      1      455     deblocked       alive
GM042B6  435     43503  3,5,1,1   3      1      456     deblocked       alive
GM042B6  435     43504  3,7,1,1   7      1      922     deblocked       alive
GM042B6  435     43505  3,8,1,1   7      1      923     deblocked       alive
GM231B6  436     43601  3,6,1,1   2      1      465     deblocked       alive
GM231B6  436     43602  3,7,1,1   2      1      466     deblocked       alive
GM231B6  436     43603  3,8,1,1   2      1      467     deblocked       alive
GM231B6  436     43604  3,2,1,1   3      1      468     deblocked       alive
GM231B6  436     43605  3,7,1,1   3      1      933     deblocked       alive
GM231B6  436     43606  3,8,1,1   3      1      934     deblocked       alive
GM231B6  436     43607  3,7,1,1   4      1      935     deblocked       alive
GM231B6  436     43608  3,6,1,1   7      1      936     deblocked       alive
GM231B6  436     43609  3,5,1,1   7      1      937     deblocked       alive
GM121B7  437     43701  2,2,1,1   3      1      469     deblocked       alive
GM121B7  437     43702  2,3,1,1   3      1      470     deblocked       alive
GM121B7  437     43703  2,3,1,1   4      1      471     deblocked       alive
GM121B7  437     43704  2,4,1,1   3      1      472     deblocked       alive
GM121B7  437     43705  2,3,1,1   5      1      473     deblocked       alive
GM121B7  437     43706  2,3,1,1   6      1      900     deblocked       alive
GM121B7  437     43707  2,4,1,1   5      1      901     deblocked       alive
GM121B7  437     43708  2,4,1,1   6      1      902     deblocked       alive
GM121B7  437     43709  2,5,1,1   5      1      903     deblocked       alive
GM121B7  437     43710  2,7,1,1   7      1      904     deblocked       alive
CQGM121B1        439     43901  2,6,1,1   3      1      481     blocked         dead    frPvcShow "wan65",481
CQGM121B1        439     43902  2,6,1,1   4      1      482     blocked         dead    frPvcShow "wan97",482
CQGM121B1        439     43903  2,7,1,1   3      1      483     blocked         dead    frPvcShow "wan65",483
CQGM121B1        439     43905  2,8,1,1   3      1      422     blocked         dead    frPvcShow "wan65",422
CQGM121B1        439     43906  2,8,1,1   4      1      423     blocked         dead    frPvcShow "wan97",423
CQGM121B2        440     44001  2,9,1,1   3      1      476     blocked         dead    frPvcShow "wan65",476
CQGM121B2        440     44002  2,9,1,1   4      1      477     blocked         dead    frPvcShow "wan97",477
CQGM121B2        440     44003  3,6,1,1   5      1      491     blocked         dead    frPvcShow "wan129",491
CQGM121B2        440     44004  3,7,1,1   5      1      492     blocked         dead    frPvcShow "wan129",492
CQGM121B2        440     44005  2,6,1,1   6      1      917     blocked         dead    frPvcShow "wan161",917
CQGM121B2        440     44006  2,7,1,1   5      1      918     blocked         dead    frPvcShow "wan129",918
GM042B5  441     44101  2,5,1,1   3      1      478     blocked         dead    frPvcShow "wan65",478
GM042B5  441     44102  2,5,1,1   4      1      479     blocked         dead    frPvcShow "wan97",479
GM042B5  441     44103  3,8,1,1   5      1      493     blocked         dead    frPvcShow "wan129",493
GM042B5  441     44104  3,2,1,1   6      1      494     blocked         dead    frPvcShow "wan161",494
GM042B5  441     44105  2,6,1,1   1      1      913     blocked         dead    frPvcShow "wan1",913
GM042B5  441     44106  2,7,1,1   1      1      914     blocked         dead    frPvcShow "wan1",914
GM121B6  442     44202  3,5,1,1   4      1      497     blocked         dead    frPvcShow "wan97",497
GM121B6  442     44203  3,6,1,1   4      1      498     blocked         dead    frPvcShow "wan97",498
GM231B7  443     44301  2,2,1,1   4      1      909     deblocked       alive
GM231B7  443     44302  2,2,1,1   5      1      910     deblocked       alive
GM231B7  443     44303  2,2,1,1   6      1      911     deblocked       alive
GM231B7  443     44304  2,2,1,1   7      1      912     deblocked       alive
-----------------------------------------------------------------------------------------
BSC     NSEI    NSVCI   BOARD   TRUNK   FRC     DLCI    BLOCKING-ST     OP-ST   Comment
-----------------------------------------------------------------------------------------


解析要求：
         1）对(BLOCKING-ST     OP-ST) 字段的取值进行计数

2）找出(BLOCKING-ST     OP-ST)状态为blocked     dead  的记录，输出对应的BSC  NSVCI。

3）如果NSEI相同的记录状态全是dead的，输出对应的BSC
4）如果BOARD前两位相同的记录（比如3,6）全是 dead的，输出对用的BOARD，BSC,  NSVCI 

展示结果：
          （时间戳）2010-11-18 10:00:02查询
SGSNn共120条GB链路，其中deblocked  alive 80条，manblocked  dead 2条， blocked  dead10条、manblocked alive 1条***。其中blocked  dead的链路有 44202（GM121B6）、44203（GM121B6 ）*****
     GM231B6可能存在问题，其n条链路状态全为DEAD
        板子3.6可能存在问题，其n条链路状态全为DEAD，NSVCI为44203(GM121B6 )、43608（GM231B6 ）*****（同一个板卡最多不超过8个,这里n<=8））


 */
public class CmdSGSNGBCheck extends Command {

	List<Map<String,String>> records=new ArrayList<Map<String,String>>();
	
	@Override
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n");
		int startid=0;
		for(String b:blocks){
			if(b.startsWith("--------")){
				startid++;
				continue;
			}else if(startid<2){
				continue;
			}
			String[] bs=b.split("\\s");
			GBCheckObj gbo=new GBCheckObj();
			try{
				int i=0;
				for(String bss:bs){
					if(!bss.equals("")){
						if(i==0){
							gbo.BSC=bss;
						}else if(i==1){
							gbo.NSEI=bss;
						}else if(i==2){
							gbo.NSVCI=bss;
						}else if(i==3){
							gbo.BOARD=bss;
						}else if(i==4){
							gbo.TRUNK=bss;
						}else if(i==5){
							gbo.FRC=bss;
						}else if(i==6){
							gbo.DLCI=bss;
						}else if(i==7){
							gbo.BLOCKING_ST=bss;
						}else if(i==8){
							gbo.OP_ST=bss;
						}
						i++;
					}
				}
			}catch(Exception e){}
			System.out.println(gbo);
		}
		
		
		String errorstr="";
		
		
		result=nowstr+"查询 \r\n"+"100条记录中 ";

		return result;
	}
	
	@Override
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		
		
		 orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
		String result=getresult(orgresult);		

		MemLog log=new MemLog();
		log.setCommandid(command.getCommandid());
		log.setCommandname(command.getCommananame());
		log.setCreatetime(new Timestamp(System.currentTimeMillis()));
		log.setDeviceid(device.getDeviceid());
		log.setDevicename(device.getDevicename());
		log.setResult(result);
		log.setOrgresult(Hibernate.createClob(orgresult));
		log.setUserid(Integer.parseInt(userid));
		System.out.println(log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
		try{
			memservice.save(log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		String ss="-----------------------------------------------------------------------------------------\r\n" +
				"BSC     NSEI    NSVCI   BOARD   TRUNK   FRC     DLCI    BLOCKING-ST     OP-ST   Comment\r\n" +
				"-----------------------------------------------------------------------------------------\r\n" +
				"CQGM042B4        411     41101  3,6,1,1   6      1      411     blocked         dead    frPvcShow \"wan161\",411\r\n" +
				"CQGM042B4        411     41102  3,4,1,1   7      1      412     blocked         dead    frPvcShow \"wan193\",412\r\n" +
				"CQGM042B4        411     41103  3,7,1,1   6      1      413     blocked         dead    frPvcShow \"wan161\",413\r\n" +
				"CQGM042B4        411     41104  2,6,1,1   2      1      915     blocked         dead    frPvcShow \"wan33\",915\r\n" +
				"CQGM042B4        411     41105  2,6,1,1   5      1      916     blocked         dead    frPvcShow \"wan129\",916\r\n" +
				"CQGM121B4        416     41601  3,2,1,1   1      1      454     deblocked       alive\r\n" +
				"CQGM121B4        416     41602  3,3,1,1   1      1      455     deblocked       alive\r\n" +
				"CQGM121B4        416     41603  3,4,1,1   1      1      456     deblocked       alive\r\n" +
				"CQGM121B4        416     41604  3,5,1,1   1      1      457     deblocked       alive\r\n" +
				"CQGM121B4        416     41605  3,2,1,1   7      1      485     deblocked       alive\r\n" +
				"CQGM121B4        416     41606  3,3,1,1   7      1      486     deblocked       alive\r\n" +
				"CQGM121B4        416     41607  3,6,1,1   1      1      458     deblocked       alive\r\n" +
				"CQGM121B4        416     41608  3,7,1,1   1      1      459     deblocked       alive\r\n" +
				"CQGM121B4        416     41609  3,8,1,1   1      1      460     deblocked       alive\r\n" +
				"CQGM121B4        416     41610  3,2,1,1   2      1      461     deblocked       alive\r\n" +
				"CQGM121B4        416     41611  3,2,1,1   4      1      930     deblocked       alive\r\n" +
				"CQGM121B4        416     41612  3,3,1,1   4      1      931     deblocked       alive\r\n" +
				"CQGM121B4        416     41613  3,6,1,1   3      1      932     deblocked       alive\r\n" +
				"CQGM121B5        420     42001  3,3,1,1   2      1      462     deblocked       alive\r\n" +
				"CQGM121B5        420     42002  3,4,1,1   2      1      463     deblocked       alive\r\n" +
				"CQGM121B5        420     42003  3,5,1,1   2      1      464     deblocked       alive\r\n" +
				"CQGM121B5        420     42004  3,2,1,1   5      1      487     deblocked       alive\r\n" +
				"CQGM121B5        420     42005  3,3,1,1   5      1      488     deblocked       alive\r\n" +
				"CQGM121B5        420     42006  3,4,1,1   6      1      495     deblocked       alive\r\n" +
				"CQGM121B5        420     42007  2,7,1,1   6      1      919     deblocked       alive\r\n" +
				"CQGM121B5        420     42008  2,8,1,1   5      1      920     deblocked       alive\r\n" +
				"CQGM121B5        420     42009  2,5,1,1   6      1      921     deblocked       alive\r\n" +
				"CQGM042B2        432     43201  2,3,1,1   2      1      432     blocked         dead    frPvcShow \"wan33\",432\r\n" +
				"CQGM042B2        432     43202  2,4,1,1   2      1      433     blocked         dead    frPvcShow \"wan33\",433\r\n" +
				"CQGM042B2        432     43203  2,5,1,1   2      1      441     blocked         dead    frPvcShow \"wan33\",441\r\n" +
				"CQGM042B2        432     43204  2,7,1,1   2      1      442     blocked         dead    frPvcShow \"wan33\",442\r\n" +
				"CQGM042B2        432     43205  2,8,1,1   2      1      443     blocked         dead    frPvcShow \"wan33\",443\r\n" +
				"CQGM042B2        432     43206  2,9,1,1   2      1      499     blocked         dead    frPvcShow \"wan33\",499\r\n" +
				"CQGM042B2        432     43207  2,4,1,1   1      1      402     blocked         dead    frPvcShow \"wan1\",402\r\n" +
				"CQGM042B2        432     43208  2,8,1,1   1      1      403     blocked         dead    frPvcShow \"wan1\",403\r\n" +
				"CQGM042B2        432     43209  2,5,1,1   1      1      404     blocked         dead    frPvcShow \"wan1\",404\r\n" +
				"CQGM231B4        433     43301  2,8,1,1   6      1      409     deblocked       alive\r\n" +
				"CQGM231B4        433     43302  2,9,1,1   5      1      410     deblocked       alive\r\n" +
				"CQGM231B4        433     43303  2,9,1,1   6      1      421     deblocked       alive\r\n" +
				"CQGM231B4        433     43304  2,2,1,1   8      1      445     deblocked       alive\r\n" +
				"CQGM231B4        433     43305  2,3,1,1   7      1      446     deblocked       alive\r\n" +
				"CQGM231B4        433     43306  2,3,1,1   8      1      447     deblocked       alive\r\n" +
				"CQGM231B4        433     43307  3,5,1,1   6      1      405     deblocked       alive\r\n" +
				"CQGM231B4        433     43308  2,9,1,1   1      1      491     deblocked       alive\r\n" +
				"CQGM231B4        433     43309  2,9,1,1   8      1      492     deblocked       alive\r\n" +
				"CQGM231B4        433     43310  3,2,1,1   8      1      493     deblocked       alive\r\n" +
				"CQGM231B4        433     43311  3,3,1,1   8      1      494     deblocked       alive\r\n" +
				"CQGM231B4        433     43312  3,4,1,1   8      1      495     deblocked       alive\r\n" +
				"CQGM231B4        433     43313  3,5,1,1   8      1      496     deblocked       alive\r\n" +
				"GM231B5  434     43401  2,4,1,1   7      1      448     deblocked       alive\r\n" +
				"GM231B5  434     43402  2,4,1,1   8      1      449     deblocked       alive\r\n" +
				"GM231B5  434     43403  2,5,1,1   7      1      450     deblocked       alive\r\n" +
				"GM231B5  434     43404  2,5,1,1   8      1      451     deblocked       alive\r\n" +
				"GM231B5  434     43405  2,6,1,1   7      1      452     deblocked       alive\r\n" +
				"GM231B5  434     43406  2,6,1,1   8      1      453     deblocked       alive\r\n" +
				"GM231B5  434     43407  3,4,1,1   5      1      489     deblocked       alive\r\n" +
				"GM231B5  434     43408  3,5,1,1   5      1      490     deblocked       alive\r\n" +
				"GM231B5  434     43409  3,6,1,1   8      1      497     deblocked       alive\r\n" +
				"GM231B5  434     43410  3,7,1,1   8      1      498     deblocked       alive\r\n" +
				"GM231B5  434     43411  3,8,1,1   8      1      499     deblocked       alive\r\n" +
				"GM042B6  435     43501  3,3,1,1   3      1      454     deblocked       alive\r\n" +
				"GM042B6  435     43502  3,4,1,1   3      1      455     deblocked       alive\r\n" +
				"GM042B6  435     43503  3,5,1,1   3      1      456     deblocked       alive\r\n" +
				"GM042B6  435     43504  3,7,1,1   7      1      922     deblocked       alive\r\n" +
				"GM042B6  435     43505  3,8,1,1   7      1      923     deblocked       alive\r\n" +
				"GM231B6  436     43601  3,6,1,1   2      1      465     deblocked       alive\r\n" +
				"GM231B6  436     43602  3,7,1,1   2      1      466     deblocked       alive\r\n" +
				"GM231B6  436     43603  3,8,1,1   2      1      467     deblocked       alive\r\n" +
				"GM231B6  436     43604  3,2,1,1   3      1      468     deblocked       alive\r\n" +
				"GM231B6  436     43605  3,7,1,1   3      1      933     deblocked       alive\r\n" +
				"GM231B6  436     43606  3,8,1,1   3      1      934     deblocked       alive\r\n";
		
		CmdSGSNGBCheck c=new CmdSGSNGBCheck();
		String result=c.getresult(ss);
		System.out.println("result:\r\n"+result);
		
	}
	
}
class GBCheckObj{
	String BSC;
	String NSEI;
	String NSVCI;
	String BOARD;
	String TRUNK;
	String FRC;
	String DLCI;
	String BLOCKING_ST;
	String OP_ST;
	public String toString(){
		return "<"+BSC+"><"+NSEI+"><"+NSVCI+"><"+BOARD+"><"+TRUNK+"><"+FRC+"><"+DLCI+"><"+BLOCKING_ST+"><"+OP_ST+">";
	}
}
