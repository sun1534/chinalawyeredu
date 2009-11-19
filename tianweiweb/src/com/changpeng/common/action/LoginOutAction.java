/**
 * 
 */
package com.changpeng.common.action;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.changpeng.common.sysdata.CommonData;
import com.changpeng.core.service.UserService;
import com.opensymphony.xwork2.ActionContext;





/**
 * 
 * @author 肖云亮
 *
 */
public class LoginOutAction extends AbstractAction {


	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws Exception {
		UserService service = (UserService) this.getBean("userService");
		this.remove("USERSESSION");
		
		
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);

		javax.servlet.http.Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		for (int i = 0; cookies != null && i < cookies.length; i++) {
			System.out.println("cookies[i].getName():::"+cookies[i].getName());
			if (cookies[i].getName().equals(CommonData.LOGINCOOKIE)) {
				cookie = cookies[i];
			}
		}
		if (cookie != null){
	 	cookie.setMaxAge(0);
	  	cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("删除COOKIE......");
		}
		return SUCCESS;

	}

}
