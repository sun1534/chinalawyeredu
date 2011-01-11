package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.text.NumberFormat;
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
 * 命令名称：告警检查
命令位置：在菜单GGSN—标准维护命令下

原始命令解析
show chassis alarms         
show system alarms
原始输出结果举例如下：
ericsson@GGSNCQ02_RE1> show chassis alarms 
No alarms currently active

 ericsson@GGSNCQ02_RE1> show system alarms 
No alarms currently active
 
解析要求：
         1）如果无告警反馈无告警
         2）否则反馈告警。



展示结果：
           （时间戳）2010-11-18 10:00:02查询
无告警
            或详细告警

 */
public class CmdGGSNAlertCheck implements Command {
	
	public String getresult(String orgstr) {
		String result="";
		
		return result;
	}
	
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		String orgresult1=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"show chassis alarms");
		String orgresult2=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"show system alarms");
		String result="无告警";
		if(orgresult1.indexOf("No alarms currently active")<0){
			result=orgresult1;
		}else if(orgresult2.indexOf("No alarms currently active")<0){
			result=orgresult2;
		}
			
		
//		String result=getresult(orgresult);
		

		MemLog log=new MemLog();
		log.setCommandid(command.getCommandid());
		log.setCommandname(command.getCommananame());
		log.setCreatetime(new Timestamp(System.currentTimeMillis()));
		log.setDeviceid(device.getDeviceid());
		log.setDevicename(device.getDevicename());
		log.setResult(orgresult1+orgresult2);
		log.setOrgresult(Hibernate.createClob(orgresult1+orgresult2));
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
		String ss="Filesystem              Size       Used      Avail  Capacity   Mounted on\r\n" +
				"/dev/ad0s1a             217M       100M       100M       50%  /\r\n" +
				"devfs                    16K        16K         0B      100%  /dev/\r\n" +
				"/dev/vn0                 13M        13M         0B      100%  /packages/mnt/jbase\r\n" +
				"/dev/vn1                 55M        55M         0B      100%  /packages/mnt/jkernel-8.0-20090206.0\r\n" +
				"/dev/vn2                 10M        10M         0B      100%  /packages/mnt/jpfe-M40-8.0-20090206.0\r\n" +
				"/dev/vn3                2.8M       2.8M         0B      100%  /packages/mnt/jdocs-8.0-20090206.0\r\n" +
				"/dev/vn4                 18M        18M         0B      100%  /packages/mnt/jroute-8.0-20090206.0\r\n" +
				"/dev/vn5                7.8M       7.8M         0B      100%  /packages/mnt/jcrypto-8.0-20090206.0\r\n" +
				"/dev/vn6                 57M        57M         0B      100%  /packages/mnt/jggsn-8.0-20090206.0\r\n" +
				"/dev/vn7                7.5M       7.5M         0B      100%  /packages/mnt/jpfe-common-8.0-20090206.0\r\n" +
				"mfs:142                 2.0G       6.0K       1.8G        0%  /tmp\r\n" +
				"mfs:149                 2.0G       862K       1.8G        0%  /mfs\r\n" +
				"/dev/ad0s1e              24M        82K        22M        0%  /config\r\n" +
				"procfs                  4.0K       4.0K         0B      100%  /proc\r\n" +
				"/dev/ad1s1f              34G       5.4G        26G       17%  /var";
		
		String r=new CmdGGSNAlertCheck().getresult(ss);
		System.out.println("\r\n---\r\n"+r);
	}
}
