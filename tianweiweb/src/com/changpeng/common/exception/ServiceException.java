/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.changpeng.common.exception;

/**
 *
 * @author mfq
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
