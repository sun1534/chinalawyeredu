import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import sms.Sms;

public class NewTestSendSms {
	private static DateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String mobile = "18999105664";
		String content = "测试短信下发:" + SDF.format(new Date());
		
	}
}