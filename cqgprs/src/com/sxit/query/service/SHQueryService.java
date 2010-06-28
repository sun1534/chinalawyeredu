/**
 * 
 */
package com.sxit.query.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sxit.common.BasicService;
import com.sxit.models.UserStateQuery;
import com.sxit.models.system.SysUser;
import com.sxit.query.model.MobileApnState;

/**
 * 
 * 执行脚本的方式获得数据
 * 
 * @author 华锋 Nov 5, 2009-10:32:58 PM
 * 
 */
public class SHQueryService extends BasicService {
	private static Log _LOG = LogFactory.getLog(SHQueryService.class);

	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 存储执行命令出来的结果数据信息
	 */
	public static Map<String, List<String>> QUERY_RESULT = new LinkedHashMap<String, List<String>>();

	public void saveQueryState(UserStateQuery query) {

		query.setDetails(Hibernate.createClob(query.getDetailsStr()));
		query.setQuerydate(new java.sql.Timestamp(System.currentTimeMillis()));
		basicDAO.save(query);

	}

	public com.sxit.common.PaginationSupport getQueryLogsByMsisdn(String msisdn, Timestamp from, Timestamp to,
			int pageNo, int pageSize) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserStateQuery.class);
		dc.add(Restrictions.eq("msisdn", msisdn));
		if (from != null)
			dc.add(Restrictions.ge("querydate", from));
		if (to != null)
			dc.add(Restrictions.le("querydate", to));
		dc.addOrder(Order.desc("id"));
		return this.findPageByCriteria(dc, pageSize, pageNo);

	}

	public UserStateQuery downloadAQuery(String msisdn, String random) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserStateQuery.class);
		dc.add(Restrictions.eq("random", random));
		dc.add(Restrictions.eq("msisdn", msisdn));
		List list = this.findAllByCriteria(dc);
		if (list != null && list.size() > 0) {
			return (UserStateQuery) list.get(0);
		}
		return null;
	}

	public List<MobileApnState> queryUserState(String sgsnids, String msisdn, String random, SysUser sysuser) {
		UserQuery query = null;

		List<MobileApnState> list=new ArrayList<MobileApnState>();
		String[] sgsnidarr = sgsnids.split(";");
		for (String sgsnid : sgsnidarr) {
			if (!sgsnid.equals("")) {
				_LOG.debug("SGSNID:::::::::::::::"+sgsnid);
				int sgsnidseq = Integer.parseInt(sgsnid.substring(sgsnid.length() - 1));
			
				if (sgsnidseq < 7) {
					query = new EricssonQuery();
				} else {
					query = new HuaweiQuery();
				}
				MobileApnState mas = query.queryUserState(sgsnid, msisdn, random);
				
				list.add(mas);
			
				// 保存到数据库里
				UserStateQuery dbquery = new UserStateQuery();
				dbquery.setImsi((mas.getSubdata() == null ? "无" : mas.getSubdata().getImsi()));
				dbquery.setMsisdn(msisdn);
				dbquery.setRandom(random);
				dbquery.setResult(mas.getWebstring());
				dbquery.setSgsnid(sgsnid);
				dbquery.setHasexception(mas.getHasexception() ? 1 : 0);
				dbquery.setException(mas.getException());
				dbquery.setExecuser(sysuser.getUsername());
				
				System.out.println("query.getConsoleResultLines():::::" + query.getConsoleResultLines().length());

				dbquery.setDetailsStr(query.getConsoleResultLines());
				Save2DbThread thread = new Save2DbThread(dbquery, null, basicDAO);
				thread.start();
				_LOG.debug("启动入库线程...");
				//这里有异常哦
				if (mas.getStatus()!=null&&!mas.getStatus().equals("noattach"))
					break;
			}
		}
		return list;

	}

	public static void main(String args[]) throws Exception {

	}
}