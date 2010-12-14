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
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;

/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM
 * 命令名称：SGSN 用户信令日志查看
 * 命令位置：在菜单SGSN—标准维护命令下
 * 
 * 原始命令
 * 在SGSN上 cd /logs/session_event_log/tmp,使用tail –100 session_event_log.n
 * cd  /logs/session_event_log/tmp
 * tail –100 session_event_log.n                
 * （ n为数字，需要判断找最新的文件）
 * 原始输出结果举例如下：
 * ======== SESSION EVENT (G): MS INITIATED ACTIVATE REQUEST =========
 * Time      : 2010-08-04 11:05:59 
 * Node      : e_Erlang__Global_pm1_16_2_1@eqm01s10p2
 * IMSI      : 460007891330943
 * MSISDN    : 8613629711998
 * SM Cause  : Service Option not Subscribed (#33)
 * Details   : Service option not subscribed
 * APN Req.  : uninet
 * APN Sub.  : cmnet cmwap cmmm
 * 
 * 解析要求：（该日志GPRS分析系统有处理，可借鉴）
 *       1）每次运行对输出的100条结果进行计数，按SM Cause 值计数输出结果。
 *       2）如果100条记录中有同一号码MSISDN有10条以上记录，需要输出号码和该号码的错误原因，
 *       3）如果100条记录中同一APN Req.有10条以上记录，需要输出该APN和错误原因。
 *       展示结果：
 *                  （时间戳）2010-11-18 10:00:02查询
 *                  100条记录中，33号错误90次，38号错误9次，27号错误1次。
 *                  15002399016错误11次，错误码33
 *                  Uninet 错误10次，错误码33
 *                  
 */
public class CmdSGSNSignalLog implements Command {

	List<Map<String,String>> records=new ArrayList<Map<String,String>>();
	Map<String,Integer> smscause=new HashMap<String,Integer>();
	
	
	Map<String,Integer> numbercnt=new HashMap<String,Integer>();
	Map<String,String> numbererror=new HashMap<String,String>();
	@Override
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n\r\n\r\n");
		for(String b:blocks){
			String[] bs=b.split("\r\n");
			for(String bb:bs){
				int tmp_spindex=bb.indexOf(":");
				String name=bb.substring(0, tmp_spindex).trim();
				String value=bb.substring(tmp_spindex+1).trim();
				if(name.equals("SM Cause")){
					String code=value.substring(value.indexOf("#")+1,value.indexOf(")", value.indexOf("#")));
					if(smscause.containsKey(code)){
						smscause.put(code, smscause.get(code).intValue()+1);
					}else{
						smscause.put(code, 1);
					}
				}else if(name.equals("MSISDN")){
					
					if(numbercnt.containsKey(value)){
						numbercnt.put(value, smscause.get(value).intValue()+1);
						for(String sss1:bs){
							int tmp_spindex1=sss1.indexOf(":");
							String name1=sss1.substring(0, tmp_spindex1).trim();
							String value1=sss1.substring(tmp_spindex1+1).trim();
//							System.out.println("==========="+name1+"->"+value1);
							if(name1.equals("SM Cause")){
								String code=value1.substring(value1.indexOf("#")+1,value1.indexOf(")", value1.indexOf("#")));
								numbererror.put(value, code);
							}
						}
					}else{
						numbercnt.put(value, 1);
						for(String sss1:bs){
							int tmp_spindex1=sss1.indexOf(":");
							String name1=sss1.substring(0, tmp_spindex1).trim();
							String value1=sss1.substring(tmp_spindex1+1).trim();
//							System.out.println("==========="+name1+"->"+value1);
							if(name1.equals("SM Cause")){
								String code=value1.substring(value1.indexOf("#")+1,value1.indexOf(")", value1.indexOf("#")));
								numbererror.put(value, code);
							}
						}
					}
				}
				
			}
		}
		String errorstr="";
		for(Entry<String,Integer> e:smscause.entrySet()){
//			System.out.println(e.getKey()+"--->"+e.getValue());
			errorstr+=e.getKey()+"号错误"+e.getValue()+"次 ";
		}
		String numerror="";
		for(Entry<String,Integer> e:numbercnt.entrySet()){
//			System.out.println(e.getKey()+"--->"+e.getValue());
			numerror+=e.getKey()+"错误"+e.getValue()+"次，错误码是"+numbererror.get(e.getKey())+"\r\n";
		}
		
		result=nowstr+"查询 \r\n"+"100条记录中 "+errorstr+"\r\n"+numerror;

		return result;
	}
	
	private String getlastlogid(String ss){
		int lastid=0;
//		String ss="ls\r\n" +
//				"session_event_log.1 session_event_log.2 \r\n====";
		String[] aa=ss.split("\\s");
		for(String s:aa){
			if(s.startsWith("session_event_log.")){
				try{
					lastid=Integer.parseInt(s.substring(s.indexOf(".")+1));
				}catch(Exception e){
					
				}
			}
		}
		return "session_event_log."+lastid;
	}
	@Override
	public String getresult(MemService memservice,MemDevice device,MemDevicecommand command, String userid) {

		String getpath=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),"ls /logs/session_event_log/tmp");
		String lastid=getlastlogid(getpath);
		
		
		String orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript()+lastid);
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
//		String ss="APN Sub.  : cmmm\r\n\r\n\r\n" +
//				"======== SESSION EVENT (W): MS INITIATED ACTIVATE REQUEST =========\r\n" +
//				"Time      : 2010-03-15 19:16:20 Node      : e_Erlang__Global_pm2_20_2_1@eqm02s14p2\r\n" +
//				"IMSI      : 460021239105325\r\n" +
//				"MSISDN    : 8615123916610\r\n" +
//				"SM Cause  : Network Failure (#38)\r\n" +
//				"Details   : Failure in the Radio Interface Procedure (#14)\r\n" +
//				"PDP Addr. : 10.198.15.213\r\n" +
//				"APN Used  : cmwap.mnc002.mcc460.gprs\r\n" +
//				"RNC Id    : 623\r\n\r\n\r\n" +
//				"======== SESSION EVENT (W): MS INITIATED ACTIVATE REQUEST =========\r\n" +
//				"Time      : 2010-03-15 19:30:36 \r\n" +
//				"Node      : e_Erlang__Global_pm2_16_2_1@eqm02s10p2\r\n" +
//				"IMSI      : 460006514936620\r\n" +
//				"SM Cause  : Service Option not Subscribed (#33)\r\n" +
//				"Details   : Service option not subscribed\r\n" +
//				"APN Req.  : cmwap\r\n" +
//				"APN Sub.  : cmmm\r\n\r\n\r\n" +
//				"======== SESSION EVENT (W): MS INITIATED ACTIVATE REQUEST =========\r\n" +
//				"Time      : 2010-03-15 19:31:35 \r\n" +
//				"Node      : e_Erlang__Global_pm2_16_2_1@eqm02s10p2\r\n" +
//				"IMSI      : 460006514936620\r\n" +
//				"SM Cause  : Service Option not Subscribed (#33)\r\n" +
//				"Details   : Service option not subscribed\r\n" +
//				"APN Req.  : cmwap\r\n" +
//				"APN Sub.  : cmmm\r\n\r\n\r\n" +
//				"======== SESSION EVENT (W): MS INITIATED ACTIVATE REQUEST =========\r\n" +
//				"Time      : 2010-03-15 20:02:35 \r\n" +
//				"Node      : e_Erlang__Global_pm2_16_2_1@eqm02s10p2\r\n" +
//				"IMSI      : 460006514936620";
//		
//		CmdSGSNSignalLog c=new CmdSGSNSignalLog();
//		String result=c.getresult(ss);
//		System.out.println("result:\r\n"+result);
		
		int lastid=0;
		String ss="ls\r\n" +
				"session_event_log.1 session_event_log.2 \r\n====";
		String[] aa=ss.split("\\s");
		for(String s:aa){
			if(s.startsWith("session_event_log.")){
				try{
					lastid=Integer.parseInt(s.substring(s.indexOf(".")+1));
				}catch(Exception e){
					
				}
			}
		}
	}
}
