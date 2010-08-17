/*
 * Created on 2005-7-5 11:06:48
 *  
 */
package com.sxit.cmpp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class CMPPConnect implements CMPPRequestBody {

    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CMPPConnect.class);

    /**
     * 企业代码
     */
    private String spid;

    /**
     * 密码
     */
    private String password;

    /**
     * 版本
     */
    private int version;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 
     * @param spid 6位的企业代码
     * @param password 6位的密码
     */
    public CMPPConnect(String spid, String password) {
        this.spid = spid;
        this.password = password;
        this.version = 2;
        SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmmss");
        Date currentTime = new Date();
        timestamp = formatter.format(currentTime);

    }

    public byte[] getRequestBody() {
        byte[] body = new byte[27];

        int cur = 0;
        byte[] sp = Common.getText(6, spid);
        System.arraycopy(sp, 0, body, cur, sp.length);
        cur += sp.length;

//        byte[] authen = md5(sp);
        byte[] authen = md5(spid);
        System.arraycopy(authen, 0, body, cur, authen.length);
        cur += authen.length;

        //byte[] ver = { (byte) version };
        //System.arraycopy(ver, 0, body, cur, ver.length);
        body[cur] = (byte) version;
        cur += 1;

        byte btimestamp[] = Common.intToBytes4(Integer.parseInt(timestamp));
        System.arraycopy(btimestamp, 0, body, cur, btimestamp.length);

        return body;
    }

    /**
     * 得到md5后的16位byte型数组
     * 
     * @param sp
     * @return
     */
//    private byte[] md5(byte[] sp) {
//        byte bzero[] = new byte[9];
//		byte[] bSPpassword = password.getBytes();
//        byte btimestamp[] = (timestamp).getBytes();
//        byte bmd5[] = new byte[sp.length + 9 + bSPpassword.length + btimestamp.length];
//        int cur = 0;
//        System.arraycopy(sp, 0, bmd5, cur, sp.length);
//        cur += sp.length;
//        System.arraycopy(bzero, 0, bmd5, cur, 9);
//        cur += bzero.length;
//        System.arraycopy(bSPpassword, 0, bmd5, cur, bSPpassword.length);
//        cur += bSPpassword.length;
//        System.arraycopy(btimestamp, 0, bmd5, cur, btimestamp.length);
//        byte[] result = new byte[16];
//        try {
//            MessageDigest md = MessageDigest.getInstance("md5");
//            md.update(bmd5);
//            result = md.digest();
//        }
//        catch (NoSuchAlgorithmException e) {
//            log.error("CMPPConnect md5():"+e.toString());
//        }
//        return result;
//
//    }
	private byte[] md5(String spid) {
		byte sp[]=spid.getBytes();
		byte bzero[] = new byte[9];
		byte[] bSPpassword = password.getBytes();
//		System.out.println("============================================================");
		byte btimestamp[] = (timestamp).getBytes();
		byte bmd5[] = new byte[sp.length + 9 + bSPpassword.length + btimestamp.length];
		int cur = 0;
		System.arraycopy(sp, 0, bmd5, cur, sp.length);
		cur += sp.length;
		System.arraycopy(bzero, 0, bmd5, cur, 9);
		cur += bzero.length;
		System.arraycopy(bSPpassword, 0, bmd5, cur, bSPpassword.length);
		cur += bSPpassword.length;
		System.arraycopy(btimestamp, 0, bmd5, cur, btimestamp.length);
		byte[] result = new byte[16];
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(bmd5);
			result = md.digest();
			if(log.isDebugEnabled()){
				log.debug("md5散列码："+Common.bytes2hex(result));
			}
		}
		catch (NoSuchAlgorithmException e) {
			log.error(e.toString());
		}
		return result;

	}
}