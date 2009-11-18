/**
 * ServiceException.java
 */
package com.sxit.common.exception;

/**
 * @author 华锋
 * 2009-1-5  下午02:36:11
 *
 */
public class ServiceException extends RuntimeException{

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