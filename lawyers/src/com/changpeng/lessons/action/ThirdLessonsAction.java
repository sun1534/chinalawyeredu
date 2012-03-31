/**
 * ThirdLessonsAction.java
 */
package com.changpeng.lessons.action;

import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.util.Base64;

/**
 * @author 华锋
 * May 5, 201010:56:17 PM
 *
 */
public class ThirdLessonsAction extends AbstractAction {
	public String getTopbarpic(){
		return super.webpara.getTopbarpic();
	}
	/* (non-Javadoc)
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		// TODO Auto-generated method stub
		String token=this.getLoginUser().getLawyerno()+"|"+this.getLoginUser().getLawyername();
		this.url=url+=new String(Base64.encode(token.getBytes("gb2312")),"gb2312");
		
		return SUCCESS;
	}
	private String url="http://www.zfwlxt.com/NetClassRoom/HZLXInterface.aspx?stoken=";
	public String getUrl(){
		return this.url;
	}

}
