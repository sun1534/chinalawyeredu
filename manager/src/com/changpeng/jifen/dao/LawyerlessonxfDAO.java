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
		
//		List list = find("from com.changpeng.models.Lawyerlessonxf xf where xf.learnmode=4 and xf.lessonid=" + budengid);
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
					peixunsql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + " and c." + field + "=" + fieldvalue + ")";
					weipeixunsql = "select aa.dabiaofen,aa.lawyername ,aa.lawyerid,bb.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,aa.localfen from lawyers aa left outer join sys_group bb on aa.theoffice = bb.GROUPID where aa.lawyerid not in (select cc.lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and (UNIX_TIMESTAMP(cc.lastupdate) between "+ _from + " and " + _end + ") and cc." + field + "=" + fieldvalue + ")";
				} else {
					peixunsql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ")";
					weipeixunsql = "select aa.dabiaofen,aa.lawyername ,aa.lawyerid,bb.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,aa.localfen from lawyers aa left outer join sys_group bb on aa.theoffice = bb.GROUPID where aa.lawyerid not in (select cc.lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and (UNIX_TIMESTAMP(cc.lastupdate) between "+ _from + " and " + _end + ") ";
				}

				if (officename != null && !"".equals(officename)) {
					peixunsql += " and b.groupname like '" + officename + "%'";
					weipeixunsql += " and bb.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					peixunsql += " and a.lawyername like '" + username + "%'";
					weipeixunsql += " and aa.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					peixunsql += " and a.lawyerno like '" + lawyerno + "%'";
					weipeixunsql += " and aa.lawyerno like '" + lawyerno + "%'";
				}
//				if (field != null && !"".equals(field)) {
//					peixunsql += " and c." + field + " =" + fieldvalue + "";
//				}
			
				if (field != null && !"".equals(field)) {
					if(field.equals("officeid")){
						peixunsql += " and a.theoffice =" + fieldvalue ;
						weipeixunsql += " and aa.theoffice =" + fieldvalue ;
					}
					else if(field.equals("cityid")){
						peixunsql += " and a.directunion =" + fieldvalue;
						weipeixunsql += " and aa.directunion =" + fieldvalue;
					}
					else{
						peixunsql+=" and a.provinceunion =" + fieldvalue;
						weipeixunsql+=" and aa.provinceunion =" + fieldvalue;
					}
				}

//				if (officename != null && !"".equals(officename)) {
//					
//				}
//				if (username != null && !"".equals(username)) {
//					
//				}
//				if (lawyerno != null && !"".equals(lawyerno)) {
//					
//				}
				
				/*if (field != null && !"".equals(field)) {
					weipeixunsql += " and c." + field + " =" + fieldvalue + "";
				}*/
				// modify at 2009-06-06
