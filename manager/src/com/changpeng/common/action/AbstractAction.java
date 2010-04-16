package com.changpeng.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.changpeng.common.BasicService;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.Constants;
import com.changpeng.common.DataVisible;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.SysRight;
import com.changpeng.models.SysUnionparams;
import com.changpeng.models.SysUser;
import com.changpeng.system.service.SysOperlogService;
import com.changpeng.system.util.RightTree;
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

	protected BasicService basicService;

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
	 * 客户访问ip地址,对于需要的action来说
	 */
	protected boolean needsession = true;

	protected String opResult = "";
	protected String userIp = "";

	public AbstractAction() {
		basicService = (BasicService) this.getBean("basicService");
	}

	@Override
	public String execute() throws Exception {
		
//		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
//
//		int length = cookies == null ? 0 : cookies.length;
//		for (int i = 0; i < length; i++) {
//			_LOG.debug(cookies[i].getName() + "=>" + cookies[i].getValue() + "=>" + cookies[i].getDomain() + "=>"
//					+ cookies[i].getPath());
//		}
		_LOG.debug("REFERER:"+ServletActionContext.getRequest().getHeader("REFERER"));
		userIp = getIpAddr(ServletActionContext.getRequest());
//		getDomain(ServletActionContext.getRequest());

		SysRight right = RightTree.rightMap.get(this.rightCode);
		try {
			String result = go();
			return result;
		} catch (Exception e) {
			_LOG.error(e);
			e.printStackTrace();
			this.opResult += e.getMessage();
			throw e;
		} finally {
//			if (right != null && right.getLogflag()) {// 记录日志，如果这个模块需要记录日志的话
//				_LOG.debug("===RightCode:" + rightCode + ",LOGFLAG=" + right.getLogflag());
				this.logOperation();
//			}
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
	private String navigator;

	public String getNavigator() {
		// StringBuilder sb = new StringBuilder();
		// try {
		// // _LOG.debug("this.rigthCode===" + this.rightCode);
		//
		// List<SysRight> navigatorList =
		// RightTree.getParentRights(this.rightCode);
		//
		// // _LOG.debug(navigatorList);
		// int size = navigatorList.size();
		// for (int i = 0; i < size; i++) {
		// SysRight right = navigatorList.get(i);
		// // if (right.getLinkurl() != null)
		// // sb.append("<a
		// //
		// href=\"").append(right.getLinkurl()).append("\">").append(right.getRightname()).append("</a>");
		// // else
		// // sb.append("<a
		// // href=\"#\">").append(right.getRightname()).append("</a>");
		// sb.append(right.getRightname());
		// if (i != (size - 1))
		// sb.append("&gt;&gt;");
		// }
		// // _LOG.debug("导航条1111:" + sb);
		// } catch (Exception e) {
		//
		// _LOG.error("导航条失败:" + e);
		// e.printStackTrace();
		//
		// }
		// return sb.toString();
		return "";

	}

	protected void logOperation() throws ServiceException {
		if (opResult != null && !opResult.equals("")) {
			SysUser user = getLoginUser();
			SysOperlogService log = (SysOperlogService) getBean("sysOperlogService");
			log.insertLog(user, rightCode, opResult);
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

	/**
	 * 数据的可见性
	 */
	protected DataVisible datavisible;

	public DataVisible getDatavisible() {
		return this.datavisible;
	}



	/**
	 * @param rightCode
	 *            the rightCode to set
	 */
	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

//	private void getDomain(HttpServletRequest request) {
//		Constants.CURRENT_DOMAIN = request.getHeader("x-host");
//
//		if (Constants.CURRENT_DOMAIN == null || Constants.CURRENT_DOMAIN.length() == 0
//				|| "unknown".equalsIgnoreCase(Constants.CURRENT_DOMAIN)) {
//			Constants.CURRENT_DOMAIN = request.getHeader("X-Host");
//		}
//
//		SysUnionparams params = CommonDatas.SysUnionparams.get(Constants.CURRENT_DOMAIN);
//		if (params != null) {
//			
////			System.out.println(" params.getLogopath():"+ params.getLogopath());
//			
//			if (params.getIndexpic() != null && !"".equals(params.getIndexpic())) {
//				Constants.INDEX_PIC = params.getIndexpic();
//			} else {
//				Constants.INDEX_PIC = Constants.DEFAULT_INDEX_PIC;
//			}
//			if (params.getLogopath() != null && !"".equals(params.getLogopath())) {
//				Constants.LOGO_PATH = params.getLogopath();
//			} else {
//				Constants.LOGO_PATH = Constants.DEFAULT_LOGO_PATH;
//			}
//			if (params.getSysname() != null && !"".equals(params.getSysname())) {
//				Constants.SYS_NAME = params.getSysname();
//			} else {
//				Constants.SYS_NAME = Constants.DEFAULT_SYS_NAME;
//			}
//			if (params.getTopbarpic() != null && !"".equals(params.getTopbarpic())) {
//				Constants.TOP_BAR_PIC = params.getTopbarpic();
//			} else {
//				Constants.TOP_BAR_PIC = Constants.DEFAULT_TOP_BAR_PIC;
//			}
//
//			Constants.HAVELOCAL = params.getHavelocal();
//
//		}else{
//			Constants.TOP_BAR_PIC = Constants.DEFAULT_TOP_BAR_PIC;
//			Constants.SYS_NAME = Constants.DEFAULT_SYS_NAME;
//			Constants.INDEX_PIC = Constants.DEFAULT_INDEX_PIC;
//			Constants.LOGO_PATH = Constants.DEFAULT_LOGO_PATH;
//			Constants.HAVELOCAL =Constants.DEFAULT_HAVELOCAL;
//		}
//		LOG.debug("Constants.SYS_NAME:" + Constants.SYS_NAME);
//	}

	protected String getIpAddr() {
		this.userIp = getIpAddr(ServletActionContext.getRequest());
		return this.userIp;
	}

	public String getLogopath() {
		return com.changpeng.common.Constants.LOGO_PATH;
	}

	public String getResourcePath() {
		return com.changpeng.common.Constants.RESOURCE_PATH;
	}
	
	public String getSysName(){
		return com.changpeng.common.Constants.SYS_NAME;
	}
	
	public void setNow(String now){
		
	}
	public void setTime(String time){
		
	}

	static {

	}
}