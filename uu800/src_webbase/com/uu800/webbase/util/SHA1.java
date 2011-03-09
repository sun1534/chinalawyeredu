/**
 * 
 */
package com.uu800.webbase.util;

import java.security.MessageDigest;

/**
 * @author sg
 *
 */
public class SHA1 {
	// 加密函数
	public final static String SHA1(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-1");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return byte2hex(md);
		} catch (Exception e) {
			return null;
		}
	}

	// 二进制转字符串
	public final static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
				// if (n<b.length-1) hs=hs+":";
			}
		}
		return hs.toUpperCase();
	}

	// 验证函数
	public final static boolean isEquale(String pass, String key) { // 二行制转字符串
		try {
			byte[] strTemp = pass.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("SHA-1");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			String str = byte2hex(md);
			return key.equals(str);
		} catch (Exception e) {
			return false;
		}
	}

	// 测试
	public static void main(String[] args) {
		System.out.println(SHA1.SHA1("XX"));
		System.out.println(SHA1.SHA1("XX").length());
		System.out.println(SHA1.SHA1("123456"));
		System.out.println(isEquale("XX", "20026DC165C030FE3A5D9609A6E61AB26210CBC1"));
	}
}
