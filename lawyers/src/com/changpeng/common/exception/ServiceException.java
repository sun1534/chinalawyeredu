/**
 * ServiceException.java
 */
package com.changpeng.common.exception;

/**
 * @author 华锋
 * 2008-2-26 下午02:36:11
 *
 */
public class ServiceException extends Exception{

	public ServiceException(){
	  super();
	}
	
	public ServiceException(String msg){
		super(msg);
	}
	
	public ServiceException(Throwable e){
		super(e);
	}
}