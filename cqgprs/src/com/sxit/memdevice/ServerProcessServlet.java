package com.sxit.memdevice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.sxit.common.Globals;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.common.Client;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.system.service.SysUserService;

public class ServerProcessServlet extends HttpServlet {
	private static final Log LOG = LogFactory
			.getLog(ServerProcessServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		String optype=request.getParameter("optype");
		String result="error request";
		if(optype==null||optype.equals("")){
			
		}else if(optype.equals("login")){
			SysUserService userService = (SysUserService) Globals.getBean("sysUserService");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			int loginResult = userService.userLogin(username, password);
			if (loginResult == -1) {
				result="Account is not exist";
			}else if(loginResult == -2){
				result="Account is freezing";
			}else if(loginResult == -3){
				result="Wrong password";
			}else{
				result="OK";
			}
		}else if(optype.equals("getdevice")){
			MemService memservice=(MemService) Globals.getBean("memService");
			int userid=Integer.parseInt(request.getParameter("userid"));
			List devicelist=memservice.getUserDeviceList(userid, 0, Integer.MAX_VALUE).getItems();
			Element xmlInfo = DocumentHelper.createElement("devicelist");
			for(int i=0;i>devicelist.size();i++){
				MemDevice device=(MemDevice)devicelist.get(i);
				Element element=xmlInfo.addElement("device");
				
				element.addElement("deviceid").addText(Integer.toString(device.getDeviceid()));
				element.addElement("devicename").addText(device.getDevicename());
			}
			result=xmlInfo.asXML();
			
		}else if(optype.equals("getcommond")){
			MemService memservice=(MemService) Globals.getBean("memService");
			int userid=Integer.parseInt(request.getParameter("userid"));
			int deviceid=Integer.parseInt(request.getParameter("deviceid"));
			List commandlist=memservice.getCommandList(deviceid, "", 0, Integer.MAX_VALUE).getItems();
			Element xmlInfo = DocumentHelper.createElement("devicelist");
			for(int i=0;i>commandlist.size();i++){
				MemDevicecommand device=(MemDevicecommand)commandlist.get(i);
				Element element=xmlInfo.addElement("command");
				
				element.addElement("commandid").addText(Integer.toString(device.getCommandid()));
				element.addElement("commandname").addText(device.getCommananame());
				element.addElement("commandtype").addText(Integer.toString(device.getCommandtype()));
			}
			result=xmlInfo.asXML();
			
		}else if(optype.equals("execommand")){
			String userid=request.getParameter("userid");
			String commandid=request.getParameter("commandid");
			MemService memservice=(MemService) Globals.getBean("memService");
			MemDevicecommand command=(MemDevicecommand)memservice.get(MemDevicecommand.class, commandid);
			MemDevice device=(MemDevice)memservice.get(MemDevice.class, command.getDeviceid());
			
			result=Client.getres(device.getLoginName(), device.getLoginPwd(),command.getCommandscript());
		}
		out.write(result);
	}
}
