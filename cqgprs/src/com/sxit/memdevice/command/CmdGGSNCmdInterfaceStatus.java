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
 * GGSN状态查询
 * 命令名称：GGSN命令接口状态查询
 * 命令位置：在菜单GGSN—标准维护命令下

原始命令解析
show system storage           

原始输出结果举例如下：
ericsson@GGSNCQ02_RE1> show system storage
Filesystem              Size       Used      Avail  Capacity   Mounted on
/dev/ad0s1a             217M       100M       100M       50%  /
devfs                    16K        16K         0B      100%  /dev/
/dev/vn0                 13M        13M         0B      100%  /packages/mnt/jbase
/dev/vn1                 55M        55M         0B      100%  /packages/mnt/jkernel-8.0-20090206.0
/dev/vn2                 10M        10M         0B      100%  /packages/mnt/jpfe-M40-8.0-20090206.0
/dev/vn3                2.8M       2.8M         0B      100%  /packages/mnt/jdocs-8.0-20090206.0
/dev/vn4                 18M        18M         0B      100%  /packages/mnt/jroute-8.0-20090206.0
/dev/vn5                7.8M       7.8M         0B      100%  /packages/mnt/jcrypto-8.0-20090206.0
/dev/vn6                 57M        57M         0B      100%  /packages/mnt/jggsn-8.0-20090206.0
/dev/vn7                7.5M       7.5M         0B      100%  /packages/mnt/jpfe-common-8.0-20090206.0
mfs:142                 2.0G       6.0K       1.8G        0%  /tmp
mfs:149                 2.0G       862K       1.8G        0%  /mfs
/dev/ad0s1e              24M        82K        22M        0%  /config
procfs                  4.0K       4.0K         0B      100%  /proc
/dev/ad1s1f              34G       5.4G        26G       17%  /var
 
 
解析要求：
         1）如果无结果反馈直接告警“GGSN02指令无响应”
         2）如果结果中/dev/ad1s1f              34G       5.4G        26G       17%  /var    这里利用率超过50%则在手机终端展现该条结果。



展示结果：
           （时间戳）2010-11-18 10:00:02查询
GGSNn工作中，正常
            或“GGSN02指令无响应”
            或GGSNn工作中，占用超50%，/dev/ad1s1f              34G       5.4G        26G       17%  /var 

 */
public class CmdGGSNCmdInterfaceStatus implements Command {
	
	public String getresult(String orgstr) {
		String result="";
		String[] blocks=orgstr.split("\r\n");
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		if(orgstr.indexOf("\r\n")>0){
			result=nowstr+"查询 占用超过50% \r\n  ";
			
			for(String block:blocks){
				
				String[] values=block.split("\\s+");
				if(values.length>4){
					System.out.println("---"+values[4]);
					try{
						NumberFormat nf=NumberFormat.getPercentInstance();
						float fv=nf.parse(values[4]).floatValue();
						if(fv>0.5){
							result+=block+"\r\n";
						}
					}catch(Exception e){System.out.print(e);}
				}
			}
		}else{
			result=nowstr+"查询 \r\n  GGSN指令无响应";
		}
		return result;
	}
	
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		String orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
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
		
		String r=new CmdGGSNCmdInterfaceStatus().getresult(ss);
		System.out.println("\r\n---\r\n"+r);
	}
}
