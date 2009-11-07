/**
 * 
 */
package com.sxit.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import jofc2.model.Chart;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * @author 华锋 Oct 21, 2009-10:42:45 AM
 * 
 */
public class FlashChartResult extends StrutsResultSupport {

	private static final String ENCODING = "UTF-8";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4702848904993212381L;

	@Override
	protected void doExecute(String str, ActionInvocation invocation) throws Exception {
		Chart chart = (Chart) invocation.getStack().findValue("flashChart");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json-rpc;charset=" + ENCODING);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "No-cache");
		String json = chart.toString();
//		String json=chart.toDebugString();
		response.setContentLength(json.getBytes(ENCODING).length);
		PrintWriter out = response.getWriter();
		System.out.println(chart.toDebugString());
		out.print(json);
		out.flush();
		out.close();
	}
}