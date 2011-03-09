/**
 * AbstractSystemContextDo.java
 */
package com.uu800.admin.base.service;

import com.uu800.admin.base.entity.Userinfo;
/**
 * 记录操作日志、登录日志等
 * 
 * @author 华锋
 * Feb 28, 2011 9:42:45 PM
 */
public interface ILogService {

	/**
	 * 记录日志，包括前台用户的访问日志，后台用户的操作日志等等。
	 * 一般这里只记录操作日志
	 * @param userId 登录用户id
	 * @param userName 姓名
	 * @param modelId 对应模块
	 * @param url 对应url
	 * @param ip 对应ip
	 * @param opResult 对应操作说明,比如删除了xxx信息等
	 * @param result 对应此action的返回信息
	 */
	public int logOpt(int userId,String loginName,int orgId,String moduleId,String url,String ip,String opResult,String result);
	
	/**
	 * 
	 * @param userinfo
	 * @param contextId
	 * @param loginIp
	 * @param emarks
	 * @return
	 */
	public int logLogin(Userinfo userinfo, String contextId,String loginIp,String emarks);
	
	/**
	 * 
	 * @param userId
	 * @param loginId
	 * @param remarks
	 */
	public void logLogout(int userId, int loginId, String remarks);
}
