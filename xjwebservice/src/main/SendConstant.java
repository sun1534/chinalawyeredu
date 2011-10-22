/**
 * SendConstant.java
 */
package main;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;

/**
 * @author 刘哈哈 Apr 18, 20113:00:15 PM
 * 
 */
public class SendConstant {

	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(SendConstant.class);

	// 发送短信的详细情况的次数
	public static int DETAIL_COUNT;
	// 几点中发，和那个job中的时间要一致
	public static int SEND_HOUR;
	// 没周几发违章信息
	public static List<Integer> SEND_PROMPT_WEEKDAY = new ArrayList<Integer>();
	public static String DEFAULT_BANNER = "【尊敬的汽车保姆用户】";// 默认的banner信息
	// 下发的违章信息,要加入这个
	public static String DEFAULT_ENDFIX = "（违章内容仅供参考，详情请前往就近交警大队查询）";
	
	public static int PAGESIZE=1000;
	static {
		InputStream in = null;

		try {

			Properties prop = new Properties();
		
			in = SendConstant.class.getResourceAsStream("/main/send.properties");
			prop.load(in);
		

			DETAIL_COUNT = Integer.parseInt(prop.getProperty("detailcount"));

			DEFAULT_ENDFIX = new String(prop.getProperty("endfix").getBytes("iso8859-1"),"utf-8");
			DEFAULT_BANNER = new String(prop.getProperty("banner").getBytes("iso8859-1"),"utf-8");

			SEND_HOUR = Integer.parseInt(prop.getProperty("sendhour"));
			PAGESIZE = Integer.parseInt(prop.getProperty("pagesize"));
			String weekdays = prop.getProperty("weekdays");
			String s[] = weekdays.split(",");
			for (String ss : s) {
				if (!ss.equals(""))
					SEND_PROMPT_WEEKDAY.add(Integer.parseInt(ss.trim()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception ee) {
				LOG.error("关闭sms.properties异常:" + e);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(SEND_HOUR);
		System.out.println(DEFAULT_ENDFIX);
		System.out.println(DEFAULT_BANNER);
		System.out.println(SEND_PROMPT_WEEKDAY);
	}
}
