package com.changpeng.core.progress.action;

import java.sql.Timestamp;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.core.model.CoreProduct;
import com.changpeng.core.model.CorePublish;
import com.changpeng.core.user.model.CoreUser;

public class OrderProductAction extends AbstractAction {

	private int productid;

	public OrderProductAction() {
		this.rightCode = "PUBLIC";
	}

	@Override
	protected String go() throws Exception {
		// String result=SUCCESS;
		if (this.currentUserid == 0) {
			this.message = "请登录后进行订购！";
			this.redirectURL = "../home/home.action";
		} else {
			CoreProduct product = (CoreProduct) service.get(CoreProduct.class, productid);
			CoreUser user = (CoreUser) service.get(CoreUser.class, this.currentUserid);
			if (user.getStatus() == 1 || user.getStatus() == 3) {
				this.message = "请先进行身份审核再订购产品！";
				// this.redirectURL="/user/userverify.action";
				this.redirectURL = "javascript:applyverify()";
			} else if (user.getStatus() == 2) {
				this.message = "您的身份正在审核，请等待审核成功后订购！";
				this.redirectURL = "javascript:$.unblockUI()";
			} else if (user.getStatus() == 0) {

				// channelid
				// feetime
				// orderno
				// waitid
				// 初始订购
				CorePublish publish = new CorePublish();
				publish.setChannelid(0);
				publish.setProductid(productid);
				publish.setUserid(currentUserid);
				publish.setCreatetime(new Timestamp(System.currentTimeMillis()));
				publish.setFee(product.getPrice() * product.getFeerate() / 100);
				publish.setPaystatus((short) 1);
				publish.setStatusid((short) 1);
				publish.setRemarks("");
				publish.setStarttime(new Timestamp(System.currentTimeMillis()));
				publish.setEndtime(new Timestamp(System.currentTimeMillis()));
				publish.setUserRole((short) this.currentRole);
				if (this.currentRole == 2) {
					if (publish.getFee() == 0.0) {
						publish.setStatusid((short) 3);
						publish.setPaystatus((short) 0);
					} else {
						publish.setStatusid((short) 2);
					}
				}
				service.save(publish);
				if (this.currentRole == 2) {
					this.message = "订购成功，请您进行支付！";
					this.redirectURL = "/progress/publishview.action?publishid=" + publish.getId();
				} else {
					this.message = "订购成功，现在可以选取您要发布的内容！";
					this.redirectURL = "/progress/publishview.action?publishid=" + publish.getId();
				}
			}
		}

		return SUCCESS;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

}
