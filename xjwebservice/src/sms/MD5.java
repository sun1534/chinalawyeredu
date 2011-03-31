
package sms;

import java.security.MessageDigest;
/**
 * 
 * @author 刘哈哈
 * Mar 30, 20115:13:14 PM
 *
 */
public class MD5 {
	/**
	 * 将字符串md5,采取的编码为encoding
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str,String encoding) {
//		return str;
		try {
			byte[] strTemp = str.getBytes(encoding);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return byte2hex(md);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static String md5(String str) {
		return md5(str,"utf-8");
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
	public static boolean isEquale(String pass, String key,String encoding) { // 二行制转字符串
		try {
			byte[] strTemp = pass.getBytes(encoding);
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
	
	public static boolean isEquale(String pass, String key) { // 二行制转字符串
		return isEquale(pass,key,"utf-8");
	}
}
