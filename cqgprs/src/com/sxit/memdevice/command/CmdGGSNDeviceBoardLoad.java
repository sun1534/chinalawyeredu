package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.Date;

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
 * 命令名称：设备板卡负荷查询
 * 命令位置：在菜单GGSN—标准维护命令下
 * 
 * 原始命令解析
 * 
 * show services ggsn status           
 * 
 * 原始输出结果举例如下：
 * Interface: gc-0/0/0
  External address: 221.177.188.33
  Internal address: 10.0.0.16
  Function: Node Controller
  Hardware version: 1.11.0.0 , Software version: 4.0.86.4 
  CPU0 utilization: 3 percent
  DRAM: 1072693248, Used: 254046208
  PDP service: Ready, Assigned PDP contexts: 123815, PDP capacity: 200000
  PDP services start time: Tue Jul 20 01:23:04 2010 (107403 microsecond)

Interface: gc-1/0/0
  External address: 221.177.188.34
  Internal address: 10.0.0.32
  Function: Session Controller
  Hardware version: 1.11.0.0 , Software version: 4.0.86.4 
  CPU0 utilization: 12 percent
  DRAM: 1072693248, Used: 288681984
  PDP service: Ready, Assigned PDP contexts: 61690, PDP capacity: 750000
  Weighted PDP context load in control: 83535
  Maximum filter depth per user 0, Mean: 0
  PDP services start time: Tue Jul 20 01:23:04 2010 (133106 microsecond)

Interface: gc-2/0/0
  External address: 221.177.188.35
  Internal address: 10.0.0.48
  Function: Session Controller
  Hardware version: 1.9.0.0 , Software version: 4.0.86.4 
  CPU0 utilization: 11 percent
  DRAM: 1072693248, Used: 289542144
  PDP service: Ready, Assigned PDP contexts: 62125, PDP capacity: 750000
  Weighted PDP context load in control: 83531
  Maximum filter depth per user 0, Mean: 0
  PDP services start time: Tue Jul 20 01:23:04 2010 (133702 microsecond)

Interface: gu-0/2/0
  External address: 221.177.188.49
  Internal address: 10.0.0.18
  Function: Packet Forwarder
  Hardware version: 1.9.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 50 percent
  DRAM: 1073741824, Used: 308856664
  PDP service: Ready, Assigned PDP contexts: 13770, PDP capacity: 500000
  Weighted PDP context load in payload: 13770
  PDP services start time: Tue Jul 20 01:23:52 2010 (549923 microsecond)

Interface: gu-1/2/0
  External address: 221.177.188.50
  Internal address: 10.0.0.34
  Function: Packet Forwarder
  Hardware version: 1.9.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 45 percent
  DRAM: 1073741824, Used: 308229848
  PDP service: Ready, Assigned PDP contexts: 14080, PDP capacity: 500000
  Weighted PDP context load in payload: 14080
  PDP services start time: Tue Jul 20 01:23:52 2010 (719228 microsecond)

Interface: gu-3/0/0
  External address: 221.177.188.51
  Internal address: 10.0.0.64
  Function: Packet Forwarder
  Hardware version: 1.9.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 46 percent
  DRAM: 1073741824, Used: 309885048
  PDP service: Ready, Assigned PDP contexts: 15133, PDP capacity: 500000
  Weighted PDP context load in payload: 15133
  PDP services start time: Tue Jul 20 01:23:52 2010 (856344 microsecond)

Interface: gu-2/1/0
  External address: 221.177.188.52
  Internal address: 10.0.0.49
  Function: Packet Forwarder
  Hardware version: 1.11.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 27 percent
  DRAM: 1073741824, Used: 287597160
  PDP service: Ready, Assigned PDP contexts: 13553, PDP capacity: 500000
  Weighted PDP context load in payload: 13553
  PDP services start time: Tue Jul 20 01:23:53 2010 (180899 microsecond)

Interface: gu-1/3/0
  External address: 221.177.188.53
  Internal address: 10.0.0.35
  Function: Packet Forwarder
  Hardware version: 1.11.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 41 percent
  DRAM: 1073741824, Used: 308490208
  PDP service: Ready, Assigned PDP contexts: 14047, PDP capacity: 500000
  Weighted PDP context load in payload: 14047
  PDP services start time: Tue Jul 20 01:23:53 2010 (863488 microsecond)

Interface: gu-2/2/0
  External address: 221.177.188.54
  Internal address: 10.0.0.50
  Function: Packet Forwarder
  Hardware version: 1.10.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 37 percent
  DRAM: 1073741824, Used: 289850608
  PDP service: Ready, Assigned PDP contexts: 13939, PDP capacity: 500000
  Weighted PDP context load in payload: 13939
  PDP services start time: Tue Jul 20 01:23:54 2010 (33746 microsecond)

Interface: gu-0/3/0
  External address: 221.177.188.55
  Internal address: 10.0.0.19
  Function: Packet Forwarder
  Hardware version: 1.11.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 28 percent
  DRAM: 1073741824, Used: 288837728
  PDP service: Ready, Assigned PDP contexts: 14121, PDP capacity: 500000
  Weighted PDP context load in payload: 14120
  PDP services start time: Tue Jul 20 01:23:54 2010 (127305 microsecond)

