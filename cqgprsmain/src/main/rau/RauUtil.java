/**
 * NumberUtil.java
 */
package main.rau;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 华锋
 * Mar 30, 201011:25:04 PM
 *
 */
public class RauUtil {
	public static String IP_REGIX="([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	private static Pattern pattern=Pattern.compile(IP_REGIX);
	public static String tohex(int i){
	
		String s=Integer.toHexString(i);
		if(s.length()==1)
			return "000"+s;
		if(s.length()==2)
			return "00"+s;
		if(s.length()==3)
			return "0"+s;
		else
			return s;
	}
	

    public static boolean isIp(String ipAddress){
//        String test = "";
//        Pattern pattern = Pattern.compile(test);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }
	
	public static void main(String[] args){
		System.out.println(tohex(1));
		
		System.out.println(isIp("12.2.1.4"));
		System.out.println(isIp("132.22.1231.4"));
		System.out.println(isIp("121.23.255.4"));
		System.out.println(isIp("112.25.1.4"));
	}
}
