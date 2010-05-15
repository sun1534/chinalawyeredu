/**
 * LxnetrecsService.java
 */

package com.changpeng.jifen.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.common.util.NumberUtil;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.jifen.dao.LxnetrecsDAO;
import com.changpeng.models.BasicLawyerlessonxf;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.LawyerlessonxfShixi;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxnetrecs;

/**
 * 
 * 刷卡情况
 * 
 * @author 华锋 2008-5-5 下午01:11:04
 * 
 */
public class LxnetrecsService extends BasicService {

	private static Log _LOG = LogFactory.getLog(LxnetrecsService.class);

	private LxnetrecsDAO lxnetrecsDAO;

	private LawyerlessonxfDAO lawyerlessonxfDAO;
	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLxnetrecsDAO(LxnetrecsDAO lxnetrecsDAO) {
		this.lxnetrecsDAO = lxnetrecsDAO;
	}

	/**
	 * 这个课程这个人有没有看视频的记录情况
	 * 
	 * @param lessonid
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
	public Lxnetrecs getLxnetrecs(int lessonid, long userid) throws ServiceException {
		try {
			return lxnetrecsDAO.getLxnetrecs(lessonid, userid);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增视频记录情况，同时更新这个人的积分
	 * 
	 * @param netrecs
	 * @throws ServiceException
	 */
	public float saveLxnetrecs(final Lxnetrecs netrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {

					lxnetrecsDAO.save(netrecs);
					System.out.println(netrecs.getUserid());
					Lawyers lawyer = (Lawyers) lxnetrecsDAO.get(Lawyers.class, netrecs.getUserid());
//System.out.println(lawyer);
					BasicLawyerlessonxf xf=null;
					if(lawyer.getLawyertype()==-1)
						xf = lawyerlessonxfDAO.getXuefenShixi(netrecs.getLessonid(), netrecs.getUserid(), 2);
					else
						xf = lawyerlessonxfDAO.getXuefen(netrecs.getLessonid(), netrecs.getUserid(), 2);
					Lessons lesson = (Lessons) lxnetrecsDAO.get(Lessons.class, netrecs.getLessonid());
					float huodexuefen = Float.parseFloat(NumberUtil.toMoney(lesson.getXuefen()
							* (netrecs.getLookedminutes() / netrecs.getAllminutes())));

					if(lesson.getFenshuoff()!=null&&!"".equals(lesson.getFenshuoff())){//获得的学分要打折
						int zhekou=Integer.parseInt(lesson.getFenshuoff());
						huodexuefen=Float.parseFloat(NumberUtil.toMoney((huodexuefen*zhekou)/100));
					}
					
					if (xf == null) {
						if(lawyer.getLawyertype()==-1)
						xf = new LawyerlessonxfShixi();
						else
							xf = new Lawyerlessonxf();
						xf.setLawyerid(lawyer.getLawyerid());
						xf.setLawyername(lawyer.getLawyername());
						xf.setProvinceid(lawyer.getProvinceunion());
						xf.setCityid(lawyer.getDirectunion());
						xf.setOfficeid(lawyer.getTheoffice());
						xf.setLearnmode(2);
						xf.setPxxf(huodexuefen);
		
						
						xf.setRemarks("视频积分:"+xf.getPxxf());
						xf.setPxminutes(netrecs.getLookedminutes());
						xf.setPxreqminutes(netrecs.getAllminutes());
						xf.setLessonid(netrecs.getLessonid());

						xf.setTitle(lesson.getTitle());
						xf.setLastupdate(netrecs.getLasttime());
						xf.setPxdate(netrecs.getLasttime());
						xf.setTheyear(netrecs.getJifenyear());
						xf.setIslastyear(netrecs.getJifenyear()==netrecs.getNowyear()?0:1);
						lxnetrecsDAO.save(xf);

					}
					else if (huodexuefen > xf.getPxxf()) {// 获得的学分大于现有的学分的时候，才更新
						xf.setPxxf(huodexuefen);
						xf.setRemarks((xf.getRemarks() != null ? xf.getRemarks() : "") + "|视频:"+huodexuefen);
						xf.setPxminutes(netrecs.getLookedminutes());
						xf.setPxreqminutes(netrecs.getAllminutes());
						lxnetrecsDAO.update(xf);
					}

					// 客户的话，先不分配，由主办律师自己去进行分配
					return huodexuefen;
				}
			});
			return (Float) object;
		}
		catch (Exception e) {
			_LOG.error("新增视频获得学分失败:::" + e);
			throw new ServiceException(e);
		}
	}

	/**
	 * 一个视频看了很多次的情况下,对这个进来进行更新
	 * @param netrecs
	 * @return
	 * @throws ServiceException
	 */
	public float updateLxnetrecs(final Lxnetrecs netrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					// 更新这个课程
					lxnetrecsDAO.update(netrecs);

					
					Lawyers lawyer = (Lawyers) lxnetrecsDAO.get(Lawyers.class, netrecs.getUserid());

					BasicLawyerlessonxf xf=null;
					if(lawyer.getLawyertype()==-1)
						xf = lawyerlessonxfDAO.getXuefenShixi(netrecs.getLessonid(), netrecs.getUserid(), 2);
					else
						xf = lawyerlessonxfDAO.getXuefen(netrecs.getLessonid(), netrecs.getUserid(), 2);
					
//					Lawyerlessonxf xf = lawyerlessonxfDAO.getXuefen(netrecs.getLessonid(), netrecs.getUserid(), 2);
					Lessons lesson = (Lessons) lxnetrecsDAO.get(Lessons.class, netrecs.getLessonid());


					float weidazhefen = Float.parseFloat(NumberUtil.toMoney(lesson.getXuefen()
							* (netrecs.getLookedminutes() / netrecs.getAllminutes())));
					float dazhefen=weidazhefen;
					if(lesson.getFenshuoff()!=null&&!"".equals(lesson.getFenshuoff())){//获得的学分要打折
						int zhekou=Integer.parseInt(lesson.getFenshuoff());
						dazhefen=Float.parseFloat(NumberUtil.toMoney((weidazhefen*zhekou)/100));
					}
					_LOG.debug("获得的学分::::::" + dazhefen+",打了几折:::"+lesson.getFenshuoff());

					if (dazhefen > xf.getPxxf().floatValue()) {//大于的时候才更新
						xf.setPxxf(dazhefen);
						xf.setRemarks((xf.getRemarks() != null ? xf.getRemarks() : "") + "|视频:"+dazhefen);

						xf.setTitle(lesson.getTitle());
						xf.setLastupdate(netrecs.getLasttime());
						xf.setPxminutes(netrecs.getLookedminutes());
                        xf.setPxdate(netrecs.getLasttime());  
                        
                        
                        if(lesson.getXuefen().floatValue()==weidazhefen)
                        	xf.setIsfull(true);
                        
						// xf.setPxdate(budeng.getBudengdate());
						lxnetrecsDAO.update(xf);
					}
					return dazhefen;
				}
			});
			return (Float) object;
		}
		catch (Exception e) {
			_LOG.error("更新视频获得学分失败:::" + e);
			throw new ServiceException(e);
		}

	}
}
