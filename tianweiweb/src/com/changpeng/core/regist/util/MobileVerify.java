/**
 * 
 */
package com.changpeng.core.regist.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 	
 * 中国移动134.135.136.137.138.139.150.151.152.157.158.159.187.188 ,147(数据卡)
 * 中国联通130.131.132.155.156.185.186
 * 中国电信133.153.180.189
 * CDMA 133,153
 * 
 * 正则如下：
 * </pre>
 * 
 * @author 华锋 May 27, 2009 2:08:53 AM
 * 
 * 
 * 
 */
public class MobileVerify {

	/**
	 * 匹配移动手机号
	 */
	private static String PATTERN_CMCMOBILENUM = "^1(3[4-9]|5[012789]|8[78])[0-9]{8}$";
	/**
	 * 匹配电信手机号
	 */
	private static String PATTERN_CTCMOBILENUM = "^18[09][0-9]{8}$";
	/**
	 * 匹配联通手机号
	 */
	private static String PATTERN_CUTMOBILENUM = "^1(3[0-2]|5[56]|8[56])[0-9]{8}$";
	/**
	 * 匹配CDMA手机号
	 */
	private static String PATTERN_CDMAMOBILENUM = "^1[35]3[0-9]{8}$";

	private static Pattern pattern = Pattern.compile(PATTERN_CMCMOBILENUM);

	/**
	 * 判断是否移动的号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		Matcher matcher = pattern.matcher(mobile);
		boolean b = matcher.matches();
		return b;

	}
	
	public static void main(String[] args){
		System.out.println(isMobile(""));
		System.out.println(isMobile("121241231"));
		System.out.println(isMobile("13510073023"));
		System.out.println(isMobile("135100730231"));
		System.out.println(isMobile("13510073032"));
		System.out.println(isMobile("15110073023"));
		System.out.println(isMobile("13210073023"));
		System.out.println(isMobile("18810073023"));
		System.out.println(isMobile("18710073023"));
		System.out.println(isMobile("18610073023"));
		System.out.println(isMobile("15810073023"));
		System.out.println(isMobile("12110073023"));
		System.out.println(isMobile("114110073023"));
		System.out.println(isMobile("15510073023"));
		System.out.println(isMobile("15310073023"));
		System.out.println(isMobile("15510073023"));
		System.out.println(isMobile("15610073023"));
		System.out.println(isMobile("15710073023"));
	}
}
