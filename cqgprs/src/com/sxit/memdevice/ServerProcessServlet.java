package com.sxit.memdevice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sxit.common.Globals;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.command.Command;
import com.sxit.memdevice.common.Client;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

public class ServerProcessServlet extends HttpServlet {
	private static final Log LOG = LogFactory
			.getLog(ServerProcessServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String optype="";
		String username="";
		String password="";
		String deviceid="";
		String userid="";
		String commandid="";
		//获取请求的xml串
		InputStream is=request.getInputStream();
		byte[] b=new byte[1024];
		int len;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		while((len=is.read(b))>0){
			baos.write(b,0,len);
		}
		String sb =new String(baos.toByteArray());
		System.out.println("request-"+sb);
		Document doc=null;
		try{
			doc=DocumentHelper.parseText(sb);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Element> list=doc.getRootElement().elements();
		for(Element element:list){
			if(element.getName().equals("optype")){
				optype=element.getStringValue();
			}
			if(element.getName().equals("username")){
				username=element.getStringValue();
			}
			if(element.getName().equals("password")){
				password=element.getStringValue();
			}
			if(element.getName().equals("deviceid")){
				deviceid=element.getStringValue();
			}
			if(element.getName().equals("userid")){
				userid=element.getStringValue();
			}
			if(element.getName().equals("commandid")){
				commandid=element.getStringValue();
			}
		}
		System.out.println("[optype,username,password,deviceid,userid]--"+"["+optype+","+username+","+password+","+deviceid+","+userid+"]");
		
		String result="error request";
		Enumeration eee=request.getParameterNames();
		while(eee.hasMoreElements()){
			String n=eee.nextElement().toString();
			System.out.println(n+""+request.getParameter(n));
		}
		if(optype==null||optype.equals("")){
			
		}else if(optype.equals("login")){
			SysUserService userService = (SysUserService) Globals.getBean("sysUserService");
			
			int loginResult = userService.userLogin(username, password);
			if (loginResult == -1) {
				result="Account is not exist";
			}else if(loginResult == -2){
				result="Account is freezing";
			}else if(loginResult == -3){
				result="Wrong password";
			}else{
				SysUser sysuser=userService.getSysUserByLoginname(username);
				result="OK"+","+sysuser.getUserid()+","+sysuser.getUsername();
			}
		}else if(optype.equals("getdevice")){
			MemService memservice=(MemService) Globals.getBean("memService");
			
			List devicelist=memservice.getUserDeviceList(Integer.parseInt(userid), 0, Integer.MAX_VALUE).getItems();
			Element xmlInfo = DocumentHelper.createElement("devicelist");
			for(int i=0;i<devicelist.size();i++){
				MemDevice device=(MemDevice)devicelist.get(i);
				Element element=xmlInfo.addElement("device");
				System.out.println("getDevicename:"+device.getDevicename());
				element.addElement("deviceid").addText(Integer.toString(device.getDeviceid()));
				element.addElement("devicename").addText(device.getDevicename());
			}
			result=xmlInfo.asXML();
			
		}else if(optype.equals("getcommand")){
			MemService memservice=(MemService) Globals.getBean("memService");
			
			List commandlist=memservice.getCommandList(Integer.parseInt(deviceid), "", 1, Integer.MAX_VALUE).getItems();
			Element xmlInfo = DocumentHelper.createElement("commandlist");
			System.out.println("commandlist.size():"+commandlist.size());
			for(int i=0;i<commandlist.size();i++){
				MemDevicecommand command=(MemDevicecommand)commandlist.get(i);
				Element element=xmlInfo.addElement("command");
				
				element.addElement("commandid").addText(Integer.toString(command.getCommandid()));
				element.addElement("commandname").addText(command.getCommananame());
				element.addElement("commandtype").addText(Integer.toString(command.getCommandtype()));
			}
			result=xmlInfo.asXML();
			
		}else if(optype.equals("execommand")){
			MemService memservice=(MemService) Globals.getBean("memService");
			MemDevicecommand command=(MemDevicecommand)memservice.get(MemDevicecommand.class, Integer.parseInt(commandid));
			MemDevice device=(MemDevice)memservice.get(MemDevice.class, command.getDeviceid());
			String orgresult="";
			
			//标准命令 进行命令解析
			if(command.getCommandtype()==1){
				try{
					orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
					Command cmdprocess=(Command)Class.forName(command.getPlugin()).newInstance();
					result=cmdprocess.getresult(orgresult);
				}catch(Exception e){
					result=e.getMessage();
					e.printStackTrace();
				}
			}else{
				orgresult=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
				result=orgresult;
			}
			
			MemLog log=new MemLog();
			log.setCommandid(command.getCommandid());
			log.setCommandname(command.getCommananame());
			log.setCreatetime(new Timestamp(System.currentTimeMillis()));
			log.setDeviceid(device.getDeviceid());
			log.setDevicename(device.getDevicename());
			log.setResult(result);
			log.setUserid(Integer.parseInt(userid));
			System.out.println(log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
			try{
				memservice.save(log);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(optype.equals("login_device")){
			MemService memservice=(MemService) Globals.getBean("memService");
			MemDevice device=(MemDevice)memservice.get(MemDevice.class, Integer.parseInt(deviceid));
			
			if(device.getLoginName().equals(username)&&device.getLoginPwd().equals(password)){
				result="OK";
			}else{
				result="user/password wrong!";
			}
		}else if(optype.equals("execommand_cus")){
			
			String commandstr=request.getParameter("commandstr");
			MemService memservice=(MemService) Globals.getBean("memService");
			MemDevice device=(MemDevice)memservice.get(MemDevice.class, Integer.parseInt(deviceid));
			
			result=Client.getres(device.getIp(),device.getLoginName(), device.getLoginPwd(),commandstr);
			MemLog log=new MemLog();
			
			log.setCommandid(-1);
			log.setCommandname("自定义命令");
			log.setCreatetime(new Timestamp(System.currentTimeMillis()));
			log.setDeviceid(device.getDeviceid());
			log.setDevicename(device.getDevicename());
			log.setResult(result);
			log.setUserid(Integer.parseInt(userid));
			System.out.println(log.getCommandid()+","+log.getCommandname()+","+log.getCreatetime()+","+log.getDeviceid()+","+log.getDevicename()+","+log.getResult()+","+log.getUserid());
			try{
				memservice.save(log);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
//		result=new String(result.getBytes("GBK"),"ISO-8859-1");
		out.write(result);
	}
	
	public static void main(String[] args) throws Exception{
//		String str="<devicelist><device><deviceid>1</deviceid><devicename>web服务器</devicename></device></devicelist>";
//		Document doc=DocumentHelper.parseText(str);
//		List<Element> list=doc.getRootElement().elements();
//		System.out.println(list.size());
		
		Document doc=null;
		try{
			doc=DocumentHelper.parseText("<request><optype>login</optype><username>admin</username><password>admin*()a</password></request>");
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Element> list=doc.getRootElement().elements();
		for(Element element:list){
			System.out.println(element.getName());
			System.out.println(element.getStringValue());
			String optype=element.elementText("optype");
			String username=element.elementText("username");
			String password=element.elementText("password");
			String deviceid=element.elementText("deviceid");//request.getParameter("deviceid");
			String userid=element.elementText("userid");//request.getParameter("userid");
			System.out.println("[optype,username,password,deviceid,userid]--"+"["+optype+","+username+","+password+","+deviceid+","+userid+"]");
		}
	}
}
