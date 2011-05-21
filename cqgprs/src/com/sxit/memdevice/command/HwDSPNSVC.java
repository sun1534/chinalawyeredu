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
import com.sxit.memdevice.common.ClientHW;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;

/**
 * 
 * @author 孟凡强
 * 2011-5-4 18:33
 * 
 DSP NSVC （查询Gb Over FR的NSVC 虚链路状态）

指令内容举例：

+++    SGSN                2011-04-22 10:40:06
O&M    #244857    
%%DSP NSVC;%%
RETCODE = 0  执行成功

NSVC信息
--------
 框号  槽号  操作结果  E1/T1端口号  BC 标识  SGSN索引  NSVC 标识  NSVC的有效状态

 1     0     查询成功  2            1        0         9111       UNBLOCKED
 1     0     查询成功  3            1        0         9112       UNBLOCKED
 1     0     查询成功  4            1        0         9841       DEAD BLOCKED
 1     0     查询成功  5            1        0         9842       DEAD BLOCKED
 1     0     查询成功  6            1        0         9851       DEAD BLOCKED
 1     0     查询成功  7            1        0         9852       DEAD BLOCKED
(结果个数 = 6)

列出NSVC的有效状态为”DEAD BLOCKED”的每一行，如：

NSVC信息
--------
框号  槽号  操作结果  E1/T1端口号  BC 标识  SGSN索引  NSVC 标识  NSVC的有效状态
1     0     查询成功  4            1        0         9841       DEAD BLOCKED
 1     0     查询成功  5            1        0         9842       DEAD BLOCKED
 1     0     查询成功  6            1        0         9851       DEAD BLOCKED
 1     0     查询成功  7            1        0         9852       DEAD BLOCKED
 */
public class HwDSPNSVC extends Command {

	public String getresult(String orgstr) {
		String result="";
		String[] blocks=orgstr.split("\r\n");
		for(int i=0;i<blocks.length;i++){
			if(blocks[i].trim().startsWith("NSVC")){
				result+=blocks[i]+"\r\n"+blocks[i+1]+"\r\n"+blocks[i+2]+"\r\n";
			}
			if(blocks[i].trim().endsWith("DEAD BLOCKED")){
				result+=blocks[i];
			}
		}
		return orgstr;
	}

	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		if(device.getIshuawei()==1){
			 orgresult=ClientHW.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
			}else{
				orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
			}
		 String result="命令结果解析失败";
		 try{
			 result=getresult(orgresult);	 
		 }catch(Exception e){
			 System.out.println(e.getMessage());
			 e.printStackTrace();
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
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public static void main(String[] args){
		
		
	}
}
