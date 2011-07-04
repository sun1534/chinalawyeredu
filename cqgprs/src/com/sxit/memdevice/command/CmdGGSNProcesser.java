package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
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
 * 命令名称：进程监控
命令位置：在菜单GGSN—标准维护命令下

原始命令解析
show system processes extensive           

原始输出结果举例如下：
对进程的监控
ericsson@GGSNCQ02_RE1> show system processes extensive
last pid: 97087;  load averages:  0.06,  0.06,  0.01  up 143+14:48:24    16:16:52
90 processes:  1 running, 89 sleeping
 
Mem: 159M Active, 270M Inact, 108M Wired, 808M Cache, 69M Buf, 662M Free
Swap: 2048M Total, 2048M Free
 

  PID USERNAME   PRI NICE  SIZE    RES STATE    TIME   WCPU    CPU COMMAND
97087 root        32   0 21792K   848K RUN      0:01 25.00%  1.22% top
72734 root         2   0  2792K  2396K select  53.4H  1.12%  1.12% gcdrd
96881 root         2   0 17424K 10164K select   0:00  0.05%  0.05% mgd

解析要求：
         1）
last pid: 97087;  load averages:  0.06,  0.06,  0.01  up 143+14:48:24    16:16:52
90 processes:  1 running, 89 sleeping
这里进程数量超过150 个，则需要把告警，并把CPU占用高的前5条记录反馈给手机

展示结果：
           （时间戳）2010-11-18 10:00:02查询
进程正常，st pid: 97087;  load averages:  0.06,  0.06,  0.01  up 143+14:48:24    16:16:52  
90 processes:  1 running, 89 sleeping
或进程数超150个，CPU占用高的前5条记录
 */
public class CmdGGSNProcesser extends Command {
	float[] f=new float[]{0.0f,0.0f,0.0f,0.0f,0.0f};
	String[] tts=new String[]{"","","","",""};
	
	public String getresult(String orgstr)  {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n\r\n");
		String desc1="";
		if(blocks.length>2){
			desc1=blocks[0].substring(blocks[0].indexOf("\r\n")+2);
			System.out.println(desc1);
			String ts=desc1.substring(desc1.indexOf("\r\n")+2);
			//System.out.println(ts);
			if(Integer.parseInt(ts.substring(0, ts.indexOf(" ")))>100){
				String[] ps=blocks[2].split("\r\n");
				for(int i=1;i<ps.length;i++){
					String[] b=ps[i].split("\\s+");
						NumberFormat nf=NumberFormat.getPercentInstance();
						try {
							float ft=nf.parse(b[8]).floatValue();
							for(int j=0;j<f.length;j++){
								if(ft>f[j]){
									f[j]=ft;
									tts[j]=ps[i];
									break;
								}
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					System.out.println("");
				}
			}else{
				result=nowstr+"查询 \r\n "+desc1;
				return result;
			}
		}
		
	
		result=nowstr+"查询 \r\n  ";
		result=nowstr+"查询 \r\n "+desc1;
		for(String s:tts){
			result+="\r\n"+s;
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
		System.out.println(log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
		try{
			memservice.save(log);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		String ss="ericsson@GGSNCQ02_RE1> show system processes extensive\r\n" +
				"last pid: 97087;  load averages:  0.06,  0.06,  0.01  up 143+14:48:24    16:16:52\r\n" +
				"90 processes:  1 running, 89 sleeping\r\n" +
				"\r\n" +
				"Mem: 159M Active, 270M Inact, 108M Wired, 808M Cache, 69M Buf, 662M Free\r\n" +
				"Swap: 2048M Total, 2048M Free\r\n" +
				" \r\n" +
				"\r\n" +
				"  PID USERNAME   PRI NICE  SIZE    RES STATE    TIME   WCPU    CPU COMMAND\r\n" +
				"97087 root        32   0 21792K   848K RUN      0:01 25.00%  1.22% top\r\n" +
				"72734 root         2   0  2792K  2396K select  53.4H  1.12%  1.12% gcdrd\r\n" +
				"96881 root         2   0 17424K 10164K select   0:00  0.05%  0.05% ";
		System.out.println(new CmdGGSNProcesser().getresult(ss));
		System.out.println(ss.indexOf("total 2"));
	}
}
