/**
 * AbstractSystemContextDo.java
 */
package com.uu800.admin.base.dao;

import org.springframework.stereotype.Repository;

import com.uu800.webbase.BasicDao;


/**
 * 系统启动、关闭等时候操作的一些基本
 * 
 * @author 华锋
 * Feb 28, 2011 9:42:45 PM
 */
@Repository
public class LogDao extends BasicDao{

	/**
	 * 记录日志，包括前台用户的访问日志，后台用户的操作日志等等。
	 * 一般这里只记录操作日志
	 * @param l 登录用户id
	 * @param userName 姓名
	 * @param modelId 对应模块
	 * @param url 对应url
	 * @param ip 对应ip
	 * @param opResult 对应操作说明,比如删除了xxx信息等
	 * @param result 对应此action的返回信息
	 */
	public void logOpt(long l,String userName,String moduleId,String url,String ip,String opResult,String result){
		// TBD:
		return;
	}
	
}
