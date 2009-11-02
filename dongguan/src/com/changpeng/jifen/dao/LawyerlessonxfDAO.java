/**
 * LawyerlessonxfDAO.java
 */

package com.changpeng.jifen.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.changpeng.common.BasicDAO;
import com.changpeng.common.CommonDatas;
import com.changpeng.common.PaginationSupport;
import com.changpeng.common.action.AbstractAction;
import com.changpeng.common.exception.ServiceException;
import com.changpeng.models.Jifentongji;
import com.changpeng.models.Lawyerlessonxf;

/**
 * @author 华锋 2008-5-4 下午11:57:36
 * 
 */
public class LawyerlessonxfDAO extends BasicDAO {
	private static Log _LOG = LogFactory.getLog(AbstractAction.class);

	public Lawyerlessonxf getXfByBudengid(int budengid) {

		List list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.budengid=" + budengid);
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
	public Lawyerlessonxf getXuefen(int lessonid, long userid, String learnmode) {
		List list = null;
		if (learnmode != null && !learnmode.equals(""))
			list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.lessonid=" + lessonid + " and xf.lawer.userid=" + userid
					+ " and learnmode='" + learnmode + "'");
		else
			list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.lessonid=" + lessonid + " and xf.lawer.userid=" + userid);

		if (list == null || list.size() == 0)
			return null;
		return (Lawyerlessonxf) list.get(0);
	}
	
	/**
	 * 得到这个人某年的总积分
	 * @param userid
	 * @return
	 */
	public float getYearTotal(int userid,int year){
		float nowxuefen=0.0f;
		DetachedCriteria	detachedCriteria = DetachedCriteria.forClass(Lawyerlessonxf.class).add(Restrictions.eq("lawer.userid", userid))
//					.add(Restrictions.between("lastupdate", _from, _end));
			.add(Restrictions.eq("theyear",year));
			List jflist = this.findAllByCriteria(detachedCriteria);
			int length = jflist == null ? 0 : jflist.size();
			for (int i = 0; i < length; i++) {
				Lawyerlessonxf xf = (Lawyerlessonxf) jflist.get(i);
				nowxuefen += xf.getPxxf();
			}
			return nowxuefen;
	}

	/**
	 * 
	 * @param from
	 * @param end
	 * @param officename
	 * @param username
	 * @param lawerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */

//	public PaginationSupport getJifentongjiAll(final Timestamp from, final Timestamp end, final String officename, final String username,
//			final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		public PaginationSupport getJifentongjiAll(final int year, final String officename, final String username,
				final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

//				_LOG.debug("积分统计起始:" + from + ",结束:" + end);
//				long _from = from.getTime() / 1000;
//				long _end = end.getTime() / 1000;

//				String peixunsql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and (UNIX_TIMESTAMP(c.lastupdate) between "
//						+ _from + " and " + _end + ")";
				String peixunsql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and theyear="+year;
				if (officename != null && !"".equals(officename)) {
					peixunsql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					peixunsql += " and a.username like '" + username + "%'";
				}
				if (lawerno != null && !"".equals(lawerno)) {
					peixunsql += " and a.lawerno like '" + lawerno + "%'";
				}
				peixunsql += " group by a.USERNAME,a.userid,b.GROUPNAME";

//				String weipeixunsql = "select aa.USERNAME ,aa.userid,bb.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from sys_user aa left outer join sys_group bb on aa.GROUPID = bb.GROUPID where aa.roleid=1 and not exists (select userid from lawyerlessonxf cc where cc.userid=aa.userid and (UNIX_TIMESTAMP(cc.lastupdate) between "
//						+ _from + " and " + _end + ")) ";
				String weipeixunsql = "select aa.USERNAME ,aa.userid,bb.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from sys_user aa left outer join sys_group bb on aa.GROUPID = bb.GROUPID where aa.roleid=1 and not exists (select userid from lawyerlessonxf cc where cc.userid=aa.userid and theyear= "+year+")";
				if (officename != null && !"".equals(officename)) {
					weipeixunsql += " and bb.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					weipeixunsql += " and aa.username like '" + username + "%'";
				}
				if (lawerno != null && !"".equals(lawerno)) {
					weipeixunsql += " and aa.lawerno like '" + lawerno + "%'";
				}
				weipeixunsql += " group by aa.USERNAME,aa.userid,bb.GROUPNAME ";

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
	 * @param lawerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 *            达标的总数
	 * @return
	 */
//	public PaginationSupport getJifentongjiDabiao(final Timestamp from, final Timestamp end, final String officename, final String username,
//			final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		public PaginationSupport getJifentongjiDabiao(final int year, final String officename, final String username,
				final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

//				_LOG.debug("达标数统计起始:" + from + ",结束:" + end);
//				long _from = from.getTime() / 1000;
//				long _end = end.getTime() / 1000;

//				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and (UNIX_TIMESTAMP(c.lastupdate) between "+_from + " and " + _end + ") ";
				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and theyear="+year;

				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.username like '" + username + "%'";
				}
				if (lawerno != null && !"".equals(lawerno)) {
					sql += " and a.lawerno like '" + lawerno + "%'";
				}

				int dabiao = 30;
				if (CommonDatas.SysParameter.get("dabiaofen") != null)
					dabiao = Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				sql += " group by a.USERNAME,a.userid,b.GROUPNAME having format(sum(pxxf),2)>=" + dabiao;

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
	 * @param lawerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
