/**
 * ElearningInterfaceServlet.java
 */

package com.changpeng.service.szsync;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <pre>
 * 此处为和深圳的律协网站的同步接口，接收来自网站的律师同步，事务所同步，用户同步，登录同步，退出同步的请求等等
 * 用dom4j来解析xml
 * </pre>
 * 
 * @author 华锋 2008-5-12 上午10:37:46
 * 
 */
public class ElearningInterfaceSyncServlet extends HttpServlet {

	private static final Log LOG = LogFactory.getLog(ElearningInterfaceSyncServlet.class);

	private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init() throws ServletException {

	}

	/*
	 * 这里要解析xml (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

		// int length = request.getContentLength();
		InputStream is = request.getInputStream();
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		LOG.debug("请求消息:\r\n" + sb.toString());

		StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

		try {
			// org.dom4j.Document doc = new SAXReader().read(is);
			Document document = DocumentHelper.parseText(sb.toString());
			LOG.debug("document:::" + document);
			Element element = document.getRootElement();

			LOG.debug("element:::" + element.getName() + ",,,=" + element.attributeValue("name"));
			
			String rootname=element.getName();
			
			ElearningSyncRequests syncreq = null;
			if (rootname.equals("offices")) {
				syncreq = new OfficeSyncRequest();
			}
			else if (rootname.equals("lawyers")) {
				// 得到律师信息
				syncreq = new LawyerSyncRequest();
			}
			else if (rootname.equals("users")) {
				syncreq = new UserSyncRequest();
			}
			// 得到培训课程中已经有人培训了的课程信息
			else if (rootname.equals("userlogin")) {
				syncreq = new LoginSyncRequest();
			}
			else if (rootname.equals("userlogout")) {
				syncreq = new LogoutSyncRequest();
			}
			else {
				syncreq = new ErrorSyncRequest();
			}
			result.append(syncreq.requestSyncService(element));

		}
		catch (Exception e) {
			result.append("<response>");
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>").append("系统异常:" + e.getMessage()).append("</respmsg>");
			result.append("</response>");
			LOG.error("异常:::" + e);
		}
		finally {
			LOG.debug("返回消息:\r\n" + result);
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(result.toString());
			writer.flush();
			writer.close();
		}
	}
}