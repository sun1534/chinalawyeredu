/**
 * SmsNotificationBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;

import service.MoService;
import service.OrderConstant;
import service.OrderService;
import sms.Sms;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage;

import common.Globals;

public class SmsNotificationBindingImpl implements
		cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification {

	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(SmsNotificationBindingImpl.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 收到上行消息的处理
	 */
	public void notifySmsReception(java.lang.String registrationIdentifier,
			cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage message) throws java.rmi.RemoteException {

		if (message != null) {

			LOG.debug(df.format(new Date()) + "=>MO消息=>" + registrationIdentifier + "=>" + message.getMessage() + "=>"
					+ message.getSenderAddress() + "=>" + message.getSmsServiceActivationNumber());
			String content = message.getMessage() == null ? "" : message.getMessage();
			String sender = message.getSenderAddress().toString().replace("tel:", "").replace("+86", "");
			String dest = message.getSmsServiceActivationNumber().toString().replace("tel:", "").replace("+86", "");
			// A#车牌号#车牌类型#地市电话区号
			LOG.debug(df.format(new Date()) + "=>MO消息转化=>" + registrationIdentifier + "=>" + content + "=>" + sender
					+ "=>" + dest);
			try {

				MoService moService = (MoService) Globals.getWebBean("moService");
				OrderService orderService = (OrderService) Globals.getWebBean("orderService");

				int result = -2;
				// A#车牌号#车牌类型(黄/黑/蓝)#地市电话区号
				// B#地市电话区号
				// C#新车牌#车牌类型(黄/黑/蓝
				String busno = "";
				String bustype = "";
				String areaCode = "";
				int moid = moService.logMo(sender, content, dest, "");
				boolean updateorder = true;
				int a, b, c = 0;

				if (content.startsWith("A#") || content.startsWith("a#")) {
					String[] splits = content.split("#");
					int len = splits.length;
					if (len >= 2) {
						busno = splits[1];
					}
					if (len >= 3) {
						bustype = splits[2];
					}
					if (len >= 4) {
						areaCode = splits[3];
					}
					a = 1;
				} else if (content.startsWith("B#") || content.startsWith("b#")) {
					String[] splits = content.split("#");
					if (splits.length >= 2)
						areaCode = splits[1];
					b = 1;
				} else if (content.startsWith("C#") || content.startsWith("c#")) {
					String[] splits = content.split("#");
					if (splits.length >= 2)
						busno = splits[1];
					if (splits.length >= 3) {
						bustype = splits[2];
					}
					c = 1;
				} else {
					updateorder = false;
				}
				LOG.debug("updateorder==" + updateorder);
				if (updateorder) {

					if (areaCode != null && !areaCode.equals("") && OrderConstant.AREA_DATABASE.get(areaCode) == null) {
						// if (OrderConstant.AREA_DATABASE.get(areaCode) ==
						// null) {
						LOG.warn("区域信息不存在::" + areaCode);
						Sms.sendSms(sender, OrderConstant.MO_AREACODE_ERROR, "mo");
						// }
					} else {

						result = orderService.updateDzjcOrderInfo(sender, busno, bustype, areaCode);
						LOG.info("上行业务命令正确的处理结果:" + result);
						if (result == -1) {
							Sms.sendSms(sender, OrderConstant.MO_DO_ERROR, "mo");
						} else if (result == -2) {
							Sms.sendSms(sender, OrderConstant.NOT_ORDER, "mo");
						} else {
							String msg = "";
							if (!busno.equals(""))
								msg += "车牌号:" + busno + ",";
							if (!busno.equals(""))
								msg += "类型:" + bustype + ",";
							if (!areaCode.equals(""))
								msg += "区号:" + areaCode + ",地区:" + OrderConstant.AREA_DATABASE.get(areaCode) + ",";
							if (!msg.equals(""))
								msg = msg.substring(0, msg.length() - 1);
							Sms.sendSms(sender, OrderConstant.MO_HANDLE_OK + "(" + msg + ")", "mo");
							if ((content.startsWith("A#") || content.startsWith("a#"))&&!busno.equals("")) {// 第一次
								OrderService os = (OrderService) Globals.getWebBean("orderService");
								int cnt = os.getWZCount(busno);
								String smscontent = "";
								if (cnt == 0)
									smscontent = "您没有违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
								else
									smscontent = "您有" + cnt + "条违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
								Sms.sendSms(sender, smscontent, "mo");
							}
						}
					}
				} else {
					LOG.warn("上行命令字不正确：：" + content);
					Sms.sendSms(sender, OrderConstant.MO_ERROR, "mo");
				}

				moService.updateMoDotime(moid, result + "");
			} catch (Exception e) {
				LOG.error("上行处理失败", e);
			}

		} else
			LOG.debug(df.format(new Date()) + "=>notifySmsReception=>" + registrationIdentifier + "=>"
					+ SmsMessage.getTypeDesc() + "=>message为null");

	}

	/**
	 * 收到状态报告的处理
	 */
	public void notifySmsDeliveryReceipt(java.lang.String correlator,
			cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation deliveryStatus)
			throws java.rmi.RemoteException {
		if (deliveryStatus != null)
			LOG.debug(df.format(new Date()) + "=>状态报告=>" + correlator + "=>" + deliveryStatus.getAddress() + "=>"
					+ deliveryStatus.getDeliveryStatus());
		else
			LOG.debug(df.format(new Date()) + "=>notifySmsDeliveryReceipt=>" + correlator + "=>"
					+ DeliveryInformation.getTypeDesc() + "=>deliveryStatus为null");

	}

}
