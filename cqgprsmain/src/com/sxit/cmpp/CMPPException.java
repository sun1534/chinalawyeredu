/*
 * NAME: com.sxit.cmpp.CMPPException.java Company:SXIT
 */
package com.sxit.cmpp;

import java.io.IOException;


/**
 * cmpp建立和关闭连接时候封装IOException
 * @author HuaFeng
 * @version 1.0 (2005-3-29 15:20:45)
 */
public class CMPPException extends IOException {
	public CMPPException() {
        super();
    }

    public CMPPException(String msg) {
        super(msg);
    }
}