//	public PaginationSupport getJifentongjiNotDabiao(final Timestamp from, final Timestamp end, final String officename, final String username,
//			final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		public PaginationSupport getJifentongjiNotDabiao(final int year, final String officename, final String username,
				final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

//				_LOG.debug("未达标数统计起始:" + from + ",结束:" + end);
//				long _from = from.getTime() / 1000;
//				long _end = end.getTime() / 1000;

//				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and (UNIX_TIMESTAMP(c.lastupdate) between "
//						+ _from + " and " + _end + ") ";

				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,sum((case when (c.learnmode = '现场培训') then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = '在线视频') then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = '文本课件') then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = '补登积分') then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = '未到扣分') then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID inner join lawyerlessonxf c on a.USERID = c.userid  where a.roleid=1 and theyear="+year;
				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.username like '" + username + "%'";
				}
				if (lawerno != null && !"".equals(lawerno)) {
					sql += " and a.lawerno like '" + lawerno + "%'";
				}

				int dabiao = 30;
				if (CommonDatas.SysParameter.get("dabiaofen") != null)
					dabiao = Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				sql += " group by a.USERNAME,a.userid,b.GROUPNAME having format(sum(pxxf),2)<" + dabiao;

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
	 * @param lawerno
	 * @param pageNo
	 * @param pageSize
	 * @param totalCount
	 * @return
	 */
//	public PaginationSupport getJifentongjiWeipeixun(final Timestamp from, final Timestamp end, final String officename, final String username,
//			final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		public PaginationSupport getJifentongjiWeipeixun(final int year, final String officename, final String username,
				final String lawerno, final int pageNo, final int pageSize, final int totalCount) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

//				_LOG.debug("未培训数统计起始:" + from + ",结束:" + end);
//				long _from = from.getTime() / 1000;
//				long _end = end.getTime() / 1000;
//				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID where a.roleid=1 and not exists (select userid from lawyerlessonxf c where c.userid=a.userid and (UNIX_TIMESTAMP(c.lastupdate) between "
//						+ _from + " and " + _end + ")) ";
				
				String sql = "select a.USERNAME ,a.userid,b.GROUPNAME ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from sys_user a left outer join sys_group b on a.GROUPID = b.GROUPID where a.roleid=1 and not exists (select userid from lawyerlessonxf c where c.userid=a.userid and theyear="+year+") ";
				// select aa.username,aa.userid,bb.groupname,0,0,0,0,0,0 from sys_user aa left outer join sys_group bb on a.groupid= b.groupid where
				// not exists (select userid from lawyerlessonxf cc where cc.userid=aa.userid and (UNIX_TIMESTAMP(cc.lastupdate) between 1210176000
				// and 1241711999)) group by aa.USERNAME,aa.userid,bb.GROUPNAME
				if (officename != null && !"".equals(officename)) {
					sql += " and b.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					sql += " and a.username like '" + username + "%'";
				}
				if (lawerno != null && !"".equals(lawerno)) {
					sql += " and a.lawerno like '" + lawerno + "%'";
				}
				sql += " group by a.USERNAME,a.userid,b.GROUPNAME ";
				_LOG.info("未培训数的统计SQL:" + sql);
				Query queryObject = session.createSQLQuery(sql).addEntity(Jifentongji.class);
//				Query queryObject = session.createSQLQuery(sql);
				
				int startIndex = (pageNo - 1) * pageSize;
//				List list = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
//				int length=list==null?0:list.size();
//				
//				List items=new ArrayList();
//				for(int i=0;i<length;i++){
//					try{
//						
//						
//						
//					Object[] obj=(Object[])list.get(i);
//				
//					System.out.println("obj.length====="+obj.length);
//					
//					Jifentongji tongji=new Jifentongji();
//					JifentongjiId id=new JifentongjiId();
//					id.setUsername(obj[0].toString());
//					id.setUserid((Integer)obj[1]);
//					id.setGroupname(obj[2].toString());
//					id.setXianchang(((BigDecimal)obj[3]).doubleValue());
//					id.setVideo(((BigDecimal)obj[4]).doubleValue());
//					id.setDoc(((BigDecimal)obj[5]).doubleValue());
//					id.setBudeng(((BigDecimal)obj[6]).doubleValue());
//					id.setKoufen(((BigDecimal)obj[7]).doubleValue());
//					id.setZongjifen(((BigDecimal)obj[8]).doubleValue());
//					
//
//					
//					tongji.setId(id);
//					items.add(tongji);
//					}catch(Exception e){
//						System.out.println("========"+e+"=="+e.getMessage());
//					}
//				}
				
				
				PaginationSupport ps = new PaginationSupport(items, totalCount, pageSize, startIndex);
				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
}
