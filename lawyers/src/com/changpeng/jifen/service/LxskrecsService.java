/**
 * LxnetrecsService.java
 */

package com.changpeng.jifen.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.jifen.dao.LxskrecsDAO;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;
import com.changpeng.models.Lessons;
import com.changpeng.models.Lxskrecs;

/**
 * 视频情况
 * 
 * @author 华锋 2008-5-5 下午01:11:04
 * 
 */
public class LxskrecsService extends BasicService {
	private java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final Log LOG = LogFactory.getLog(LxskrecsService.class);
	private LxskrecsDAO lxskrecsDAO;
	private LawyerlessonxfDAO lawyerlessonxfDAO;
private LawyersDAO lawyersDAO;
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
	public void setLxskrecsDAO(LxskrecsDAO lxskrecsDAO) {
		this.lxskrecsDAO = lxskrecsDAO;
	}

	public Lxskrecs getLxskrecs(int lessonid, String kahao) throws ServiceException {
		try {
			return lxskrecsDAO.getLxskrecs(lessonid, kahao);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 事务的方式来处理
	 * 
	 * @param lxskrecses
	 * @throws Exception
	 */

	@Transactional
	public void skrecsBatch(final List<Lxskrecs> lxskrecses) throws Exception {

		try {
			for (int i = 0; i < lxskrecses.size(); i++) {
				Lxskrecs skrec = lxskrecses.get(i);
				Lxskrecs isskexist = lxskrecsDAO.getLxskrecs(skrec.getLessonid(), skrec.getKahao().trim());
				// 还没有刷卡记录
				if (isskexist == null) {
					skrec.setUploadtime(new java.sql.Timestamp(System.currentTimeMillis()));

					float xuefen = saveLxskrecsNoTransaction(skrec);

					LOG.info(skrec.getKahao() + "新增刷卡记录,获得了学分:" + xuefen);
				} else {

					isskexist.setUploadtime(new java.sql.Timestamp(System.currentTimeMillis()));
					updateLxskrecsNoTransaction(isskexist);
					LOG.info(skrec.getKahao() + "重复刷卡了:" + skrec.getKahao() + ",," + skrec.getLessonid());
				}
			}
		} catch (Exception e) {
			LOG.error("异常在112行:" + e);
		}

		// }
		// });
		// }
		// catch (Exception e) {
		// throw new ServiceException(e);
		// }

	}

	private float saveLxskrecsNoTransaction(Lxskrecs skrecs) {
		Lessons lesson = (Lessons) lxskrecsDAO.get(Lessons.class, skrecs.getLessonid());
		float huodexuefen = 0;
		// xf.setPxxf(budeng.getXuefen());
		// if (lesson.getKaoqinshichang() == null || lesson.getKaoqinshichang()
		// == 0) {
		// SysUser lawer = (SysUser)
		// sysUserDAO.getSysUserByCardNo(skrecs.getKahao());
		Lawyers lawyer =lawyersDAO.getLawyerbyLawyerno(skrecs.getKahao(),skrecs.getGroupid() );
//		Lawyers lawyer =lawyersDAO.getLawyerByCardno(skrecs.getKahao(),skrecs.getGroupid());
		
		if (lawyer != null) {
			Lawyerlessonxf oldxf = lawyerlessonxfDAO.getXuefen(skrecs.getLessonid(), lawyer.getLawyerid(), 0);
			// if (oldxf != null && oldxf.getPxxf().floatValue() ==
			// lesson.getXuefen().floatValue()) {
			if (oldxf != null && oldxf.getIsfull()) {
				skrecs.setIscheck("N");
				skrecs.setRemarks("该律师该课程已经满分,不再积分");
			}
			// else if (skrecs.getTimelong() <
			// lesson.getKaoqinshichang().floatValue()) {
			// skrecs.setIscheck("N");
			// skrecs.setRemarks("考勤时长为:" + skrecs.getTimelong() + ",小于设置的:" +
			// lesson.getKaoqinshichang() + ",不积分");
			// }
			// else if (oldxf != null && oldxf.getPxxf().floatValue() <
			// lesson.getXuefen().floatValue()
			// && skrecs.getTimelong() >=
			// lesson.getKaoqinshichang().floatValue()) {
			else if (oldxf != null && oldxf.getPxxf().floatValue() < lesson.getXuefen().floatValue()) {
				// 更新学分为满分
				oldxf.setRemarks((oldxf.getRemarks() == null ? "" : oldxf.getRemarks()) + "之前培训方式为:"
						+ oldxf.getLearnmode() + ",修改为现场培训,培训时间为:" + oldxf.getPxdate() + ",也修改为现在的");
				oldxf.setLearnmode(1);
				oldxf.setPxdate(skrecs.getSkdate());
				oldxf.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
				oldxf.setPxxf(lesson.getXuefen());
				huodexuefen = lesson.getXuefen();
				lxskrecsDAO.update(oldxf);
				skrecs.setIscheck("Y");
			}
			// else if (oldxf == null && skrecs.getTimelong() >=
			// lesson.getKaoqinshichang().floatValue()) {
			else if (oldxf == null) {

				Lawyerlessonxf xf = new Lawyerlessonxf();
				huodexuefen = lesson.getXuefen();
				xf.setPxxf(lesson.getXuefen());
				xf.setLearnmode(1);
				xf.setPxdate(skrecs.getSkdate());
				xf.setLessonid(lesson.getLessonid());
				xf.setTitle(lesson.getTitle());
				xf.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
				xf.setPxdate(skrecs.getSkdate());
				xf.setLawyerid(lawyer.getLawyerid());
				xf.setLawyername(lawyer.getLawyername());

				xf.setIsfull(true);
				xf.setProvinceid(lawyer.getProvinceunion());
				xf.setCityid(lawyer.getDirectunion());
				xf.setOfficeid(lawyer.getTheoffice());
				xf.setTheyear(skrecs.getJifenyear());
				xf.setIslastyear(0);
				xf.setRemarks("现场培训时间为" + skrecs.getTimelong() + ",设置满分");
				skrecs.setIscheck("Y");
				skrecs.setRemarks((skrecs.getRemarks() == null ? "" : skrecs.getRemarks()) + "|该课程设置了满分的学分");
				lxskrecsDAO.save(xf);
			} else {
				LOG.debug("其他的情况，暂时还没有想出来。。。。");
			}
		} else {
			skrecs.setIscheck("N");
			skrecs.setRemarks(skrecs.getKahao() + "在数据库中没找到对应的律师,不计学分");
			LOG.warn("卡号：" + skrecs.getKahao() + "没有对应的律师信息,仅更新刷卡记录,不计学分");
		}
		// }
		// else {
		// skrecs.setIscheck("N");
		// skrecs.setRemarks("课程"+lesson.getLessonid()+"设置了考勤时长:" +
		// lesson.getKaoqinshichang());
		// }
		lxskrecsDAO.save(skrecs);
		return huodexuefen;
	}

	private float updateLxskrecsNoTransaction(Lxskrecs skrecs) {
		String skremarks = skrecs.getRemarks() == null ? "" : skrecs.getRemarks();
		LOG.debug("之前的备注信息为:" + skremarks);

		Lessons lesson = (Lessons) lxskrecsDAO.get(Lessons.class, skrecs.getLessonid());
		// SysUser lawer = (SysUser)
		// sysUserDAO.getSysUserByCardNo(skrecs.getKahao());
		// SysUser lawer = (SysUser)
		// sysUserDAO.getSysUserByLawerNo(skrecs.getKahao());
		Lawyers lawyer =lawyersDAO.getLawyerbyLawyerno(skrecs.getKahao(),skrecs.getGroupid() );
//		Lawyers lawyer = null;
		float huodongxuefen = 0;
		if (lawyer != null) {
			Lawyerlessonxf oldxf = lawyerlessonxfDAO.getXuefen(skrecs.getLessonid(), lawyer.getLawyerid(), 0);
			// if (oldxf != null && oldxf.getPxxf().floatValue() ==
			// lesson.getXuefen().floatValue()) {
			if (oldxf.getIsfull()) {
				skrecs.setRemarks(skremarks + "|该律师的该课程已经满分了,不再登记积分了");
			}
			// else if (skrecs.getTimelong() <
			// lesson.getKaoqinshichang().floatValue()) {
			// skrecs.setRemarks(skremarks + "|考勤时长为:" + skrecs.getTimelong() +
			// ",小于设置的:" + lesson.getKaoqinshichang() + ",不补登积分");
			// }
			// else if (oldxf != null && oldxf.getPxxf().floatValue() <
			// lesson.getXuefen().floatValue()
			// && skrecs.getTimelong() >=
			// lesson.getKaoqinshichang().floatValue()) {
			else if (oldxf != null && oldxf.getPxxf().floatValue() < lesson.getXuefen().floatValue()) {
				// 更新学分为满分
				oldxf.setRemarks((oldxf.getRemarks() == null ? "" : oldxf.getRemarks()) + "之前培训方式为:"
						+ oldxf.getLearnmode() + ",修改为现场培训,培训时间为:" + oldxf.getPxdate() + ",也修改为现在的");
				oldxf.setLearnmode(1);
				oldxf.setPxdate(skrecs.getSkdate());
				oldxf.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
				oldxf.setPxxf(lesson.getXuefen());
				huodongxuefen = lesson.getXuefen();
				lxskrecsDAO.update(oldxf);
				skrecs.setIscheck("Y");
			}
			// else if (oldxf == null && skrecs.getTimelong() >=
			// lesson.getKaoqinshichang().floatValue()) {
			else if (oldxf == null) {

				Lawyerlessonxf xf = new Lawyerlessonxf();
				huodongxuefen = lesson.getXuefen();
				xf.setPxxf(lesson.getXuefen());
				xf.setLearnmode(1);
				xf.setPxdate(skrecs.getSkdate());
				xf.setLessonid(lesson.getLessonid());
				xf.setTitle(lesson.getTitle());
				xf.setLastupdate(new java.sql.Timestamp(System.currentTimeMillis()));
				xf.setPxdate(skrecs.getSkdate());
				xf.setLawyerid(lawyer.getLawyerid());
				xf.setLawyername(lawyer.getLawyername());
				xf.setProvinceid(lawyer.getProvinceunion());
				xf.setCityid(lawyer.getDirectunion());
				xf.setOfficeid(lawyer.getTheoffice());
				xf.setRemarks("现场培训时间为" + skrecs.getTimelong() + ",设置满分");
				skrecs.setIscheck("Y");
				skrecs.setRemarks((skrecs.getRemarks() == null ? "" : skrecs.getRemarks()) + "|该课程设置了满分的学分");
				xf.setTheyear(skrecs.getJifenyear());
				xf.setIslastyear(0);
				lxskrecsDAO.save(xf);
			} else {
				LOG.debug("其他的情况，暂时还没有想出来。。。。");
			}
		} else {
			LOG.warn("卡号：" + skrecs.getKahao() + "没有对应的律师信息,仅更新刷卡记录");
		}
		lxskrecsDAO.update(skrecs);
		// 客户的话，先不分配，由主办律师自己去进行分配
		LOG.debug("再次刷卡,获得的学分为:" + huodongxuefen);
		return huodongxuefen;
	}

	/**
	 * 新增刷卡记录情况，同时更新这个人的积分<br/> 新增的积分为满分
	 * 
	 * @param netrecs
	 * @throws ServiceException
	 */
	public float saveLxskrecs(final Lxskrecs skrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return saveLxskrecsNoTransaction(skrecs);
				}
			});
			return (Float) object;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 同一个卡号，同一个课程，刷了多次的情况
	 * 
	 * @param skrecs
	 * @return
	 * @throws ServiceException
	 */
	public float updateLxskrecs(final Lxskrecs skrecs) throws ServiceException {
		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			Object object = transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					return updateLxskrecsNoTransaction(skrecs);
				}
			});
			return (Float) object;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @param lawyersDAO the lawyersDAO to set
	 */
	public void setLawyersDAO(LawyersDAO lawyersDAO) {
		this.lawyersDAO = lawyersDAO;
	}

}