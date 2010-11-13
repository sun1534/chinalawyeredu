package com.sxit.memdevice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.common.Globals;
import com.sxit.system.service.SysUserService;

/**
 * * @author 肖云亮 2010-08-31 下午03:12:13
 * 
 */
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
			String userid=request.getParameter("userid");
		}else if(optype.equals("getcommond")){
			String userid=request.getParameter("userid");
		}else if(optype.equals("execommand")){
			String userid=request.getParameter("userid");
			String commandid=request.getParameter("commandid");
		}
		out.write(result);
	}
}
