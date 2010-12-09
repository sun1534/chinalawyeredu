package com.sxit.memdevice.command;

import java.util.Date;

/**
 * 
 * @author 孟凡强
 * Dec 9, 2010 5:05:14 PM
 * 命令名称：DNS解析查询
 * 命令位置：在菜单DNS—标准维护命令下
 * 原始命令
 * pS – ef | grep named
 * nslookup
 * 
 * 获取配置文件中一个路由区记录进行解析
 * 
 * 原始结果举例？
 * 
 * 解析要求：
 * 判断进程是否正常
 * 判断是否能正常解析
 * 
 * 结果展示：
 * （时间戳）2010-11-18 10:00:02查询
 * DNS解析正常   
 * or
 * DNS解析异常   
 * or
 * DNSj进程异常
 */
public class CmdDNSQuery implements Command {
	String p1="total 0";
	String p2="No such file or directory";
	@Override
	public String getresult(String orgstr) {
		String result="";
		String nowstr=DateUtil.getSimpleDateTime(new Date());
		if(orgstr.indexOf(p1)>-1||orgstr.indexOf(p2)>-1){
			result=nowstr+" 无GGSN02的话单产生，最后一个话单为“话单名”";
		}else{
			result=nowstr+"查询 \r\n*         CG话单生成正常";
		}
		return result;
	}
	
	@Override
	public String getresult(String ip, String username, String pwd, String devicename) {
		// TODO Auto-generated method stub
		return null;
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
