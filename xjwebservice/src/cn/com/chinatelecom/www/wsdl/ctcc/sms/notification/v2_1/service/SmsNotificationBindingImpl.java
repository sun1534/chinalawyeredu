/**
 * SmsNotificationBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.xml.soap.SOAPElement;

import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.logging.Log;

import service.MoService;
import service.OrderConstant;
import service.OrderService;
import sms.Sms;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.DeliveryInformation;
import cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage;

import common.Globals;

import entity.UserOrder;

public class SmsNotificationBindingImpl implements
		cn.com.chinatelecom.www.wsdl.ctcc.sms.notification.v2_1._interface.SmsNotification {

	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(SmsNotificationBindingImpl.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//
	// /**
	// * 收到上行消息的处理
	// *
	// *
	// *
	// */
	// public void notifySmsReception_old(java.lang.String
	// registrationIdentifier,
	// cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage message) throws
	// java.rmi.RemoteException {
	//
	// if (message != null) {
	//
	// LOG.debug(df.format(new Date()) + "=>MO消息=>" + registrationIdentifier +
	// "=>" + message.getMessage() + "=>"
	// + message.getSenderAddress() + "=>" +
	// message.getSmsServiceActivationNumber());
	// String content = message.getMessage() == null ? "" :
	// message.getMessage().trim();
	// content = content.replace("＃", "#");
	// String sender = message.getSenderAddress().toString().replace("tel:",
	// "").replace("+86", "");
	// String dest =
	// message.getSmsServiceActivationNumber().toString().replace("tel:",
	// "").replace("+86", "");
	// // A#车牌号#车牌类型#地市电话区号
	// LOG.debug(df.format(new Date()) + "=>MO消息转化=>" + registrationIdentifier +
	// "=>" + content + "=>" + sender
	// + "=>" + dest);
	// try {
	//
	// MoService moService = (MoService) Globals.getWebBean("moService");
	// OrderService orderService = (OrderService)
	// Globals.getWebBean("orderService");
	//
	// int result = -2;
	// // A#车牌号#车牌类型(黄/黑/蓝)#地市电话区号
	// // B#地市电话区号
	// // C#新车牌#车牌类型(黄/黑/蓝
	// String busno = "";
	// String bustype = "";
	// String areaCode = "";
	// int moid = moService.logMo(sender, content, dest, "");
	// boolean updateorder = true;
	// int a = 0, b = 0, c = 0, d = 0;
	//
	// String first = "";
	// if (content.length() >= 1)
	// first = content.substring(0, 1);
	//
	// if (content.equalsIgnoreCase("TY") || content.equalsIgnoreCase("DZ")) {
	// LOG.warn("上行的为定制命令字,不考虑");
	// } else if (OrderConstant.AREA_DATABASE.containsKey(content)) { //
	// 只有正确的区域才行
	// areaCode = content;
	// b = 1;
	// } else if (OrderConstant.MEMBER_CHEPAI.containsKey(first)
	// || OrderConstant.MEMBER_CHEPAI.containsKey(content)
	// || OrderConstant.MEMBER_CHEPAI.values().contains(content)) {//
	// 只有正确的车牌类型才行
	// if (OrderConstant.MEMBER_CHEPAI.values().contains(first))
	// bustype = first;
	// else
	// bustype = content;
	// d = 1;
	// } else if (OrderConstant.CHEPAI_FIRST.contains(first) && content.length()
	// < 8 && content.length() > 5) { // 这样来判断，如果你实在要输错了，那就没法子了,content的内容必须小于8
	// busno = content;
	// c = 1;
	// } else if (content.startsWith("A#") || content.startsWith("a#")) {
	// LOG.debug("车牌A#===>" + content);
	// String[] splits = content.split("#");
	// int len = splits.length;
	// if (len >= 2) {
	// busno = splits[1];
	// first = busno.substring(0, 1);
	// }
	// if (len >= 3) {
	// bustype = splits[2];
	// }
	// if (len >= 4) {
	// areaCode = splits[3];
	// }
	// a = 1;
	// } else if (content.startsWith("B#") || content.startsWith("b#")) {
	// String[] splits = content.split("#");
	// if (splits.length >= 2)
	// areaCode = splits[1];
	// b = 1;
	// } else if (content.startsWith("D#") || content.startsWith("d#")) {
	// String[] splits = content.split("#");
	// if (splits.length >= 2)
	// bustype = splits[1];
	// d = 1;
	// } else if (content.startsWith("C#") || content.startsWith("c#")) {
	// String[] splits = content.split("#");
	// if (splits.length >= 2) {
	// busno = splits[1];
	// first = busno.substring(0, 1);
	// }
	// if (splits.length >= 3) {
	// bustype = splits[2];
	// }
	// c = 1;
	// } else {
	// updateorder = false;
	// }
	//
	// if (busno != null && !busno.equals("") &&
	// !OrderConstant.PROVINCE_FIRST.contains(first)) {
	// busno = "新" + busno; // 车牌前补新疆的区号信息
	// }
	// LOG.debug("updateorder==" + updateorder);
	// UserOrder uo = (UserOrder) orderService.getUserOrder(sender, null);
	// if (uo != null) {
	// String productId = uo.getProductid();
	// if (updateorder) {
	//
	// if (areaCode != null && !areaCode.equals("")
	// && OrderConstant.AREA_DATABASE.get(areaCode) == null) {
	// // if (OrderConstant.AREA_DATABASE.get(areaCode) ==
	// // null) {
	// LOG.warn("区域信息不存在::" + areaCode);
	// Sms.sendSms(sender, OrderConstant.MO_AREACODE_ERROR, "mo", productId);
	// // }
	// } else {
	//
	// result = orderService.updateDzjcOrderInfo(sender, busno, bustype,
	// areaCode);
	// LOG.info("上行业务命令正确的处理结果:" + result);
	// if (result == -1) {
	// Sms.sendSms(sender, OrderConstant.MO_DO_ERROR, "mo", productId);
	// } else if (result == -2) {
	// Sms.sendSms(sender, OrderConstant.NOT_ORDER, "mo", productId);
	// } else {
	// String msg = "";
	// if (!busno.equals(""))
	// msg += "车牌号:" + busno + ",";
	// if (!busno.equals(""))
	// msg += "类型:" + bustype + ",";
	// if (!areaCode.equals(""))
	// msg += "区号:" + areaCode + ",地区:" +
	// OrderConstant.AREA_DATABASE.get(areaCode) + ",";
	// if (!msg.equals(""))
	// msg = msg.substring(0, msg.length() - 1);
	//
	// if (b == 1) { // 上行的是修改区号信息
	// Sms.sendSms(sender, "您选择" + OrderConstant.AREA_DATABASE.get(areaCode)
	// + "地区，请提供您的车牌类型1、蓝牌2、黄牌3、黑牌，请回复1或2或3.返回请按回复0", "mo", productId);
	// } else if (d == 1) {// 上行的是车牌类型,则现在设置车牌
	// Sms.sendSms(sender, "您选择的车牌类型为" +
	// OrderConstant.MEMBER_CHEPAI.get(bustype)
	// + "牌车，请回复您的车牌号码（英文字母+数字），如车牌号为新AC4102则回复AC4102．返回请按回复0", "mo",
	// productId);
	//
	// } else if (c == 1) {
	// // 您已成功开通汽车保姆体验业务，您的车牌是蓝牌车新AC4102，感谢您的使用，变更车牌请回复0，询：09915881229
	//
	// Sms.sendSms(sender, "您已成功开通汽车保姆体验业务，您的车牌是"
	// + OrderConstant.MEMBER_CHEPAI.get(uo.getChepaileixing()) + "车"
	// + uo.getChepai() + "，感谢您的使用，变更车牌请回复0，询：09915881229", "mo", productId);
	//
	// if (busno != null && !busno.equals("")) {
	// int cnt = orderService.getWZCount(uo.getChepai());
	// String smscontent = "";
	// if (cnt == 0)
	// smscontent = "您没有违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
	// else
	// smscontent = "您有" + cnt + "条违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
	// Sms.sendSms(sender, smscontent, "mo", productId);
	// }
	// } else if (a == 1) {
	// Sms.sendSms(sender, OrderConstant.MO_HANDLE_OK + "(" + msg + ")", "mo",
	// productId);
	// }
	//
	// }
	// }
	// } else {
	// LOG.warn("上行命令字不正确：：" + content);
	// Sms.sendSms(sender, OrderConstant.MO_ERROR, "mo", productId);
	// }
	// }
	//
	// moService.updateMoDotime(moid, result + "");
	// } catch (Exception e) {
	// LOG.error("上行处理失败", e);
	// }
	//
	// } else
	// LOG.debug(df.format(new Date()) + "=>notifySmsReception=>" +
	// registrationIdentifier + "=>"
	// + SmsMessage.getTypeDesc() + "=>message为null");
	//
	// }

	/**
	 * * B#后面跟的是地区的区号如B#0991 输入错误提示：您输入的区号不正确，如乌鲁木齐请回复B#0991，详询09915881229
	 * L#跟的车牌类型 L#蓝牌 输入错误提示：您输入的车牌类型不正确，蓝牌车请输入L#蓝牌，黄牌车请输入L#黄牌，详询09915881229
	 * C#跟的是车牌号码 C#A2D250 输入错误提示：您输入的车牌号码不正确，如新AA4758请输入C#AA4758，详询09915881229
	 */
	public void notifySmsReception(java.lang.String registrationIdentifier,
			cn.com.chinatelecom.www.schema.ctcc.sms.v2_1.SmsMessage message) throws java.rmi.RemoteException {
		String linkid = "";
		String productId = "";
		if (message != null) {
			MessageContext context = MessageContext.getCurrentContext();
			SOAPEnvelope requestEnvelope = context.getRequestMessage().getSOAPEnvelope();
			SOAPHeaderElement requestSequenceIdHeader = requestEnvelope.getHeaderByName(
					"http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "NotifySOAPHeader");
			LOG.debug("SOAPHeaderElement==" + requestSequenceIdHeader);
			if (requestSequenceIdHeader != null) {
				Iterator iterator = requestSequenceIdHeader.getChildElements();
				while (iterator.hasNext()) {
					SOAPElement element = (SOAPElement) iterator.next();
					String elementName = element.getElementName().getLocalName();

					LOG.info("头部信息：" + elementName + "==>" + element.getValue());

					if (elementName.equals("linkId"))
						linkid = element.getValue();
					// soapHeader.setServiceCode(element.getValue());
					// else if (elementName.equals("servicePwd"))
					// soapHeader.setServicePwd(element.getValue());
				}
			}
			// soapHeader.setIp(this.getIP());

			LOG.debug(df.format(new Date()) + "=>MO消息=>" + registrationIdentifier + "=>" + message.getMessage() + "=>"
					+ message.getSenderAddress() + "=>" + message.getSmsServiceActivationNumber());
			String content = message.getMessage() == null ? "" : message.getMessage().trim();
			content = content.replace("＃", "#");
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
				int a = 0, b = 0, c = 0;

				if (content.equalsIgnoreCase("TY") || content.equalsIgnoreCase("DZ")||content.equalsIgnoreCase("QX") || content.equalsIgnoreCase("QXTY")) {
					LOG.warn("上行的为定制命令字,不考虑");
				} else if (content.startsWith("B#") || content.startsWith("b#")) { // B#0991
					int idx = content.indexOf("#");
					productId = "131100215010000000997";
					areaCode = content.substring(idx + 1);
					a = 1;
				} else if (content.startsWith("C#") || content.startsWith("c#")) { // C#A2D250
					int idx = content.indexOf("#");
					busno = content.substring(idx + 1);
					productId = "131100215010000000999";
					b = 1;
				} else if (content.startsWith("L#") || content.startsWith("l#")) {// L#蓝牌
					int idx = content.indexOf("#");
					bustype = content.substring(idx + 1);
					productId = "131100215010000000998";
					if (bustype.length() > 1)
						bustype = bustype.substring(0, 1);

					c = 1;
				} else {
					updateorder = false;
				}

				if (busno != null && !busno.equals("")) {
					String first = busno.substring(0, 1);
					if (!OrderConstant.PROVINCE_FIRST.contains(first))
						busno = "新" + busno; // 车牌前补新疆的区号信息
				}
				LOG.debug("updateorder==" + updateorder + ",a=" + a + ",b=" + b + ",c=" + c + ",areaCode=" + areaCode
						+ ",busno=" + busno + ",bustype=" + bustype);
				UserOrder uo = (UserOrder) orderService.getUserOrder(sender, null);
				if (uo != null) {
					if (productId.equals("")) {
						productId = uo.getProductid();
						linkid = "";
					}
					if (updateorder) {

						String s = orderService.getMemberChepaiTypeName(bustype);

						if (areaCode != null && !areaCode.equals("")
								&& OrderConstant.AREA_DATABASE.get(areaCode) == null) {
							LOG.warn("区域信息不存在::" + areaCode);
							Sms.sendSms(sender, OrderConstant.MO_AREACODE_ERROR, "mo", linkid, productId);
						} else if (bustype != null && !bustype.equals("") && s.equals("未知")) {
							LOG.warn("车牌类型信息不正确::" + bustype);
							Sms.sendSms(sender, OrderConstant.MO_CHEPAITYPE_ERROR, "mo", linkid, productId);
						} else {
							result = orderService.updateDzjcOrderInfo(sender, busno, bustype, areaCode);
							LOG.info("上行业务命令正确的处理结果:" + result);
							if (result == -1) {
								Sms.sendSms(sender, OrderConstant.MO_DO_ERROR, "mo", linkid, productId);
							} else if (result == -2) {
								Sms.sendSms(sender, OrderConstant.NOT_ORDER, "mo", linkid, productId);
							} else {
								String msg = "";
								if (!busno.equals(""))
									msg += "车牌号:" + busno + ",";
								if (!bustype.equals(""))
									msg += "类型:" + bustype + ",";
								if (!areaCode.equals(""))
									msg += "区号:" + areaCode + ",地区:" + OrderConstant.AREA_DATABASE.get(areaCode) + ",";
								if (!msg.equals(""))
									msg = msg.substring(0, msg.length() - 1);

								if (a == 1) { // 上行的是修改区号信息
									Sms.sendSms(sender, "您选择" + OrderConstant.AREA_DATABASE.get(areaCode)
											+ "地区，请提供您的车牌类型1、蓝牌2、黄牌3、黑牌，请回复L#蓝牌或者L#1。", "mo", linkid, productId);
								} else if (c == 1) {// 上行的是车牌类型,则现在设置车牌
									Sms
											.sendSms(sender, "您选择的车牌类型为"
													+ orderService.getMemberChepaiTypeName(bustype)
													+ "牌车，请回复您的车牌号码（英文字母+数字），如车牌号为新AC4102则回复c#AC4102。", "mo", linkid,
													productId);

								} else if (b == 1) { // 车牌
									// 您已成功开通汽车保姆体验业务，您的车牌是蓝牌车新AC4102，感谢您的使用，变更车牌请回复0，询：09915881229

									s = orderService.getMemberChepaiTypeName(uo.getChepaileixing());
									if (!s.equals("未知"))
										Sms.sendSms(sender, "您已成功开通汽车保姆体验业务，您的车牌是"
												+ orderService.getMemberChepaiTypeName(uo.getChepaileixing()) + "牌车"
//												+ uo.getChepai()
												+busno
												+ "，感谢您的使用，假设您的车牌是新AC4102，变更车牌请回复c#AC4102，询：09915881229", "mo", linkid,
												productId);
									else//您已成功开通汽车保姆体验业务，您的车牌是蓝牌车null，感谢您的使用，如需变更车牌请回复c#车牌号码，如C#AA4062，询：09915881229
										Sms.sendSms(sender, "您已成功开通汽车保姆体验业务，您的车牌是" 
//												+ uo.getChepai()
												+busno
												+ "，感谢您的使用，如需变更车牌请回复c#车牌号码，如C#AA4062，询：09915881229", "mo", linkid,
												productId);
									if (busno != null && !busno.equals("")) {
										if (uo.getChepai() != null) {
											int cnt = orderService.getWZCount(uo.getChepai());
											String smscontent = "";
											if (cnt == 0)
												smscontent = "您没有违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
											else
												smscontent = "您有" + cnt + "条违章信息（违章内容仅供参考，详情请前往就近交警大队查询）";
											Sms.sendSms(sender, smscontent, "mo", "",productId);
										}
									}
								} 
//								else if (a == 1) {
//									Sms.sendSms(sender, OrderConstant.MO_HANDLE_OK + "(" + msg + ")", "mo", linkid,
//											productId);
//								}

							}
						}
					} else {
						LOG.warn("上行命令字不正确：：" + content);
						Sms.sendSms(sender, OrderConstant.MO_ERROR, "mo", productId, linkid);
					}
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
		
		MessageContext context = MessageContext.getCurrentContext();
		SOAPEnvelope requestEnvelope = context.getRequestMessage().getSOAPEnvelope();
		SOAPHeaderElement requestSequenceIdHeader = requestEnvelope.getHeaderByName(
				"http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "NotifySOAPHeader");
		LOG.debug("SOAPHeaderElement==" + requestSequenceIdHeader);
		if (requestSequenceIdHeader != null) {
			Iterator iterator = requestSequenceIdHeader.getChildElements();
			while (iterator.hasNext()) {
				SOAPElement element = (SOAPElement) iterator.next();
				String elementName = element.getElementName().getLocalName();

				LOG.info("状态报告头部信息：" + elementName + "==>" + element.getValue());
			}
		}
		
		if (deliveryStatus != null)
			LOG.debug(df.format(new Date()) + "=>状态报告=>" + correlator + "=>" + deliveryStatus.getAddress() + "=>"
					+ deliveryStatus.getDeliveryStatus());
		else
			LOG.debug(df.format(new Date()) + "=>notifySmsDeliveryReceipt=>" + correlator + "=>"
					+ DeliveryInformation.getTypeDesc() + "=>deliveryStatus为null");

	}

}
