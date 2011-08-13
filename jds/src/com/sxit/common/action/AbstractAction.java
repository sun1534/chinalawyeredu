package com.sxit.common.action;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sxit.common.component.HibernateSession;
import com.sxit.system.model.TsysFunction;
import com.sxit.system.model.TsysUser;

/**
 * 
 * <p>
 * 功能： Action的基类
 * </p>
 * <p>
 * 作者： 张如兵
 * </p>
 * <p>
 * 公司： 深圳信科
 * </p>
 * <p>
 * 日期： 2007-10-25
 * </p>
 * 
 * @版本： V1.0
 * @修改：
 */

public abstract class AbstractAction extends ActionSupport {

	protected static final Logger LOG = Logger.getLogger(AbstractAction.class);
	
	private HibernateSession hs;
	
	protected String runtype = "tomcat";
	protected String usertype = "system";
	// zrb add 2007-8-23
	// 用于判断jsp页面跳转
	protected String nextpage = "";
	protected String backurl = "";
	protected String cururl = "";
	protected String message = "";
	// 用于判断action中的跳转
	protected String flag = ""; // flag为in的时候表述输入 flag为out表示输出 flag为save时表示保存

	protected String rights = null;
	protected int pagenumber = 0;
	protected TsysUser curuser;

	protected HashMap powers = null;

	@Override
	public String execute() throws Exception {
		
		LOG.info("---------action start------------------------------------");
		com.changpeng.lawcase.util.CommanDatas.getUserNames(getSession());
		com.changpeng.lawcase.util.CommanDatas.getAllActions(getSession());
		curuser = (TsysUser) get("curuser");
		
		if (curuser == null) {
			LOG.info("操作信息:未登录用户 访问了地址: " + cururl);
			if(this.cururl.toLowerCase().indexOf("/lawcase/")!=-1){
				return "login";
			}
		} else {
			if (!runtype.equals("testCase")) {
				LOG.info("操作信息:" + curuser.getUsername() + "[ip:" + getIP() + "] 访问了地址: " + cururl);
			}
		}

		LOG.debug("executing action");
		
		String result;
		try {
		  result=go();
		  hs.commitSession();
		} catch (JDBCException e) {
			message = "数据库异常！<br>";
			message += e.getCause().getMessage();		
			LOG.debug(e.getCause().getMessage());
			hs.setRollBackOnly(true);
			return ERROR;
		} catch (HibernateException e) {
			message = "操作失败！<br>";			
			e.printStackTrace();
			hs.setRollBackOnly(true);
			return ERROR;
		}

		return result;
	}

	protected abstract String go() throws JDBCException, HibernateException;

	public void setHibernateSession(HibernateSession hs) {
		this.hs = hs;
	}

	public HibernateSession getHibernateSession() {
		return hs;
	}

	protected void commit() throws JDBCException, HibernateException {
		//hs.sessionCommit();  此方法已经不用
	}

	/**
	 * Get the Hibernate Session instance
	 */
	protected Session getSession() throws HibernateException {
		return hs.getSession();	 
	}

	/**
	 * Get an object from the WebWork user session
	 */
	protected Object get(String name) {
		return ActionContext.getContext().getSession().get(name);
		// ActionContext.
	}

	/**
	 * Put an object in the WebWork user session
	 */
	protected void set(String name, Object value) {
		ActionContext.getContext().getSession().put(name, value);
	}

	// 页面跳转
	public String getMessage() {
		return message;
	}

	public String getNextpage() {
		if(backurl!=null&&!"".equals(backurl))
		{
			return backurl;
		}
		return nextpage;
	}

	public String getBackurl() {
		return backurl;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	// 用于判断action中的跳转
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return this.flag;
	}

	public int getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
	}

	// 根据权限返回不同的结果
	public String getPowerResult() {
		// zrb add 2007-8-23
		curuser = (TsysUser) get("curuser");
		cururl = getCururl();

		if (rights != null) {
			powers = (HashMap) get("powers");
			if (curuser == null) {
				nextpage = cururl;
				message = "您没有登录！";
				return "login";
			}

			// 如果是管理员则返回success
			if (curuser.isIsadmin()) {
				return "success";
			}
			if (powers == null) {
				nextpage = cururl;
				message = "您没有登录！";
				return "login";
			}
			if (!this.hasRights(rights, powers)) {
				nextpage = cururl;
				return "login";
			}
		}
		return "success";
	}

	/**
	 * 用于判断用户的权限是足够
	 * 
	 * @param rights
	 *            String
	 * @param powers
	 *            HashMap
	 * @return boolean
	 */
	public boolean hasRights(String rights, HashMap powers) {
		StringTokenizer st = new StringTokenizer(rights, ",");

		String str1 = st.nextToken();
		String str2 = st.nextToken();
		int right = Integer.parseInt(str2);

		if (powers.containsKey(str1)) {
			int power = ((Integer) powers.get(str1)).intValue();
			// 判断权限
			if (right == (power & right)) {
				return true;
			}
		}
		// 当right==0时 此action可以被别的模块调用 而不存在权限问题
		if (right == 0) {
			return true;
		}
		// 如果是管理员则返回ture
		if (curuser.isIsadmin()) {
			return true;
		}
		// 如果为公共模块则返回ture
		TsysFunction function = (TsysFunction) getSession().get(TsysFunction.class, str1);
		if (function != null) {
			if (function.isIscommon()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @todo 传递xml中不能使用的&符号
	 * @return String
	 */
	public String getCururl() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		String url1 = request.getRequestURL().toString();
		String url2 = request.getQueryString();
		return url1 + (url2 == null ? "" : "?" + url2);
	}

	public String getIP() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		return request.getRemoteHost();
		
	}

	public String getRuntype() {
		return runtype;
	}

	public void setRuntype(String runtype) {
		this.runtype = runtype;
	}
}
