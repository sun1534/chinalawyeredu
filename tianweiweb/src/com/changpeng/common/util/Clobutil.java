package com.changpeng.common.util;

import java.io.Reader;
import java.sql.Clob;

public class Clobutil {
	public static String tostring(Clob clob) {
		StringBuffer sb = new StringBuffer(1024);
		Reader instream = null;
		try {
			instream = clob.getCharacterStream();
			char[] buffer = new char[(int) clob.length()];
			int length = 0;
			while ((length = instream.read(buffer)) != -1) {
				sb.append(buffer);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (instream != null)
					instream.close();
			} catch (Exception dx) {
				instream = null;
			}
		}
		return sb.toString();
	}
}
