package com.changpeng.diaocha.action;

import com.changpeng.models.*;
import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.diaocha.service.DiaochareplyService;
import com.opensymphony.xwork2.ActionContext;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class DiaochaReplyAction extends AbstractAction {
	private String[] replys;
	private int[] wentiid;
	private int diaochaid;
	private String[] other;
	private String username;
	private String lawyerno;

	// private List<Diaochawenti> wentilist;

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public void setOther(String[] other) {
		this.other = other;
	}

	public void setDiaochaid(int diaochaid) {
		this.diaochaid = diaochaid;
	}

	public void setReplys(String[] replys) {
		this.replys = replys;
	}

	public void setWentiid(int[] wentiid) {
		this.wentiid = wentiid;
	}

	public DiaochaReplyAction() {
		// this.rightCode = "diaochareply"; this.needsession=false;
		this.needsession = false;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	protected String go() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);

		String ip = getIpAddr(request);

		String cookievalue = com.changpeng.common.util.MD5.md5(diaochaid + "");// 参与完毕后，写个cookie信息进去
		String reqcookie = "";
		String cookiename = "hasreplied" + diaochaid;
		Cookie[] cookies = request.getCookies();
		int length = cookies == null ? 0 : cookies.length;
		for (int i = 0; i < length; i++) {
			if (cookies[i].getName().equals(cookiename)) {
				reqcookie = cookies[i].getValue();
			}
		}

		BasicService service = (BasicService) this.getBean("basicService");
		// List list = service
		// .find("from Diaochareply where diaochawenti.diaocha.diaochaid="
		// + diaochaid + " and replyuser='"
		// + getLoginUser().getLoginname() + "'");

		
		System.out.println("数据信息："+cookiename+","+cookievalue+","+reqcookie+","+ip);
		List list = null;
		if (!reqcookie.equals("")) {
			list = service.find("from Diaochareply where diaochawenti.diaocha.diaochaid=" + diaochaid + " and ip='"
					+ ip + "' and cookie='" + reqcookie + "'");
		}
		if (list != null && list.size() > 0) {
			this.message = "您已经参与过该调查";
			return "message";
		}

		// 修改为事务处理
		DiaochareplyService replyService = (DiaochareplyService) this.getBean("diaochareplyService");
		
		String batchid=System.currentTimeMillis()+"";
		int count = replyService.saveReply(wentiid, replys, other, getLoginUser(), ip, cookievalue, username,lawyerno,diaochaid,batchid);

		this.nextPage = "diaochaStat.pl?diaochaid=" + diaochaid;
		if (count > 0) {
			Diaocha diaocha = (Diaocha) service.get(Diaocha.class, diaochaid);
			diaocha.setReplycount(diaocha.getReplycount() + 1);
			service.update(diaocha);
			this.message = "调查提交成功，感谢合作";

			Cookie _cookie = new Cookie(cookiename, cookievalue);
			_cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(_cookie);

			return SUCCESS;
		} else {

			this.message = "您此次调查未提交任何选项";
			return "message";
		}

	}

}
