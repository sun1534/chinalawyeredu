/**
 * LawyerlessonxfService.java
 */

package com.changpeng.jifen.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.changpeng.common.BasicService;
import com.changpeng.common.Constants;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.dao.LawyerlessonxfDAO;
import com.changpeng.jifen.util.Jifenstatics;
import com.changpeng.jifen.util.LearnmodeStatics;
import com.changpeng.lawyers.dao.LawyersDAO;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.SysUser;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author 华锋 2008-5-4 下午11:55:50
 * 
 */
public class LawyerlessonxfService extends BasicService {
	private static Log _LOG = LogFactory.getLog(AbstractAction.class);
	private LawyerlessonxfDAO lawyerlessonxfDAO;
	private LawyersDAO lawyersDAO;

	/**
	 * @param lawyersDAO
	 *            the lawyersDAO to set
	 */
	public void setLawyersDAO(LawyersDAO lawyersDAO) {
		this.lawyersDAO = lawyersDAO;
	}

	/**
	 * @param lawyerlessonxfDAO
	 *            the lawyerlessonxfDAO to set
	 */
	public void setLawyerlessonxfDAO(LawyerlessonxfDAO lawyerlessonxfDAO) {
		this.lawyerlessonxfDAO = lawyerlessonxfDAO;
	}

