package sms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.SOAPElement;

import main.SendConstant;

import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;

import service.OrderConstant;

/**
 * 提供ReqSOAPHeader信息组装方法
 * 
 * @author YJ
 * 
 */
public class SetSOAPHeaderHelper {

	private static String SPID = SendConstant.SPID;
	private static String SPPWD = SendConstant.SPPWD;
	public static String PRODUCTID = OrderConstant.TY_DZJC_PRODUCTID;
	public static String LINKID = "";
	private static String QNAME = SendConstant.QNAME;
	private static DateFormat SDF = new SimpleDateFormat("MMddHHmmss");
	public static String OAFA = "";

	/**
	 * 设置请求头信息
	 * 
	 * @param _call
	 */
	public void setSoapHeader(org.apache.axis.client.Call _call) {

		String spId = SPID;
		String spPassword = MD5.md5(SPPWD);
		String timeStamp = System.currentTimeMillis() / 1000 + "";
		String productId = PRODUCTID;
		String transactionId = System.currentTimeMillis() / 1000 + "";
		String transEnd = "-1";
		String linkId = LINKID;
		// OA应该是主叫号码 ，FA是计费号码
		// String OA = "tel:1234567890";
		// String FA = "tel:1234567890";
		String OA = "tel:+86" + OAFA;
		String FA = "tel:+86" + OAFA;
		// 获取当前时间作为时间戳(MMddHHmmss)
		Date date = new Date();

		timeStamp = SDF.format(date);
		String multicastMessaging = "false";
		try {
			System.out.println("header:::" + productId + ",,,linkid:" + linkId);
			PrefixedQName qName = new PrefixedQName(QNAME, "RequestSOAPHeader", "");
			SOAPHeaderElement header = new SOAPHeaderElement(qName);
			SOAPElement _spId = header.addChildElement("spId");
			_spId.addTextNode(spId);
			SOAPElement _spPassword = header.addChildElement("spPassword");
			_spPassword.addTextNode(spPassword);
			SOAPElement _productId = header.addChildElement("productId");
			_productId.addTextNode(productId);
			SOAPElement _timeStamp = header.addChildElement("timeStamp");
			_timeStamp.addTextNode(timeStamp);
			// SOAPElement _SAN = header.addChildElement("SAN");2

			// _SAN.addTextNode(SAN);
			SOAPElement _transactionId = header.addChildElement("transactionId");
			transactionId = "";
			_transactionId.addTextNode(transactionId);
			SOAPElement _transEnd = header.addChildElement("transEnd");
			_transEnd.addTextNode(transEnd);
			SOAPElement _linkId = header.addChildElement("linkId");
			_linkId.addTextNode((linkId == null ? "" : linkId));
			SOAPElement _OA = header.addChildElement("OA");
			_OA.addTextNode(OA);
			SOAPElement _FA = header.addChildElement("FA");
			_FA.addTextNode(FA);
			SOAPElement _multicastMessaging = header.addChildElement("multicastMessaging");
			_multicastMessaging.addTextNode(multicastMessaging);
			_call.addHeader(header);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
