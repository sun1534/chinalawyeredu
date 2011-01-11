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
 * 命令名称：业务负荷查询
命令位置：在菜单GGSN—标准维护命令下

原始命令解析
show services statistics           

原始输出结果举例如下：

Start time: Tue Jul 20 01:23:14 2010
Sample time: Thu Aug 12 14:17:42 2010
  Active PDP contexts: 123646
    With traffic class conversational: 0
    With traffic class streaming: 0
    With traffic class interactive: 118347
    With traffic class background: 5299
  Active IPv6 PDP contexts: 0
  PDP creations: 142642984, Failed: 1546804
  IPv6 PDP creations: 0, Failed: 0
  Secondary PDP creations: 0, Failed: 0
    IPv6 secondary PDP creations: 0
    IPv6 secondary PDP attempts: 0
  PDP updates: 53621193, Failed: 53400
  PDP deactivations: 142291396, Failed: 107077
    PDP deletions by idle-supervision: 4460652
    PDP deletions by session-supervision: 0
    PDP deletions by user command: 0
  Shared: 0
  Number of active L2TP tunnels: 0
  Number of active L2TP sessions: 0
  Maximum number of active L2TP tunnels since restart: 0
  Maximum number of active L2TP sessions since restart: 0
Active subscribers: 122246
Uplink traffic:
  Packets: 21610664517
  Bytes: 2511234972279
  Dropped packets: 22786461
  Packets for IPv6: 0
  Bytes for IPv6: 0
  Dropped packets for IPv6: 3097933
Downlink traffic:
  Packets: 21678641868
  Bytes: 10861313015698
  Dropped packets: 232709431
  Packets for IPv6: 0
  Bytes for IPv6: 0
  Dropped packets for IPv6: 0
Traffic flow template filters: 0
Weighted PDP context load:
  In control: 166736
  In payload: 123647
Service-based charging statistics:
  Service-based charging initiated deactivations: 0
  External URT update requests, no match: 0
  External credit update requests, no match: 0
  External update requests that failed: 0
Authorization-based packet discard statistics:
  Rating server: Packets: 0
  Configuration: Packets: 0
  Empty bucket list: Packets: 0
  Prepaid server: Packets: 0
  Error list: Packets: 0
Radius statistics:
  Failed RADIUS Authentication procedures: 118
  Failed RADIUS Accounting procedures: 29609

{master}

解析要求：
         1）把数值不为0的展示出来


展示结果：
           （时间戳）2010-11-18 10:00:02查询


 */
public class CmdGGSNServicesStatistics implements Command {
	
	public String getresult(String orgstr) {
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String result=nowstr+"查询 \r\n";
		boolean allok=true;
		String[] rs=orgstr.split("\r\n");
		for(String s:rs){
			if(!s.substring(s.lastIndexOf(":")).trim().equals("0")){
				result+=s+"\r\n";
			}
		}
		
		
		return result;
	}
	
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		String orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
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
		String ss="";
		String r=new CmdGGSNServicesStatistics().getresult(ss);
		System.out.println("==============\r\n"+r);
	}
}
