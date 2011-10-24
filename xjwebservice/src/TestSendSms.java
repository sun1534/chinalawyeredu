import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;

import sms.SetSOAPHeaderHelper;

import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation;
import cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1._interface.SendSms;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.send.v2_1.service.SendSmsServiceLocator;

public class TestSendSms {
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = null;
		
		String urlStr = "http://115.168.94.105:38080/sag/services/SendSmsService?wsdl";
long now=System.currentTimeMillis();
		try {
			url = new URL(urlStr);
			SendSmsServiceLocator locat = new SendSmsServiceLocator();
		
			SendSms service = locat.getSendSms(url);
//
			args=new String[]{"13510073023"};
			org.apache.axis.types.URI address=new org.apache.axis.types.URI("tel:+86"+args[0]);
			org.apache.axis.types.URI[] addresses=new  org.apache.axis.types.URI[]{address};
			
			java.lang.String senderName="10628919";
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.ChargingInformation charging=new ChargingInformation();
			charging.setAmount(new BigDecimal(0));
			charging.setCode("0");
			charging.setCurrency("0");
			charging.setDescription("FREE");
			
			java.lang.String message="20110926 21:13 TEST SEND SMS TO 86"+args[0];
			cn.com.chinatelecom.www.schema.ctcc.common.v2_1.SimpleReference receiptRequest=new SimpleReference();
			receiptRequest.setCorrelator(System.currentTimeMillis()+"");
//			receiptRequest.setCorrelator(correlator)
			org.apache.axis.types.URI uri=new org.apache.axis.types.URI("tel:+86"+args[0]);
			receiptRequest.setEndpoint(uri);
			receiptRequest.setInterfaceName("SendSms");

			
			SetSOAPHeaderHelper.OAFA=args[0];
			SetSOAPHeaderHelper.PRODUCTID="131100215010000000991";
			
//			DeliveryInformation[] informations=service.getSmsDeliveryStatus("19310100212172529300627");
			System.out.println("到这里的时间为::"+(System.currentTimeMillis()-now));
			now=System.currentTimeMillis();
			String result = service.sendSms(addresses, senderName, charging, message, receiptRequest);


			
//			System.out.println("发送结果:" + informations[0]);
			System.out.println("发送结 果:" + result+"===>"+(System.currentTimeMillis()-now));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}