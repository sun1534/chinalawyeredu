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
 * 
 * 命令名称：SGSN业务情况查询
命令位置：在菜单SGSN—标准维护命令下

原始命令
在SGSN上运行命令 eci stats        :
eci stats
原始输出结果举例如下：
This is eci version 1.0.0 operating on an SGSN/MME '09A-00-03' (WG).
SGSN-G connection statistics: 

-   63908 SGSN-G connections active    (  7.04 %).
-  159642 SGSN-G connections attached  ( 17.58 %).
-  683961 SGSN-G connections idle      ( 75.31 %).
-     677 SGSN-G connections unstable  (  0.30 %)*.
---------------------------------------------
-  908188 SGSN-G connections in total.

SGSN-W connection statistics: 

-       5 SGSN-W connections active    (  3.11 %).
-      77 SGSN-W connections attached  ( 47.83 %).
-      72 SGSN-W connections idle      ( 44.72 %).
-       7 SGSN-W connections unstable  (  7.87 %)*.
---------------------------------------------
-     161 SGSN-W connections in total.


MBMS connection statistics: 

-       0 MBMS connections active    (     - %).
-       0 MBMS connections attached  (     - %).
-       0 MBMS connections idle      (     - %).
-       0 MBMS connections unstable  (     - %)*.
---------------------------------------------
-       0 MBMS connections in total.


*) See 'eci help stats' for info
->ok
解析要求：
1）结果截取，只SGSN-G connection statistics: SGSN-W connection statistics: 两个部分的



展示结果：
          （时间戳）2010-11-18 10:00:02查询
SGSN-G connection statistics: 
 */
public class CmdSGSNEciStats extends Command {

	List<StatLinkObj> objs=new ArrayList<StatLinkObj>(); 
	
	List<StatObj> statobj=new ArrayList<StatObj>();
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n\r\n");
		
		
		
		result=nowstr+"查询 \r\n";
		result+=orgstr.substring(0,orgstr.indexOf("\r\n\r\n\r\n"));
		return result;
	}

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
		String ss="This is eci version 1.0.0 operating on an SGSN/MME '09A-00-03' (WG).\r\n" +
				"SGSN-G connection statistics: \r\n" +
				"\r\n" +
				"-   63908 SGSN-G connections active    (  7.04 %).\r\n" +
				"-  159642 SGSN-G connections attached  ( 17.58 %).\r\n" +
				"-  683961 SGSN-G connections idle      ( 75.31 %).\r\n" +
				"-     677 SGSN-G connections unstable  (  0.30 %)*.\r\n" +
				"---------------------------------------------\r\n" +
				"-  908188 SGSN-G connections in total.\r\n" +
				"\r\n" +
				"SGSN-W connection statistics: \r\n" +
				"-       5 SGSN-W connections active    (  3.11 %).\r\n" +
				"\r\n" +
				"-      77 SGSN-W connections attached  ( 47.83 %).\r\n" +
				"-      72 SGSN-W connections idle      ( 44.72 %).\r\n" +
				"-       7 SGSN-W connections unstable  (  7.87 %)*.\r\n" +
				"---------------------------------------------\r\n" +
				"-     161 SGSN-W connections in total.\r\n" +
				"\r\n" +
				"\r\n" +
				"MBMS connection statistics: \r\n" +
				"\r\n" +
				"-       0 MBMS connections active    (     - %).\r\n" +
				"-       0 MBMS connections attached  (     - %).\r\n" +
				"-       0 MBMS connections idle      (     - %).\r\n" +
				"-       0 MBMS connections unstable  (     - %)*.\r\n" +
				"---------------------------------------------\r\n" +
				"-       0 MBMS connections in total.\r\n" +
				"\r\n" +
				"\r\n" +
				"*) See 'eci help stats' for info";
		
		CmdSGSNEciStats c=new CmdSGSNEciStats();
		String result=c.getresult(ss);
		System.out.println("result:\r\n"+result);
		
	}
}