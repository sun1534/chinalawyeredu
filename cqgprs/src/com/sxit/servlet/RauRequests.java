/**
 * ElearningRequests.java
 */

package com.sxit.servlet;


/**
 * 
 * 处理收到的各类消息
 * 
 * @author 华锋 2008-5-12 下午03:13:22
 * 
 */
public abstract class RauRequests {


	public abstract String requestService(org.dom4j.Element rootElement);
}
