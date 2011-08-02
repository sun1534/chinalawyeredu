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
 命令名称：查询GGSN 各板卡状态                    适合GGSN02 03 06 07 08
原始命令：show services ggsn status
                   
命令结果：
{master}
jiangshuai@GGSNCQ02_RE1> show services ggsn status | no-more 
Interface: gc-0/0/0
  External address: 221.177.188.33
  Internal address: 10.0.0.16
  Function: Node Controller
  Hardware version: 1.11.0.0 , Software version: 4.0.86.4 
  CPU0 utilization: 2 percent
  DRAM: 1072693248, Used: 276496384
  PDP service: Ready, Assigned PDP contexts: 29760, PDP capacity: 200000
  PDP services start time: Fri Aug 20 01:53:01 2010 (298939 microsecond)


解析要求：
解析GGSN板卡数量及解析红色部分，gc卡FUNCTIONG,功能字段：
如上述结果为：
gc：3
gu：9
gc-0/0/0 ：Node Controller
gc-1/0/0 ：Session Controller
gc-2/0/0 ：Session Controller

然后计算各板卡的CPU及内存利用率：
如：
gc-0/0/0 ： CPU利用率为2%，内存利用率为276496384/1072693248=25.8%
…………
…………
gu-3/2/0：CPU利用率为2%，内存利用率为276496384/1072693248=25.8%


最后手机结果显示为：
gc：3
gc-0/0/0 ：Node Controller，CPU利用率为2%，内存利用率为25.8%
gc-1/0/0 ：Session Controller，CPU利用率***，内存利用率为
gc-2/0/0 ：Session Controller，CPU利用率****，内存利用率为
--------------------------------------------------------------------------------------------
gu：9
gu-0/2/0：CPU利用率为2%，内存利用率为25.8%
*********
gu-3/2/0：CPU利用率为2%，内存利用率为25.8%

 */
public class CmdGGSNStatus extends Command {
	
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		int gc=0;
		int gu=0;
		String gcdesc="";
		String gudesc="";
		String[] blocks=orgstr.split("\n\n");
		for(String b:blocks){
			b=b.toLowerCase();
			String cur="gc";
			if(b.indexOf("function")>-1&&b.indexOf("interface")>-1&&b.indexOf("dram")>-1){
				String[] is=b.split("\n");
				String curdesc="";
				for(String i:is){
					if(i.trim().startsWith("interface")){
						String n=i.substring(i.indexOf(":")+1).trim();
						if(n.startsWith("gc")){
							gc++;
							cur="gc";
						}else if(n.startsWith("gu")){
							gu++;
							cur="gu";
						}
						curdesc=n+":";
					}
					if(i.trim().startsWith("function")){
						curdesc=curdesc+i.substring(i.indexOf(":")+1);
					}
					if(i.trim().startsWith("cpu")){
						curdesc=curdesc+"CPU利用率为"+i.substring(i.indexOf(":")+1).replace("percent", "").trim()+"%";
					}
					if(i.trim().startsWith("dram")){
						String[] ds=i.split(",");
						long d1=1;
						long u1=0;
						if(ds.length>1){
							d1=Long.parseLong(ds[0].substring(ds[0].indexOf(":")+1).trim());
							u1=Long.parseLong(ds[1].substring(ds[1].indexOf(":")+1).trim());
						}
						float up=((u1*1000)/d1)/10.0f;
						curdesc=curdesc+"内存利用率为"+up+"%";
					}
				}
				if(cur.equals("gc")){
					gcdesc=gcdesc+curdesc+"\n";
				}else{
					gudesc=gudesc+curdesc+"\n";
				}
			}
			
		}
		result="gc:"+gc+"\n"+gcdesc+"\n--------------------------------\n"+"gu:"+gu+"\n"+gudesc;
		
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
		String ss="DRAM: 1072693248, Used: 276496384";
		String[] ds=ss.split(",");
		for(String d:ds){
			System.out.println(d);
		}
		
	}
}
