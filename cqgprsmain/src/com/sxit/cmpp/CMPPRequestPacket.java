/*
 * Created on 2005-7-5 10:15:19
 * 
 */
package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * cmpp请求包
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class CMPPRequestPacket {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(CMPPRequestPacket.class);

	/**
	 * 包长度,包括包头和包体的总长度
	 */
	private int totalLength;

	/**
	 * 发送包的命令字id
	 * 
	 * @link #commandID
	 */
	private int commandID;

	/**
	 * 发送序列号，步长为1,达到最大值后循环使用
	 */
	private int sequenceID;

	/**
	 * CMPP包体
	 */
	private CMPPRequestBody body;

	/**
	 * 设置命令字
	 * 
	 * @param commandID
	 */
	public void setCommandID(int commandID) {
		this.commandID = commandID;
	}

	/**
	 * 设置序列号
	 * 
	 * @param sequenceID
	 */
	public void setSequenceID(int sequenceID) {
		this.sequenceID = sequenceID;
	}

	/**
	 * 设置包体
	 * 
	 * @param body
	 */
	public void setRequestBody(CMPPRequestBody body) {
		this.body = body;
	}

	/**
	 * 取得包体
	 * 
	 * @return
	 */
	public CMPPRequestBody getRequestBody() {
		return this.body;
	}

	/**
	 * 取得整个cmpp请求包的字节形式
	 * 
	 * @return
	 */
	public byte[] getRequestPacket() {
		log.info(body.getClass().getName() + " 消息处理,sequenceID=" + sequenceID);
		byte[] bodybytes = body.getRequestBody();
		this.totalLength = 12 + bodybytes.length;

		byte[] requestPacket = new byte[totalLength];
		System.arraycopy(Common.intToBytes4(totalLength), 0, requestPacket, 0, 4);
		System.arraycopy(Common.intToBytes4(commandID), 0, requestPacket, 4, 4);
		System.arraycopy(Common.intToBytes4(sequenceID), 0, requestPacket, 8, 4);
		System.arraycopy(bodybytes, 0, requestPacket, 12, bodybytes.length);

		return requestPacket;
	}
}