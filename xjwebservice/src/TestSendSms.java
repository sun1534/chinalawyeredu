import java.math.BigDecimal;
import java.net.URL;

import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation;
import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsServiceLocator;

public class TestSendSms {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		
		String urlStr = "http://115.168.94.105:38080/sag/services/SendSmsService?wsdl";

		try {
			url = new URL(urlStr);
			SendSmsServiceLocator locat = new SendSmsServiceLocator();
		
			SendSms service = locat.getSendSms(url);

			
			org.apache.axis.types.URI address=new org.apache.axis.types.URI("tel:+8618999910502");
			org.apache.axis.types.URI[] addresses=new  org.apache.axis.types.URI[]{address};
			
			java.lang.String senderName="10628919";
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging=new ChargingInformation();
			charging.setAmount(new BigDecimal(0));
			charging.setCode("0");
			charging.setCurrency("0");
			charging.setDescription("免费");
			
			java.lang.String message="20110726 00:49测试短信下发到8618999910502";
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest=new SimpleReference();
			receiptRequest.setCorrelator(System.currentTimeMillis()+"");
			org.apache.axis.types.URI uri=new org.apache.axis.types.URI("tel:+8618999910502");
			receiptRequest.setEndpoint(uri);
			receiptRequest.setInterfaceName("SendSms");
//			DeliveryInformation[] informations=service.getSmsDeliveryStatus("19310100212172529300627");
			String result = service.sendSms(addresses, senderName, charging, message, receiptRequest);

//			System.out.println("发送结果:" + informations[0]);
			System.out.println("发送结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}