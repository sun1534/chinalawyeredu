package com.sxit.common.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sxit.common.BasicService;
import com.sxit.common.Constants;
import com.sxit.common.Globals;
import com.sxit.common.exception.ServiceException;
import com.sxit.log.service.SysLogService;
import com.sxit.models.system.SysRight;
import com.sxit.models.system.SysUser;
import com.sxit.system.util.RightTree;

/**
 * <pre>
 * 系统的基类 1、权限判断和是否登录在拦截器中进行处理
 *  2、此处对session的信息进行处理,不再在子类中进行ActionContext的调用 
 *  3、统一记录操作日志信息 
 *  4、统一获得对request,response以及session信息的访问
 * </pre>
 * 
 * @author 华锋 2009-1-5 下午04:54:59 Tompan 2009-3-13修改去掉异常的日志记录
 * 
 */
public abstract class AbstractAction extends ActionSupport  {
	private static Log _LOG = LogFactory.getLog(AbstractAction.class);
	/**
	 * 当前action对应的rightCode
	 */

	protected String rightCode;
	/**
	 * action处理完毕,跳转到信息页面后,继续返回的页面
	 */
	protected String nextPage = "javascript:history.go(-1)";
	/**
	 * 错误或者信息显示页面上,显示的消息
	 */
	protected String message;
	/**
	 * 是否需要登录,主要是没有rightcode的情况下，要做下这个判断
	 * 
	 * 如果用actionname来做rightcode的话,就不存在这个问题了
	 * 
	 */
	protected boolean needsession = true;

	protected String opResult = "";

	protected BasicService basicService;

	public AbstractAction() {
		basicService = (BasicService) Globals.getBean("basicService");
	}

	/**
	 * 主要是记录操作日志信息
	 */
	@Override
	public String execute() throws Exception {

		// response.addHeader("pragma","NO-cache");
		// response.addHeader("Cache-Control","no-cache");
		// response.addDateHeader("Expries",0);
		SysRight right = RightTree.rightMap.get(this.rightCode);
		try {
			String result = go();
			return result;
		} catch (Exception e) {
			// _LOG.error(e);
			e.printStackTrace();
			// this.opResult += e.getMessage();
			throw e;
		} finally {
			if (right != null && right.getLogflag()) {// 记录日志，如果这个模块需要记录日志的话
				// _LOG.debug("===RightCode:"+rightCode+",LOGFLAG="+right.getLogflag());
				this.logOperation();
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract String go() throws Exception;

	/**
	 * 将信息存储到会话信息中
	 * 
	 * @param key
	 * @param value
	 */
	protected void set(Object key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * 从会话信息中获取信息
	 * 
	 * @param key
	 * @return
	 */
	protected Object get(Object key) {
		return ActionContext.getContext().getSession().get(key);
	}

	/**
	 * 从会话中去除某个数据
	 * 
	 * @param key
	 */
	protected void remove(Object key) {
		ActionContext.getContext().getSession().remove(key);
	}

	/**
	 * 页面到了提示页面后，点返回后跳转的页面
	 * 
	 * @return
	 */
	public String getNextPage() {
		_LOG.info("下一页面:::::" + this.nextPage);
		return this.nextPage;
	}

	public void setRightCode(String rightCode) {
		// return this.rightCode;
		this.rightCode = rightCode;
	}

	public String getMessage() {
		return this.message;

	}

	/**
	 * 根据在spring配置文件中的bean的id,得到初始化了的bean对象
	 * 
	 * @param name
	 * @return
	 */
	protected Object getBean(String name) {
		// Globals globals = new Globals();
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
		// return globals.getBean(name);
	}

	protected SysUser getLoginUser() {
		SysUser sysUser = (SysUser) get(Constants.LOGIN_USER);
		return sysUser;
	}

	/**
	 * 导航条菜单列表
	 */
	// private List navigator;
	// private String navigator;
	public String getNavigator() {
		StringBuilder sb = new StringBuilder();
		try {
			// _LOG.debug("this.rigthCode===" + this.rightCode);
			List<SysRight> navigatorList = RightTree.getParentRights(this.rightCode);
			// _LOG.debug(navigatorList);
			int size = navigatorList.size();
			for (int i = 0; i < size; i++) {
				SysRight right = navigatorList.get(i);
				// if (right.getLinkurl() != null)
				// sb.append("<a
				// href=\"").append(right.getLinkurl()).append("\">").append(right.getRightname()).append("</a>");
				// else
				// sb.append("<a
				// href=\"#\">").append(right.getRightname()).append("</a>");
				sb.append("<a href='").append(right.getLinkurl() == null || "".equals(right.getLinkurl()) ? "#" : right.getLinkurl()).append("'>")
						.append(right.getRightname()).append("</a>");
				if (i != (size - 1))
					sb.append("&gt;");
			}
			// _LOG.info("导航条1111:" + sb);

		} catch (Exception e) {
			_LOG.error("导航条失败:" + e);
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 量大的情况下，考虑队列的方式来实现
	 * 
	 * @throws ServiceException
	 */
	private void logOperation() throws ServiceException {
		if (opResult != null && !opResult.equals("")) {
			SysUser user = getLoginUser();
			SysLogService log = (SysLogService) getBean("sysLogService");
			log.insertLog(user.getLoginId(), opResult, this.rightCode, user.getUserid(),user.getUsername());
		}
	}

	protected void debug(String msg) {
		if (_LOG.isDebugEnabled())
			_LOG.debug(msg);
	}

	/**
	 * 
	 * 
	 * @return the needsession
	 */
	public boolean isNeedsession() {
		return needsession;
	}

	public String getRightCode() {
		return rightCode;
	}

	public String getSysName() {
		return com.sxit.common.Constants.SYS_NAME;
	}

	public String getResourcepath(){
		return com.sxit.common.Constants.RESOURCE_PATH;
	}
	public String getResourcepathmanage(){
		return com.sxit.common.Constants.RESOURCE_PATH_MANAGE;
	}
	static {
		com.sxit.system.util.CommonDatas.getUsers();
		com.sxit.system.util.CommonDatas.getGroups();
	}
	
	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevDate() {
		return getPrevCountDate(1);
	}
	/*
	 * 得到当前日期的前一天
	 */
	protected Date getPrevCountDate(int days) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 0-days);
		Date d = new Date();
		d.setTime(c.getTimeInMillis());
		return d;
	}
}