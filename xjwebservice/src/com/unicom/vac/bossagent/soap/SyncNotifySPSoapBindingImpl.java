/**
 * SyncNotifySPSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.unicom.vac.bossagent.soap;

import java.text.DateFormat;

import org.apache.commons.logging.Log;

import service.OrderConstant;
import service.OrderService;

import com.unicom.vac.bossagent.soap.sync.req.OrderRelationUpdateNotifyRequest;
import com.unicom.vac.bossagent.soap.sync.rsp.OrderRelationUpdateNotifyResponse;
import common.Globals;

import entity.LogUnicomOrder;

public class SyncNotifySPSoapBindingImpl implements com.unicom.vac.bossagent.soap.SyncNotifySPService {

	private static final Log LOG = org.apache.commons.logging.LogFactory.getLog(SyncNotifySPSoapBindingImpl.class);
	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static volatile int SEQ = 1;

	public OrderRelationUpdateNotifyResponse orderRelationUpdateNotify(
			OrderRelationUpdateNotifyRequest req) throws java.rmi.RemoteException {
		LOG.debug("联通订购数据解析开始::::::::" + (SEQ++));
		
		LOG.debug(req.getRecordSequenceId());
		LOG.debug(req.getUserIdType()); // 用户ID类型1:
																		// MSISDN2:
																		// PsedoCode
		LOG.debug(req.getUserId()); // 用户手机号码或伪码UserIdType填1
																	// 为手机号码UserIdType填2
																	// 为伪码
		LOG.debug(req.getServiceType());
		LOG.debug(req.getSpId());//
		LOG.debug(req.getProductId()); //产品标识（此为SP在PRM侧申请的SP_Productid）

		LOG.debug(req.getUpdateType());

		LOG.debug(req.getUpdateTime());//更新时间
		//更新操作的详细描述
//		联通在信、彩信等定购、点播接入号，和CheckPrice请求中AccessNo字段一致；
//		对crm侧订购、退订由vac根据产品填写；对其他业务填空
		LOG.debug(req.getUpdateDesc());
		//事务关联ID，用于点播业务的临时定购关系关联，由平台产生。格式如下：
//		MMDDHHMMSS+10位随机序列号；
//		为空表示无效。
//		用户点播时使用。
		LOG.debug(req.getLinkId());//
		LOG.debug(req.getContent());//内容，当UpdateType=5时，本字段填原用户手机号码或伪码，具体填写方式由UserIdType字段决定
		LOG.debug(req.getEffectiveDate());//订购关系生效时间, 格式：yyyyMMddhhmmss
		LOG.debug(req.getExpireDate());//订购关系失效时间, 格式：yyyyMMddhhmmss
		LOG.debug(req.getTime_stamp());//时间戳由VAC生成,格式是: MMDDHHMMSS，月日时分秒。
		//采用32位的MD5加密串,以便SP鉴权定购关系来源的合法性, MD5 加密算法如下:
//		EncodeStr=UserId+共享密钥+ ProductId+ Time_Stamp
//		共享密钥由SP分配,时间戳由VAC设备生成，格式是：MMDDHHMMSS，月日时分秒。共享密钥的定义见BSS与VAC接口规范SP属性接口OrderKey
//		如果SP的OrderKey为空,VAC不加密
		LOG.debug(req.getEncodeStr());//
		//先不管，新增先
		LogUnicomOrder luo=new LogUnicomOrder();
		luo.setContent(req.getContent());
		luo.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		luo.setEffectiveDate(req.getEffectiveDate());
		luo.setEncodeStr(req.getEncodeStr());
		luo.setExpireDate(req.getExpireDate());
		luo.setLinkId(req.getLinkId());
		luo.setProductId(req.getProductId());
		luo.setRecordseq(req.getRecordSequenceId());
		luo.setServiceType(req.getServiceType());
		luo.setSpId(req.getSpId());
		luo.setTimeStamp(req.getTime_stamp());
		luo.setUpdateDesc(luo.getUpdateDesc());
		luo.setUpdateTime(req.getUpdateTime());
		luo.setUpdateType(req.getUpdateType());
		luo.setUserId(req.getUserId());
		luo.setUserIdType(req.getUserIdType());
		
		OrderService orderService = (OrderService) Globals.getWebBean("orderService");
		//先新增
		orderService.save(luo);
		//更新操作的类型包括：
		//1：订购
		//2：退定
		//3：点播
		//4：定购关系变更(一般是修改有效期)（保留，暂不用）
		//5：改号
		int updatetype=req.getUpdateType();
		if(updatetype==1){
			
			String msg="请回复您的车牌号类型，蓝牌车 请回复1，黄牌车 请回复2，黑牌车 请回复3，返回0。询0991-5881229";
			
			
		}else if(updatetype==2){
			String msg="您已成功取消新疆宽洋汽车保姆业务，感谢您的使用！详询0991-5881229";
			
		}else if(updatetype==3){
			String content=req.getContent();
			String first = "";
			String bustype="";
			String busno="";
			String areaCode="";
			int a = 0, b = 0, c = 0, d = 0;
			if (content.length() >= 1)
				first = content.substring(0, 1);
			if (OrderConstant.MEMBER_CHEPAI.containsKey(first)
					|| OrderConstant.MEMBER_CHEPAI.containsKey(content)
					|| OrderConstant.MEMBER_CHEPAI.values().contains(content)) {// 只有正确的车牌类型才行
				if (OrderConstant.MEMBER_CHEPAI.values().contains(first))
					bustype = first;
				else
					bustype = content;
				d = 1;
			} else if (OrderConstant.CHEPAI_FIRST.contains(first)&&content.length()<8&&content.length()>5) { // 这样来判断，如果你实在要输错了，那就没法子了,content的内容必须小于8
				busno = content;
				c = 1;
			} else if (OrderConstant.AREA_DATABASE.containsKey(content)) { // 只有正确的区域才行
				areaCode = content;
				b = 1;
			} 
			
			
			
			
			
			
			String msg="请回复您的车牌号码（英文字母+数字），如车牌号为新AC4102则回复AC4102．0返回。询0991-5881229。";
			
			msg="您已成功添加新AAA607车牌，自开通之日起该车牌乌鲁木齐地区的违章信息将及时发送您手机短信中，请您留意关注。";
			
		}else if(updatetype==4){
			LOG.warn("订购关系变更请求，暂不处理");
			
		}else if(updatetype==5){
			LOG.warn("改号请求，暂不处理");
			
		}else{
			LOG.warn("永远不会发生的updatetype,但是确实有了，值为:"+updatetype);
		}
		
		
		OrderRelationUpdateNotifyResponse resp=new OrderRelationUpdateNotifyResponse();
		resp.setRecordSequenceId(req.getRecordSequenceId());
		resp.setResultCode(0);
//		结果识别码
//		0: 成功
//		1. 失败
//		当vac通知sp订购关系，但SP发现已经存在该订购关系时，SP返回VAC成功?
		
		
		
		
		
		return resp;
	}

}
