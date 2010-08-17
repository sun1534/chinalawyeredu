/*
 * NAME: com.sxit.cmpp.ConnectResp.java Company:SXIT
 */

package com.sxit.cmpp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sxit.cmpp.Common;

/**
 * cmppconnectresp消息
 * 
 * @author HuaFeng
 * @version 1.0 (2005-3-29 10:54:53)
 */
public class CMPPConnectResp extends CMPPResponsePacket {
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPConnectResp.class);

    /**
     * 返回的状态,1位
     */
    public int status;

    /**
     * 返回的认证码
     */
    public String authenticaion;

    /**
     * ismg返回的协议版本
     */
    public int version;

    public CMPPConnectResp(){
    	this.commandID=CommandID.CMPP_CONNECT_REP;
    	this.totalLength=30;
    }
    
    /**
     * 解析从输入流得到的包体字节流
     */
    public void parseResponseBody(byte[] packet) {

        byte[] length = new byte[4];
        System.arraycopy(packet, 0, length, 0, 4);
        this.totalLength = Common.bytes4ToInt(length);
        if (log.isDebugEnabled()) {
            log.debug("返回包长度解析后为:" + totalLength);
        }

        byte[] commandid = new byte[4];
        System.arraycopy(packet, 4, commandid, 0, 4);
        this.commandID = Common.bytes4ToInt(commandid);
        if (log.isDebugEnabled()) {
            log.debug("返回包命令字解析后=" + commandID + "，实际=" + CommandID.CMPP_CONNECT_REP);
        }

        byte[] seqid = new byte[4];
        System.arraycopy(packet, 8, seqid, 0, 4);
        this.sequenceID = Common.bytes4ToInt(seqid);
        if (log.isDebugEnabled()) {
            log.debug("返回包序列号解析后为:" + sequenceID);
        }

        //        byte stat[] = new byte[4];
        //        System.arraycopy(packet, 12, stat, 0, 4);
        //        status = Common.bytes4ToInt(stat);
        status = packet[12]; //cmpp3.0是4个字节，2.0只有1个字节

        byte[] authen = new byte[16];
        System.arraycopy(packet, 13, authen, 0, 16);
        this.authenticaion = new String(authen);

        version = packet[29];
        log.info("connectResp消息解析成功,status=" + status + ",sequenceID=" + sequenceID);
    }
}