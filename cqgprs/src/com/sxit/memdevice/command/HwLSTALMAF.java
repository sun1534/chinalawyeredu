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
 * LST ALMAF （查看活动告警）
 * 指令内容举例：

RETCODE = 0  执行成功

ALARM  956195      故障      重要告警        SGSN      605      通讯系统
   告警同步号  =  956195
     告警名称  =  NTP服务器无响应
 告警发生时间  =  2011-04-08 00:20:14
     定位信息  =  场地号=0, 行号=6, 列号=8, 框号=0, 槽号=12, 板位置=IO, 单板类型=UGTP, NTP服务器IP地址=221.177.78.247, 告警门限=8

(结果个数 = 1)

---    END

此告警内容建议完全呈现。
 */
public class HwLSTALMAF extends Command {

	public String getresult(String orgstr) {
		
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
