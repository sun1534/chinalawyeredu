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
 IPNSVC （查询Gb Over IP的NSVC 虚链路状态）

指令内容举例：

+++    SGSN                2011-04-25 16:51:43
O&M    #313170    
%%DSP IPNSVC;%%
RETCODE = 0  执行成功

IP网络NSVC配置表
----------------
 IPNSVC标识  NSE标识  框号  槽号  IP地址类型  本地IP地址   本端UDP端口号  对端IP地址     对端UDP端口号  状态

 21803       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.3    10000          可操作
 21547       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.3    10000          可操作
 21802       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.4    10000          可操作
 21546       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.4    10000          可操作
 21801       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.5    10000          可操作
 21545       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.5    10000          可操作
 21800       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.6    10000          可操作
 21544       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.6    10000          可操作
 21799       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.7    10000          可操作
 21543       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.7    10000          可操作
 21798       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.8    10000          可操作
 21542       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.8    10000          可操作
 21797       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.9    10000          可操作
 21541       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.9    10000          可操作
 21796       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.10   10000          可操作
 21540       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.10   10000          可操作
 21795       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.11   10000          可操作
 21539       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.11   10000          可操作
 21794       1038     19    2     IPV4        10.181.6.34  10001          10.181.95.12   10000          可操作
 21538       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.12   10000          可操作

列出状态为“不可操作”的每一行：

IP网络NSVC配置表
----------------
IPNSVC标识  NSE标识  框号  槽号  IP地址类型  本地IP地址   本端UDP端口号  对端IP地址     对端UDP端口号  状态
21545       1038     19    1     IPV4        10.181.6.33  10000          10.181.95.5    10000          不可操作


 */
public class HwDSPIPNSVC extends Command {

	public String getresult(String orgstr) {
		String result="";
		String[] blocks=orgstr.split("\r\n");
		for(int i=0;i<blocks.length;i++){
			if(blocks[i].trim().startsWith("IP网络NSVC配置表")){
				result+=blocks[i]+"\r\n"+blocks[i+1]+"\r\n"+blocks[i+2]+"\r\n";
			}
			if(blocks[i].trim().endsWith("不可操作")){
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
