/**
 * LawyerlessonxfDAO.java
 */

package com.changpeng.jifen.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.jifen.action.JifenTongji;
import com.changpeng.jifen.util.NumberUtil;
import com.changpeng.models.Jifentongji;
import com.changpeng.models.Lawyerlessonxf;

/**
 * @author 华锋 2008-5-4 下午11:57:36
 * 
 */
public class LawyerlessonxfDAO extends BasicDAO {
	private static Log _LOG = LogFactory.getLog(LawyerlessonxfDAO.class);

	/**
	 * 
	 * 
	 * @param budengid
	 * @return
	 */
	public Lawyerlessonxf getXfByBudengid(int budengid) {
		List list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.lessonid=" + budengid);

		// List list = find("from com.changpeng.models.Lawyerlessonxf xf where
		// xf.learnmode=4 and xf.lessonid=" + budengid);
		if (list == null || list.size() == 0)
			return null;
		return (Lawyerlessonxf) list.get(0);

	}

	/**
	 * 根据lessonid,userid和learnmode得到学分情况 如果learnmode为空,则不考虑learnmode的情况
	 * 
	 * @param budengid
	 * @return
	 * @throws ServiceException
	 */
	public Lawyerlessonxf getXuefen(int lessonid, int userid, int learnmode) {
		String sql = "";

		if (learnmode == 0)
			sql = "from com.changpeng.models.Lawyerlessonxf xf where xf.lessonid=" + lessonid + " and xf.lawyerid="
					+ userid;
		else
			sql = "from com.changpeng.models.Lawyerlessonxf xf where xf.lessonid=" + lessonid + " and xf.lawyerid="
					+ userid + " and xf.learnmode=" + learnmode;
		List list = find(sql);

		if (list == null || list.size() == 0)
			return null;
		return (Lawyerlessonxf) list.get(0);
	}

	private String getStaticSql(int theyear, String field, int fieldvalue, String title) {
		String sql = "";
		String condition = "";
		if (title != null && !title.equals(""))
			condition = " and title like '%" + title + "%'";
		if (field != null && !field.equals(""))
			sql = "select lawyerid,sum((case when (learnmode = 1) then pxxf else 0 end)) AS xianchang,sum((case when (learnmode = 2) then pxxf else 0 end)) AS video,sum((case when (learnmode = 3) then pxxf else 0 end)) AS doc,sum((case when (learnmode = 4) then pxxf else 0 end)) AS budeng,sum((case when (learnmode = 5) then pxxf else 0 end)) AS koufen,(case when sum(pxxf) is null then 0 else sum(pxxf) end) AS zongjifen from lawyerlessonxf where "
					+ field + "=" + fieldvalue + " " + condition + " and theyear=" + theyear + " group by lawyerid";
		else
			sql = "select lawyerid,sum((case when (learnmode = 1) then pxxf else 0 end)) AS xianchang,sum((case when (learnmode = 2) then pxxf else 0 end)) AS video,sum((case when (learnmode = 3) then pxxf else 0 end)) AS doc,sum((case when (learnmode = 4) then pxxf else 0 end)) AS budeng,sum((case when (learnmode = 5) then pxxf else 0 end)) AS koufen,(case when sum(pxxf) is null then 0 else sum(pxxf) end) AS zongjifen from lawyerlessonxf where theyear="
					+ theyear + condition + " group by lawyerid";

		return sql;
	}

