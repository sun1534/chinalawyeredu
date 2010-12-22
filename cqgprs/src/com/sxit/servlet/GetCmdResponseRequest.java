/**
 * GetLessonRequest.java
 */

package com.sxit.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class GetCmdResponseRequest extends RauRequests {
	private static final Log LOG = LogFactory.getLog(GetCmdResponseRequest.class);

	public String requestService(org.dom4j.Element rootElement) {
		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		result.append("<respcode>").append(-1).append("</respcode>");
		result.append("<respmsg>错误的请求消息</respmsg>");
		result.append("</response>");
		return result.toString();
	}
}