package com.sxit.memdevice.command;

import java.sql.Timestamp;

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
 * 2011-5-4 18:33
 * 
 +++    SGSN                2011-04-22 10:44:24
O&M    #244858    
%%LST GBPDPRESTBL;%%
RETCODE = 0  执行成功

UGBI资源使用状态表
------------------
 框号  槽号  PDP资源拥塞标志

 1     0     No
 1     1     No
 1     2     No
 1     12    No
 1     13    No
 2     0     No
 2     1     No
 2     2     No
 2     3     No
 2     14    No
 4     0     No
 4     1     No
 4     2     No
 4     3     No
 4     12    No
 5     0     No
 5     1     No
 5     2     No
 5     3     No
 5     13    No
 6     0     No
 6     1     No
 6     2     No
 6     3     No
 6     13    No
 7     0     No
 7     1     No
 7     2     No
 7     3     No
 7     13    No
 8     0     No
 8     1     No
 8     2     No
 8     3     No
 8     12    No
 9     0     No
 9     1     No
 9     2     No
 9     3     No
 9     12    No
 10    0     No
 10    1     No
 10    2     No
 10    3     No
 10    12    No
 11    0     No
 11    1     No
 11    2     No
 11    3     No
 11    12    No
 12    0     No
 12    1     No
 12    2     No
 12    3     No
 12    12    No
 13    0     No
 13    1     No
 13    2     No
 13    3     No
 13    12    No
 14    0     No
 14    1     No
 14    2     No
 14    3     No
 14    12    No
 15    0     No
 15    1     No
 15    2     No
 15    3     No
 15    12    No
 16    1     No
 16    2     No
 16    3     No
 16    12    No
 16    13    No
 17    1     No
 17    2     No
 17    3     No
 17    12    No
 17    13    No
 18    1     No
 18    2     No
 18    12    No
 18    13    No
 19    1     No
 19    2     No
 19    12    No
 19    13    No
(结果个数 = 88)


---    END

若“PDP资源拥塞标志”出现“Yes”，则列出该行，例：
UGBI资源使用状态表
------------------
 框号  槽号  PDP资源拥塞标志
19    2     Yes

 */
public class HwLSTGBPDPRESTBL extends Command {

	public String getresult(String orgstr) {
		String result="";
		String[] blocks=orgstr.split("\r\n");
		for(int i=0;i<blocks.length;i++){
			System.out.println("block-->"+blocks[i]);
			if(blocks[i].trim().startsWith("UGBI资源使用状态表")){
				result+=blocks[i]+"\r\n"+blocks[i+1]+"\r\n"+blocks[i+2]+"\r\n";
			}
			if(blocks[i].trim().toLowerCase().contains("yes")){
				result+=blocks[i];
			}
		}
		return orgstr;
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
