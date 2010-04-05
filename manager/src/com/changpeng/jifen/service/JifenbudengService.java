/**
 * JifenbudengService.java
 */

package com.changpeng.jifen.service;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.BasicService;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.action.JifenbudengBatch;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Jifenbudeng;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.Lawyers;
import com.changpeng.models.SysUser;

/**
 * @author 华锋 2008-5-4 下午11:43:53
 * 
 */
public class JifenbudengService extends BasicService {
	private static final Log LOG = LogFactory.getLog(JifenbudengService.class);
	private BasicDAO basicDAO = null;
	private LawyerlessonxfDAO lawyerlessonxfDAO;

	private LawyersDAO lawyersDAO;

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void setBasicDAO(BasicDAO basicDAO) {
		this.basicDAO = basicDAO;
	}

	public void setLawyersDAO(LawyersDAO lawyersDAO) {
		this.lawyersDAO = lawyersDAO;
	}

	/**
	 * 积分补登的话
	 * 
	 * @param budeng
	 * @throws ServiceException
	 */
	public void saveJifenbudeng(final Jifenbudeng budeng) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {

					basicDAO.save(budeng);
					Lawyerlessonxf xf = new Lawyerlessonxf();
					xf.setLawyerid(budeng.getLawyerid());
					xf.setLawyername(budeng.getLawyername());
					xf.setLearnmode(4);
					xf.setPxxf(budeng.getXuefen());
					xf.setRemarks(budeng.getCreateuser() + "补登的积分");
					xf.setLessonid(0 - budeng.getBudengid());
					xf.setProvinceid(budeng.getProvinceid());
					xf.setCityid(budeng.getCityid());
					xf.setOfficeid(budeng.getOfficeid());
					xf.setTitle(budeng.getTitle());
					xf.setLastupdate(budeng.getCreatetime());
					xf.setPxdate(budeng.getBudengdate());
					xf.setIsfull(true);
					xf.setTheyear(budeng.getTheyear());

					// xf.setPxdate(budeng.getBudengdate());
					basicDAO.save(xf);
					// 客户的话，先不分配，由主办律师自己去进行分配
					// return null;
				}
			});
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 积分补登的话
	 * 
	 * @param budeng,file
	 * @throws ServiceException
	 */
	@Transactional
	public List<String> saveJifenbudeng(List<JifenbudengBatch> budenglist, final SysUser sysUser, final int provinceid,
			final int cityid) throws ServiceException {

		List<String> result = new ArrayList<String>();
		try {

			java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
			for (JifenbudengBatch budengbatch : budenglist) {

				StringBuffer sb = new StringBuffer();
				float xuefen = 0f;
				Date budengdate = null;
				int theyear = 0;
				try {
					xuefen = Float.parseFloat(budengbatch.getXuefen());
				} catch (Exception e) {
					sb.append("学分数据错,应为数字类型,输入为:" + budengbatch.getXuefen() + "|||");
				}
				try {
					theyear = Integer.parseInt(budengbatch.getTheyear());
				} catch (Exception e) {
					sb.append("积分年度数据错,应为数字类型,输入为:" + budengbatch.getTheyear() + "|||");
				}
				try {

					budengdate = df.parse(budengbatch.getBudengdate());
				} catch (Exception e) {
					sb.append("积分日期数据错,应为2010-3-11的形式,输入为:" + budengbatch.getBudengdate() + "|||");
				}

			

				if (sb.length() == 0) {
					Lawyers lawyer = (Lawyers) lawyersDAO
					.getLawyerbyLawyerno(budengbatch.getLawyerno(), provinceid, cityid);
					if (lawyer != null) {

						Jifenbudeng budeng = new Jifenbudeng();

						budeng.setLawyerno(budengbatch.getLawyerno());
						budeng.setTitle(budengbatch.getTitle());
						budeng.setTheyear(theyear);
						budeng.setBudengdate(budengdate);
						budeng.setXuefen(xuefen);

						budeng.setLawyerid(lawyer.getLawyerid());
						budeng.setLawyername(lawyer.getLawyername());
						budeng.setProvinceid(lawyer.getProvinceunion());
						budeng.setCityid(lawyer.getDirectunion());
						budeng.setOfficeid(lawyer.getTheoffice());
						budeng.setCreatetime(timestamp);
						budeng.setCreateuser(sysUser.getUsername());
						budeng.setCreateuserid(sysUser.getUserid());
						basicDAO.save(budeng);
						Lawyerlessonxf xf = new Lawyerlessonxf();
						xf.setLawyerid(budeng.getLawyerid());
						xf.setLawyername(budeng.getLawyername());
						xf.setLessonid(0 - budeng.getBudengid());
						xf.setLearnmode(4);
						xf.setPxxf(budeng.getXuefen());
						xf.setRemarks(budeng.getCreateuser() + "批量补登的积分");
						xf.setLessonid(budeng.getBudengid());
						xf.setProvinceid(lawyer.getProvinceunion());
						xf.setCityid(lawyer.getDirectunion());
						xf.setOfficeid(lawyer.getDirectunion());
						xf.setTitle(budeng.getTitle());
						xf.setLastupdate(budeng.getCreatetime());
						xf.setPxdate(budeng.getBudengdate());
						xf.setIsfull(true);
						xf.setTheyear(budeng.getTheyear());
						basicDAO.save(xf);
					} else {
						result.add("第" + budengbatch.getExcelline() + "行执业证号不存在:" + budengbatch.getLawyerno());	
					}
				} else {
					result.add("第" + budengbatch.getExcelline() + "行数据错误:" + sb.toString());
				}
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// /**
	// * 积分补登的话
	// *
	// * @param budeng,file
	// * @throws ServiceException
	// */
	// public String saveJifenbudeng(final String title, final Float xf, final
	// File file, final SysUser sysUser,
	// final DataVisible visible) throws ServiceException {
	//
	// try {
	// TransactionTemplate transactionTemplate = new
	// TransactionTemplate(this.transactionManager);
	//
	// Object object = transactionTemplate.execute(new TransactionCallback() {
	// public Object doInTransaction(TransactionStatus status) {
	// String message = "";
	// try {
	// BufferedReader reader = new BufferedReader(new FileReader(file));
	// String lawyerno = "";
	// Lawyers lawyer = null;
	// int count = 0;
	// int success = 0;
	// String temp = "";
	//
	// while ((lawyerno = reader.readLine()) != null) {
	//
	// lawyerno = lawyerno.trim();
	// LOG.info(lawyerno);
	// lawyer = lawyersDAO.getLawyerbyLawyerno(lawyerno,
	// visible.getProvinceid(), visible
	// .getCityid());
	//
	// if (lawyer != null) {
	//
	// Jifenbudeng budeng = new Jifenbudeng();
	// budeng.setTitle(title);
	// budeng.setXuefen(xf);
	// budeng.setBudengdate(new java.sql.Timestamp(System.currentTimeMillis()));
	// budeng.setLawyerno(lawyerno);
	// budeng.setLawyerid(lawyer.getLawyerid());
	// budeng.setLawyername(lawyer.getLawyername());
	// budeng.setProvinceid(lawyer.getProvinceunion());
	// budeng.setCityid(lawyer.getDirectunion());
	// budeng.setOfficeid(lawyer.getTheoffice());
	// budeng.setCreatetime(new java.sql.Timestamp(System.currentTimeMillis()));
	// budeng.setCreateuser(sysUser.getUsername());
	// budeng.setCreateuserid(sysUser.getUserid());
	// basicDAO.save(budeng);
	//
	// Lawyerlessonxf xf = new Lawyerlessonxf();
	// xf.setLawyerid(budeng.getLawyerid());
	// xf.setLawyername(budeng.getLawyername());
	// xf.setLessonid(0-budeng.getBudengid());
	// xf.setLearnmode(4);
	// xf.setPxxf(budeng.getXuefen());
	// xf.setRemarks(budeng.getCreateuser() + "补登的积分");
	// xf.setLessonid(budeng.getBudengid());
	// xf.setProvinceid(lawyer.getProvinceunion());
	// xf.setCityid(lawyer.getDirectunion());
	// xf.setOfficeid(lawyer.getDirectunion());
	// xf.setTitle(budeng.getTitle());
	// xf.setLastupdate(budeng.getCreatetime());
	// xf.setPxdate(budeng.getBudengdate());
	// xf.setIsfull(true);
	// xf.setTheyear(budeng.getTheyear());
	// basicDAO.save(xf);
	// success++;
	// } else {
	// temp += lawyerno + "<br>";
	// }
	// count++;
	// if (count > 1000) {
	// message = "文件列表中第1000行以后的数据被忽略，成功补登积分" + success + "条.";
	// if (!"".equals(temp)) {
	// message += "以下数据对应的律师资料不存在，被忽略<br>：<font color='red'>\"" + temp.trim()
	// + "\"</font>";
	// }
	// break;
	// }
	// }
	// if (success == 0) {
	// message = "文件列表中对应的律师资料不存在";
	// } else if (success > 0 && !"".equals(temp)) {
	// message = "积分补登成功，以下数据对应的律师资料不存在，被忽略：<br><font color='red'>" +
	// temp.trim() + "</font>";
	// } else if (success > 0 && "".equals(temp)) {
	// message = "积分补登成功";
	// }
	// } catch (IOException e) {
	// message = "系统异常，请联系管理员";
	// e.printStackTrace();
	// LOG.error(e);
	// } catch (Exception e) {
	// message = "系统异常，请联系管理员";
	// e.printStackTrace();
	// LOG.error(e);
	// }
	// return message;
	// }
	// });
	// return (String) object;
	// } catch (Exception e) {
	// throw new ServiceException(e);
	// }
	// }

	public void deleteJifenbudeng(final int budengid) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {

					basicDAO.execute("delete from com.changpeng.models.Jifenbudeng budeng where budeng.budengid="
							+ budengid);
					basicDAO
							.execute("delete from com.changpeng.models.Lawyerlessonxf xf where xf.learnmode=4 and xf.lessonid="
									+ (0 - budengid));

				}
			});
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * 更新积分补登
	 * 
	 * @param budeng
	 * @throws ServiceException
	 */
	public void updateJifenbudeng(final Jifenbudeng budeng, final float oldbudengjifen) throws ServiceException {

		try {
			TransactionTemplate transactionTemplate = new TransactionTemplate(this.transactionManager);
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {

					basicDAO.update(budeng);

					Lawyerlessonxf xf = lawyerlessonxfDAO.getXfByBudengid(budeng.getBudengid());
					if (xf != null) {
						float chayi = budeng.getXuefen().floatValue() - oldbudengjifen;

						xf.setPxxf(xf.getPxxf().floatValue() + chayi);
						basicDAO.update(xf);
					}

					// 客户的话，先不分配，由主办律师自己去进行分配
					// return null;
				}
			});
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

}
