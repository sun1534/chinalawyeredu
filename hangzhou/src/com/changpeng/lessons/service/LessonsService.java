/**
 * LessonsService.java
 */

package com.changpeng.lessons.service;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.lessons.dao.LessonsDAO;
import com.changpeng.models.Lessonbaoming;
import com.changpeng.models.Lessonscore;

/**
 * @author 华锋 2008-5-16 上午10:55:55
 * 
 */
public class LessonsService extends BasicService {

	private LessonsDAO lessonsDAO;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @param lessonsDAO
	 *            the lessonsDAO to set
	 */
	public void setLessonsDAO(LessonsDAO lessonsDAO) {
		this.lessonsDAO = lessonsDAO;
	}

	public Lessonscore getScorebyLessonUser(long userid, int lessonid) throws ServiceException {
		try {
			return lessonsDAO.getScorebyLessonUser(userid, lessonid);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}
	
	public List getBaominglistByUser(long userid) throws ServiceException {
		try {
			return lessonsDAO.getBaominglistByUser(userid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 判断这个人的这个课程是否已经报名了
	 * 
	 * @param userid
	 * @param lessonid
	 * @return
	 */
	public Lessonbaoming getBaomingbyLessonUser(long userid, int lessonid) throws ServiceException {
		try {
			return lessonsDAO.getBaomingbyLessonUser(userid, lessonid);
		}
		catch (Exception e) {

			throw new ServiceException(e);
		}
	}

}
