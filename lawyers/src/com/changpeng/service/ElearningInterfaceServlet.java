/**
 * ElearningInterfaceServlet.java
 */

package com.changpeng.service;

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
 * 和培训管理系统的接口
 * 用dom4j来解析xml
 * </pre>
 * 
 * @author 华锋 2008-5-12 上午10:37:46
 * 
 */
public class ElearningInterfaceServlet extends HttpServlet {

	private static final Log LOG = LogFactory.getLog(ElearningInterfaceServlet.class);

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
		
		int groupid=Integer.parseInt(request.getParameter("groupid"));
		InputStream is = request.getInputStream();
		java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line="";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		LOG.debug("请求消息:\r\n" + sb.toString());

		StringBuilder result = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

		try {
			// org.dom4j.Document doc = new SAXReader().read(is);
			Document document = DocumentHelper.parseText(sb.toString());
			LOG.debug("document:::"+document);
			
			
			Element element = document.getRootElement();
			
			
			
			LOG.debug("element:::"+element.getName()+",,,="+element.attributeValue("name"));
			
			
//			Element method = element.element("method");
		Element method=element;	
			LOG.debug("method.getName()==="+method.getName());
			
			String methodname = method.attributeValue("name");
			
			LOG.debug("methodname==="+methodname);
			ElearningRequests learningreq = null;
			if (methodname.equals("login")) {
				learningreq = new LoginRequest();
			}
			else if (methodname.equals("getLawyersInfo")) {
				// 得到律师信息
				learningreq = new GetLawyerRequest();
			}
			else if (methodname.equals("getLessonsInfo")) {
				learningreq = new GetLessonRequest();
			}
			// 得到培训课程中已经有人培训了的课程信息
			else if (methodname.equals("getClassRegInfo")) {
				learningreq = new GetClassRegRequest();
			}
			else if (methodname.equals("setSKRecs")) {
				learningreq = new SetSKRecsRequest();
			}
			else {
				learningreq = new ErrorRequest();
			}
			result.append(learningreq.requestService(groupid,method));
			
		}
		catch (Exception e) {
			result.append("<response>");
			result.append("<respcode>").append(-1).append("</respcode>");
			result.append("<respmsg>").append("系统异常:" + e.getMessage()).append("</respmsg>");
			result.append("</response>");
			LOG.error("异常:::" + e);
		}
		finally {
			
			LOG.debug("返回消息:\r\n"+result);
			
			response.setCharacterEncoding("utf-8");
			OutputStream os = response.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(result.toString());
			writer.flush();
			writer.close();
		}
	}
}