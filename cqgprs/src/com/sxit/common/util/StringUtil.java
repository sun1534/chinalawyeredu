/*
 * Created on 2005-6-27 9:36:51
 * 
 */

package com.sxit.common.util;

/**
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 
	 * @param str
	 * @param strlen
	 * @return
	 */
	public static byte[] addRightSpace(String str, int strlen) {
		byte[] t = str.getBytes();
		// if (t.length > strlen)
		// throw new IllegalArgumentException("str.length=" + t.length +
		// ",strlen=" + strlen);
		byte[] result = new byte[strlen];
		for (int i = 0; i < result.length; i++)
			result[i] = 0x20;
		System.arraycopy(t, 0, result, 0, t.length);
		return result;

	}

	/**
	 * 
	 * @param str
	 * @param strlen
	 * @return
	 */
	public static byte[] addLeftSpace(String str, int strlen) {
		byte[] t = str.getBytes();
		// if (t.length > strlen)
		// throw new IllegalArgumentException("str.length=" + t.length +
		// ",strlen=" + strlen);
		byte[] result = new byte[strlen];
		for (int i = 0; i < result.length; i++)
			result[i] = 0x20;
		System.arraycopy(t, 0, result, strlen - t.length, t.length);
		return result;
	}

	/**
	 * 
	 * @param str
	 * @param strlen
	 * @return
	 */
	public static byte[] addLeftZero(String str, int strlen) {
		byte[] t = str.getBytes();
		// if (t.length > strlen)
		// throw new IllegalArgumentException("str.length=" + t.length +
		// ",strlen=" + strlen);
		byte[] result = new byte[strlen];
		System.arraycopy(t, 0, result, strlen - t.length, t.length);
		return result;
	}

	/**
	 * 
	 * @param str
	 * @param strlen
	 * @return
	 */
	public static byte[] addRightZero(String str, int strlen) {
		byte[] t = str.getBytes();
		// if (t.length > strlen)
		// throw new IllegalArgumentException("str.length=" + t.length +
		// ",strlen=" + strlen);
		byte[] result = new byte[strlen];
		System.arraycopy(t, 0, result, 0, t.length);
		return result;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToUpper(String str) {
		char firstChar = Character.toUpperCase(str.charAt(0));
		StringBuffer buffer = new StringBuffer(str);
		buffer.deleteCharAt(0);
		buffer.insert(0, firstChar);
		return buffer.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToLower(String str) {
		char firstChar = Character.toLowerCase(str.charAt(0));
		StringBuffer buffer = new StringBuffer(str);
		buffer.deleteCharAt(0);
		buffer.insert(0, firstChar);
		return buffer.toString();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toGBK(String str) {
		if (str == null) {
			return null;
		}
		try {
			str = new String(str.getBytes("ISO8859-1"), "GBK");
		}
		catch (Exception e) {
			System.out.print(e.toString());
		}
		return str;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String toGB2312(String str) {
		if (str == null) {
			return null;
		}
		try {
			str = new String(str.getBytes("ISO8859-1"), "GB2312");
		}
		catch (Exception e) {
			System.out.print(e.toString());
		}
		return str;
	}

	/**
	 * 
	 * @param strText
	 * @return
	 */
	public static String gb2Unicode(String strText) {
		if (strText == null || strText.equals("")) {
			return strText;
		}
		char c;
		StringBuffer strRet = new StringBuffer();
		int intAsc = 0;
		String strHex = "";
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			if (intAsc > 128) {
				strHex = Integer.toHexString(intAsc);
				strRet.append("&#x").append(strHex).append(";");
			}
			else {
				// strRet = strRet + c;
				strRet.append(c);
			}
		}
		return strRet.toString();
	}

	/**
	 * 
	 * @param strText
	 * @return
	 */
	public static String gb2UTF8(String strText) {
		if (strText == null || strText.equals("")) {
			return strText;
		}
		char c;
		String strRet = "";
		int intAsc;
		String strHex = "";
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (strHex.length() == 1)
				strHex = "000" + strHex;
			else if (strHex.length() == 2)
				strHex = "00" + strHex;
			else if (strHex.length() == 3)
				strHex = "0" + strHex;

			strRet = strRet + "&#x" + strHex + ";";
		}
		return strRet;
	}

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
			// sb.append(" ");
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
	
	public static String getDateFromTime(String str){
		String date="";
		if(str!=null)
			date=str.split(" ")[0];
		return date;
	}
	public static String getHmFromTime(String str){
		String hm="";
		if(str!=null&&str.split(" ").length>1)
			hm=str.split(" ")[1];		
		return hm;
	}
}