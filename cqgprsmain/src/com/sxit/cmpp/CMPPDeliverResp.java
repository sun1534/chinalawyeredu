/*
 * Created on 2005-7-5 11:06:48
 *  
 */
package com.sxit.cmpp;


/**
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class CMPPDeliverResp implements CMPPRequestBody {

    /**
     * Logger for this class
     */
//    private static final Log log = LogFactory.getLog(CMPPDeliverResp.class);

    /**
     * ∆Û“µ¥˙¬Î
     */
    private byte[] msg_Id;

    /**
     * √‹¬Î
     */
    private int result;

    public CMPPDeliverResp(byte[] msg_Id, int result) {
        this.msg_Id = msg_Id;
        this.result = result;
        

    }
  

    public byte[] getRequestBody() {
        byte[] body = new byte[9];

        int cur = 0;
        System.arraycopy(msg_Id, 0, body, cur, msg_Id.length);
        cur += msg_Id.length;

        byte resultbytes[] = Common.intToBytes4(result);
        System.arraycopy(resultbytes, 0, body, cur, resultbytes.length);

        return body;
    }
}