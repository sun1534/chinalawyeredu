/**
 * ElearningRequests.java
 */

package com.changpeng.service.szsync;

import com.changpeng.common.context.Globals;

/**
 * @author 华锋 2008-5-12 下午03:13:22
 * 
 */
public abstract class ElearningSyncRequests {
	protected Globals globals = new Globals();

	public abstract String requestSyncService(org.dom4j.Element rootElement);
}