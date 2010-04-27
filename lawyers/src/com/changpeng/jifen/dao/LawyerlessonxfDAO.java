/**
 * LawyerlessonxfDAO.java
 */

package com.changpeng.jifen.dao;

import java.sql.Timestamp;
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
import com.changpeng.models.Jifentongji;
import com.changpeng.models.Lawyerlessonxf;
import com.changpeng.models.LawyerlessonxfShixi;

/**
 * @author 华锋 2008-5-4 下午11:57:36
 * 
 */
public class LawyerlessonxfDAO extends BasicDAO {
	private static Log _LOG = LogFactory.getLog(AbstractAction.class);

	/**
	 * 
	 * 
	 * @param budengid
	 * @return
	 */
	public Lawyerlessonxf getXfByBudengid(int budengid) {

		List list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.learnmode=4 and xf.lessonid=" + budengid);
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
/**
 * 得到实习律师的学分
 * @param lessonid
 * @param userid
 * @param learnmode
 * @return
 */
	public LawyerlessonxfShixi getXuefenShixi(int lessonid, int userid, int learnmode) {
		String sql = "";

		if (learnmode == 0)
			sql = "from com.changpeng.models.LawyerlessonxfShixi xf where xf.lessonid=" + lessonid + " and xf.lawyerid="
					+ userid;
		else
			sql = "from com.changpeng.models.LawyerlessonxfShixi xf where xf.lessonid=" + lessonid + " and xf.lawyerid="
					+ userid + " and xf.learnmode=" + learnmode;
		List list = find(sql);

		if (list == null || list.size() == 0)
			return null;
		return (LawyerlessonxfShixi) list.get(0);
	}
	/**
	 * 
	 * @param from
	 * @param end
	 * @param officename
	 * @param username
	 * @param lawyerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */

	public PaginationSupport getJifentongjiAll(final Timestamp from, final Timestamp end, final String officename,
			final String username, final String lawyerno, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				_LOG.debug("积分统计起始:" + from + ",结束:" + end);
				long _from = from.getTime() / 1000;
				long _end = end.getTime() / 1000;

				String peixunsql = "";
				String weipeixunsql = "";
				if (field != null && !"".equals(field)) {
					peixunsql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ")";
					weipeixunsql = "select ac.lawyername ,ac.lawyerid,bb.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers aa left outer join sys_group bb on aa.GROUPID = bb.GROUPID where a not exists (select lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and (UNIX_TIMESTAMP(cc.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ")";
				} else {
					peixunsql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ")";
					weipeixunsql = "select ac.lawyername ,ac.lawyerid,bb.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers aa left outer join sys_group bb on aa.GROUPID = bb.GROUPID where a not exists (select lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and (UNIX_TIMESTAMP(cc.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

				if (officename != null && !"".equals(officename)) {
					peixunsql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					peixunsql += " and c.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					peixunsql += " and a.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					peixunsql += " and c." + field + " =" + fieldvalue + "";
				}
				peixunsql += " group by c.lawyername,c.lawyerid,b.GROUPNAME";

				if (officename != null && !"".equals(officename)) {
					weipeixunsql += " and bb.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					weipeixunsql += " and aa.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					weipeixunsql += " and aa.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					weipeixunsql += " and c." + field + " =" + fieldvalue + "";
				}
				weipeixunsql += " group by aa.lawyername,aa.lawyerid,bb.GROUPNAME ";

				String sql = peixunsql + " union " + weipeixunsql;
				_LOG.info("统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql).addEntity(Jifentongji.class);

				int startIndex = (pageNo - 1) * pageSize;

				_LOG.debug("总数::::" + totalCount);

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	/**
	 * 得到达标的
	 * 
	 * @param from
	 * @param end
	 * @param officename
	 * @param username
	 * @param lawyerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 *            达标的总数
	 * @return
	 */
	public PaginationSupport getJifentongjiDabiao(final Timestamp from, final Timestamp end, final String officename,
			final String username, final String lawyerno, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				_LOG.debug("达标数统计起始:" + from + ",结束:" + end);
				long _from = from.getTime() / 1000;
				long _end = end.getTime() / 1000;

				String sql = "";
				if (field != null && !"".equals(field)) {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ")";
				} else {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and c.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					sql += " and c." + field + " =" + fieldvalue + "";
				}
				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPNAME having
				// sum(pxxf)>=" + dabiao;
				sql += " group by c.lawyername,c.lawyerid,b.GROUPNAME having FORMAT(sum(pxxf),2)>=a.dabiaofen";
				_LOG.info("达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql).addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	/**
	 * 得到未达标的
	 * 
	 * @param from
	 * @param end
	 * @param officename
	 * @param username
	 * @param lawyerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
	public PaginationSupport getJifentongjiNotDabiao(final Timestamp from, final Timestamp end,
			final String officename, final String username, final String lawyerno, final int pageNo,
			final int pageSize, final int totalCount, final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				_LOG.debug("未达标数统计起始:" + from + ",结束:" + end);
				long _from = from.getTime() / 1000;
				long _end = end.getTime() / 1000;

				String sql = "";

				if (field != null && !"".equals(field)) {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on c.lawyerid = a.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ")";
				} else {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS xianchang,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS video,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS doc,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS budeng,format(sum((case when (c.learnmode = 1) then c.pxxf else 0 end)),2) AS koufen,(case when sum(c.pxxf) is null then 0 else format(sum(c.pxxf),2) end) AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on c.lawyerid = a.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and c.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}
				if (field != null && !"".equals(field)) {
					sql += " and c." + field + " =" + fieldvalue + "";
				}

				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPNAME having
				// sum(pxxf)<" + dabiao;
				sql += " group by c.lawyername,c.lawyerid,b.GROUPNAME having FORMAT(sum(pxxf),2)<a.dabiaofen";

				_LOG.info("未达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql).addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	/**
	 * 得到未培训的用户
	 * 
	 * @param from
	 * @param end
	 * @param officename
	 * @param username
	 * @param lawyerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
	public PaginationSupport getJifentongjiWeipeixun(final Timestamp from, final Timestamp end,
			final String officename, final String username, final String lawyerno, final int pageNo,
			final int pageSize, final int totalCount, final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

				_LOG.debug("未培训数统计起始:" + from + ",结束:" + end);
				long _from = from.getTime() / 1000;
				long _end = end.getTime() / 1000;
				String sql = "";

				if (field != null && !"".equals(field)) {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where  not exists (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ") ";
				} else {
					sql = "select c.lawyername ,c.lawyerid,b.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where  not exists (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and c.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					sql += " and a.lawyerno like '" + lawyerno + "%'";
				}

				if (field != null && !"".equals(field)) {
					sql += " and c." + field + " =" + fieldvalue + "";
				}

				sql += " group by c.lawyername,c.lawyerid,b.GROUPNAME ";
				_LOG.info("未培训数的统计SQL:" + sql);
				Query queryObject = session.createSQLQuery(sql).addEntity(Jifentongji.class);
				// Query queryObject = session.createSQLQuery(sql);

				int startIndex = (pageNo - 1) * pageSize;
				// List list =
				// queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();

				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
}