	/**
	 * 根据lessonid,userid和learnmode得到学分情况
	 * 
	 * @param budengid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyerlessonxf getXuefen(int lessonid, int userid, int learnmode) throws ServiceException {
		try {
			return lawyerlessonxfDAO.getXuefen(lessonid, userid, learnmode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	
//	public PaginationSupport getJifentongji(Timestamp from, Timestamp end, String officename, String username,
//			String lawerno,String title, int pageNo, int pageSize, int isdabiao, Jifenstatics jifenstatics, String field,
//			int fieldvalue) throws ServiceException {
//		try {
//			if (isdabiao == 0)
//				return lawyerlessonxfDAO.getJifentongjiAll(from, end, officename, username, lawerno, title,pageNo, pageSize,
//						jifenstatics.getAllusers(), field, fieldvalue);
//			else if (isdabiao == 1)
//				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, title,pageNo,
//						pageSize, jifenstatics.getDabiaoshu(), field, fieldvalue);
//			else if (isdabiao == 2)
//				return lawyerlessonxfDAO.getJifentongjiNotDabiao(from, end, officename, username, lawerno,title,pageNo,
//						pageSize, jifenstatics.getWeidabiao(), field, fieldvalue);
//			else if (isdabiao == 3)
//				return lawyerlessonxfDAO.getJifentongjiWeipeixun(from, end, officename, username, lawerno,title, pageNo,
//						pageSize, jifenstatics.getWeipeixun(), field, fieldvalue);
//			else
//				return lawyerlessonxfDAO.getJifentongjiDabiao(from, end, officename, username, lawerno, title,pageNo,
//						pageSize, jifenstatics.getDabiaoshu(), field, fieldvalue);
//		} catch (Exception e) {
//			throw new ServiceException(e);
//		}
//
//	}

	public PaginationSupport getJifentongji(int year, String officename, String username, String lawyerno, String title, int pageNo,
			int pageSize, int isdabiao, Jifenstatics jifenstatics, String field, int fieldvalue)
			throws ServiceException {
		try {
			if (isdabiao == 0)
				return lawyerlessonxfDAO.getJifentongjiAll(year, officename, username, lawyerno, title,pageNo, pageSize,
						jifenstatics.getAllusers(), field, fieldvalue);
			else if (isdabiao == 1)
				return lawyerlessonxfDAO.getJifentongjiDabiao(year, officename, username, lawyerno,title, pageNo, pageSize,
						jifenstatics.getDabiaoshu(), field, fieldvalue);
			else if (isdabiao == 2)
				return lawyerlessonxfDAO.getJifentongjiNotDabiao(year, officename, username, lawyerno,title, pageNo,
						pageSize, jifenstatics.getWeidabiao(), field, fieldvalue);
			else if (isdabiao == 3)
				return lawyerlessonxfDAO.getJifentongjiWeipeixun(year, officename, username, lawyerno,title, pageNo,
						pageSize, jifenstatics.getWeipeixun(), field, fieldvalue);
			else
				return lawyerlessonxfDAO.getJifentongjiDabiao(year, officename, username, lawyerno,title, pageNo, pageSize,
						jifenstatics.getDabiaoshu(), field, fieldvalue);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public Jifenstatics getFiledDabiaoshu(Timestamp _from, Timestamp _end, float dabiaofen,float localfen, String field, int fieldvalue)
			throws ServiceException {
		String sql = "";
		if (field != null && !field.equals("")) {

			sql = "select a.lawyerid,FORMAT(sum(pxxf),2),format(sum((case when (a.learnmode = 1) then a.pxxf else 0 end)),2) from lawyerlessonxf a where (UNIX_TIMESTAMP(a.lastupdate) between "
					+ _from.getTime()
					/ 1000
					+ " and "
					+ _end.getTime()
					/ 1000
					+ ") and a."
					+ field
					+ "="
					+ fieldvalue
					+ " group by a.lawyerid";
		} else {
			sql = "select a.lawyerid,FORMAT(sum(pxxf),2),format(sum((case when (a.learnmode = 1) then a.pxxf else 0 end)),2) from lawyerlessonxf a where (UNIX_TIMESTAMP(a.lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by a.lawyerid";
		}

		List list = lawyerlessonxfDAO.findBySqlQuery(sql);
		int length = list == null ? 0 : list.size();
		int dabiaoshu = 0;
		int weidabiaoshu = 0;

		for (int i = 0; i < length; i++) {
			Object[] obj = (Object[]) list.get(i);
			float xuefen = Float.parseFloat(obj[1].toString());
			// debug(obj[0] + "===" + obj[1]);
			float xianchang=Float.parseFloat(obj[2].toString());
			// debug(obj[0] + "===" + obj[1]);
			if (xuefen >= dabiaofen&&xianchang>=localfen) {
				dabiaoshu++;
			} else {
				weidabiaoshu++;
			}
		}
		String lawyerfield = "theoffice";
		if (field.equals("officeid"))
			lawyerfield = "theoffice";
		else if (field.equals("cityid"))
			lawyerfield = "directunion";
		else if (field.equals("provinceid"))
			lawyerfield = "provinceunion";

		int lawyercnt = lawyersDAO.getFieldLawyerCnt(lawyerfield, fieldvalue);
		Jifenstatics jifenstatics = new Jifenstatics();
		jifenstatics.setAllusers(lawyercnt);
		jifenstatics.setWeipeixun(lawyercnt - length);
		jifenstatics.setDabiaoshu(dabiaoshu);
		jifenstatics.setWeidabiao(weidabiaoshu);
		return jifenstatics;
	}

	public Jifenstatics getFiledDabiaoshu(int year, float dabiaofen, float localfen,String field, int fieldvalue)
			throws ServiceException {
		
		SysUser user=(SysUser)ActionContext.getContext().getSession().get(Constants.LOGIN_USER);
		
		String table="lawyerlessonxf";
		if(user.getSysRole()!=null){
			int roleid=user.getSysRole().getRoleid();
			if(roleid==11||roleid==12){
				table="lawyerlessonxf_gongzheng";
			}
		}
		
		String sql = "";
		if (field != null && !field.equals("")) {

			sql = "select a.lawyerid,FORMAT(sum(pxxf),2),format(sum((case when (a.learnmode = 1) then a.pxxf else 0 end)),2) from "+table+" a where (a.theyear=" + year + ") and a."
					+ field + "=" + fieldvalue + " group by a.lawyerid";
		} else {
			sql = "select a.lawyerid,FORMAT(sum(pxxf),2),format(sum((case when (a.learnmode = 1) then a.pxxf else 0 end)),2) from "+table+" a where (a.theyear=" + year
					+ ") group by a.lawyerid";
		}
		System.out.println("Dabiaoshu sql::"+sql);
		List list = lawyerlessonxfDAO.findBySqlQuery(sql);
		int length = list == null ? 0 : list.size();
		int dabiaoshu = 0;
		int weidabiaoshu = 0;

		for (int i = 0; i < length; i++) {
			Object[] obj = (Object[]) list.get(i);
			float xuefen = Float.parseFloat(obj[1].toString());
			float xianchang=Float.parseFloat(obj[2].toString());
			// debug(obj[0] + "===" + obj[1]);
			if (xuefen >= dabiaofen&&xianchang>=localfen) {
				dabiaoshu++;
			} else {
				weidabiaoshu++;
			}
		}
		String lawyerfield = "theoffice";
		if (field.equals("officeid"))
			lawyerfield = "theoffice";
		else if (field.equals("cityid"))
			lawyerfield = "directunion";
		else if (field.equals("provinceid"))
			lawyerfield = "provinceunion";

		int lawyercnt = lawyersDAO.getFieldLawyerCnt(lawyerfield, fieldvalue);
		Jifenstatics jifenstatics = new Jifenstatics();
		jifenstatics.setAllusers(lawyercnt);
		jifenstatics.setWeipeixun(lawyercnt - length);
		jifenstatics.setDabiaoshu(dabiaoshu);
		jifenstatics.setWeidabiao(weidabiaoshu);
		return jifenstatics;
	}

	public LearnmodeStatics getFiledLearnmode(Timestamp _from, Timestamp _end, String field, int fieldvalue)
			throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {

			sql = "select learnmode,count(distinct(lawyerid)) from lawyerlessonxf where (UNIX_TIMESTAMP(lastupdate) between "
					+ _from.getTime()
					/ 1000
					+ " and "
					+ _end.getTime()
					/ 1000
					+ ") and "
					+ field
					+ "="
					+ fieldvalue
					+ " group by learnmode";

		} else {
			sql = "select learnmode,count(distinct(lawyerid)) from lawyerlessonxf where (UNIX_TIMESTAMP(lastupdate) between "
					+ _from.getTime() / 1000 + " and " + _end.getTime() / 1000 + ") group by learnmode";
		}
		LearnmodeStatics statics = new LearnmodeStatics();

		_LOG.debug("getFiledLearnmode::" + sql);

		List tongjilist = lawyerlessonxfDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场培训2在线视频3文本课件4积分补登

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			_LOG.debug("obj[0]:" + obj[0] + "-===>obj[1]:" + obj[1]);
			int learnmode = Integer.parseInt(obj[0].toString());
			if (learnmode == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 2) {
				statics.setVideo(Integer.parseInt(obj[1].toString()));
				_LOG.debug("video::" + statics.getVideo());
			} else if (learnmode == 3) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 4) {
				statics.setBudeng(Integer.parseInt(obj[1].toString()));
			}
		}

		return statics;
	}

	public LearnmodeStatics getFiledLearnmode(int year, String field, int fieldvalue) throws ServiceException {

		String sql = "";

		if (field != null && !field.equals("")) {

			sql = "select learnmode,count(distinct(lawyerid)) from lawyerlessonxf where (theyear=" + year + ") and "
					+ field + "=" + fieldvalue + " group by learnmode";

		} else {
			sql = "select learnmode,count(distinct(lawyerid)) from lawyerlessonxf where (theyear=" + year
					+ ") group by learnmode";
		}
		LearnmodeStatics statics = new LearnmodeStatics();

		_LOG.debug("getFiledLearnmode::" + sql);

		List tongjilist = lawyerlessonxfDAO.findBySqlQuery(sql);
		int tongjilength = tongjilist == null ? 0 : tongjilist.size();
		// 1现场培训2在线视频3文本课件4积分补登

		for (int i = 0; i < tongjilength; i++) {
			Object[] obj = (Object[]) tongjilist.get(i);
			_LOG.debug("obj[0]:" + obj[0] + "-===>obj[1]:" + obj[1]);
			int learnmode = Integer.parseInt(obj[0].toString());
			if (learnmode == 1) {
				statics.setLocal(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 2) {
				statics.setVideo(Integer.parseInt(obj[1].toString()));
				_LOG.debug("video::" + statics.getVideo());
			} else if (learnmode == 3) {
				statics.setWenbenkejian(Integer.parseInt(obj[1].toString()));
			} else if (learnmode == 4) {
				statics.setBudeng(Integer.parseInt(obj[1].toString()));
			}
		}

		return statics;
	}
}
