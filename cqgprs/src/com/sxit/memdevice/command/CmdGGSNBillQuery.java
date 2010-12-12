package com.sxit.memdevice.command;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Hibernate;

import com.mysql.jdbc.Clob;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;



/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM
 * 命令名称：GGSN话单查询
 * 命令位置：在菜单GGSN—标准维护命令下
 * 原始命令解析
 * ls -l /var/log/ggsn
 * 
 * 原始输出结果举例如下：
 * ？补充
 * 解析要求：
 *     1）查看时候有GGSNCQ开头的最近的话单（1分钟之内肯定有），正常情况下话单是产生在这里，CG1分钟来取1次
 *        如果无话单，告警“*点*分GGSN02无话单产生”
 *        如果有话单，则反馈话单生成正常
 *        如果话单时间不是最近的，超过5分钟，则提示话单堆积
 * 展示结果：
 *     （时间戳）2010-11-18 10:00:02查询
 *     话单生成正常
 *     或
 *     *点*分GGSN02无话单产生
 *     或
 *     话单堆积
 */
public class CmdGGSNBillQuery implements Command {
	String p1="total 0";
	String p2="No such file or directory";
	@Override
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		if(orgstr.indexOf(p1)>-1||orgstr.indexOf(p2)>-1){
			result=nowstr+"查询 \r\n  无GGSN02的话单产生";//最后一个话单为“话单名”
		}else{
			result=nowstr+"查询 \r\n* GGSN话单生成正常";
		}
		return result;
	}
	
	@Override
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
