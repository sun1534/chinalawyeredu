/**
 * ElearningInterfaceServlet.java
 */

package com.sxit.servlet;

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

import com.sxit.common.BasicService;
import com.sxit.common.Globals;

/**
 * <pre>
 * 和培训管理系统的接口
 * 用dom4j来解析xml
 * </pre>
 * 
 * @author 华锋 2008-5-12 上午10:37:46
 * 
 */
public class RauInterfaceServlet extends HttpServlet {

	private static final Log LOG = LogFactory.getLog(RauInterfaceServlet.class);

	private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init() throws ServletException {

	}

	/*
	 * 这里要解析xml (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

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
			Document document = DocumentHelper.parseText(sb.toString());
		
			Element method = document.getRootElement();
			String methodname = method.attributeValue("name");
			LOG.debug("method.getName():::" + method.getName() + ",,,=" + methodname);
		

			RauRequests raureq = null;
			if (methodname.equals("setServerParam")) {
				raureq = new SetServerParamRequest();
			} else if (methodname.equals("executeCmd")) {
				// 得到律师信息
				raureq = new ExecuteCmdRequest();
			} else if (methodname.equals("getCmdResp")) {
				raureq = new GetCmdResponseRequest();
			}
			// 得到培训课程中已经有人培训了的课程信息
			else if (methodname.equals("replaceNamed")) {
				raureq = new ReplaceNamedRequest();
			} else if (methodname.equals("restoreNamed")) {
				raureq = new RestoreNamedRequest();
			} else {
				raureq = new ErrorRequest();
			}
			result.append(raureq.requestService(method));

		} catch (Exception e) {
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