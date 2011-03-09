/**
 * 
 */
package com.uu800.webbase.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * @author 鍗庨攱
 * 
 */
public class Chinese2Pinyin {

	private static final HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();;
	static {

		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	public static String to2pinyin(String str) {
		if(!(str!=null&&!str.equals("")))
			return "";
		StringBuffer sb = new StringBuffer();
		HanyuPinyinOutputFormat f = new HanyuPinyinOutputFormat();
		char[] strchar = str.toCharArray();
		for (int i = 0; i < strchar.length; i++) {
			try {
				String[] s = PinyinHelper.toHanyuPinyinStringArray(strchar[i],
						f);
				int length=s[0].length();
				sb.append(s[0].substring(0,length-1));
			} catch (Exception e) {
				sb.append(strchar[i]);
			}
		}
		return sb.toString();
	}
	public static void main(String[] args){
		System.out.println(to2pinyin("鍒樺崕閿�"));
	}
}