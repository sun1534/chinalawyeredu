/**
 * com.prj.util.EncryptUtil.java
 * 
 */
package com.uu800.webbase.util;

import java.security.MessageDigest;

/**
 *Jan 31, 2010 4:06:38 PM 
 *@author 华锋
 *
 */
public class EncryptUtil {

	/**
	 * md5加密
	 * @param s
	 * @return
	 */
	public final static String MD5(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return NumberUtil.bytes2NoSpaceHex(md);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * sha-1加密
	 * @param s
	 * @return
	 */
	public static String SHA1(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-1");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return NumberUtil.bytes2NoSpaceHex(md);
	
		} catch (Exception e) {
			return null;
		}
	}
}
