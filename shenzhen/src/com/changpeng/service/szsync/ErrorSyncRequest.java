/**
 * GetLessonRequest.java
 */

package com.changpeng.service.szsync;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 华锋 2008-5-12 下午03:12:21
 * 
 */
public class ErrorSyncRequest extends ElearningSyncRequests {
	private static final Log LOG = LogFactory.getLog(ErrorSyncRequest.class);

	public String requestSyncService(org.dom4j.Element rootElement) {
		StringBuilder result = new StringBuilder("");
		result.append("<response>");
		result.append("<respcode>-1</respcode>");
		result.append("<respmsg>错误的同步请求消息</respmsg>");
		result.append("</response>");
		return result.toString();
	}
}