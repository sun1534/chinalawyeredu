package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Hibernate;

import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.memdevice.common.ClientHW;
import com.sxit.memdevice.common.ClientTZ;
import com.sxit.memdevice.common.ClientTZHW;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDeviceTransit;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;



/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM  --------------未完成
 * 命令名称： APN首次查询
         APN对比查询
         异常APN结果获取              组合命令
命令位置：在菜单GGSN—标准维护命令下
原始命令解析
show services ggsn  statistics apn          
 
原始输出结果举例如下：



解析要求：
APN首次查询和对比查询实际命令一致，后台需要记录两次查询的结果，然后APN对比查询时需要对结果和上次结果做差值计算，然后进行后台判断，找出异常APN,手机再通过异常APN结果获取从后台获得异常APN

需要解析的字段
如果Actie PDP contexts=0，对应的APN为异常APN,激活用户数为0
      如果Downlink traffic或Uplink traffic:两次的差值为0，对应的APN为异常APN,流量为0，
      如果Radius statistics: FAILD两次的差值为大于10，对应的APN为异常APN，鉴权失败。
           APN :cmnet
         Actie PDP contexts:14334     
         IP-address pool statistics:
           AVAILABLE IP-addresses:115287
           IP-address in quarantine:1445
         Uplink traffic:
          Pacekets:234434
          BYTES:2200000
          DROPPED PACKETS:3324
         Downlink traffic
          Pacekets:2344342
          BYTES:22000002
          DROPPED PACKETS:33241
         Radius statistics:
          Failed Radius Authentication procedure :0
          Failed Radius Accounting procedure :0
展示结果：
APN首次查询和APN对比查询  只有查询成功的提示
           （时间戳）2010-11-18 10:00:02查询成功
          异常APN结果获取，
           （时间戳）2010-11-18 10:00:02 PDP激活为0的APN有smjt.cq、mem2.cq**，流量为0的APN有dl.cq,鉴权异常的有cqfc.cq

 */
public class CmdGGSNAPNCheck extends Command {
	
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

		if (device.getIshuawei() == 1) {
			if(device.getIstransit()==1){
				MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
				orgresult = ClientTZHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(),transit.getIp(),transit.getLoginname(),transit.getPwd(), command.getCommandscript());
			}else{
				orgresult = ClientHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
			}
		} else if(device.getIstransit()==1) {
			MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
			orgresult = ClientTZ.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(),transit.getIp(),transit.getLoginname(),transit.getPwd(),command.getCommandscript());
		} else{
			orgresult = Client.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
		}
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
		String r=new CmdGGSNAPNCheck().getresult(ss);
		System.out.println("==============\r\n"+r);
	}
}
