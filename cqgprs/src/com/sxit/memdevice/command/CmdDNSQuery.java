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
 * Dec 9, 2010 5:05:14 PM
 * 命令名称：DNS解析查询
 * 命令位置：在菜单DNS—标准维护命令下
 * 原始命令
 * pS – ef | grep named
 * nslookup
 * 
 * 获取配置文件中一个路由区记录进行解析
 * 
 * 原始结果举例？
 * 
 * 解析要求：
 * 判断进程是否正常
 * 判断是否能正常解析
 * 
 * 结果展示：
 * （时间戳）2010-11-18 10:00:02查询
 * DNS解析正常   
 * or
 * DNS解析异常   
 * or
 * DNSj进程异常
 */
public class CmdDNSQuery extends Command {
	
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		
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
		String ss="$ ls -l  /var/opt/BGw/ServerGroup1/Server1/BGwTTStorage/STS/working\r\n" +
				"total 23718\r\n" +
				"-rw-r--r--   1 bgw      med      2047882 Dec  9 18:03 BGwOutput:cqgsn2_S201012091802971:20101209:180342:061:NO-LABEL\r\n" +
				"-rw-r--r--   1 bgw      med      2047855 Dec  9 18:03 BGwOutput:cqgsn2_S201012091802972:20101209:180342:062:NO-LABEL\r\n" +
				"-rw-r--r--   1 bgw      med      2047782 Dec  9 18:03 BGwOutput:cqgsn2_S201012091802973:20101209:180342:063:NO-LABEL\r\n" +
				"-rw-r--r--   1 bgw      med      5999448 Dec  9 18:00 SGSNCQ02.8:chsLog.413:20101209:180041:060:NO-LABEL\r\n" +
				"$ ls -l";
		
		System.out.println(ss.indexOf("total 2"));
	}
}
