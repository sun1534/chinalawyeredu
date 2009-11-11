package com.changpeng.core.progress.action;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.action.AbstractListAction;
import com.changpeng.core.model.CorePublish;
import com.tenpay.PayResponseHandler;

/**
 * 商家ID:1205090201
 * 
 * @author 华锋 Sep 23, 2009 10:12:37 PM
 * 
 */
public class PayResultAction extends AbstractListAction {

	// 密钥
//	private static final String KEY = "8934e7d15453e97507ef794cf7b0519d";
	private static final String KEY = "631c3aaffdcebd88e7a19ac1e756c770";
	
	private int id;

	public PayResultAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		// 密钥
		String key = KEY;

		// 创建PayResponseHandler实例
		PayResponseHandler resHandler = new PayResponseHandler(ServletActionContext.getRequest(), ServletActionContext.getResponse());

		resHandler.setKey(key);

		// 判断签名
		if (resHandler.isTenpaySign()) {
			// 交易单号
			// String transaction_id =
			// resHandler.getParameter("transaction_id");

			// 金额金额,以分为单位
			// String total_fee = resHandler.getParameter("total_fee");

			// 支付结果
			String pay_result = resHandler.getParameter("pay_result");

			String id = resHandler.getParameter("attach");
			CorePublish publish = (CorePublish) service.get(CorePublish.class, Integer.parseInt(id));
			if ("0".equals(pay_result)) {
				// ------------------------------
				// 处理业务开始
				// ------------------------------
				publish.setFeetime(new java.sql.Timestamp(System.currentTimeMillis()));
				// 0 支付成功，1 未支付2正在支付3支付失败
				publish.setPaystatus((short) 0);
				// 审批状态 0 通过 可以发布到电视 1 初订购，等待完善内容 2 订购，内容完整，需要付费 3 付费完成，待审核状态 5
				// 审核未通过 99 正在发布电视中 100 发布完成，已经从电视上撤下来了
				publish.setStatusid((short) 3);

				// 注意交易单不要重复处理
				// 注意判断返回金额

				// ------------------------------
				// 处理业务完毕
				// ------------------------------

				// 调用doShow, 打印meta值跟js代码,告诉财付通处理成功,并在用户浏览器显示$show页面.
				// resHandler.doShow("http://211.154.157.174:8580/tenpay/show.jsp");

				showurl += "?result=0";
			} else {
				// 当做不成功处理
				// out.println("支付失败");
				publish.setPaystatus((short) 3);
				publish.setFeetime(new java.sql.Timestamp(System.currentTimeMillis()));

				showurl += "?result=1";
			}
			service.update(publish);

		} else {
			// out.println("认证签名失败");
			// String debugInfo = resHandler.getDebugInfo();
			// System.out.println("debugInfo:" + debugInfo);
			showurl += "?result=2";
		}

		return SUCCESS;
	}

	private String showurl = "http://211.148.192.252/progress/showpayresult.action";

	public String getShowurl() {
		return this.showurl;
	}
}
