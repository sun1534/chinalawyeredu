/**
 * IndexAction.java
 */
package com.changpeng.common.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.context.Globals;
import com.changpeng.models.SysUnionparams;
import com.opensymphony.xwork2.ActionContext;


/**
 * @author 华锋
 * 2008-3-4 下午10:31:14
 *
 */
public class IndexAction extends AbstractAction {
	private String loginname;
	private String password;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.changpeng.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {

		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = request.getCookies();
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
				this.loginname = cookies[i].getValue();
			}
			if (cookies[i].getName().equals(Constants.COOKIE_LOGINNAME)) {
				this.password = cookies[i].getValue();
			}
		}
		
		
		
		System.out.println("getRemoteAddr::"+request.getRemoteAddr());
		System.out.println("getServerName::"+request.getServerName());
		String ServerName=request.getServerName();
		
		BasicService baseservice = (BasicService) Globals.getBean("basicService");
		List list = baseservice.findAll(SysUnionparams.class);
		for (int i = 0; i < list.size(); i++) {
			SysUnionparams area = (SysUnionparams) list.get(i);
			if (area.getDomain() != null &&! area.getDomain().equals("")) {
				System.out.println(area.getDomain());
				if(area.getDomain()==ServerName || area.getDomain().equals(ServerName)){
					//toppic=area.getTopbarpic();

					
//					Constants.TOP_BAR_PIC=area.getTopbarpic();
//					Constants.DEFAULT_TOP_BAR_PIC=Constants.TOP_BAR_PIC;
//					Constants.SYS_NAME=area.getSysname();
//					Constants.DEFAULT_SYS_NAME=Constants.SYS_NAME;
//					Constants.INDEX_PIC=area.getIndexpic();		
//					Constants.INDEX_PIC=Constants.INDEX_PIC;
//					System.out.println("第一次加载："+Constants.SYS_NAME);
//					System.out.println("第一次加载："+Constants.TOP_BAR_PIC);
//					System.out.println("第一次加载："+Constants.INDEX_PIC);
				}
			}
		}

		
		return SUCCESS;
	}

	public String getPassword() {
		return this.password;
	}

	public String getLoginname() {
		return this.loginname;
	}
	
	public String getIndexpic() {
		return  webpara.getIndexpic();
	}
}