/**
 * 
 */
package com.changpeng.global.service;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;


/**
 * @author 华锋
 * May 16, 2009 3:28:55 PM
 *
 */
public class GlobalService extends BasicService {

	
	
	private BasicDAO basicDAO;

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}
	
	
	
	/**
	 * 将积分数据保存到数据表里
	 * @param logMark
	 */
	public void saveLogMark(com.changpeng.global.model.GlobalLogMark  logMark){
		basicDAO.save(logMark);
	}
}
