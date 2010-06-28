/**
 * Clob2String.java
 */

package com.sxit.query.util;

import java.io.Reader;
import java.sql.Clob;

/**
 * @author 华锋 2008-7-4 上午10:39:46
 * 
 */
public class Clob2String {

	public  static String clob2String(Clob clob) {
		if (clob == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer(65535);// 64K
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];// 每次获取60K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
			
//			System.out.println(sb);
			
			
		}
		catch (Exception ex) {
			sb.append("");
		}
		finally {
			try {
				if (clobStream != null)
					clobStream.close();
			}
			catch (Exception e) {
				System.out.println("clob2string 转换失败:" + e);
			}
		}
		if (sb == null)
			return "";
		else
			return sb.toString();
	}
}
