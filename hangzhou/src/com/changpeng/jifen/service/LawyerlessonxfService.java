/**
 * LawyerlessonxfService.java
 */

package com.changpeng.jifen.service;

import java.sql.Timestamp;

import com.changpeng.common.BasicService;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.action.Jifenstatics;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.models.Lawyerlessonxf;

/**
 * @author 华锋 2008-5-4 下午11:55:50
 * 
 */
public class LawyerlessonxfService extends BasicService {

	private LawyerlessonxfDAO lawyerlessonxfDAO;

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

	public Lawyerlessonxf getXfByBudengid(int budengid) throws ServiceException {
		try {
			return lawyerlessonxfDAO.getXfByBudengid(budengid);

		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据lessonid,userid和learnmode得到学分情况
	 * 
	 * @param budengid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyerlessonxf getXuefen(int lessonid, long userid, String learnmode) throws ServiceException {
		try {
			return lawyerlessonxfDAO.getXuefen(lessonid, userid, learnmode);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PaginationSupport getJifentongji(final Timestamp from, final Timestamp end, final String officename, final String username,
			final String lawerno, final int pageNo, final int pageSize, final int isdabiao, Jifenstatics jifenstatics) throws ServiceException {
		try {
			if (isdabiao == 0)
				return lawyerlessonxfDAO.getJifentongjiAll(from, end, officename, username, lawerno, pageNo, pageSize, jifenstatics.getAllusers());
			else if (isdabiao == 1)
				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, pageNo, pageSize, jifenstatics.getDabiaoshu());
			else if (isdabiao == 2)
				return lawyerlessonxfDAO.getJifentongjiNotDabiao(from, end, officename, username, lawerno, pageNo, pageSize, jifenstatics.getWeidabiao());
			else if (isdabiao == 3)
				return lawyerlessonxfDAO.getJifentongjiWeipeixun(from, end, officename, username, lawerno, pageNo, pageSize, jifenstatics.getWeipeixun());
			else
				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, pageNo, pageSize, jifenstatics.getDabiaoshu());
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}

	}
}
