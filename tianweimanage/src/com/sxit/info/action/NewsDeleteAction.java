/**
 * 
 */
package com.sxit.info.action;

import com.sxit.common.action.AbstractListAction;
import com.sxit.models.info.TinfInfo;

/**
 * @author 华锋 Jul 8, 2009 11:27:42 PM
 * 
 */
public class NewsDeleteAction extends AbstractListAction {

	private int infoid;

	public int getInfoid() {
		return infoid;
	}

	public void setInfoid(int infoid) {
		this.infoid = infoid;
	}

	protected String go() throws Exception {

		TinfInfo info = (TinfInfo) basicService.get(TinfInfo.class, infoid);
		int thetype = info.getTypeid();
		basicService.delete(info);
		this.nextPage = "newsList.action?thetype=" + thetype;
		this.message = "此信息删除成功,请返回";

		return SUCCESS;
	}
}
