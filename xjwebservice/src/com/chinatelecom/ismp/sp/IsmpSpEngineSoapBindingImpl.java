/**
 * IsmpSpEngineSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.chinatelecom.ismp.sp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPElement;

import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.commons.logging.Log;

import service.OrderConstant;
import service.OrderService;
import sms.Sms;

import com.chinatelecom.ismp.sp.req.NotifyManagementInfoReq;
import com.chinatelecom.ismp.sp.req.ServiceConsumeNotifyReq;
import com.chinatelecom.ismp.sp.rsp.NotifyManagementInfoRsp;
import com.chinatelecom.ismp.sp.rsp.Response;
import common.Globals;

/**
 * 业务的订购和退订
 * 
 * @author 刘哈哈 Mar 30, 20115:02:45 PM
 * 
 */
public class IsmpSpEngineSoapBindingImpl implements com.chinatelecom.ismp.sp.IsmpSpEngine {
	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(IsmpSpEngineSoapBindingImpl.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param req
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public com.chinatelecom.ismp.sp.rsp.Response orderRelationUpdateNotify(
			com.chinatelecom.ismp.sp.req.OrderRelationUpdateNotifyReq req) throws java.rmi.RemoteException {

		String linkid = "";

		MessageContext context = MessageContext.getCurrentContext();
		SOAPEnvelope requestEnvelope = context.getRequestMessage().getSOAPEnvelope();
		SOAPHeaderElement requestSequenceIdHeader = requestEnvelope.getHeaderByName(
				"http://www.chinatelecom.com.cn/schema/ctcc/common/v2_1", "OrderRelationSOAPHeader");
		if (requestSequenceIdHeader != null) {
			Iterator iterator = requestSequenceIdHeader.getChildElements();

			while (iterator.hasNext()) {
				SOAPElement element = (SOAPElement) iterator.next();
				String elementName = element.getElementName().getLocalName();

				System.out.println("订购头部信息：" + elementName + "==>" + element.getValue());

				if (elementName.equals("linkId"))
					linkid = element.getValue();
				// soapHeader.setServiceCode(element.getValue());
				// else if (elementName.equals("servicePwd"))
				// soapHeader.setServicePwd(element.getValue());
			}
			// soapHeader.setIp(this.getIP());
		}
		LOG.debug(df.format(new Date()) + "=>订购关系=>" + req.getOPType() + "=>" + req.getPackageID() + "=>"
				+ req.getProductID() + "=>" + req.getStreamingNo() + "=>" + req.getUserID() + "=>"
				+ req.getUserIDType());

		// 0：定购
		// 1：暂停
		// 2：暂停恢复
		// 3：退定
		// 4：退定该SP的所有产品和套餐
		// 5：暂停该SP的所有产品和套餐
		int optype = req.getOPType();
		String packageId = req.getPackageID();
		String productId = req.getProductID();
		String streamNo = req.getStreamingNo();
		String userId = req.getUserID();
		int userIdType = req.getUserIDType();
		Response resp = new Response();

		try {

			OrderService orderService = (OrderService) Globals.getWebBean("orderService");

			int logid = orderService.logUserOrder(userId, packageId, productId, streamNo, "", optype, userIdType, "");
			LOG.debug("订购日志处理ID:" + logid);
			int result = -2;
			if (optype == 0) {

				// 下发短信，提示发送上行短信. 车牌号#车牌类型#地市电话区号
				result = orderService.order(userId, packageId, productId, streamNo, "", userIdType);
				if (result >= 0) {
					// LOG.warn(userId+"您的订购信息已受理成功,请按照如下的格式上传您的车牌号、车牌类型和车牌所在地市区号："
					// + OrderConstant.ORDER_MO_CONTENT);
					String sms = "";
					if (productId.equals(OrderConstant.TY_DZJC_PRODUCTID))
						sms = "您已成功定制新疆宽洋汽车保姆体验业务，本业务信息费0元/月，请回复您所在地区号，如乌鲁木齐请回复B#0991。询09915881229";
					else
						sms = "您已成功定制新疆宽洋汽车保姆业务，本业务信息费10元/月，请回复您所在地区号，如乌鲁木齐请回复B#0991。询09915881229";

					// Sms.sendSms(userId,
					// "您的订购信息已受理成功,请按照如下的格式上传您的车牌号、车牌类型和车牌所在地市区号：" +
					// OrderConstant.ORDER_MO_CONTENT,"order");
					Sms.sendSms(userId, sms, "order", linkid, productId);

				} else {
					LOG.warn("订购业务处理失败,不下发短信（即使下发也不能成功,因为没有订购关系）");
					// Sms.sendSms(userId,"");
				}
			} else if (optype == 1) {
				result = orderService.pause(userId, productId);
				LOG.info("业务暂停:" + result);
				// Sms.sendSms(userId, OrderConstant.ORDER_PAUSE_CONTENT);//
				// 下发暂停短信
			} else if (optype == 2) {
				result = orderService.resume(userId, productId);
				LOG.info("业务恢复:" + result);
				// Sms.sendSms(userId, OrderConstant.ORDER_CANCEL_CONTENT); //
				// 下发退订短信
			} else if (optype == 3) {
				result = orderService.cancel(userId, productId);
				LOG.info("业务退订:" + result);
				// Sms.sendSms(userId, OrderConstant.ORDER_CANCEL_CONTENT); //
				// 下发退订短信
			} else if (optype == 4) {
				LOG.warn("其他类型忽略:" + optype);
			} else if (optype == 5) {
				LOG.warn("其他类型忽略:" + optype);
			} else {
				LOG.warn("其他类型忽略:" + optype);
			}

			orderService.updateLogOrderResult(logid, result + "");

			if (result >= 0)
				resp.setResultCode(0);
			else
				resp.setResultCode(result);
			resp.setStreamingNo(req.getStreamingNo());

			LOG.info("返回消息OK");
		} catch (Exception e) {

			resp.setResultCode(-100);
			resp.setStreamingNo(req.getStreamingNo());
			LOG.error("orderRelationUpdateNotify", e);
		}
		return resp;
	}

	/**
	 * 
	 * @param req
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public com.chinatelecom.ismp.sp.rsp.Response serviceConsumeNotify(
			com.chinatelecom.ismp.sp.req.ServiceConsumeNotifyReq req) throws java.rmi.RemoteException {

		LOG.debug(df.format(new Date()) + "=>serviceConsumeNotify=>" + req.getLinkID() + "=>" + req.getProductID()
				+ "=>" + req.getStreamingNo() + "=>" + req.getUserID() + "=>" + req.getUserIDType() + "=>"
				+ req.getFeatureStr() + "=>" + ServiceConsumeNotifyReq.getTypeDesc());

		Response resp = new Response();
		resp.setResultCode(0);
		resp.setStreamingNo(req.getStreamingNo());

		return resp;
	}

	/**
	 * 
	 * @param req
	 * @return
	 * @throws java.rmi.RemoteException
	 */
	public com.chinatelecom.ismp.sp.rsp.NotifyManagementInfoRsp notifyManagementInfo(
			com.chinatelecom.ismp.sp.req.NotifyManagementInfoReq req) throws java.rmi.RemoteException {

		LOG.debug(df.format(new Date()) + "=>notifyManagementInfo=>" + req.getStatus() + "=>" + req.getStreamingNo()
				+ "=>" + req.getID() + "=>" + req.getIDType() + "=>" + NotifyManagementInfoReq.getTypeDesc());

		int status = req.getStatus();
		// 0：正常
		// 1：申请
		// 2：暂停（IDType为2、3时不可以填本项枚举值）
		// 3：预注销
		// 4：注销

		if (status == 0) {

		} else if (status == 1) {

		} else if (status == 2) {

		} else if (status == 3) {

		} else if (status == 4) {

		}

		NotifyManagementInfoRsp resp = new NotifyManagementInfoRsp();
		resp.setResultCode(0);
		resp.setStreamingNo(req.getStreamingNo());

		return resp;
	}

	/**
	 * 
	 * @return
	 */
	private String getClientInfo() {
		MessageContext mc = null;
		HttpServletRequest request = null;
		try {
			mc = MessageContext.getCurrentContext();
			if (mc == null)
				throw new Exception("无法获取到MessageContext");
			request = (HttpServletRequest) mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
			String ip = request.getRemoteAddr();
			LOG.debug("ip:" + ip);
			return request.getRemoteAddr();
		} catch (Exception e) {
			LOG.error("获取IP失败", e);
			return "127.0.0.0";
		}

	}
}
