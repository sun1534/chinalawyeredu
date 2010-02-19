/*
 * Created on 2005-6-30 9:52:57
 * 
 */

package com.changpeng.common.util;

import java.text.DecimalFormat;

/**
 * 
 * @author HuaFeng
 * @version 1.0
 */
public class NumberUtil {
	private static final char chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private final static DecimalFormat nf = new DecimalFormat("#####0.0");
	/**
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte b) {
		char hex[] = new char[2];
		hex[0] = chars[(new Byte(b).intValue() & 0xf0) >> 4];
		hex[1] = chars[new Byte(b).intValue() & 0xf];
		return new String(hex);
	}
	public static String toMoney(String s) {
		if (s == null || s.equals("")) {
			return "0.00";
		}
		try {
			return toMoney(Double.parseDouble(s));
		} catch (Exception e) {
			return s;
		}
	}

	public static String toMoney(double d) {
		try {
			if(d==0)
				return "0.0";
			return nf.format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}
	/**
	 * 
	 * @param b
	 * @return
	 */
	public static String bytes2hex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byte2hex(b[i]));
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param mybytes
	 * @return
	 */
	public static long bytes2long(byte mybytes[]) {
		long tmp = (0xff & mybytes[0]) << 56 | (0xff & mybytes[1]) << 48 | (0xff & mybytes[2]) << 40 | (0xff & mybytes[3]) << 32
				| (0xff & mybytes[4]) << 24 | (0xff & mybytes[5]) << 16 | (0xff & mybytes[6]) << 8 | 0xff & mybytes[7];
		return tmp;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] long2bytes(long i) {
		byte mybytes[] = new byte[8];
		mybytes[7] = (byte) (int) ((long) 255 & i);
		mybytes[6] = (byte) (int) (((long) 65280 & i) >> 8);
		mybytes[5] = (byte) (int) (((long) 0xff0000 & i) >> 16);
		mybytes[4] = (byte) (int) (((long) 0xff000000 & i) >> 24);
		int high = (int) (i >> 32);
		mybytes[3] = (byte) (0xff & high);
		mybytes[2] = (byte) ((0xff00 & high) >> 8);
		mybytes[1] = (byte) ((0xff0000 & high) >> 16);
		mybytes[0] = (byte) ((0xff000000 & high) >> 24);
		return mybytes;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] int2bytes(int i) {

		byte mybytes[] = new byte[4];
		mybytes[3] = (byte) (0xff & i);
		mybytes[2] = (byte) ((0xff00 & i) >> 8);
		mybytes[1] = (byte) ((0xff0000 & i) >> 16);
		mybytes[0] = (byte) ((0xff000000 & i) >> 24);
		return mybytes;
	}

	/**
	 * 
	 * @param mybytes
	 * @return
	 */
	public static int bytes2int(byte mybytes[]) {

		int tmp = (0xff & mybytes[0]) << 24 | (0xff & mybytes[1]) << 16 | (0xff & mybytes[2]) << 8 | 0xff & mybytes[3];
		return tmp;
	}

	/**
	 * 
	 * @param mybytes
	 * @return
	 */
	public static short bytes2short(byte mybytes[]) {

		short tmp = (short) ((0xff & mybytes[0]) << 8 | 0xff & mybytes[1]);
		return tmp;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] short2bytes(short i) {
		byte mybytes[] = new byte[2];
		mybytes[1] = (byte) (0xff & i);
		mybytes[0] = (byte) ((0xff00 & i) >> 8);
		return mybytes;
	}

	/**
	 * 
	 * @param a
	 * @param n
	 * @return
	 */
	public static long nsquare(int a, int n) {
		if (n == 0)
			return 1;
		else if (n < 0)
			throw new java.lang.IllegalArgumentException();
		else {
			long result = a;
			for (int i = 1; i < n; i++) {
				result *= a;
			}
			return result;
		}
	}

	/**
	 * 
	 * @param a
	 * @param n
	 * @return
	 */
	public static int reversensquare(long a, int n) {
		if (a <= 0)
			throw new java.lang.IllegalArgumentException();
		else {
			for (int i = 0;; i++) {
				if (nsquare(n, i) <= a && nsquare(n, i + 1) > a)
					return i;

			}
		}
	}

	public static void main(String args[]) {
		System.out.println(reversensquare(15, 2));
		System.out.println(reversensquare(13, 2));
		System.out.println(reversensquare(17, 2));
	}
}