	private JifenTongji getTongjiObject(Object[] obj) {
		JifenTongji tongji = new JifenTongji();
		tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
		tongji.setName(obj[1].toString());
		tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
		tongji.setGroupid(Integer.parseInt(obj[3].toString()));
		tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4])));
		tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5])));
		tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6])));
		tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7])));
		tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8])));
		tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9])));
		tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10])));
		return tongji;
	}

	public PaginationSupport getJifentongjiAll(final int year, final String officename, final String username,
			final String lawyerno, final String title, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				String staticsql = getStaticSql(year, field, fieldvalue, title);
				String sql = "select a.dabiaofen,a.lawyername,a.lawyerid,b.groupid,c.xianchang,c.video,c.doc,c.budeng,c.koufen,c.zongjifen,a.localfen from lawyers a inner join sys_group b on a.theoffice=b.groupid left join ("
						+ staticsql + ") c on a.lawyerid=c.lawyerid where 1=1 ";

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '%" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.lawyername like '%" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}

				if (field != null && !"".equals(field)) {
					if (field.equals("officeid")) {
						sql += " and a.theoffice =" + fieldvalue;
					} else if (field.equals("cityid")) {
						sql += " and a.directunion =" + fieldvalue;
					} else {
						sql += " and a.provinceunion =" + fieldvalue;
					}
				}
				String coutnsql = "select count(*) from (" + sql + ") a";
				System.out.println(coutnsql);
				int totalCount = getCountBySqlQuery(coutnsql);

				sql += " order by b.groupid";
				_LOG.info("统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);// .addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;
				_LOG.debug("总数::::" + totalCount);
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();

				List newlist = new ArrayList();
				for (int i = 0; items != null && i < items.size(); i++) {
					Object[] obj = (Object[]) items.get(i);

					newlist.add(getTongjiObject(obj));
				}

				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	public PaginationSupport getJifentongjiDabiao(final int year, final String officename, final String username,
			final String lawyerno, final String title, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				String staticsql = getStaticSql(year, field, fieldvalue, title);
				String sql = "select a.dabiaofen,a.lawyername,a.lawyerid,b.groupid,c.xianchang,c.video,c.doc,c.budeng,c.koufen,c.zongjifen,a.localfen from lawyers a inner join sys_group b on a.theoffice=b.groupid inner join ("
						+ staticsql + ") c on a.lawyerid=c.lawyerid where 1=1 ";

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					if (field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue;
					else if (field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql += " and a.provinceunion =" + fieldvalue;
				}
				sql += " and format(c.zongjifen,2)>=a.dabiaofen and format(c.xianchang,2)>=a.localfen";
				_LOG.info("达标数的统计SQL:" + sql);

				String coutnsql = "select count(*) from (" + sql + ") a";
				int totalCount = getCountBySqlQuery(coutnsql);
				sql += " order by b.groupid";

				Query queryObject = session.createSQLQuery(sql);// .addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List newlist = new ArrayList();
				for (int i = 0; items != null && i < items.size(); i++) {
					Object[] obj = (Object[]) items.get(i);

					newlist.add(getTongjiObject(obj));
				}

				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	public PaginationSupport getJifentongjiNotDabiao(final int year, final String officename, final String username,
			final String lawyerno, final String title, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				String staticsql = getStaticSql(year, field, fieldvalue, title);
				String sql = "select a.dabiaofen,a.lawyername,a.lawyerid,b.groupid,c.xianchang,c.video,c.doc,c.budeng,c.koufen,c.zongjifen,a.localfen from lawyers a inner join sys_group b on a.theoffice=b.groupid inner join ("
						+ staticsql + ") c on a.lawyerid=c.lawyerid where 1=1 ";

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					if (field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue;
					else if (field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql += " and a.provinceunion =" + fieldvalue;
				}

				sql += " and (format(c.zongjifen,2)<a.dabiaofen or format(c.xianchang,2)<a.localfen)";

				String coutnsql = "select count(*) from (" + sql + ") a";
				int totalCount = getCountBySqlQuery(coutnsql);
				sql += " order by b.groupid";

				_LOG.info("未达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);// .addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();

				List newlist = new ArrayList();
				for (int i = 0; items != null && i < items.size(); i++) {
					Object[] obj = (Object[]) items.get(i);
					newlist.add(getTongjiObject(obj));
				}

				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	public PaginationSupport getJifentongjiWeipeixun(final int year, final String officename, final String username,
			final String lawyerno, final String title, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				String sql = "";
				String condition = "";
				if (title != null && !title.equals(""))
					condition = " and title like '%" + title + "%'";

				if (field != null && !"".equals(field)) {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,a.localfen from lawyers a inner join sys_group b on a.theoffice = b.GROUPID where not exists (select distinct lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid "
							+ condition + " and c.theyear=" + year + " and c." + field + "=" + fieldvalue + ") ";
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,a.localfen from lawyers a inner join sys_group b on a.theoffice = b.GROUPID where not exists (select distinct lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid "
							+ condition + " and c.theyear=" + year + ") ";
				}
				// if (title != null && !"".equals(title)) {
				// sql += " and c.title like '" + title + "%'";
				// }
				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}

				if (field != null && !"".equals(field)) {
					if (field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue;
					else if (field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql += " and a.provinceunion =" + fieldvalue;
				}

				String coutnsql = "select count(*) from (" + sql + ") a";
				int totalCount = getCountBySqlQuery(coutnsql);
				sql += " order by b.groupid";
				_LOG.info("未培训数的统计SQL:" + sql);
				Query queryObject = session.createSQLQuery(sql);// .addEntity(Jifentongji.class);

				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List newlist = new ArrayList();
				for (int i = 0; items != null && i < items.size(); i++) {
					Object[] obj = (Object[]) items.get(i);
					newlist.add(getTongjiObject(obj));
				}
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);
				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
}
