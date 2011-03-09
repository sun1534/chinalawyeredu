/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uu800.admin.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.uu800.admin.base.entity.TsysUser;
import com.uu800.webbase.BaseAction;

/**
 * 
 * @author 华锋 Jul 12, 2010 2:40:56 PM
 */
public abstract class AbstractAdminAction extends BaseAction {
	
	public static String SK_SYSTEM_NAME = "SYSTEM_NAME";
	public static String SK_COMPANY_NAME = "COMPANY";

	private static Log LOG = LogFactory.getLog(AbstractAdminAction.class);
	
	/**
	 * 当前登录的用户数据
	 */
	private TsysUser userinfo;

	/**
	 * 系统设定的模块编号\
	 * 对于前台模块,都已W开头.比如登录,记录为W1000
	 * 对于后台模块，都已S开头
	 * 
	 */
	protected String moduleId="NO";
	
	/**
	 * 操作结果的说明。比如谁对什么做了什么操作
	 */
	protected String opResult="";

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @param userinfo
	 *            the userinfo to set
	 */
	public void setUserinfo(TsysUser userinfo) {
		this.userinfo = userinfo;
	}

	public AbstractAdminAction() {

	}

	//	
	// @Override
	// public String execute() throws Exception {
	//
	// try {
	// String result = go();
	//
	// return result;
	// } catch (Exception e) {
	// LOG.error("系统错误:::" + e.getMessage());
	// e.printStackTrace();
	// message = "系统繁忙,请重试";
	// return "sysmsg";
	// } finally {
	//
	// }
	// }

	// /**
	// *
	// * @return
	// * @throws Exception
	// */
	// protected abstract String go() throws Exception;

	/**
	 * 根据在spring配置文件中的bean的id,得到初始化了的bean对象
	 */
	protected Object getBean(String name) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(name);
	}

	/**
	 * 将信息存储到会话信息中
	 */
	protected void set(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}

	/**
	 * 从会话信息中获取信息
	 */
	protected Object get(String key) {
		return ActionContext.getContext().getSession().get(key);
	}

	/**
	 * 从会话信息中删除信息
	 */
	protected void remove(String key) {
		ActionContext.getContext().getSession().remove(key);
	}
	
	/**
	 * 得到系统名称
	 * @return
	 */
	public String getSysName(){
		return SK_SYSTEM_NAME;
	}
	/**
	 * 得到系统建设方
	 * @return
	 */
	public String getCompanyName(){
		return SK_COMPANY_NAME;
	}
	


	// public String getStaticpath() {
	// return CommonData.STATIC_PATH;
	// }
	//
	// public String getResourcepath() {
	// return CommonData.RESOURCE_PATH;
	// }

	/**
	 * @return the userinfo
	 */
	public TsysUser getUserinfo() {
		return userinfo;
	}

	/**
	 * @return the opResult
	 */
	public String getOpResult() {
		return opResult;
	}
}