//				if (field != null && !"".equals(field)) {
//					if(field.equals("officeid"))
//						weipeixunsql += " and aa.theoffice =" + fieldvalue + "";
//					else
//						weipeixunsql += " and aa." + field + " =" + fieldvalue + "";
//				}
				
				peixunsql += " group by b.GROUPID,a.lawyername,a.lawyerid";
				weipeixunsql += " group by bb.GROUPID,aa.lawyername,aa.lawyerid ";

				String sql = peixunsql + " union " + weipeixunsql;
				_LOG.info("统计SQL:" + sql);
				
				
				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				
				int startIndex = (pageNo - 1) * pageSize;

				_LOG.debug("总数::::" + totalCount);

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
//					private float dabiaofen;
//					private String name;
//					private int lawyerid;
//					private String groupname;
//					private float xianchang;
//					private float video;
//					private float doc;
//					private float budeng;
//					private float koufen;
//					private float zongjifen;
//					
					
					newlist.add(tongji);
				}
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
	
	
	public PaginationSupport getJifentongjiAll(final int year, final String officename,
			final String username, final String lawyerno, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

			

				String peixunsql = "";
				String weipeixunsql = "";
				if (field != null && !"".equals(field)) {
					peixunsql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where c.theyear="+year+" and c." + field + "=" + fieldvalue ;
					weipeixunsql = "select aa.dabiaofen,aa.lawyername ,aa.lawyerid,bb.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,aa.localfen from lawyers aa left outer join sys_group bb on aa.theoffice = bb.GROUPID where aa.lawyerid not in (select cc.lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and cc.theyear="+year+" and cc." + field + "=" + fieldvalue + ")";
				} else {
					peixunsql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where c.theyear="+year;
					weipeixunsql = "select aa.dabiaofen,aa.lawyername ,aa.lawyerid,bb.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,aa.localfen from lawyers aa left outer join sys_group bb on aa.theoffice = bb.GROUPID where aa.lawyerid not in (select cc.lawyerid from lawyerlessonxf cc where cc.lawyerid=aa.lawyerid and cc.theyear="+year;
				}

				if (officename != null && !"".equals(officename)) {
					peixunsql += " and b.groupname like '" + officename + "%'";
					weipeixunsql += " and bb.groupname like '" + officename + "%'";
				}
				if (username != null && !"".equals(username)) {
					peixunsql += " and a.lawyername like '" + username + "%'";
					weipeixunsql += " and aa.lawyername like '" + username + "%'";
				}
				if (lawyerno != null && !"".equals(lawyerno)) {
					peixunsql += " and a.lawyerno like '" + lawyerno + "%'";
					weipeixunsql += " and aa.lawyerno like '" + lawyerno + "%'";
				}
//				if (field != null && !"".equals(field)) {
//					peixunsql += " and c." + field + " =" + fieldvalue + "";
//				}
			
				if (field != null && !"".equals(field)) {
					if(field.equals("officeid")){
						peixunsql += " and a.theoffice =" + fieldvalue ;
						weipeixunsql += " and aa.theoffice =" + fieldvalue ;
					}
					else if(field.equals("cityid")){
						peixunsql += " and a.directunion =" + fieldvalue;
						weipeixunsql += " and aa.directunion =" + fieldvalue;
					}
					else{
						peixunsql+=" and a.provinceunion =" + fieldvalue;
						weipeixunsql+=" and aa.provinceunion =" + fieldvalue;
					}
				}
	
				peixunsql += " group by b.GROUPID,a.lawyername,a.lawyerid";
				weipeixunsql += " group by bb.GROUPID,aa.lawyername,aa.lawyerid";

				String sql = peixunsql + " union " + weipeixunsql;
				_LOG.info("统计SQL:" + sql);
				
				
				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				
				int startIndex = (pageNo - 1) * pageSize;

				_LOG.debug("总数::::" + totalCount);

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				

				
			
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));

					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
					newlist.add(tongji);
				}
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

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
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") and c." + field + "=" + fieldvalue ;
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}
				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPID having
				// sum(pxxf)>=" + dabiao;
				sql += " group by b.GROUPID,a.lawyername,a.lawyerid having FORMAT(sum(pxxf),2)>=a.dabiaofen and format(xianchang,2)>=a.localfen";
				
