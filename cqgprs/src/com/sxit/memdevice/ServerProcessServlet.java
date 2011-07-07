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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.sxit.common.BasicService;
import com.sxit.common.Globals;
import com.sxit.communicateguard.service.MemService;
import com.sxit.memdevice.command.Command;
import com.sxit.memdevice.common.Client;
import com.sxit.memdevice.common.ClientHW;
import com.sxit.memdevice.common.ClientTZ;
import com.sxit.memdevice.common.ClientTZHW;
import com.sxit.models.mem.MemDevice;
import com.sxit.models.mem.MemDeviceTransit;
import com.sxit.models.mem.MemDevicecommand;
import com.sxit.models.mem.MemLog;
import com.sxit.models.system.SysParameter;
import com.sxit.models.system.SysUser;
import com.sxit.system.service.SysUserService;

public class ServerProcessServlet extends HttpServlet {
	private static final Log LOG = LogFactory.getLog(ServerProcessServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String result = "error request";

		PrintWriter out = response.getWriter();
		try {
			// TODO Auto-generated method stub
			response.setCharacterEncoding("utf-8");

			
			String optype = "";
			String username = "";
			String password = "";
			String deviceid = "";
			String userid = "";
			String commandid = "";
			String commandtype = "";
			String paramname = "";
			String value1="";
			String value2="";
			// 获取请求的xml串
			InputStream is = request.getInputStream();
			byte[] b = new byte[1024];
			int len;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((len = is.read(b)) > 0) {
				baos.write(b, 0, len);
			}
			String sb = new String(baos.toByteArray());
			System.out.println("request-" + sb);
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(sb);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<Element> list = doc.getRootElement().elements();
			for (Element element : list) {
				if (element.getName().equals("optype")) {
					optype = element.getStringValue().trim();
				}
				if (element.getName().equals("username")) {
					username = element.getStringValue().trim();
				}
				if (element.getName().equals("password")) {
					password = element.getStringValue().trim();
				}
				if (element.getName().equals("deviceid")) {
					deviceid = element.getStringValue().trim();
				}
				if (element.getName().equals("userid")) {
					userid = element.getStringValue().trim();
				}
				if (element.getName().equals("commandid")) {
					commandid = element.getStringValue().trim();
				}
				if (element.getName().equals("commandtype")) {
					commandtype = element.getStringValue().trim();
				}
				if (element.getName().equals("paramname")) {
					paramname = element.getStringValue().trim();
				}
				if (element.getName().equals("value1")) {
					value1 = element.getStringValue().trim();
				}
				if (element.getName().equals("value2")) {
					value2 = element.getStringValue().trim();
				}

			}
			System.out.println("[optype,username,password,deviceid,userid,paramname]--" + "[" + optype + "," + username
					+ "," + password + "," + deviceid + "," + userid + "," + paramname + "]");

			Enumeration eee = request.getParameterNames();
			while (eee.hasMoreElements()) {
				String n = eee.nextElement().toString();
				System.out.println(n + "" + request.getParameter(n));
			}
			if (optype == null || optype.equals("")) {

			} else if (optype.equals("login")) {
				SysUserService userService = (SysUserService) Globals.getBean("sysUserService");

				int loginResult = userService.userLogin(username, password);
				if (loginResult == -1) {
					result = "Account is not exist";
				} else if (loginResult == -2) {
					result = "Account is freezing";
				} else if (loginResult == -3) {
					result = "Wrong password";
				} else {
					SysUser sysuser = userService.getSysUserByLoginname(username);
					result = "OK" + "," + sysuser.getUserid() + "," + sysuser.getUsername();
				}
			} else if (optype.equals("getdevice")) {
				MemService memservice = (MemService) Globals.getBean("memService");

				List devicelist = memservice.getUserDeviceList(Integer.parseInt(userid), 0, Integer.MAX_VALUE)
						.getItems();
				Element xmlInfo = DocumentHelper.createElement("devicelist");
				for (int i = 0; i < devicelist.size(); i++) {
					MemDevice device = (MemDevice) devicelist.get(i);
					Element element = xmlInfo.addElement("device");
					System.out.println("getDevicename:" + device.getDevicename());
					element.addElement("deviceid").addText(Integer.toString(device.getDeviceid()));
					element.addElement("devicename").addText(device.getDevicename());
				}
				result = xmlInfo.asXML();

			} else if (optype.equals("getcommand")) {
				MemService memservice = (MemService) Globals.getBean("memService");

				// List
				// commandlist=memservice.getCommandList(Integer.parseInt(deviceid),
				// "", 1, Integer.MAX_VALUE).getItems();
				List commandlist = memservice.getCommandList(Integer.parseInt(deviceid), Integer.parseInt(commandtype),
						null, 1, Integer.MAX_VALUE).getItems();

				Element xmlInfo = DocumentHelper.createElement("commandlist");
				System.out.println("commandlist.size():" + commandlist.size());
				for (int i = 0; i < commandlist.size(); i++) {
					MemDevicecommand command = (MemDevicecommand) commandlist.get(i);
					Element element = xmlInfo.addElement("command");

					element.addElement("commandid").addText(Integer.toString(command.getCommandid()));
					element.addElement("commandname").addText(command.getCommananame());
					element.addElement("commandtype").addText(Integer.toString(command.getCommandtype()));
				}
				result = xmlInfo.asXML();

			} else if (optype.equals("execommand")) {
				MemService memservice = (MemService) Globals.getBean("memService");
				MemDevicecommand command = (MemDevicecommand) memservice.get(MemDevicecommand.class, Integer
						.parseInt(commandid));
				MemDevice device = (MemDevice) memservice.get(MemDevice.class, command.getDeviceid());
				device.setLoginName(username);
				device.setLoginPwd(password);
				// 标准命令 进行命令解析
				if (command.getCommandtype() == 1) {
					try {
						System.out.println("加裁类1==" + command.getPlugin());
						Command cmdprocess = (Command) Class.forName(command.getPlugin()).newInstance();
						System.out.println("加裁类2==" + command.getPlugin());
						System.out.println("----" + cmdprocess);

						result = cmdprocess.getresult(memservice, device, command, userid);
					} catch (Exception e) {
						result = e.getMessage();
						e.printStackTrace();
					}
				} else if (command.getCommandtype() == 2) {//应急命令
					
					String cmdstr=command.getCommandscript();
					if(cmdstr.indexOf("${1}")>-1){
						cmdstr.replace("${1}", value1);
					}
					if(cmdstr.indexOf("${2}")>-1){
						cmdstr.replace("${2}", value2);
					}
					
					String orgresult = "";
					if (device.getIshuawei() == 1) {
						if(device.getIstransit()==1){
							MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
							orgresult = ClientTZHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(),transit.getIp(),transit.getLoginname(),transit.getPwd(), command.getCommandscript());
						}else{
							orgresult = ClientHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
						}
					} else if(device.getIstransit()==1) {
						MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
						orgresult = ClientTZ.getres(device.getIp(), username, password,transit.getIp(),transit.getLoginname(),transit.getPwd(),command.getCommandscript());
					} else{
						orgresult = Client.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
					}
					
					
					result = orgresult;
				}else{
					String orgresult = "";
					if (device.getIshuawei() == 1) {
						if(device.getIstransit()==1){
							MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
							orgresult = ClientTZHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(),transit.getIp(),transit.getLoginname(),transit.getPwd(), command.getCommandscript());
						}else{
							orgresult = ClientHW.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
						}
					} else if(device.getIstransit()==1) {
						MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
						orgresult = ClientTZ.getres(device.getIp(), username, password,transit.getIp(),transit.getLoginname(),transit.getPwd(),command.getCommandscript());
					} else{
						orgresult = Client.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), command.getCommandscript());
					}
					
					
					result = orgresult;
				}

			} else if (optype.equals("login_device")) {
				MemService memservice = (MemService) Globals.getBean("memService");
				MemDevice device = (MemDevice) memservice.get(MemDevice.class, Integer.parseInt(deviceid));

				// if(device.getLoginName().equals(username)&&device.getLoginPwd().equals(password)){
				// result="OK";
				// }else{
				// result="user/password wrong!";
				// }
				if (device.getIshuawei() == 1) {
					if(device.getIstransit()==1){
						MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
						result = ClientTZHW.testlogin(device.getIp(), username, password,transit.getIp(),transit.getLoginname(),transit.getPwd());
					}else{
						result = ClientHW.testlogin(device.getIp(), username, password);
					}
				} else if(device.getIstransit()==1) {
					MemDeviceTransit transit=(MemDeviceTransit)memservice.get(MemDeviceTransit.class, device.getDeviceid());
					result = ClientTZ.testlogin(device.getIp(), username, password,transit.getIp(),transit.getLoginname(),transit.getPwd());
				} else{
					result = Client.testlogin(device.getIp(), username, password);
				}
			} else if (optype.equals("execommand_cus")) {

				String commandstr = request.getParameter("commandstr");
				MemService memservice = (MemService) Globals.getBean("memService");
				MemDevice device = (MemDevice) memservice.get(MemDevice.class, Integer.parseInt(deviceid));

				result = Client.getres(device.getIp(), device.getLoginName(), device.getLoginPwd(), commandstr);

				MemLog log = new MemLog();

				log.setCommandid(-1);
				log.setCommandname("自定义命令");
				log.setCreatetime(new Timestamp(System.currentTimeMillis()));
				log.setDeviceid(device.getDeviceid());
				log.setDevicename(device.getDevicename());
				log.setResult(result);
				log.setUserid(Integer.parseInt(userid));
				System.out
						.println(log.getCommandid() + "," + log.getCommandname() + "," + log.getCreatetime() + ","
								+ log.getDeviceid() + "," + log.getDevicename() + "," + log.getResult() + ","
								+ log.getUserid());
				try {
					memservice.save(log);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// result=new String(result.getBytes("GBK"),"ISO-8859-1");

			} else if (optype.equals("getsysparams")) {
				BasicService basicService = (BasicService) Globals.getBean("basicService");
				String value = "";

				if (paramname.equalsIgnoreCase("tracertip")) {

					SysParameter param = (SysParameter) basicService.get(SysParameter.class, "tracertip");
					if (param != null)
						value = param.getParamvalue();
					// value = CommonDatas.SysParameter.get("tracertip");
				} else if (paramname.equalsIgnoreCase("pingip")) {
					SysParameter param = (SysParameter) basicService.get(SysParameter.class, "pingip");
					if (param != null)
						value = param.getParamvalue();
					// value = CommonDatas.SysParameter.get("pingip");
				} else if (paramname.equalsIgnoreCase("ipdesc")) {
					List listt = null;
					try {
						DetachedCriteria dc=DetachedCriteria.forClass(SysParameter.class);
						dc.add(Restrictions.eq("typeid",1));
						listt = basicService.findAllByCriteria(dc);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// .findAll(SysParameter.class);
					int lenn = listt == null ? 0 : listt.size();
					// a:b|c:d|e:f

					for (int i = 0; i < lenn; i++) {
						SysParameter param = (SysParameter) listt.get(i);
						// if (param.getTypeid() == 1) {
						if (value == null || value.equals(""))
							value += (param.getParamname() + ":" + param.getParamvalue());
						else
							value += "|" + (param.getParamname() + ":" + param.getParamvalue());
						// }
					}
				} else if (paramname.equalsIgnoreCase("ggsnip")) {
					List listt = null;
					try {
						DetachedCriteria dc=DetachedCriteria.forClass(SysParameter.class);
						dc.add(Restrictions.eq("typeid",2));
						listt = basicService.findAllByCriteria(dc);
					} catch (Exception e) {
						e.printStackTrace();
					}

					int lenn = listt == null ? 0 : listt.size();
					// a:b|c:d|e:f

					for (int i = 0; i < lenn; i++) {
						// ip:ggsn01,cmnet|ip:ggsn02,cmnet
						SysParameter param = (SysParameter) listt.get(i);
						System.out.println("param.getParamvalue()==="+param.getParamvalue());
						// if (param.getTypeid() == 2) {
						if (value == null || value.equals(""))
							value += (param.getParamname() + ":" + param.getParamvalue());
						else
							value += "|" + (param.getParamname() + ":" + param.getParamvalue());
						// }
					}
				} else {
					value = "";
				}

				System.out.println("返回value:" + value);

				// <request>
				// <optype>getsysparams</optype>
				// <!--
				// tracertip:得到tracertip
				// pingip:得到ping的ip
				// ipdesc:tracert的时候，得到所有的ip和描述信息
				// ggsnip:返回所有的ggsnip前2位和对应的ggsn序号和接入类型(cmnet/cmwap)
				// -->
				// <paramname></paramname>
				// </request>
				//
				//
				// <optype>getsysparams</optype>
				// <paramname>
				// 对应request请求时候的paramname的值
				// </paramname>
				// <paramvalue>
				// name为tracertip:返回tracertip
				// name为pingip:返回ping的ip
				// name为ipdesc:返回ip和对应的描述信息，格式为a:b|c:d|e:f
				// name为ggsnip:返回ip和对应的ggsn信息，格式为ip:ggsn01,cmnet|ip:ggsn02,cmnet
				// </paramvalue>

				result = "<optype>getsysparams</optype><paramname>" + paramname + "</paramname><paramvalue>" + value
						+ "</paramvalue>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result:" + result);
		 result = new String(result.getBytes("utf-8"), "iso-8859-1");

		out.write(result);
		// out.flush();

	}

	public static void main(String[] args) throws Exception {
		// String
		// str="<devicelist><device><deviceid>1</deviceid><devicename>web服务器</devicename></device></devicelist>";
		// Document doc=DocumentHelper.parseText(str);
		// List<Element> list=doc.getRootElement().elements();
		// System.out.println(list.size());

		Document doc = null;
		try {
			doc = DocumentHelper
					.parseText("<request><optype>login</optype><username>admin</username><password>admin*()a</password></request>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Element> list = doc.getRootElement().elements();
		for (Element element : list) {
			System.out.println(element.getName());
			System.out.println(element.getStringValue());
			String optype = element.elementText("optype");
			String username = element.elementText("username");
			String password = element.elementText("password");
			String deviceid = element.elementText("deviceid");// request.getParameter("deviceid");
			String userid = element.elementText("userid");// request.getParameter("userid");
			System.out.println("[optype,username,password,deviceid,userid]--" + "[" + optype + "," + username + ","
					+ password + "," + deviceid + "," + userid + "]");
		}
	}
}
