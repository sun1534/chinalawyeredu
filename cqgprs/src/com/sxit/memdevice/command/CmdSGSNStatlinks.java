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
 * 命令名称：Gr链路状态查看
 * 命令位置：在菜单SGSN—标准维护命令下
 * 原始命令
 * 在SGSN上运行命令 gsh action_ss7_sys_statlinks          :
 * gsh action_ss7_sys_statlinks
 * 原始输出结果举例如下：
 * 
 * 例子：
 * NodeID                  0
OPC                     1506131
SLC                     0
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     1
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     2
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     3
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     8
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     9
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     10
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     11
LinksetNo               1
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     8
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     9
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     10
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     11
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     12
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     13
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     14
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     15
LinksetNo               2
State                   In Service

NodeID                  0
OPC                     1506131
SLC                     0
LinksetNo               2
State                   In Service

解析要求：
         1）对不同OPC的输出记录数计数，对不同状态State  进行计数
2）找出OPC为1506131 State 不是In Service 的记录，输出对应的五个字段全记录。

3）如果有不是In Service 的记录，需要再查询
4）如果BOARD前两位相同的记录（比如3,6）全是 dead的，输出对用的BOARD，BSC,  NSVCI 

展示结果：
          （时间戳）2010-11-18 10:00:02查询
SGSNn共16条Gr链路，其中OPC1506131（）14条，In Service 14条，OPC1603（）2条，
Aligning M3 links 1条，Wait for T17 1条***。其    
not in service的链路为NodeID    1 OPC 11603 SLC                     
0 LinksetNo  3  State  Aligning M3 links

 */
public class CmdSGSNStatlinks implements Command {

	List<StatLinkObj> objs=new ArrayList<StatLinkObj>(); 
	
	List<StatObj> statobj=new ArrayList<StatObj>();
	@Override
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		String[] blocks=orgstr.split("\r\n\r\n");
		for(String b:blocks){
			
			String[] bs=b.split("\r\n");
			if(bs.length==5){
				StatLinkObj obj=new StatLinkObj();
				for(String bb:bs){
					bb=bb.trim();
					if(bb.startsWith("NodeID")){
						obj.NodeID=bb.substring(bb.indexOf(" ")).trim();
					}else if(bb.startsWith("OPC")){
						obj.OPC=bb.substring(bb.indexOf(" ")).trim();
					}else if(bb.startsWith("SLC")){
						obj.SLC=bb.substring(bb.indexOf(" ")).trim();
					}else if(bb.startsWith("LinksetNo")){
						obj.LinksetNo=bb.substring(bb.indexOf(" ")).trim();
					}else if(bb.startsWith("State")){
						obj.State=bb.substring(bb.indexOf(" ")).trim();
					}
				}
				System.out.println(obj);
				if(obj.NodeID!=null){
					objs.add(obj);
				}
			}
		}
		for(StatLinkObj o:objs){
			boolean newopc=true;
			for(StatObj so:statobj){
				if(o.OPC.equals(so.OPC)){
					newopc=false;
					if(so.statmap.containsKey(o.State)){
						so.statmap.put(o.State, so.statmap.get(o.State)+1);
					}else{
						so.statmap.put(o.State, 1);
					}
					so.count++;
				}
			}
			if(newopc){
				StatObj so=new StatObj();
				so.OPC=o.OPC;
				Map<String,Integer> som=new HashMap<String,Integer>();
				som.put(o.State, 1);
				so.statmap=som;
				statobj.add(so);
				so.count=1;
			}
		}
		
		
		result=nowstr+"查询 \r\n"+"共有"+objs.size()+"条Gr链路\r\n";
		for(StatObj o:statobj){
			result+="OPC"+o.OPC +" " + o.count+"条 ";
			for(Entry e:o.statmap.entrySet()){
				result+=e.getKey()+" "+e.getValue()+" 条，";
			}
			result+="\r\n";
		}
		for(StatLinkObj obj:objs){
			if(!obj.State.equals("In Service")){
				result+="\r\n not in service的链路为：\r\n"+"NodeID\t"+obj.NodeID+"\r\nOPC\t"+obj.OPC+"\r\nSLC\t"+obj.SLC+"\r\nLinksetNo\t"+obj.LinksetNo+"\r\nState\t"+obj.State+"\r\n";
			}
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
		String ss="NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     0\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     1\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     2\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     3\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     8\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     9\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     10\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     11\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     8\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     9\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     10\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     11\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     12\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     13\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     14\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     15\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     0\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     3\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     4\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     5\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     6\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     7\r\n" +
				"LinksetNo               2\r\n" +
				"State                   In Service\r\n" +
				"\r\n" +
				"NodeID                  0\r\n" +
				"OPC                     1506131\r\n" +
				"SLC                     4\r\n" +
				"LinksetNo               1\r\n" +
				"State                   In Service";
		
		CmdSGSNStatlinks c=new CmdSGSNStatlinks();
		String result=c.getresult(ss);
		System.out.println("result:\r\n"+result);
		
	}
}
class StatLinkObj{
	String NodeID;
	String OPC;
	String SLC;
	String LinksetNo;
	String State;
	
	public String toString(){
		return "<"+NodeID+"><"+OPC+"><"+SLC+"><"+LinksetNo+"><"+State+">";
	}
}
class StatObj{
	String OPC;
	Map<String,Integer> statmap=new HashMap<String,Integer>();
	int count;
}