Interface: gu-3/1/0
  External address: 221.177.188.56
  Internal address: 10.0.0.65
  Function: Packet Forwarder
  Hardware version: 1.11.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 41 percent
  DRAM: 1073741824, Used: 307036336
  PDP service: Ready, Assigned PDP contexts: 13235, PDP capacity: 500000
  Weighted PDP context load in payload: 13235
  PDP services start time: Tue Jul 20 01:23:54 2010 (221063 microsecond)

Interface: gu-3/2/0
  External address: 221.177.188.57
  Internal address: 10.0.0.66
  Function: Packet Forwarder
  Hardware version: 1.10.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 39 percent
  DRAM: 1073741824, Used: 285567992
  PDP service: Ready, Assigned PDP contexts: 11937, PDP capacity: 500000
  Weighted PDP context load in payload: 11937
  PDP services start time: Tue Jul 20 01:28:41 2010 (726394 microsecond)

{master}


解析要求：
         1）对各个CPU0 utilization进行判断，对利用率超过75%的输出器板卡信息


展示结果：
           （时间戳）2010-11-18 10:00:02查询
Interface: gu-3/1/0 CPU负荷高，为80%，其详细情况为：
Interface: gu-3/1/0
  External address: 221.177.188.56
  Internal address: 10.0.0.65
  Function: Packet Forwarder
  Hardware version: 1.11.0.0 , Software version: 128.9.7.3 
  CPU0 utilization: 80 percent
  DRAM: 1073741824, Used: 307036336
  PDP service: Ready, Assigned PDP contexts: 13235, PDP capacity: 500000
  Weighted PDP context load in payload: 13235
  PDP services start time: Tue Jul 20 01:23:54 2010 (221063 microsecond)

 */
public class CmdGGSNDeviceBoardLoad extends Command {
	
	public String getresult(String orgstr) {
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String result=nowstr+"查询 \r\n";
		boolean allok=true;
		String[] rs=orgstr.split("\r\n\r\n");
		for(String s:rs){
			String curcpuname="";
//			System.out.println("-----------------------------");
			String[] obs=s.split("\r\n");
			for(String aline:obs){
//				System.out.println("["+aline+"]");
				if(aline.trim().startsWith("Interface:")){
					curcpuname=aline.trim();
				}
				if(aline.trim().startsWith("CPU0 utilization")){
					String[] aa=aline.split(":");
					if(aa.length>1){
						try{
							int cpupercent=Integer.parseInt(aa[1].trim().split("\\s")[0]);
							if(cpupercent>75){
								allok=false;
								result=result+curcpuname+" CPU负荷高，为"+cpupercent+"%，其详细情况为：\r\n"+s+"\r\n"; 
							}
						}catch(Exception e){}
					}
				}
			}
			
		}
		if(allok){
			result=result+" 所有设备CPU负载正常\r\n";
		}
		
		return result;
	}
	
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		 orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
		String result="";
		if(orgresult.startsWith("errorcode -")){
			result=orgresult;
		}else{
			result=getresult(orgresult);
		}
		

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
		String ss="Interface: gc-1/0/0\r\n" +
				"  External address: 221.177.188.34\r\n" +
				"  Internal address: 10.0.0.32\r\n" +
				"  Function: Session Controller\r\n" +
				"  Hardware version: 1.11.0.0 , Software version: 4.0.86.4 \r\n" +
				"  CPU0 utilization: 12 percent\r\n" +
				"  DRAM: 1072693248, Used: 288681984\r\n" +
				"  PDP service: Ready, Assigned PDP contexts: 61690, PDP capacity: 750000\r\n" +
				"  Weighted PDP context load in control: 83535\r\n" +
				"  Maximum filter depth per user 0, Mean: 0\r\n" +
				"  PDP services start time: Tue Jul 20 01:23:04 2010 (133106 microsecond)\r\n" +
				"\r\n" +
				"Interface: gc-2/0/0\r\n" +
				"  External address: 221.177.188.35\r\n" +
				"  Internal address: 10.0.0.48\r\n" +
				"  Function: Session Controller\r\n" +
				"  Hardware version: 1.9.0.0 , Software version: 4.0.86.4 \r\n" +
				"  CPU0 utilization: 99 percent\r\n" +
				"  DRAM: 1072693248, Used: 289542144\r\n" +
				"  PDP service: Ready, Assigned PDP contexts: 62125, PDP capacity: 750000\r\n" +
				"  Weighted PDP context load in control: 83531\r\n" +
				"  Maximum filter depth per user 0, Mean: 0\r\n" +
				"  PDP services start time: Tue Jul 20 01:23:04 2010 (133702 microsecond)\r\n" +
				"\r\n" +
				"Interface: gu-0/2/0\r\n" +
				"  External address: 221.177.188.49\r\n" +
				"  Internal address: 10.0.0.18\r\n" +
				"  Function: Packet Forwarder\r\n" +
				"  Hardware version: 1.9.0.0 , Software version: 128.9.7.3 \r\n" +
				"  CPU0 utilization: 150 percent\r\n" +
				"  DRAM: 1073741824, Used: 308856664\r\n" +
				"  PDP service: Ready, Assigned PDP contexts: 13770, PDP capacity: 500000\r\n" +
				"  Weighted PDP context load in payload: 13770\r\n" +
				"  PDP services start time: Tue Jul 20 01:23:52 2010 (549923 microsecond)\r\n" +
				"\r\n";
		String r=new CmdGGSNDeviceBoardLoad().getresult(ss);
		System.out.println("==============\r\n"+r);
	}
}
