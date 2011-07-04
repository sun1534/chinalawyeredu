package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 
 * 命令名称：板卡的负荷状态检查
命令位置：在菜单SGSN—标准维护命令下

原始命令
在SGSN上运行命令 gsh get_eq_cpu_load    :
gsh get_eq_cpu_load
原始输出结果举例如下
=== ericsson@eqm01s13p2 ANCB ~ # gsh get_eq_cpu_load 
 
SS7_FE_BB:
    1.2  : 1
    1.4  : 1
 
SS7_FE_NB:
    1.3  : 5
    1.5  : 5
 
DP:
    2.10 : 24
 
DPwithFR:
    2.2  : 38
    2.3  : 37
    2.4  : 37
    2.5  : 37
    2.6  : 33
    2.7  : 28
2.8  : 28
    2.9  : 28
    3.2  : 33
    3.3  : 29
    3.4  : 34
    3.5  : 28
    3.6  : 28
    3.7  : 36
    3.8  : 29
 
SS7_FE_SCTP_RAN:
    1.2  : 1
    1.4  : 1
 
SS7_FE_SCTP_CN:
    1.3  : 5
    1.5  : 5
 
PEB:
    1.1  : 0
    1.21 : 0
    2.1  : 0
    2.21 : 0
    3.1  : 0
    3.21 : 0
Router:
    1.6  : 2
    1.7  : 1
    2.11 : 2
    2.12 : 2
    2.14 : 8
    2.15 : 3
    3.11 : 6
    3.12 : 3
 
AP:
    1.8  : 15
    1.9  : 18
    1.10 : 17
    1.11 : 13
    1.12 : 17
    1.13 : 15
    1.14 : 15
    1.15 : 16
    1.16 : 18
    1.17 : 14
    1.18 : 17
    2.16 : 15
    2.17 : 15
    2.18 : 16
    2.19 : 18
    2.20 : 18
    3.13 : 13
3.14 : 16
    3.15 : 14
    3.16 : 14
    3.17 : 15
    3.18 : 16
    3.19 : 15
    3.20 : 17
 
SS7_BE:
    1.3  : 5
    1.5  : 5
    2.13 : 2
    3.9  : 2
    3.10 : 2
 
NCB:
    1.19 : 40
    1.20 : 6

解析要求；
如果有超过60%的，则把板卡名和编号显示到手机终端上。

结果展示
    （时间戳）2010-11-18 10:00:02查询
     板卡正常
OR  NCB: 1.19板卡负荷超过60%

 */
public class CmdSGSNEqCpuLoad extends Command {

	List<CpuLoad> list=new ArrayList<CpuLoad>();

	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n");
		
		CpuLoad tmpCpuLoad=null;
		for(String b:blocks){
			String bt=b.trim();
			if(bt.endsWith(":")){
				System.out.println(bt);
				tmpCpuLoad=new CpuLoad();
				list.add(tmpCpuLoad);
				tmpCpuLoad.name=bt.substring(0,bt.length()-1);
			}else if(bt.indexOf(":")>1){
				String[] bts=bt.split(":");
//				System.out.println(Arrays.asList(bts));
				try{
					tmpCpuLoad.cmap.put(bts[0].trim(),Integer.parseInt(bts[1].trim()));
				}catch(Exception e){}
			}
		}
//		System.out.println(list);
		boolean allok=true;
		String errorstr="";
		for(CpuLoad c:list){
			for(Map.Entry<String,Integer> e:c.cmap.entrySet()){
				if(e.getValue()>60){
					allok=false;
					errorstr+=c.name+":"+e.getKey()+"板卡负载超过60%";
				}
			}
		}
		
		result=nowstr+"查询 \r\n";
		if(allok){
			result+="	板卡正常";
		}else{
			result+=errorstr;
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
		System.out.println("log---"+log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
		try{
			memservice.save(log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		String ss="\r\n" +
				"SS7_FE_BB:\r\n" +
				"    1.2  : 1\r\n" +
				"    1.4  : 1\r\n" +
				"\r\n" +
				"\r\n" +
				"SS7_FE_NB:\r\n" +
				"    1.3  : 5\r\n" +
				"    1.5  : 5\r\n" +
				" \r\n" +
				"DP:\r\n" +
				"    2.10 : 24\r\n" +
				" \r\n" +
				"DPwithFR:\r\n" +
				"    2.2  : 38\r\n" +
				"    2.3  : 37\r\n" +
				"    2.4  : 77\r\n" +
				"    2.5  : 37\r\n" +
				"    2.6  : 33\r\n" +
				"    2.7  : 28\r\n" +
				"2.8  : 28";
		
		CmdSGSNEqCpuLoad c=new CmdSGSNEqCpuLoad();
		String result=c.getresult(ss);
		System.out.println("result:\r\n"+result);
		
	}
	
	
}
class CpuLoad{
	String name;
	Map<String,Integer> cmap=new HashMap<String,Integer>();
	public String toString(){
		return name+"\r\n\t"+cmap;
	}
}