
package com.sxit.common.util;

import java.security.MessageDigest;

/**
 * MD5加密算法 用于用户密码的加密
 */

public class MD5 {
	/**
	 * 将字符串md5,采取的编码为gb2312
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
//		return str;
		try {
			byte[] strTemp = str.getBytes("gb2312");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return byte2hex(md);
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将二进制转换为16进制形式的字串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			}
			else {
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}

	// 验证函数
	public static boolean isEquale(String pass, String key) { // 二行制转字符串
		try {
			byte[] strTemp = pass.getBytes("gb2312");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			String str = byte2hex(md);
			return key.equals(str);
		}
		catch (Exception e) {
			return false;
		}
	}

}
