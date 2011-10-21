/**
 * SendConstant.java
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘哈哈
 * Apr 18, 20113:00:15 PM
 *
 */
public class SendConstant {

	//没有违章的时候,每周的星期几发送公共通知信息
	public static List<Integer> SEND_PROMPT_WEEKDAY=new ArrayList<Integer>();
//	public static String DZJC_DATABASE="base";
	static{
		SEND_PROMPT_WEEKDAY.add(5);
		SEND_PROMPT_WEEKDAY.add(1);
	}
	
}
