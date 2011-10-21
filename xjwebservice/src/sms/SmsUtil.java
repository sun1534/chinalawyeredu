/**
 * SmsSend.java
 */
package sms;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import service.OrderConstant;
import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation;
import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsServiceLocator;

/**
 * @author 刘哈哈 Mar 30, 20115:07:10 PM
 * 
 * 这个只是插到数据库里，另外一个定时器，每5秒钟扫描那个临时表来处理
 * 
 */
public class SmsUtil {

	private static Log LOG = LogFactory.getLog(SmsUtil.class);

	private static String URL = "http://115.168.94.105:38080/sag/services/SendSmsService?wsdl";
	private static String SENDER_NAME = "10628919";
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		String content = "测试:" + df.format(new Date());
		String type = "test";
		String linkid = null;
		String productId = OrderConstant.TY_DZJC_PRODUCTID;
		String s = sendSms(args[0], content, type, linkid, productId);
		System.out.println("短信结果:::" + s);
	}

	public static String sendSms(String mobile, String content, String type, String linkid, String productId) {
		System.out.println(mobile + "->" + productId + "->" + linkid + "->" + content);
		if (mobile == null || mobile.equals("") || mobile.length() != 11)
			return "-3";
		if (content == null || content.equals(""))
			return "-4";
		URL url = null;
		String result = "-100";
		try {
			url = new URL(URL);
			SendSmsServiceLocator locat = new SendSmsServiceLocator();
			SendSms service = locat.getSendSms(url);
			org.apache.axis.types.URI address = new org.apache.axis.types.URI("tel:+86" + mobile);
			org.apache.axis.types.URI[] addresses = new org.apache.axis.types.URI[] { address };

			java.lang.String senderName = SENDER_NAME;
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging = new ChargingInformation();
			charging.setAmount(new BigDecimal(0));
			charging.setCode("0");
			charging.setCurrency("0");
			charging.setDescription("FREE");

			java.lang.String message = content;
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest = new SimpleReference();
			receiptRequest.setCorrelator(System.currentTimeMillis() + "");
			org.apache.axis.types.URI uri = new org.apache.axis.types.URI("tel:+86" + mobile);
			receiptRequest.setEndpoint(uri);
			receiptRequest.setInterfaceName("SendSms");
			// 每次这里都要重新处理下那个productId;
			SetSOAPHeaderHelper.PRODUCTID = productId;
			SetSOAPHeaderHelper.LINKID = linkid;
			SetSOAPHeaderHelper.OAFA = mobile;

			result = service.sendSms(addresses, senderName, charging, message, receiptRequest);
			// String result="1";
			LOG.info(mobile + "=>" + SetSOAPHeaderHelper.PRODUCTID + "=>发送结果:" + result);

			//

			return result;
		} catch (Exception e) {
			LOG.error("短信发送异常", e);
			result = "-1";
		} catch (Error error) {
			LOG.error("短信失败", error);
			result = "-2";
		}
		return result;
	}
}