/**
 * 
 */
package com.sxit.service.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfInfo;
import com.sxit.models.service.CoreProduct;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class ProductDeleteAction extends AbstractListAction {

	private int productid;

	

	protected String go() throws Exception {

		CoreProduct info = (CoreProduct) basicService.get(CoreProduct.class, productid);
		
		basicService.delete(info);
		this.nextPage = "productList.action";
		this.message = "此产品信息删除成功,请返回";
		com.sxit.service.util.CommonDatas.getAllProducts();
		return SUCCESS;
	}



	public int getProductid() {
		return productid;
	}



	public void setProductid(int productid) {
		this.productid = productid;
	}
}