//				sql += " group by a.lawyername,a.lawyerid,b.GROUPID having FORMAT(sum(pxxf),2)>=10";
				
				_LOG.info("达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
//					private float dabiaofen;
//					private String name;
//					private int lawyerid;
//					private String groupname;
//					private float xianchang;
//					private float video;
//					private float doc;
//					private float budeng;
//					private float koufen;
//					private float zongjifen;
//					
					
					newlist.add(tongji);
				}
				
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}

	public PaginationSupport getJifentongjiDabiao(final int year, final String officename,
			final String username, final String lawyerno, final int pageNo, final int pageSize, final int totalCount,
			final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

		

				String sql = "";
				if (field != null && !"".equals(field)) {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (c.theyear="+year+") and c." + field + "=" + fieldvalue ;
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (c.theyear="+year+") ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}
				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPID having
				// sum(pxxf)>=" + dabiao;
				sql += " group by b.GROUPID,a.lawyername,a.lawyerid having FORMAT(sum(pxxf),2)>=a.dabiaofen and format(xianchang,2)>=a.localfen";
				
//				sql += " group by a.lawyername,a.lawyerid,b.GROUPID having FORMAT(sum(pxxf),2)>=10";
				
				_LOG.info("达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
//					private float dabiaofen;
//					private String name;
//					private int lawyerid;
//					private String groupname;
//					private float xianchang;
//					private float video;
//					private float doc;
//					private float budeng;
//					private float koufen;
//					private float zongjifen;
//					
					
					newlist.add(tongji);
				}
				
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

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
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") and c." + field + "=" + fieldvalue ;
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (UNIX_TIMESTAMP(c.lastupdate) between "
							+ _from + " and " + _end + ") ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}


				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPID having
				// sum(pxxf)<" + dabiao;
				sql += " group by b.GROUPID,a.lawyername,a.lawyerid having FORMAT(sum(pxxf),2)<a.dabiaofen or format(xianchang,2)<a.localfen";
//				sql += " group by a.lawyername,a.lawyerid,b.GROUPID having FORMAT(sum(pxxf),2)<10"+dabiaofen;
				_LOG.info("未达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
//					private float dabiaofen;
//					private String name;
//					private int lawyerid;
//					private String groupname;
//					private float xianchang;
//					private float video;
//					private float doc;
//					private float budeng;
//					private float koufen;
//					private float zongjifen;
//					
					
					newlist.add(tongji);
				}
				
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
	
	public PaginationSupport getJifentongjiNotDabiao(final int year,
			final String officename, final String username, final String lawyerno, final int pageNo,
			final int pageSize, final int totalCount, final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

			

				String sql = "";

				if (field != null && !"".equals(field)) {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (c.theyear="+year+") and c." + field + "=" + fieldvalue ;
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,sum((case when (c.learnmode = 1) then c.pxxf else 0 end)) AS xianchang,sum((case when (c.learnmode = 2) then c.pxxf else 0 end)) AS video,sum((case when (c.learnmode = 3) then c.pxxf else 0 end)) AS doc,sum((case when (c.learnmode = 4) then c.pxxf else 0 end)) AS budeng,sum((case when (c.learnmode = 5) then c.pxxf else 0 end)) AS koufen,(case when sum(c.pxxf) is null then 0 else sum(c.pxxf) end) AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID inner join lawyerlessonxf c on a.lawyerid = c.lawyerid  where  (c.theyear="+year+") ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}


				// int dabiao = 30;
				// if (CommonDatas.SysParameter.get("dabiaofen") != null)
				// dabiao =
				// Integer.parseInt(CommonDatas.SysParameter.get("dabiaofen").toString());

				// sql += " group by c.lawyername,c.lawyerid,b.GROUPID having
				// sum(pxxf)<" + dabiao;
				sql += " group by b.GROUPID,a.lawyername,a.lawyerid having FORMAT(sum(pxxf),2)<a.dabiaofen or format(xianchang,2)<a.localfen";
//				sql += " group by a.lawyername,a.lawyerid,b.GROUPID having FORMAT(sum(pxxf),2)<10"+dabiaofen;
				_LOG.info("未达标数的统计SQL:" + sql);

				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				int startIndex = (pageNo - 1) * pageSize;

				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
//					private float dabiaofen;
//					private String name;
//					private int lawyerid;
//					private String groupname;
//					private float xianchang;
//					private float video;
//					private float doc;
//					private float budeng;
//					private float koufen;
//					private float zongjifen;
//					
					
					newlist.add(tongji);
				}
				
				
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);

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
					sql = "select a.localfen,a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where a.lawyerid not in (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (UNIX_TIMESTAMP(c.lastupdate) between "+ _from + " and " + _end + ") and c." + field + "=" + fieldvalue + ") ";
				} else {
					sql = "select a.localfen,a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where a.lawyerid not in (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (UNIX_TIMESTAMP(c.lastupdate) between "+ _from + " and " + _end + ")) ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}

				sql += " group by b.GROUPID,a.lawyername,a.lawyerid ";
				_LOG.info("未培训数的统计SQL:" + sql);
				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				// Query queryObject = session.createSQLQuery(sql);

				int startIndex = (pageNo - 1) * pageSize;
				// List list =
				// queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
					newlist.add(tongji);
				}
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);
				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
	
	public PaginationSupport getJifentongjiWeipeixun(final int year,
			final String officename, final String username, final String lawyerno, final int pageNo,
			final int pageSize, final int totalCount, final String field, final int fieldvalue) {
		Object object = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {

			
				String sql = "";

				if (field != null && !"".equals(field)) {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where a.lawyerid not in (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (c.theyear="+year+") and c." + field + "=" + fieldvalue + ") ";
				} else {
					sql = "select a.dabiaofen,a.lawyername ,a.lawyerid,b.GROUPID ,0.00 AS xianchang,0.00 AS video,0.00 AS doc,0.00 AS budeng,0.00 AS koufen,0.00 AS zongjifen,a.localfen from lawyers a left outer join sys_group b on a.theoffice = b.GROUPID where a.lawyerid not in (select lawyerid from lawyerlessonxf c where a.lawyerid=c.lawyerid and (c.theyear="+year+")) ";
				}

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
					if(field.equals("officeid"))
						sql += " and a.theoffice =" + fieldvalue ;
					else if(field.equals("cityid"))
						sql += " and a.directunion =" + fieldvalue;
					else
						sql+=" and a.provinceunion =" + fieldvalue;
				}

				sql += " group by b.GROUPID,a.lawyername,a.lawyerid ";
				_LOG.info("未培训数的统计SQL:" + sql);
				Query queryObject = session.createSQLQuery(sql);//.addEntity(Jifentongji.class);
				// Query queryObject = session.createSQLQuery(sql);

				int startIndex = (pageNo - 1) * pageSize;
				// List list =
				// queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List items = queryObject.setFirstResult(startIndex).setMaxResults(pageSize).list();
				List newlist=new ArrayList();
				for(int i=0;items!=null&&i<items.size();i++){
					Object[] obj=(Object[])items.get(i);
					JifenTongji tongji=new JifenTongji();
					tongji.setDabiaofen(Float.parseFloat(NumberUtil.toMoney(obj[0].toString())));
					tongji.setName(obj[1].toString());
					tongji.setLawyerid(Integer.parseInt(obj[2].toString()));
//					tongji.setGroupname(obj[3].toString());
					tongji.setGroupid(Integer.parseInt(obj[3].toString()));
					tongji.setXianchang(Float.parseFloat(NumberUtil.toMoney(obj[4].toString())));
					tongji.setVideo(Float.parseFloat(NumberUtil.toMoney(obj[5].toString())));
					tongji.setDoc(Float.parseFloat(NumberUtil.toMoney(obj[6].toString())));
					tongji.setBudeng(Float.parseFloat(NumberUtil.toMoney(obj[7].toString())));
					tongji.setKoufen(Float.parseFloat(NumberUtil.toMoney(obj[8].toString())));
					tongji.setZongjifen(Float.parseFloat(NumberUtil.toMoney(obj[9].toString())));
					tongji.setLocalfen(Float.parseFloat(NumberUtil.toMoney(obj[10].toString())));
					
					newlist.add(tongji);
				}
				PaginationSupport ps = new PaginationSupport(newlist, totalCount, pageSize, startIndex);
				return ps;
			}
		});
		return ((PaginationSupport) object);
	}
}
