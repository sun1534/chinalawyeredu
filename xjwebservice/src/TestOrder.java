import java.net.URL;

import service.OrderConstant;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification;
import cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service.SmsNotificationServiceLocator;

import com.chinatelecom.ismp.sp.IsmpSpEngine;
import com.chinatelecom.ismp.sp.IsmpSpEngineServiceLocator;
import com.chinatelecom.ismp.sp.req.OrderRelationUpdateNotifyReq;
import com.chinatelecom.ismp.sp.rsp.Response;

import entity.UserOrder;

public class TestOrder {

	public static void main(String[] args){
		UserOrder uo=new UserOrder();
		uo.setAreacode("1991");
		if ((uo.getAreacode() == null || uo.getAreacode().equals(""))
				) {
			System.out.println(uo.getMobile() + "没有上传区域信息,忽略");
		
		}
		
		if(uo.getAreacode() != null &&( 
				!OrderConstant.AREA_DATABASE.containsKey(uo.getAreacode()) &&
				!OrderConstant.CITY_DATABASE
				.containsKey(uo.getAreacode()))){
			System.out.println(uo.getMobile() + "上传的区域信息不对"+uo.getAreacode()+",忽略");
		}
	}
	
	public static void mai1n(String[] args) throws Exception {
		URL url = null;
		// String
		// urlStr="http://58.83.134.57:9001/LnXxtSIInterface/services/SyncSmsService";
//		String urlStr = "http://211.154.157.174:50090/axis2/services/SmsNotificationService?wsdl";
		String urlStr="http://60.13.166.203:6102/axis2/services/SmsNotificationService?wsdl";
		// String
		// urlStr="http://211.137.43.181:6080/LnXxtSIInterface/services/SyncSmsService?wsdl";
		try {
			url = new URL(urlStr);
			SmsNotificationServiceLocator locat = new SmsNotificationServiceLocator();
			SmsNotification service = locat.getSmsNotification(url);

			SmsMessage message = new SmsMessage();
//			message.setMessage("L#蓝");
//			message.setMessage("B#0990");
			message.setMessage("C#12345");
			message.setSenderAddress(new org.apache.axis.types.URI("tel:+8615309912535"));
			message.setSmsServiceActivationNumber(new org.apache.axis.types.URI("tel:+8610628919"));

			String registrationIdentifier = System.currentTimeMillis() + "";
			service.notifySmsReception(registrationIdentifier, message);

			System.out.println("MO下发完成");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		URL url = null;
		// String
		// urlStr="http://58.83.134.57:9001/LnXxtSIInterface/services/SyncSmsService";
//		String urlStr = "http://211.154.157.174:50090/axis2/services/IsmpSpEngineService?wsdl";
		String urlStr="http://60.13.166.203:6102/axis2/services/IsmpSpEngineService?wsdl";
		// String
		// urlStr="http://211.137.43.181:6080/LnXxtSIInterface/services/SyncSmsService?wsdl";
		try {
			url = new URL(urlStr);
			IsmpSpEngineServiceLocator locat = new IsmpSpEngineServiceLocator();
			IsmpSpEngine service = locat.getIsmpSpEngine(url);

			OrderRelationUpdateNotifyReq param = new OrderRelationUpdateNotifyReq();
			param.setOPType(0);
			param.setPackageID(System.currentTimeMillis() + "");
			param.setProductID(OrderConstant.TY_DZJC_PRODUCTID);
			param.setStreamingNo(System.currentTimeMillis() + "");
			param.setUserID("15309912535");
			param.setUserIDType(3);

			Response resp = service.orderRelationUpdateNotify(param);

			System.out.println(resp.getResultCode());
			System.out.println(resp.getStreamingNo());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
