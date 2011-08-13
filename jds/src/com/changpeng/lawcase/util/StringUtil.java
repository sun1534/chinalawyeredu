/**
 * 
 */
package com.changpeng.lawcase.util;

/**
 * @author 华锋
 * Dec 26, 2009-11:06:01 PM
 *
 */
public class StringUtil {

	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String str2hexstr(String str) {
		byte b[] = str.getBytes();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(NumberUtil.byte2hex(b[i]));
		}
		return sb.toString();

	}

	/**
	 * 
	 * @param aa
	 * @return
	 */
	public static String strhex2str(String aa) {
		byte[] cc = aa.getBytes();
		int size = cc.length;
		byte[] cc1 = new byte[size / 2];
		int i = 0;
		while (i < size) {
			if (cc[i] > 57)
				cc[i] = (byte) (cc[i] - 55);
			else
				cc[i] = (byte) (cc[i] - 48);
			i++;
		}
		i = 0;
		int j = 0;
		while (i < size) {
			cc1[j] = (byte) (((cc[i] & 0x0f) << 4) | (cc[i + 1] & 0xff));
			j++;
			i = i + 2;
		}
		return new String(cc1);
	}
}
