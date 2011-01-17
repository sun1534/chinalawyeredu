package com.changpeng.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.WebVisitInfo;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lawyers.service.LawyerOperLogService;
import com.changpeng.models.Lawyers;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <pre>
 * 系统的基类 1、权限判断和是否登录在拦截器中进行处理
 *  2、此处对session的信息进行处理,不再在子类中进行ActionContext的调用 
 *  3、统一记录操作日志信息 
 *  4、统一获得对request,response以及session信息的访问
 * </pre>
 * 
 * @author 华锋 2008-2-20 下午04:54:59
 * 
 */
public abstract class AbstractAction extends ActionSupport {
	
	protected BasicService basicService = null;
	
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

	protected boolean needsession = true;

	protected String opResult = "";

	/**
	 * 系统设定的模块编号
	 */
	protected int moduleId;
	protected String userIp="";
	public AbstractAction() {
		basicService=(BasicService)this.getBean("basicService");
	}

	@Override
	public String execute() throws Exception {
//		userIp = getIpAddr(ServletActionContext.getRequest());
//		getDomain(ServletActionContext.getRequest());
		try {
			String result = go();
			return result;
		} catch (Exception e) {
			_LOG.error(e);
			e.printStackTrace();
			this.opResult += e.getMessage();
			throw e;
		} finally {

		}
	}

	@Override
	public String input() throws Exception {
//		getDomain(ServletActionContext.getRequest());
		return INPUT;
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
		ActionContext.getContext().getSession().put(key.toString(), value);
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
	 * 页面到了提示页面后，点返回后跳转的页面
	 * 
	 * @return
	 */
	public String getNextPage() {
		_LOG.info("下一页面:::::" + this.nextPage);
		return this.nextPage;
	}

	public String getRightCode() {
		return this.rightCode;
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

		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		return wac.getBean(name);
	}

	protected Lawyers getLoginUser() {
		Lawyers sysUser = (Lawyers) get(Constants.LOGIN_USER);
		return sysUser;
	}

	/**
	 * 获取导航菜单列表
	 * 
	 * @return
	 */
	public String getNavigator() {
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	protected void logOperation() throws ServiceException {
		if (opResult != null && !opResult.equals("")) {
			Lawyers lawyer = getLoginUser();
			LawyerOperLogService log = (LawyerOperLogService) getBean("lawyerOperLogService");
			log.insertLog(lawyer,opResult, rightCode);
		}
	}

	protected void debug(String msg) {
		if (_LOG.isDebugEnabled())
			_LOG.debug(msg);
	}

	/**
	 * @return the needsession
	 */
	public boolean isNeedsession() {
		return needsession;
	}
	

//	private String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		return ip;
//	}
	
	protected String getIpAddr(){
//		this.userIp= getIpAddr(ServletActionContext.getRequest());
		return this.userIp;
	}
	
	public String getLogopath() {
//		return com.changpeng.common.Constants.LOGO_PATH;
		return webinfo.getLogopath();
	}

	public String getResourcePath() {
		return com.changpeng.common.Constants.RESOURCE_PATH;
//		return webinfo.get
	}
	
	public void setNow(String now){
		
	}
	public void setTime(String time){
		
	}
	
	protected WebVisitInfo webinfo;
	/**
	 * @return the webinfo
	 */
	public WebVisitInfo getWebinfo() {
		return webinfo;
	}

	/**
	 * @param webinfo the webinfo to set
	 */
	public void setWebinfo(WebVisitInfo webinfo) {
		this.webinfo = webinfo;
	}

	/**
	 * @return the moduleId
	 */
	public int getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the userIp
	 */
	public String getUserIp() {
		return userIp;
	}

	/**
	 * @param userIp the userIp to set
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
}